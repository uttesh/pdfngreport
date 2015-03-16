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

package com.uttesh.pdfngreport.util.pdf;

import com.uttesh.pdfngreport.common.Constants;
import com.uttesh.pdfngreport.util.xml.Table;
import java.util.Set;
import org.testng.ITestResult;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public class SkippedTable implements ITable {

    public void populateData(Set<ITestResult> results,Table skippedTable) {
        GenerateTable generateTable = new GenerateTable();
        generateTable.generate(results, skippedTable, Constants.STATUS_SKIPPED);
        skippedTable.setTableName("Skipped");
        skippedTable.setTableHeaderColor("#D79333");
    }
    
}
