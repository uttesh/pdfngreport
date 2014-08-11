/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uttesh.pdfngreport.model;

import java.util.HashSet;
import java.util.Set;
import org.testng.ITestResult;

/**
 *
 * @author Uttesh Kumar T.H.
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
