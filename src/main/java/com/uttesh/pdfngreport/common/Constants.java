/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uttesh.pdfngreport.common;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public interface Constants {

    String COLUMN_CLASS = "Class";
    String COLUMN_PACKAGE = "Package";
    String COLUMN_METHOD = "Method";
    String COLUMN_TIME = "Time(ms)";
    String COLUMN_STATUS = "Status";
    String DATE_FORMAT = "MMMM dd YYYY hh:MM:ss";

    String STATUS_FAILED = "FAILED";
    String STATUS_PASSED = "PASSED";
    String STATUS_SKIPPED = "SKIPPED";
    String STATUS_EXCEPTION = "Exception";

    String HEADER_FAILED = "Failed";
    String HEADER_PASSED = "Passed";
    String HEADER_SKIPPED = "Skipped";
    String HEADER_STATSTICS = "Statistic's";
    
    String PDF_REPORT_FILE_NAME = "pdfng_report.pdf";
    
    String STATISTIC_TABLE_PASSED_HEADER = "Passed";
    String STATISTIC_TABLE_SKIPPED_HEADER = "Skipped";
    String STATISTIC_TABLE_FAILED_HEADER = "Failed";
    String STATISTIC_TABLE_PERCENT_HEADER = "Percent";
    String STATISTIC_TABLE_TOTAL_TIME_HEADER = "Total Time";
    
    public interface SystemProps{
        String REPORT_TITLE_PROP = "pdfreport.title";
        String REPORT_CHART_PROP = "pdfreport.chart";
        String REPORT_LOGGER_PROP = "pdfreport.logger";
        String REPORT_OUPUT_DIR = "pdfreport.outputdir";
    }
}
