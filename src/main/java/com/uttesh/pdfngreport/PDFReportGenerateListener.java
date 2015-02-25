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
import java.util.Map;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 *
 * @author Rivet Systems
 */
public class PDFReportGenerateListener implements ITestListener, ISuiteListener {

    public static Map<String, ResultMeta> result = new HashMap<String, ResultMeta>();
    public static String suiteName= "";
    public static String className ="";

    public void onStart(ISuite isuite) {
    }

    public void onFinish(ISuite isuite) {
        System.out.println("On suite finish :"+result.size());
        if (result.size() > 0) {
            PDFGenerator generator = new PDFGenerator();
            String outpurDir = System.getProperty(Constants.SystemProps.REPORT_OUPUT_DIR);
            generator.generateReport(outpurDir + "\\" + Constants.PDF_REPORT_FILE_NAME, result);
        }
    }

    public void onTestStart(ITestResult itr) {
        className = itr.getTestClass().getName();
    }

    public void onTestSuccess(ITestResult itr) {
    }

    public void onTestFailure(ITestResult itr) {
    }

    public void onTestSkipped(ITestResult itr) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult itr) {
    }

    public void onStart(ITestContext itc) {
    }

    public void onFinish(ITestContext itc) {
        ResultMeta resultMeta = new ResultMeta();
        System.out.println("Class :"+className+" passed :"+itc.getPassedTests().getAllResults().size());
        System.out.println("Class :"+className+" failed :"+itc.getFailedTests().getAllResults().size());
        System.out.println("Class :"+className+" skipped :"+itc.getSkippedTests().getAllResults().size());
        resultMeta.setFailedSet(itc.getFailedTests().getAllResults());
        resultMeta.setPassedSet(itc.getPassedTests().getAllResults());
        resultMeta.setSkippedSet(itc.getSkippedTests().getAllResults());
        result.put(className, resultMeta);
    }

}
