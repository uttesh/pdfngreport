/*
   Copyright 2015 uttesh

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.uttesh.pdfngreport;

import com.uttesh.pdfngreport.util.ChartStyle;
import com.uttesh.pdfngreport.util.Style;
import com.uttesh.pdfngreport.common.Constants;
import com.uttesh.pdfngreport.model.ResultMeta;
import com.uttesh.pdfngreport.exceptionHandler.ReportException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.uttesh.pdfngreport.util.PdfLogger;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.testng.ITestResult;

/**
 * This class will generate the pdf file by using itext open source jar file.
 *
 * @author Uttesh Kumar T.H.
 */
public class PDFGenerator {

    Font headerFont = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, Color.WHITE);
    Font header1Font = new Font(Font.TIMES_ROMAN, 10, Font.BOLD);
    Font textFont = new Font(Font.HELVETICA, 8);
    PdfLogger logger = PdfLogger.getLogger(PDFGenerator.class.getName());

    private Document document = null;
    static PdfPTable statisticTable = null, chartTable = null, successTable = null, failTable = null, skipTable = null;
    private HashMap<Integer, Throwable> throwableMap = null;
    private int exceptionCount = 0;
    private long totalExecutionTime = 0;
    FileOutputStream fileOutputStream = null;
    File file;

    /**
     * This method will take result set data from IReport.
     *
     * @see com.uttesh.pdfngreport.ResultMeta
     * @param location
     * @param result
     */
    public void generateReport(String location, Map<String, ResultMeta> result) {
        logger.log("generateReport method");
        String reportTitle = System.getProperty(Constants.SystemProps.REPORT_TITLE_PROP);
        logger.log("reportTitle :: " + reportTitle);
        this.document = new Document();
        this.throwableMap = new HashMap<Integer, Throwable>();
        file = new File(location);
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
                file.createNewFile();
            }
            logger.log("Testcase result set size :: " + result.size());
            if (result != null && result.size() > 0) {
                PdfWriter writer = PdfWriter.getInstance(this.document, new FileOutputStream(file));
//                TableHeader event = new TableHeader();
//                writer.setPageEvent(event);
                this.document.open();
                populateReportTitle(reportTitle);
                populateStatisticsTable(result);
                for (String suiteName : result.keySet()) {
                    logger.log("suiteName :: " + suiteName);
                    ResultMeta resultMeta = result.get(suiteName);
                    logger.log("passed resultset size  :: " + resultMeta.getPassedSet().size());
                    logger.log("failed resultset size  :: " + resultMeta.getFailedSet().size());
                    logger.log("skipped resultset size ::" + resultMeta.getSkippedSet().size());
                    generateSuccessTable(resultMeta.getPassedSet());
                    generateFailureTable(resultMeta.getFailedSet());
                    generateSkippedTable(resultMeta.getSkippedSet());
                }
                ExceptionSummary();
                this.document.close();
            }

        } catch (Exception exception) {
            throw new ReportException("Failed generating PDF report.", exception);
        } finally {
            if (this.document != null && this.document.isOpen()) {
                this.document.close();
            }
        }
    }

    /**
     * This method will format the table spacing before and after the table
     *
     * @see PdfPTable
     * @param table
     * @throws DocumentException
     */
    private void formatTable(PdfPTable table) throws DocumentException {
        if (table != null) {
            table.setSpacingBefore(15f);
            this.document.add(table);
            table.setSpacingAfter(15f);
        }
    }

    /**
     * This method will populate the title of the report.
     *
     * @param title
     * @throws DocumentException
     */
    private void populateReportTitle(String title) throws DocumentException {
        Paragraph p = new Paragraph(title, FontFactory.getFont(FontFactory.COURIER, 15, Font.BOLD, new Color(0, 0, 255)));
        this.document.add(p);
        this.document.add(new Paragraph((new Date().toString())));
    }

    /**
     * This method will populate the table header with background color.
     *
     * @param table
     * @param headerLabel
     * @param tabletype
     */
    private void populateTableHeaders(PdfPTable table, String headerLabel, String tabletype) {
        PdfPCell cell = null;
        Paragraph paragraph = null;

        paragraph = new Paragraph(headerLabel, headerFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        cell = new PdfPCell(paragraph);
        cell.setColspan(6);
        //cell.setBackgroundColor(headerColor);
        if (tabletype.equalsIgnoreCase(Constants.STATUS_PASSED)) {
            Style.headerCellStyle(cell, new Color(0, 153, 0));
        } else if (tabletype.equalsIgnoreCase(Constants.STATUS_FAILED)) {
            Style.headerCellStyle(cell, new Color(102, 0, 0));
        } else if (tabletype.equalsIgnoreCase(Constants.STATUS_SKIPPED)) {
            Style.headerCellStyle(cell, new Color(204, 102, 0));
        }
        table.addCell(cell);

        addHeaderCell(table, Constants.COLUMN_PACKAGE);
        addHeaderCell(table, Constants.COLUMN_CLASS);
        addHeaderCell(table, Constants.COLUMN_METHOD);
        addHeaderCell(table, Constants.COLUMN_TIME);
        // addHeaderCell(table, Constants.COLUMN_STATUS);
    }

    /**
     * This method will apply background color for cell
     *
     * @param table
     * @param column
     */
    private void addHeaderCell(PdfPTable table, String column) {
        PdfPCell cell = new PdfPCell(new Paragraph(column, header1Font));
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        table.addCell(cell);
    }

    /**
     * This is the generic method which will populate the table data.
     *
     * @param table
     * @param results
     * @param status
     * @throws DocumentException
     */
    private void populateTableData(PdfPTable table, Set<ITestResult> results, String status) throws DocumentException {
        logger.log("----------------------- "+status+" Test class/methods "+"-----------------");
        for (ITestResult result : results) {
            String str[] = result.getTestClass().getName().trim().split("\\.");
            String className = result.getTestClass().getName();
            if (str.length > 0) {
                String instanceName = result.getTestClass().getName();
                className = str[str.length - 1];
                String packageName = instanceName.substring(0, instanceName.indexOf(className) - 1);
                populateCellData(table, packageName);
                populateCellData(table, className);
            } else {
                populateCellData(table, className);
                populateCellData(table, className);
            }
            logger.log("| class :: "+className+" :: method :: "+result.getMethod().getMethodName()+" |");
            logger.log("-------------------------------------------------------------------------");
            populateCellData(table, result.getMethod().getMethodName());
            long duration = result.getEndMillis() - result.getStartMillis();
            totalExecutionTime += duration;
            populateCellData(table, duration + "");
            //populateCellData(table, status);
            if (status.equalsIgnoreCase(Constants.STATUS_FAILED)) {
                exceptionLog(table, result);
            }
        }
        logger.log("--------------------- END "+status+" Test class/methods "+"--------------------");
        logger.log("\n\n");
        formatTable(table);
    }

    /**
     * This method take the error from thrownMap and populate the error summary.
     *
     * @param table
     * @param result
     */
    private void exceptionLog(PdfPTable table, ITestResult result) {
        PdfPCell cell = null;
        Paragraph paragraph = null;
        paragraph = new Paragraph(Constants.STATUS_EXCEPTION, headerFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        cell = new PdfPCell(paragraph);
        cell.setColspan(5);
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        table.addCell(cell);

        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            this.throwableMap.put(new Integer(throwable.hashCode()), throwable);
            this.exceptionCount++;
            Paragraph excep = new Paragraph(
                    new Chunk(throwable.toString(),
                            new Font(Font.TIMES_ROMAN, 8, Font.UNDERLINE)).
                    setLocalGoto("" + throwable.hashCode()));

            paragraph.setAlignment(Element.ALIGN_LEFT);

            cell = new PdfPCell(excep);
            cell.setBackgroundColor(Color.RED);
            cell.setColspan(5);
            table.addCell(cell);
        }
    }

    /**
     * populate cell data.
     *
     * @param table
     * @param data
     */
    private void populateCellData(PdfPTable table, String data) {
        PdfPCell cell = new PdfPCell(new Paragraph(data, textFont));
        table.addCell(cell);
    }

    /**
     * This method will create success table by using ITestResult
     *
     * @see ITestResult
     * @param results
     * @throws DocumentException
     */
    private void generateSuccessTable(Set<ITestResult> results) throws DocumentException {
        this.successTable = new PdfPTable(new float[]{.5f, .3f, .3f, .2f});
        populateTableHeaders(this.successTable, Constants.HEADER_PASSED, Constants.STATUS_PASSED);
        populateTableData(this.successTable, results, Constants.STATUS_PASSED);
    }

    /**
     * This method will create failed table by using ITestResult
     *
     * @see ITestResult
     * @param results
     * @throws DocumentException
     */
    private void generateFailureTable(Set<ITestResult> results) throws DocumentException {
        this.failTable = new PdfPTable(new float[]{.5f, .3f, .3f, .2f});
        this.failTable.setTotalWidth(20f);
        populateTableHeaders(this.failTable, Constants.HEADER_FAILED, Constants.STATUS_FAILED);
        populateTableData(this.failTable, results, Constants.STATUS_FAILED);
    }

    /**
     * This method will create skipped table by using ITestResult
     *
     * @see ITestResult
     * @param results
     * @throws DocumentException
     */
    private void generateSkippedTable(Set<ITestResult> results) throws DocumentException {
        this.skipTable = new PdfPTable(new float[]{.5f, .3f, .3f, .2f});
        this.skipTable.setTotalWidth(20f);
        populateTableHeaders(this.skipTable, Constants.HEADER_SKIPPED, Constants.STATUS_SKIPPED);
        populateTableData(this.skipTable, results, Constants.STATUS_SKIPPED);
    }

    /**
     * This method will create statistics table by using ResultMeta
     *
     * @see ResultMeta
     * @param results
     * @throws DocumentException
     */
    private void populateStatisticsTable(Map<String, ResultMeta> result) throws DocumentException {
        logger.log("populateStatisticsTable() :: ");
        logger.log("result data map :: " + result);
        String chartDisplay = "show";
        if (System.getProperty(Constants.SystemProps.REPORT_CHART_PROP) != null) {
            chartDisplay = System.getProperty(Constants.SystemProps.REPORT_CHART_PROP);
        }
        logger.log("Piechart property :: " + chartDisplay);
        Paragraph p = new Paragraph(Constants.HEADER_STATSTICS, headerFont);

        p.setAlignment(Element.ALIGN_CENTER);
        PdfPCell cell = new PdfPCell(p);
        cell.setColspan(5);
        Style.headerCellStyle(cell, new Color(0, 121, 182));
        this.statisticTable = new PdfPTable(new float[]{.3f, .2f, .2f, .2f, .3f});
        this.statisticTable.addCell(cell);
        addHeaderCell(this.statisticTable, Constants.STATISTIC_TABLE_PASSED_HEADER);
        addHeaderCell(this.statisticTable, Constants.STATISTIC_TABLE_SKIPPED_HEADER);
        addHeaderCell(this.statisticTable, Constants.STATISTIC_TABLE_FAILED_HEADER);
        addHeaderCell(this.statisticTable, Constants.STATISTIC_TABLE_PERCENT_HEADER);
        addHeaderCell(this.statisticTable, Constants.STATISTIC_TABLE_TOTAL_TIME_HEADER);
        int passed = 0;
        int skipped = 0;
        int failed = 0;
        double percent = 0;
        for (String className : result.keySet()) {
            ResultMeta resultMeta = result.get(className);
            passed = passed + resultMeta.getPassedSet().size();
            skipped = skipped + resultMeta.getSkippedSet().size();
            failed = failed + resultMeta.getFailedSet().size();
            double total = passed + skipped + failed;
            percent = ((double) passed / total) * 100;
            percent = Math.round(percent * 100) / 100.0d;
        }

        populateCellData(this.statisticTable, "" + passed);
        populateCellData(this.statisticTable, "" + skipped);
        populateCellData(this.statisticTable, "" + failed);
        populateCellData(this.statisticTable, percent + "%");
        populateCellData(this.statisticTable, "" + totalExecutionTime);
        formatTable(this.statisticTable);
        if (chartDisplay.equalsIgnoreCase("show")) {
            DefaultPieDataset dataSet = new DefaultPieDataset();
            dataSet.setValue("Failed", failed);
            dataSet.setValue("Skipped", skipped);
            dataSet.setValue("Passed", passed);
            generatePieChart(dataSet);
        }
    }

    /**
     * This method will create pie chart by using full statistic data
     *
     * @see ResultMeta
     * @param results
     * @throws DocumentException
     */
    private void generatePieChart(DefaultPieDataset dataSet) throws DocumentException {
        logger.log("generatePieChart() :: ");
        this.chartTable = new PdfPTable(new float[]{.3f, .3f, .1f, .3f});
        PdfPCell cell = null;
        Paragraph paragraph = null;
        paragraph = new Paragraph("Data Chart", headerFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        cell = new PdfPCell(paragraph);
        cell.setColspan(4);
        Style.headerCellStyle(cell, new Color(0, 121, 182));
        this.chartTable.addCell(cell);

        JFreeChart chart = ChartFactory.createPieChart3D("", dataSet, true, true, false);
        ChartStyle.theme(chart);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setForegroundAlpha(0.6f);
        plot.setCircular(true);

        Color transparent = new Color(0.0f, 0.0f, 0.0f, 0.0f);
        //plot.setLabelLinksVisible(Boolean.FALSE);
        plot.setLabelOutlinePaint(transparent);
        plot.setLabelBackgroundPaint(transparent);
        plot.setLabelShadowPaint(transparent);

        java.awt.Image originalImage = chart.createBufferedImage(500, 300);
        com.lowagie.text.Image image1 = null;
        try {
            image1 = com.lowagie.text.Image.getInstance(originalImage, Color.white);
        } catch (Exception e) {
            throw new ReportException("Failed generating PieChart", e);
        }

        cell = new PdfPCell(paragraph);
        cell.setColspan(4);
        cell.setBackgroundColor(Color.WHITE);
        cell.addElement(image1);

        this.chartTable.addCell(cell);
        formatTable(this.chartTable);
    }

    /**
     * This method will create exception summary page.
     */
    private void ExceptionSummary() {

        try {
            document.newPage();
            PdfPCell cell = null;
            Paragraph paragraph = null;
            paragraph = new Paragraph("EXCEPTIONS SUMMARY",
                    FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new Color(255, 0, 0)));
            this.document.add(paragraph);
            Set<Integer> keys = this.throwableMap.keySet();
            assert keys.size() == this.exceptionCount;
            for (Integer key : keys) {
                Throwable throwable = this.throwableMap.get(key);
                Chunk chunk = new Chunk(throwable.toString(),
                        FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(255, 0, 0)));
                chunk.setLocalDestination("" + key);
                Paragraph throwTitlePara = new Paragraph(chunk);
                try {
                    this.document.add(throwTitlePara);
                } catch (DocumentException e3) {
                    e3.printStackTrace();
                }
                StackTraceElement[] elems = throwable.getStackTrace();
                String exception = "";
                for (StackTraceElement ste : elems) {
                    Paragraph throwParagraph = new Paragraph(ste.toString());
                    try {
                        this.document.add(throwParagraph);
                    } catch (DocumentException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            throw new ReportException("Exception Summary", e);
        }
    }
}
