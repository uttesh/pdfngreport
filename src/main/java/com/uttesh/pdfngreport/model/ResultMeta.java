/*
   Copyright 2015 Uttesh Kumar T.H.

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

package com.uttesh.pdfngreport.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.testng.ITestResult;

/**
 *
 * @author Uttesh Kumar T.H.
 * 
 * This bean class holds the test execution result set.
 */
public class ResultMeta {
    
    String suiteName;
    
    public List<Set<ITestResult>> passedList = new ArrayList<Set<ITestResult>>();
    public List<Set<ITestResult>> failedList = new ArrayList<Set<ITestResult>>();
    public List<Set<ITestResult>> skippedList = new ArrayList<Set<ITestResult>>();

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public List<Set<ITestResult>> getPassedList() {
        return passedList;
    }

    public void setPassedList(List<Set<ITestResult>> passedList) {
        this.passedList = passedList;
    }

    public List<Set<ITestResult>> getFailedList() {
        return failedList;
    }

    public void setFailedList(List<Set<ITestResult>> failedList) {
        this.failedList = failedList;
    }

    public List<Set<ITestResult>> getSkippedList() {
        return skippedList;
    }

    public void setSkippedList(List<Set<ITestResult>> skippedList) {
        this.skippedList = skippedList;
    }
    
}
