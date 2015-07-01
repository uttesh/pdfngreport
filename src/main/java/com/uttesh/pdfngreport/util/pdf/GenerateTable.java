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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.testng.ITestResult;

/**
 * This Class process the result set and populate the tables
 * @author Uttesh Kumar T.H.
 */
public class GenerateTable {
    
    PdfLogger logger = PdfLogger.getLogger(GenerateTable.class.getName());
    
    private int exceptionCount = 0;
    private static long totalExecutionTime = 0;
    
    /**
     * This constructor populated table data by ITestResult
     * @param results
     * @param table
     * @param status 
     */
    public void generate(Set<ITestResult> results, Table table, String status) {
        List<ColumnHeader> columns = new ArrayList<ColumnHeader>();
        String[] names = Constants.STATUS_TABLE_COLUMS;
        String failedTestScreenShotLink = null;
        String showLink =null;
        if (status.equalsIgnoreCase(Constants.STATUS_PASSED)) {
            populateColumnHeader(columns, names, Constants.SUCCESS_TABLE_HEADER_COLOR);
        } else if (status.equalsIgnoreCase(Constants.STATUS_FAILED)) {
            showLink = (String) PDFCache.getConfig(Constants.SystemProps.SHOW_SELENIUM_FAILED_SCREENSHOT_LINK);
            if(showLink!=null){
                failedTestScreenShotLink = (String) PDFCache.getConfig(Constants.SystemProps.SELENIUM_FAILED_TEST_SCREENSHOT_OUPUT_DIR);
            }
            names = Constants.FAILED_STATUS_TABLE_COLUMS;
            populateColumnHeader(columns, names, Constants.FAILED_TABLE_HEADER_COLOR);
        } else {
            populateColumnHeader(columns, names, Constants.SKIPPED_TABLE_HEADER_COLOR);
        }
        List<Row> rows = new ArrayList<Row>();
        List<TableMeta> dataList = new ArrayList<TableMeta>();
        populateTableData(dataList, results, status);
        Collections.sort(dataList, TableMeta.TableMetaComparator);
        Row row = null;
        RowMeta rowMeta = null;
        for (TableMeta tableMeta : dataList) {
            row = new Row();
            rowMeta = new RowMeta();
            if(failedTestScreenShotLink!=null){
                rowMeta.setFailedScreenShotLocation(failedTestScreenShotLink);
                rowMeta.setShowScreenshotLink("show");
            }
            rowMeta.setPackagePath(tableMeta.getPackagePath());
            rowMeta.setClassName(tableMeta.getClassName());
            rowMeta.setMethod(tableMeta.getMethod());
            rowMeta.setTime(tableMeta.getTime());
            rowMeta.setStatus(status);
            rowMeta.setBlockId(tableMeta.getBlockId());
            rowMeta.setTableName(status);
            row.setRowMeta(rowMeta);
            rows.add(row);
        }
        table.setColumnHeader(columns);
        table.setRow(rows);
    }
    
    /**
     * This is the generic method which will populate the table data.
     *
     * @param table
     * @param results
     * @param status
     * @throws DocumentException
     */
    private void populateTableData(List<TableMeta> tableMetas, Set<ITestResult> results, String status) {
        logger.log("----------------------- " + status + " Test class/methods " + "-----------------");
        TableMeta tableMeta = null;
        for (ITestResult result : results) {
            tableMeta = new TableMeta();
            tableMeta.setStatus(status);
            String str[] = result.getTestClass().getName().trim().split("\\.");
            String className = result.getTestClass().getName();
            if (str.length > 0) {
                String instanceName = result.getTestClass().getName();
                className = str[str.length - 1];
                String packageName = instanceName.substring(0, instanceName.indexOf(className) - 1);
                tableMeta.setPackagePath(packageName);
                tableMeta.setClassName(className);
            } else {
                tableMeta.setPackagePath(className);
                tableMeta.setClassName(className);
            }
            logger.log("| class :: " + className + " :: method :: " + result.getMethod().getMethodName() + " |");
            logger.log("-------------------------------------------------------------------------");
            tableMeta.setMethod(result.getMethod().getMethodName());
            long duration = result.getEndMillis() - result.getStartMillis();
            totalExecutionTime += duration;
            tableMeta.setTime(duration + "");
            tableMeta.setBlockId(className +"_"+ result.getMethod().getMethodName());
            if (status.equalsIgnoreCase(Constants.STATUS_FAILED)) {
                ExceptionBean exceptionBean = new ExceptionBean();
                exceptionBean.setLable(className + "(" + result.getMethod().getMethodName()+")");
                exceptionLog(result, tableMeta,exceptionBean);
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
    private void exceptionLog(ITestResult result, TableMeta tableMeta,ExceptionBean exceptionBean) {
        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            tableMeta.setBlockId(tableMeta.getClassName() +"_"+tableMeta.getMethod());
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
