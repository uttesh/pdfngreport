/*
 * Copyright 2016 Rivetlabs.
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
package com.uttesh.pdfngreport.model;

/**
 *
 * @author Rivetlabs
 */
public class ColumnMeta {
    
    String showTime;
    String showTestName;
    String showTestCase;
    String showTimeTaken;
    String showDesciprtion;

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getShowTestName() {
        return showTestName;
    }

    public void setShowTestName(String showTestName) {
        this.showTestName = showTestName;
    }

    public String getShowTestCase() {
        return showTestCase;
    }

    public void setShowTestCase(String showTestCase) {
        this.showTestCase = showTestCase;
    }

    public String getShowTimeTaken() {
        return showTimeTaken;
    }

    public void setShowTimeTaken(String showTimeTaken) {
        this.showTimeTaken = showTimeTaken;
    }

    public String getShowDesciprtion() {
        return showDesciprtion;
    }

    public void setShowDesciprtion(String showDesciprtion) {
        this.showDesciprtion = showDesciprtion;
    }

    
}
