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
package com.uttesh.pdfngreport.util;

import com.uttesh.pdfngreport.common.Constants;

/**
 *
 * @author Rivet Systems
 */
public class PdfLogger {

    static boolean islogger = Boolean.valueOf(System.getProperty(Constants.SystemProps.REPORT_LOGGER_PROP));

    public static PdfLogger getLogger(String className) {
        PdfLogger _logger = new PdfLogger();
        return _logger;
    }

    public static void log(Object obj) {
        if (islogger) {
            System.out.println(obj.toString());
        }
    }

}
