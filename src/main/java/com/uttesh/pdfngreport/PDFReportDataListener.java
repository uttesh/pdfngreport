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
import org.testng.ISuiteResult;
import org.testng.ITestContext;

/**
 *
 * @author Rivet Systems
 */
public class PDFReportDataListener implements ISuiteListener {

    public static Map<String, ResultMeta> result = new HashMap<String, ResultMeta>();

    public void onStart(ISuite isuite) {
        if(result!=null && result.size()>0){
            result = new HashMap<String, ResultMeta>();
        }
    }

    public void onFinish(ISuite isuite) {
        ResultMeta resultMeta = null;
        String suiteName = isuite.getName()!=null? isuite.getName() : "";
        Map<String, ISuiteResult> suiteResults = isuite.getResults();
        for (ISuiteResult sr : suiteResults.values()) {
            resultMeta = new ResultMeta();
            ITestContext tc = sr.getTestContext();
            resultMeta.setSuiteName(suiteName);
            resultMeta.setFailedSet(tc.getFailedTests().getAllResults());
            resultMeta.setPassedSet(tc.getPassedTests().getAllResults());
            resultMeta.setSkippedSet(tc.getSkippedTests().getAllResults());
            result.put(suiteName, resultMeta);
        }
        if (result.size() > 0) {
            PDFGenerator generator = new PDFGenerator();
            String outpurDir = System.getProperty(Constants.SystemProps.REPORT_OUPUT_DIR);
            generator.generateReport(outpurDir + "\\"+suiteName+"_"+Constants.PDF_REPORT_FILE_NAME, result);
        }
    }

}
