/*
 Copyright 2015 Uttesh Kumar T.H.

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

import com.uttesh.pdfngreport.common.Constants;
import com.uttesh.pdfngreport.common.ImageUtils;
import com.uttesh.pdfngreport.exceptionHandler.ReportException;
import com.uttesh.pdfngreport.handler.PdfReportHandler;
import com.uttesh.pdfngreport.model.ResultMeta;
import com.uttesh.pdfngreport.util.ExceptionSummary;
import com.uttesh.pdfngreport.util.PDFCache;
import com.uttesh.pdfngreport.util.PdfLogger;
import com.uttesh.pdfngreport.util.pdf.FailedTable;
import com.uttesh.pdfngreport.util.pdf.ITable;
import com.uttesh.pdfngreport.util.pdf.SkippedTable;
import com.uttesh.pdfngreport.util.pdf.StatisticsTable;
import com.uttesh.pdfngreport.util.pdf.SuccessTable;
import com.uttesh.pdfngreport.util.xml.ReportData;
import com.uttesh.pdfngreport.util.xml.Table;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.testng.ITestResult;

/**
 * This class will generate the pdf file by using Apache FOP open source jar
 * file.
 *
 * @author Uttesh Kumar T.H.
 */
public class PDFGenerator {

    PdfLogger logger = PdfLogger.getLogger(PDFGenerator.class.getName());
    File file;

    /**
     * This method will take result set data from IReport.
     *
     * @see com.uttesh.pdfngreport.ResultMeta
     * @param location
     * @param result
     */
    public void generateReport(String location, Map<String, ResultMeta> result) throws Exception {
        ReportData reportData = new ReportData();
        List<Table> tables = new ArrayList<Table>();
        String reportTitle = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_TITLE_PROP);
        if (reportTitle == null || reportTitle.trim().length() == 0) {
            reportTitle = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_TITLE_PROP);
        }
        String reportLocation = System.getProperty(Constants.SystemProps.REPORT_OUPUT_DIR);
        if (reportLocation == null || reportLocation.trim().length() == 0) {
            reportLocation = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_OUPUT_DIR);
        }
        String logoFile = System.getProperty(Constants.SystemProps.REPORT_LOGO_FILE);
        if (logoFile == null || logoFile.trim().length() == 0) {
            logoFile = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_LOGO_FILE);
        }
        String logo = System.getProperty(Constants.SystemProps.REPORT_LOGO);
        if (logo == null || logo.trim().length() == 0) {
            logo = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_LOGO);
        }
        String chart = System.getProperty(Constants.SystemProps.REPORT_CHART_PROP);
        if (chart == null || chart.trim().length() == 0) {
            chart = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_CHART_PROP);
        }
        String logoAlign = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_LOGO_ALIGN);
        if (logoAlign == null || logoAlign.trim().length() == 0) {
            logoAlign = "right";
        }
        String titleAlign = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_TITLE_ALIGN);
        if (titleAlign == null || titleAlign.trim().length() == 0) {
            titleAlign = "left";
        }

        InputStream exceptionIcon = getClass().getClassLoader().getResourceAsStream(Constants.Icons.EXCEPTION_ICON);

//        if (reportTitle == null || reportTitle.trim().length() == 0) {
//            InputStream titleLogoFile = getClass().getClassLoader().getResourceAsStream(Constants.SystemProps.REPORT_TITLE_LOGO);
//            reportData.setTitleLogoFile(ImageUtils.imageToBase64String(titleLogoFile));
//        } else {
        reportData.setReportTitle(reportTitle);
        //}

//        String titleType=(String) PDFCache.getConfig(Constants.SystemProps.REPORT_TITLE_TYPE);
//        if(titleType==null || titleType.trim().length() == 0){
//            titleType ="text";
//        }
        //reportData.setTitleType(titleType);
        //InputStream detailio = getClass().getClassLoader().getResourceAsStream(Constants.Icons.DETAIL_ICON);

        //InputStream imageio = getClass().getClassLoader().getResourceAsStream(Constants.Icons.IMAGE_ICON);

        reportData.setTitleAlign(titleAlign);
        reportData.setReportLocation(reportLocation);
        reportData.setChart(chart);
        reportData.setLogoFile(logoFile);
        reportData.setLogo(logo);
        reportData.setLogoAlign(logoAlign);
        reportData.setExceptionIcon(ImageUtils.imageToBase64String(exceptionIcon));
        //reportData.setActionDetailIcon(ImageUtils.imageToBase64String(detailio));
        //reportData.setActionImageIcon(ImageUtils.imageToBase64String(imageio));

        try {
            createFile(location);
            if (result != null && result.size() > 0) {
                tables.add(getStatisticsTable(result));
                for (String suiteName : result.keySet()) {
                    ResultMeta resultMeta = result.get(suiteName);
                    if (resultMeta.getPassedList() != null && resultMeta.getPassedList().size() > 0) {
                        populatePassedListTable(resultMeta, tables);
                    }
                    if (resultMeta.getFailedList() != null && resultMeta.getFailedList().size() > 0) {
                        populateFailedListTable(resultMeta, tables);
                    }
                    if (resultMeta.getSkippedList() != null && resultMeta.getSkippedList().size() > 0) {
                        populateSkippedListTable(resultMeta, tables);
                    }
                }
                ExceptionSummary exceptionSummary = new ExceptionSummary();
                reportData.setExceptionMeta(exceptionSummary.getSummary());
                reportData.setTable(tables);
                PdfReportHandler pdfReportHandler = new PdfReportHandler();
                pdfReportHandler.generatePdfReport(reportData, file);
            }

        } catch (Exception exception) {
            throw new ReportException("Failed generating PDF report.", exception);
        }
    }

    private void populatePassedListTable(ResultMeta resultMeta, List<Table> tables) {
        List<ITestResult> results = new ArrayList<ITestResult>();
        for (Set<ITestResult> itrs : resultMeta.getPassedList()) {
            results.addAll(itrs);
        }
        tables.add(getTable(results, Constants.TestCaseStatus.PASS));
    }

    private void populateFailedListTable(ResultMeta resultMeta, List<Table> tables) {
        List<ITestResult> results = new ArrayList<ITestResult>();
        for (Set<ITestResult> failed : resultMeta.getFailedList()) {
            results.addAll(failed);
        }
        tables.add(getTable(results, Constants.TestCaseStatus.FAILED));
    }

    private void populateSkippedListTable(ResultMeta resultMeta, List<Table> tables) {
        List<ITestResult> results = new ArrayList<ITestResult>();
        for (Set<ITestResult> skipped : resultMeta.getSkippedList()) {
            results.addAll(skipped);
        }
        tables.add(getTable(results, Constants.TestCaseStatus.SKIPPED));
    }

    private void createFile(String location) throws IOException {
        file = new File(location);
        System.out.println("pdf report file path :" + file.getAbsolutePath());
        new File(location).mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
        }
    }

    private Table getStatisticsTable(Map<String, ResultMeta> result) throws IOException {
        Table table = new Table();
        StatisticsTable statisticsTable = new StatisticsTable();
        statisticsTable.populateData(result, table);
        return table;
    }

    private Table getTable(List<ITestResult> results, Constants.TestCaseStatus status) {
        Table table = new Table();
        ITable itable = null;
        switch (status) {
            case PASS:
                itable = new SuccessTable();
                break;
            case FAILED:
                itable = new FailedTable();
                break;
            case SKIPPED:
                itable = new SkippedTable();
                break;
        }
        itable.populateSingleTableData(results, table);
        return table;
    }
}
