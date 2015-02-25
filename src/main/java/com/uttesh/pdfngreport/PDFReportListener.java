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
package com.uttesh.pdfngreport;

import com.uttesh.pdfngreport.common.Constants;
import com.uttesh.pdfngreport.model.ResultMeta;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

/**
 * @author Uttesh Kumar T.H.
 * This class is the main class for the pdf report generation
 * @see IReporter
 */
public class PDFReportListener implements IReporter {

    public static Map<String,ResultMeta> result = new HashMap<String, ResultMeta>();

    /**
     * This will be called from IReporter
     * @param list
     * @param suites
     * @param outputFolder 
     */
    public void generateReport(List<XmlSuite> list, List<ISuite> suites, String outputFolder) {
        ResultMeta resultMeta = null;
        for (ISuite suite : suites) {
            String suiteName = suite.getName();
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult sr : suiteResults.values()) {
                resultMeta = new ResultMeta();
                ITestContext tc = sr.getTestContext();
                resultMeta.setSuiteName(suiteName);
                resultMeta.setFailedSet(tc.getFailedTests().getAllResults());
                resultMeta.setPassedSet(tc.getPassedTests().getAllResults());
                resultMeta.setSkippedSet(tc.getSkippedTests().getAllResults());
                result.put(suiteName+":"+System.currentTimeMillis(), resultMeta);
            }
        }
        if(result.size()>0){
            PDFGenerator generator = new PDFGenerator();
            String outpurDir = System.getProperty(Constants.SystemProps.REPORT_OUPUT_DIR);
            generator.generateReport(outpurDir+"\\"+Constants.PDF_REPORT_FILE_NAME, result);
        }
    }
    
}
