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
import com.uttesh.pdfngreport.model.SystemMeta;
import com.uttesh.pdfngreport.util.xml.ReportData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
        String outputDir = System.getProperty(Constants.SystemProps.REPORT_OUPUT_DIR);
    	if (outputDir == null || outputDir.trim().length() == 0) {
    		String configOption = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_OUPUT_DIR);
    		return absolutify(configOption);
    	} else {
    		return absolutify(outputDir);
    	}
    }
    
    private static String absolutify(String relative) {
		String backSlashes = new File(relative).getAbsolutePath();
		String forwardSlashes = backSlashes.replace('\\', '/');
		return forwardSlashes;
	}

    public void populateReportDataProperties(ReportData reportData) throws Exception {
        String reportTitle = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_TITLE_PROP);
        String reportLocation = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_OUPUT_DIR);
        String logoFile = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_LOGO_FILE);
        String logo = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_LOGO);
        String chart = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_CHART_PROP);
        String logoAlign = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_LOGO_ALIGN);

        String showTimeColumn = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_TABLE_COLUMN_TIME);
        String showTestNameColumn = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_TABLE_COLUMN_TEST_NAME);
        String showTestCaseColumn = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_TABLE_COLUMN_TEST_CASE);
        String showTimeTakenColumn = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_TABLE_COLUMN_TIME_TAKEN);
        String showDescriptionColumn = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_TABLE_COLUMN_DESCRIPTION);

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
        
        File logoFilepath = new File(logoFile);
        if (logoFilepath.exists()) {
        	// This isn't really necessary; absolutify won't do anything to paths that are already absolute
        	if (logoFilepath.isAbsolute())
                reportData.setLogoFile(logoFile);
        	else
        		reportData.setLogoFile(absolutify(logoFile));
        } else {
        	System.err.println("[ERROR] Logo file located at '" + absolutify(logoFile) + "' cannot be found! Disabling logo...");
        	logo = "hide";
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
        reportData.setLogo(logo);
        reportData.setLogoAlign(logoAlign);
        reportData.setExceptionPage(exceptionPage);
        reportData.setExceptionIcon(ImageUtils.imageToBase64String(exceptionIcon));
        reportData.setShowTime(showTimeColumn);
        reportData.setShowTestName(showTestNameColumn);
        reportData.setShowTestCase(showTestCaseColumn);
        reportData.setShowTimeTaken(showTimeTakenColumn);
        reportData.setShowDesciprtion(showDescriptionColumn);
        populatedSystemDetails(reportData);
        
    }

    public String getSystemDetails(String buildVersion) {
        try {
            SystemMeta systemMeta = new SystemMeta();
            systemMeta.setHostName(getHostName());
            systemMeta.setJavaVersion(System.getProperty(Constants.BuidSystem.JAVA_VERSION));
            systemMeta.setJvmVendor(System.getProperty(Constants.BuidSystem.JAVA_VERDOR));
            systemMeta.setOsArchitecture(System.getProperty(Constants.BuidSystem.OS_ARCHETECTURE));
            systemMeta.setOs(System.getProperty(Constants.BuidSystem.OS_NAME));
            systemMeta.setUserName(System.getProperty(Constants.BuidSystem.USER_NAME));
            if (buildVersion == null && buildVersion.trim().length() == 0) {
                buildVersion = "";
            }
            String details = buildVersion.trim() + " - " + systemMeta.getUserName() + "@" + systemMeta.getHostName() + " - java " + systemMeta.getJavaVersion() + "("
                    + systemMeta.getJvmVendor() + ") - " + systemMeta.getOs() + "(" + systemMeta.getOsArchitecture() + ")";
            System.out.println(" build system details : " + details);
            return details;
        } catch (Exception e) {

        }
        return null;
    }

    private void populatedSystemDetails(ReportData reportData) {
        String buildVersion = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_APP_BUILD_VERSION);
        reportData.setBuildVersion(buildVersion != null ? buildVersion : "");
        String buildDetailsMode = (String) PDFCache.getConfig(Constants.SystemProps.REPORT_BUILD_SYSTEM_DETAILS_BY);
        String name = System.getProperty(Constants.BuidSystem.OS_NAME).trim().substring(0, 1);
        reportData.setOsName(name.toLowerCase());
        if (buildDetailsMode != null && buildDetailsMode.equalsIgnoreCase("manual")) {
            reportData.setBuildSystemDetailsBy("manual");
            reportData.setAdditionLine1((String) PDFCache.getConfig(Constants.SystemProps.REPORT_ADDITIONAL_LINE1));
            reportData.setAdditionLine2((String) PDFCache.getConfig(Constants.SystemProps.REPORT_ADDITIONAL_LINE2));
            reportData.setAdditionLine3((String) PDFCache.getConfig(Constants.SystemProps.REPORT_ADDITIONAL_LINE3));
        } else if (buildDetailsMode != null && buildDetailsMode.equalsIgnoreCase("code")) {
            reportData.setBuildSystemDetailsBy("code");
            reportData.setBuildSystemDetails(getSystemDetails(buildVersion));
        } else {
            // dont display system details
        }
    }

    private String getHostName() throws UnknownHostException {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            if (addr != null) {
                return addr.getHostName();
            }
        } catch (Exception e) {
            System.out.println("Unable to find the host name, Still execution can continue");
        }
        return "";
    }

}
