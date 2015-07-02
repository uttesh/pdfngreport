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
import com.uttesh.pdfngreport.common.ImageUtils;
import com.uttesh.pdfngreport.util.xml.ReportData;
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

    public static Properties prop = new Properties();

    public void loadProperties(String path) {
        InputStream input = null;
        try {
            PDFCache cache = new PDFCache();
            input = new FileInputStream(path);
            prop.load(input);
            Iterator iterator = prop.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                cache.putConfig(key, prop.get(key));
            }
            cache.putConfig("pdfngreport-properties", prop.get(Constants.SystemProps.REPORT_TITLE_PROP));
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

    public String getProp(String name) {
        return prop.getProperty(name);
    }

    public static String getReportLocation() {
        String location = System.getProperty(Constants.SystemProps.REPORT_OUPUT_DIR);
        if (location == null || location.trim().length() == 0) {
            location = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_OUPUT_DIR);
        }
        return location;
    }

    public void populateReportDataProperties(ReportData reportData) throws Exception {
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
            logoAlign = Constants.ALIGN_RIGHT;
        }
        String titleAlign = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_TITLE_ALIGN);
        if (titleAlign == null || titleAlign.trim().length() == 0) {
            titleAlign = Constants.ALIGN_LEFT;
        }
        String exceptionPage = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_EXCEPTION_PAGE);
        if ((exceptionPage != null || exceptionPage.trim().length() > 0)
                && exceptionPage.equalsIgnoreCase(Constants.HIDE)) {
            exceptionPage = Constants.HIDE;
        } else {
            exceptionPage = Constants.SHOW;
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
        reportData.setExceptionPage(exceptionPage);
        reportData.setExceptionIcon(ImageUtils.imageToBase64String(exceptionIcon));
    }
}
