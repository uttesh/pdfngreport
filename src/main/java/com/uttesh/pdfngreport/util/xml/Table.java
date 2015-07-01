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

import com.uttesh.pdfngreport.common.Constants;
import com.uttesh.pdfngreport.common.ImageUtils;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class used by the JAXB compiler for the XML tree Element generation,.
 *
 * @author Uttesh Kumar T.H.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Table {

    @XmlElementWrapper(name = "ColumnHeaders")
    @XmlElement(name = "ColumnHeader")
    private List<ColumnHeader> columnHeader;

    @XmlElementWrapper(name = "Rows")
    @XmlElement(name = "Row")
    private List<Row> row;

    @XmlElement(name = "TableHeader")
    private String tableHeader;

    @XmlElement(name = "TableName")
    private String tableName;

    @XmlElement(name = "TableHeaderColor")
    private String tableHeaderColor;

    @XmlElement(name = "ReportLocation")
    String reportLocation;

    @XmlElement(name = "TableHeaderIcon")
    private String tableHeaderIcon;



    @XmlTransient
    public List<ColumnHeader> getColumnHeader() {
        return columnHeader;
    }

    public void setColumnHeader(List<ColumnHeader> columnHeader) {
        this.columnHeader = columnHeader;
    }

    @XmlTransient
    public List<Row> getRow() {
        return row;
    }

    public void setRow(List<Row> row) {
        this.row = row;
    }

    @XmlTransient
    public String getTableHeader() {
        return tableHeader;
    }

    public void setTableHeader(String tableHeader) {
        this.tableHeader = tableHeader;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableHeaderColor() {
        return tableHeaderColor;
    }

    public void setTableHeaderColor(String tableHeaderColor) {
        this.tableHeaderColor = tableHeaderColor;
    }

    @XmlTransient
    public String getTableHeaderIcon() {
        return tableHeaderIcon;
    }

    public void setTableHeaderIcon(String tableHeaderIcon) {
        this.tableHeaderIcon = tableHeaderIcon;
    }

    public String getReportLocation() {
        return reportLocation;
    }

    public void setReportLocation(String reportLocation) {
        this.reportLocation = reportLocation;
    }

   
}
