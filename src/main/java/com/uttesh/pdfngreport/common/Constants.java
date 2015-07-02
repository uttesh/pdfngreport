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
    String COLUMN_TIME_TAKEN = "TimeTaken(ms)";
    String COLUMN_STATUS = "Status";
    String COLUMN_TIME="Time";
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
        Constants.COLUMN_TIME,
        Constants.COLUMN_CLASS,
        Constants.COLUMN_METHOD,
        Constants.COLUMN_TIME_TAKEN};

    String[] FAILED_STATUS_TABLE_COLUMS = {
        Constants.COLUMN_TIME,
        Constants.COLUMN_CLASS,
        Constants.COLUMN_METHOD,
        Constants.COLUMN_TIME_TAKEN,
        Constants.STATUS_EXCEPTION};
    
        String[] FAILED_STATUS_TABLE_COLUMS_NO_EXCEPTION = {
        Constants.COLUMN_TIME,
        Constants.COLUMN_CLASS,
        Constants.COLUMN_METHOD,
        Constants.COLUMN_TIME_TAKEN};

    String STATUS_FAILED = "FAILED";
    String STATUS_PASSED = "PASSED";
    String STATUS_SKIPPED = "SKIPPED";
    String STATUS_EXCEPTION = "Exception";

    String HEADER_FAILED = "Failed";
    String HEADER_PASSED = "Passed";
    String HEADER_SKIPPED = "Skipped";
    String HEADER_STATSTICS = "Statistic's";

    String SHOW = "show";
    String HIDE = "hide";
    String ALIGN_LEFT = "left";
    String ALIGN_RIGHT = "right";

    String PDF_REPORT_FILE_NAME = "pdfng-report-" + new SimpleDateFormat("dd-MMM-yyyy-hh-mm-s-a").format(new Date()) + ".pdf";

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

        String REPORT_TITLE_TYPE = "pdfreport.title.type";
        String REPORT_TITLE_PROP = "pdfreport.title.text";
        String REPORT_TITLE_ALIGN = "pdfreport.title.align";
        String REPORT_TITLE_LOGO = "pdfreport.title.logo";
        String REPORT_CHART_PROP = "pdfreport.chart";
        String REPORT_LOGGER_PROP = "pdfreport.logger";
        String REPORT_OUPUT_DIR = "pdfreport.outputdir";
        String SELENIUM_FAILED_TEST_SCREENSHOT_OUPUT_DIR = "pdfreport.selenium.failed.test.screenshot.outputdir";
        String SHOW_SELENIUM_FAILED_SCREENSHOT_LINK = "pdfreport.show.selenium.screenshot.link";
        String REPORT_PIE_CHART_TYPE_PROP = "pdfreport.pie.chart.type";
        String REPORT_LOGO = "pdfreport.logo";
        String REPORT_LOGO_FILE = "pdfreport.report.logo.file";
        String REPORT_LOGO_ALIGN = "pdfreport.report.logo.align";
        String REPORT_EXCEPTION_PAGE = "pdfreport.exception.page";
        String REPORT_TIME_COLUMN_DATE_FORMAT="pdfngreport.time.column.format";
    }

    public interface Icons {

        String SUCCESS_ICON = "com/uttesh/images/round/pass.png";
        String FAILED_ICON = "com/uttesh/images/round/failed.png";
        String SKIPPED_ICON = "com/uttesh/images/round/skip.png";
        String CHART_ICON = "com/uttesh/images/round/statistics.png";
        String EXCEPTION_ICON = "com/uttesh/images/round/exception.png";
        String DETAIL_ICON = "com/uttesh/images/round/detail.png";
        String IMAGE_ICON = "com/uttesh/images/round/image.png";
    }
}
