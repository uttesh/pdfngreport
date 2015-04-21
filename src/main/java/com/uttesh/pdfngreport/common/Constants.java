/*
 Copyright 2015 Uttesh Kumar T.H. Kumar T.H.

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
package com.uttesh.pdfngreport.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public interface Constants {

    String COLUMN_CLASS = "Test";
    String COLUMN_PACKAGE = "Package";
    String COLUMN_METHOD = "Case";
    String COLUMN_TIME = "Time(ms)";
    String COLUMN_STATUS = "Status";
    String DATE_FORMAT = "MMMM dd YYYY hh:MM:ss";

    public enum TestCaseStatus {

        PASS("PASS"), FAILED("FAILED"), SKIPPED("SKIPPED");

        private String statusCode;

        private TestCaseStatus(String s) {
            statusCode = s;
        }

        public String getStatusCode() {
            return statusCode;
        }

    }

    String[] STATISTIC_TABLE_COLUMS = {Constants.STATISTIC_TABLE_PASSED_HEADER,
        Constants.STATISTIC_TABLE_SKIPPED_HEADER,
        Constants.STATISTIC_TABLE_FAILED_HEADER,
        Constants.STATISTIC_TABLE_PERCENT_HEADER};

    String[] STATUS_TABLE_COLUMS = {
        //Constants.COLUMN_PACKAGE,
        Constants.COLUMN_CLASS,
        Constants.COLUMN_METHOD,
        "",
        Constants.COLUMN_TIME};

    String[] FAILED_STATUS_TABLE_COLUMS = {
        //Constants.COLUMN_PACKAGE,
        Constants.COLUMN_CLASS,
        Constants.COLUMN_METHOD,
        "",
        Constants.COLUMN_TIME,
        Constants.STATUS_EXCEPTION};

    String STATUS_FAILED = "FAILED";
    String STATUS_PASSED = "PASSED";
    String STATUS_SKIPPED = "SKIPPED";
    String STATUS_EXCEPTION = "Exception";

    String HEADER_FAILED = "Failed";
    String HEADER_PASSED = "Passed";
    String HEADER_SKIPPED = "Skipped";
    String HEADER_STATSTICS = "Statistic's";

    String PDF_REPORT_FILE_NAME = "pdfng-report-"+new SimpleDateFormat("dd-MMM-yyyy-hh-mm-s-a").format(new Date())+".pdf";

    String STATISTIC_TABLE_PASSED_HEADER = "Passed";
    String STATISTIC_TABLE_SKIPPED_HEADER = "Skipped";
    String STATISTIC_TABLE_FAILED_HEADER = "Failed";
    String STATISTIC_TABLE_PERCENT_HEADER = "Percent";
    String STATISTIC_TABLE_TOTAL_TIME_HEADER = "Total Time";
    String STATISTIC_TABLE_HEADER_COLOR = "#5BC0DE";
    String SUCCESS_TABLE_HEADER_COLOR = "#5CB85C";
    String FAILED_TABLE_HEADER_COLOR = "#D9534F";
    String SKIPPED_TABLE_HEADER_COLOR = "#F0AD4E";
    String REPORT_XSL_TEMPLATE = "com/uttesh/config/report.xsl";
    String XML_EXTENSION = ".xml";
    String REPORT_CHART_FILE = "chart.png";
    String FORWARD_SLASH = "\\";

    public interface SystemProps {

        String REPORT_TITLE_PROP = "pdfreport.title";
        String REPORT_CHART_PROP = "pdfreport.chart";
        String REPORT_LOGGER_PROP = "pdfreport.logger";
        String REPORT_OUPUT_DIR = "pdfreport.outputdir";
    }
}
