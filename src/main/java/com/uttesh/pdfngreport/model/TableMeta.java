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

package com.uttesh.pdfngreport.model;

import java.util.Comparator;

/**
 * Bean handler class for the PDF generation.
 * @author Uttesh Kumar T.H.
 */
public class TableMeta  {
    
    String className;
    String packagePath;
    String method;
    String testCaseName;
    String testContextName;
    String time;
    String status;
    String blockId;
    String suiteName;
    String timeTaken;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public String getTestContextName() {
        return testContextName;
    }

    public void setTestContextName(String testContextName) {
        this.testContextName = testContextName;
    }


  
    public static Comparator<TableMeta> TableMetaComparator  = new Comparator<TableMeta>() {
	    public int compare(TableMeta tableMeta1, TableMeta tableMeta2) {
	      String ClassName1 = tableMeta1.getClassName().toUpperCase();
	      String ClassName2 = tableMeta2.getClassName().toUpperCase();
	      //ascending order
	      return ClassName1.compareTo(ClassName2);
	      //descending order
	      //return ClassName2.compareTo(ClassName1);
	    }
	};
    
}
