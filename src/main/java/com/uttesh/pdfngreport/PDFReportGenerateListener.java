/*
 The MIT License (MIT)

 Copyright (c) <year> <copyright holders>

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
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
