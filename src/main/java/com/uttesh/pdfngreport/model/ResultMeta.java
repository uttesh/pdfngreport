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

import java.util.HashSet;
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
        
    public  Set<ITestResult> passedSet = new HashSet<ITestResult>();
    public  Set<ITestResult> failedSet = new HashSet<ITestResult>();
    public  Set<ITestResult> skippedSet = new HashSet<ITestResult>();

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public Set<ITestResult> getPassedSet() {
        return passedSet;
    }

    public void setPassedSet(Set<ITestResult> passedSet) {
        this.passedSet = passedSet;
    }

    public Set<ITestResult> getFailedSet() {
        return failedSet;
    }

    public void setFailedSet(Set<ITestResult> failedSet) {
        this.failedSet = failedSet;
    }

    public Set<ITestResult> getSkippedSet() {
        return skippedSet;
    }

    public void setSkippedSet(Set<ITestResult> skippedSet) {
        this.skippedSet = skippedSet;
    }
    
}
