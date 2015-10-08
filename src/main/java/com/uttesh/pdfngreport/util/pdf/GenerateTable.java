/*
 * Copyright 2015 Uttesh Kumar T.H..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uttesh.pdfngreport.util.pdf;

import com.uttesh.pdfngreport.common.Constants;
import com.uttesh.pdfngreport.model.TableMeta;
import com.uttesh.pdfngreport.util.ExceptionBean;
import com.uttesh.pdfngreport.util.PDFCache;
import com.uttesh.pdfngreport.util.PdfLogger;
import com.uttesh.pdfngreport.util.xml.ColumnHeader;
import com.uttesh.pdfngreport.util.xml.Row;
import com.uttesh.pdfngreport.util.xml.RowMeta;
import com.uttesh.pdfngreport.util.xml.Table;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.testng.ITestResult;
import org.testng.annotations.Test;

/**
 * This Class process the result set and populate the tables
 *
 * @author Uttesh Kumar T.H.
 */
public class GenerateTable {

    PdfLogger logger = PdfLogger.getLogger(GenerateTable.class.getName());

    private int exceptionCount = 0;
    private static long totalExecutionTime = 0;

    /**
     * This constructor populated table data by ITestResult
     *
     * @param results
     * @param table
     * @param status
     */
    public void generate(Set<ITestResult> results, Table table, String status) {
        List<ColumnHeader> columns = new ArrayList<ColumnHeader>();
        String[] names = Constants.STATUS_TABLE_COLUMS;
        String failedTestScreenShotLink = null;
        String showLink = null;
        String exceptionPage = null;
        if (status.equalsIgnoreCase(Constants.STATUS_PASSED)) {
            populateColumnHeader(columns, names, Constants.SUCCESS_TABLE_HEADER_COLOR);
        } else if (status.equalsIgnoreCase(Constants.STATUS_FAILED)) {

            exceptionPage = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_EXCEPTION_PAGE);
            if ((exceptionPage != null || exceptionPage.trim().length() > 0)
                    && exceptionPage.equalsIgnoreCase(Constants.HIDE)) {
                exceptionPage = Constants.HIDE;
                names = Constants.FAILED_STATUS_TABLE_COLUMS_NO_EXCEPTION;

            } else {
                exceptionPage = Constants.SHOW;
                showLink = (String) PDFCache.getConfig(Constants.SystemProps.SHOW_SELENIUM_FAILED_SCREENSHOT_LINK);
                if (showLink != null) {
                    failedTestScreenShotLink = (String) PDFCache.getConfig(Constants.SystemProps.SELENIUM_FAILED_TEST_SCREENSHOT_OUPUT_DIR);
                }
                names = Constants.FAILED_STATUS_TABLE_COLUMS;
            }
            populateColumnHeader(columns, names, Constants.FAILED_TABLE_HEADER_COLOR);
        } else {
            populateColumnHeader(columns, names, Constants.SKIPPED_TABLE_HEADER_COLOR);
        }

        String timeFormat = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_TIME_COLUMN_DATE_FORMAT);
        if (timeFormat == null || timeFormat.isEmpty()) {
            timeFormat = Constants.DATE_FORMAT;
        }
        List<Row> rows = new ArrayList<Row>();
        List<TableMeta> dataList = new ArrayList<TableMeta>();
        populateTableData(dataList, results, status, timeFormat);
        Collections.sort(dataList, TableMeta.TableMetaComparator);
        Row row = null;
        RowMeta rowMeta = null;
        for (TableMeta tableMeta : dataList) {
            row = new Row();
            rowMeta = new RowMeta();
            if (exceptionPage != null) {
                rowMeta.setExceptionPage(exceptionPage);
            }
            if (failedTestScreenShotLink != null) {
                rowMeta.setFailedScreenShotLocation(failedTestScreenShotLink);
                rowMeta.setShowScreenshotLink(Constants.SHOW);
            }
            rowMeta.setPackagePath(tableMeta.getPackagePath());
            if (tableMeta.getTestContextName() != null && tableMeta.getTestContextName().trim().length() > 0) {
                rowMeta.setClassName(tableMeta.getTestContextName());
            } else {
                rowMeta.setClassName(tableMeta.getClassName());
            }
            rowMeta.setMethod(tableMeta.getMethod());
            rowMeta.setTimeTaken(tableMeta.getTimeTaken());
            rowMeta.setTime(tableMeta.getTime());
            rowMeta.setStatus(status);
            rowMeta.setBlockId(tableMeta.getBlockId());
            rowMeta.setTableName(status);
            row.setRowMeta(rowMeta);
            rows.add(row);
        }
        table.setColumnHeader(columns);
        table.setRow(rows);
        String name = System.getProperty(Constants.BuidSystem.OS_NAME).trim().substring(0, 1);
        table.setOsName(name.toLowerCase());
    }

    private String populateSpace(String text) {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(text);
        boolean found = matcher.find();
        StringBuilder sb = new StringBuilder();
        if (text.length() > 30 && !found) {
            sb.append(text.substring(0, 30));
            sb.append("\n");
            sb.append(text.substring(30, text.length()));
            sb.append("\n");
            return sb.toString();
        } else {
            return text;
        }
    }

    /**
     * This is the generic method which will populate the table data.
     *
     * @param table
     * @param results
     * @param status
     * @throws DocumentException
     */
    private void populateTableData(List<TableMeta> tableMetas, Set<ITestResult> results, String status, String timeFormat) {
        logger.log("----------------------- " + status + " Test class/methods " + "-----------------");
        TableMeta tableMeta = null;
        for (ITestResult result : results) {
            tableMeta = new TableMeta();
            tableMeta.setStatus(status);
            String str[] = result.getTestClass().getName().trim().split("\\.");
            String className = result.getTestClass().getName();
            String testContextName = result.getTestContext().getName();
            String _className = "";
            if (str.length > 0) {
                String instanceName = result.getTestClass().getName();
                className = str[str.length - 1];
                if (className != null) {
                    _className = className;
                    _className = populateSpace(_className);
                }
                String packageName = instanceName.substring(0, ((instanceName.indexOf(className)) - 1));
                if (packageName != null) {
                    packageName = populateSpace(packageName);
                }
                tableMeta.setPackagePath(packageName);
                tableMeta.setClassName(_className);
            } else {
                tableMeta.setPackagePath(_className);
                tableMeta.setClassName(_className);
            }
            logger.log("| class :: " + className + " :: method :: " + result.getMethod().getMethodName() + " |");
            logger.log("-------------------------------------------------------------------------");
            String methodName = result.getMethod().getMethodName();
            Test _test = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
            if (_test != null && _test.testName() != null && _test.testName().trim().length() > 0) {
                methodName = _test.testName();
            }
            tableMeta.setTestContextName(testContextName);
            tableMeta.setMethod(populateSpace(methodName));
            long duration = result.getEndMillis() - result.getStartMillis();
            totalExecutionTime += duration;
            tableMeta.setTimeTaken(duration + "");
            String timeTaken = new SimpleDateFormat(timeFormat).format(result.getStartMillis());
            tableMeta.setTime(timeTaken);
            tableMeta.setBlockId(className + "_" + result.getMethod().getMethodName());
            if (status.equalsIgnoreCase(Constants.STATUS_FAILED)) {
                ExceptionBean exceptionBean = new ExceptionBean();
                exceptionBean.setLable(className + "(" + result.getMethod().getMethodName() + ")");
                exceptionLog(result, tableMeta, exceptionBean);
            }
            tableMetas.add(tableMeta);
        }
        logger.log("--------------------- END " + status + " Test class/methods " + "--------------------");
        logger.log("\n\n");
    }

    /**
     * This method take the error from thrownMap and populate the error summary.
     *
     * @param table
     * @param result
     */
    private void exceptionLog(ITestResult result, TableMeta tableMeta, ExceptionBean exceptionBean) {
        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            tableMeta.setBlockId(tableMeta.getClassName() + "_" + tableMeta.getMethod());
            exceptionBean.setThrowable(throwable);
            PDFCache.put(tableMeta.getBlockId(), exceptionBean);
            this.exceptionCount++;
        }
    }

    public static void populateColumnHeader(List<ColumnHeader> columns, String[] names, String color) {
        ColumnHeader columnHeader = null;
        for (String name : names) {
            columnHeader = new ColumnHeader();
            columnHeader.setColorCode(color);
            columnHeader.setName(name);
            columns.add(columnHeader);
        }
    }

    public int getExceptionCount() {
        return exceptionCount;
    }

    public void setExceptionCount(int exceptionCount) {
        this.exceptionCount = exceptionCount;
    }

    public static long getTotalExecutionTime() {
        return totalExecutionTime;
    }

    public static void setTotalExecutionTime(long totalExecutionTime) {
        GenerateTable.totalExecutionTime = totalExecutionTime;
    }

}
