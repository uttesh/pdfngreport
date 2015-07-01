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
import com.uttesh.pdfngreport.common.ImageUtils;
import com.uttesh.pdfngreport.util.xml.Table;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.testng.ITestResult;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public class FailedTable implements ITable {

    public void populateData(Set<ITestResult> results, Table failedTable) {
        GenerateTable generateTable = new GenerateTable();
        generateTable.generate(results, failedTable, Constants.STATUS_FAILED);
        failedTable.setTableName("Failed");
        failedTable.setTableHeaderColor("#D22722");
        try {
            InputStream failedio = getClass().getClassLoader().getResourceAsStream(Constants.Icons.FAILED_ICON);
            failedTable.setTableHeaderIcon(ImageUtils.imageToBase64String(failedio));

           
        } catch (Exception ex) {
            Logger.getLogger(SuccessTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void populateSingleTableData(List<ITestResult> results, Table table) {
        Set<ITestResult> resultSet = new HashSet<ITestResult>();
        for (ITestResult testResult : results) {
            resultSet.add(testResult);
        }
        populateData(resultSet, table);
    }

}
