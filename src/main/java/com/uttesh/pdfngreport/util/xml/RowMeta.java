package com.uttesh.pdfngreport.util.xml;

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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class used by the JAXB compiler for the XML tree Element generation,.
 *
 * @author Uttesh Kumar T.H.
 */
@XmlRootElement
public class RowMeta {

    @XmlElement(name = "STATUS")
    String status;

    @XmlElement(name = "BLOCKID")
    String blockId;

    @XmlElement(name = "PACKAGEPATH")
    String packagePath;

    @XmlElement(name = "CLASSNAME")
    String className;

    @XmlElement(name = "METHOD")
    String method;

    @XmlElement(name = "DESCRIPTION")
    String description;

    @XmlElement(name = "TIME")
    String time;

    @XmlElement(name = "TIMETAKEN")
    String timeTaken;

    @XmlElement(name = "PASSED")
    String passed;
    @XmlElement(name = "FAILED")
    String failed;
    @XmlElement(name = "SKIPPED")
    String skipped;
    @XmlElement(name = "PERCENTAGE")
    String percentage;

    @XmlElement(name = "TableName")
    String tableName;

    @XmlElement(name = "FAILED_SCREEN_SHOT_LOCATION")
    String failedScreenShotLocation;

    @XmlElement(name = "SHOW_SCREEN_SHOT_LINK")
    String showScreenshotLink = "hide";

    @XmlElement(name = "ExceptionPage")
    private String exceptionPage;
    
    @XmlElement(name = "SHOW_COLUMN_TIME")
    String showTime;
    
    @XmlElement(name = "SHOW_COLUMN_TEST_NAME")
    String showTestName;
    
    @XmlElement(name = "SHOW_COLUMN_TEST_CASE")
    String showTestCase;
    
    @XmlElement(name = "SHOW_COLUMN_TIME_TAKEN")
    String showTimeTaken;
    
    @XmlElement(name = "SHOW_COLUMN_DESCRIPTION")
    String showDesciprtion;

    @XmlTransient
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    @XmlTransient
    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    @XmlTransient
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @XmlTransient
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @XmlTransient
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @XmlTransient
    public String getPassed() {
        return passed;
    }

    public void setPassed(String passed) {
        this.passed = passed;
    }

    @XmlTransient
    public String getFailed() {
        return failed;
    }

    public void setFailed(String failed) {
        this.failed = failed;
    }

    @XmlTransient
    public String getSkipped() {
        return skipped;
    }

    public void setSkipped(String skipped) {
        this.skipped = skipped;
    }

    @XmlTransient
    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    @XmlTransient
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @XmlTransient
    public String getFailedScreenShotLocation() {
        return failedScreenShotLocation;
    }

    public void setFailedScreenShotLocation(String failedScreenShotLocation) {
        this.failedScreenShotLocation = failedScreenShotLocation;
    }

    @XmlTransient
    public String getShowScreenshotLink() {
        return showScreenshotLink;
    }

    public void setShowScreenshotLink(String showScreenshotLink) {
        this.showScreenshotLink = showScreenshotLink;
    }

    public String getExceptionPage() {
        return exceptionPage;
    }

    @XmlTransient
    public void setExceptionPage(String exceptionPage) {
        this.exceptionPage = exceptionPage;
    }

    @XmlTransient
    public String getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

    @XmlTransient
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @XmlTransient
    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    @XmlTransient
    public String getShowTestName() {
        return showTestName;
    }

    public void setShowTestName(String showTestName) {
        this.showTestName = showTestName;
    }

    @XmlTransient
    public String getShowTestCase() {
        return showTestCase;
    }

    public void setShowTestCase(String showTestCase) {
        this.showTestCase = showTestCase;
    }

    @XmlTransient
    public String getShowTimeTaken() {
        return showTimeTaken;
    }

    public void setShowTimeTaken(String showTimeTaken) {
        this.showTimeTaken = showTimeTaken;
    }

    @XmlTransient
    public String getShowDesciprtion() {
        return showDesciprtion;
    }

    public void setShowDesciprtion(String showDesciprtion) {
        this.showDesciprtion = showDesciprtion;
    }


}
