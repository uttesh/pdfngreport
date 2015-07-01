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
package com.uttesh.pdfngreport.util;

import com.uttesh.pdfngreport.common.Constants;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public class PdfngUtil {

    public static Properties  prop = new Properties();
    public void loadProperties(String path) {
        InputStream input = null;
        try {
            PDFCache cache = new PDFCache();
            input = new FileInputStream(path);
            // load a properties file
            prop.load(input);
            Iterator iterator = prop.keySet().iterator();
            while(iterator.hasNext()){
                String key = (String)iterator.next();
                cache.putConfig(key, prop.get(key));
            }
            cache.putConfig("pdfngreport-properties", prop.get(Constants.SystemProps.REPORT_TITLE_PROP));
//            cache.putConfig(Constants.SystemProps.REPORT_TITLE_PROP, prop.get(Constants.SystemProps.REPORT_TITLE_PROP));
//            cache.putConfig(Constants.SystemProps.REPORT_TITLE_PROP, prop.get(Constants.SystemProps.REPORT_TITLE_PROP));
//            cache.putConfig(Constants.SystemProps.REPORT_CHART_PROP, prop.get(Constants.SystemProps.REPORT_CHART_PROP));
//            cache.putConfig(Constants.SystemProps.REPORT_LOGGER_PROP, prop.get(Constants.SystemProps.REPORT_LOGGER_PROP));
//            cache.putConfig(Constants.SystemProps.REPORT_OUPUT_DIR, prop.get(Constants.SystemProps.REPORT_OUPUT_DIR));
//            cache.putConfig(Constants.SystemProps.REPORT_PIE_CHART_TYPE_PROP,prop.get(Constants.SystemProps.REPORT_PIE_CHART_TYPE_PROP));
//            cache.putConfig(Constants.SystemProps.REPORT_LOGO_FILE,prop.get(Constants.SystemProps.REPORT_LOGO_FILE));
//            cache.putConfig(Constants.SystemProps.REPORT_LOGO,prop.get(Constants.SystemProps.REPORT_LOGO));
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getProp(String name){
        return prop.getProperty(name);
    }
    
    public static String getReportLocation() {
        String location = System.getProperty(Constants.SystemProps.REPORT_OUPUT_DIR);
        if (location == null || location.trim().length() == 0) {
            location = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_OUPUT_DIR);
        }
        return location;
    }
}
