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
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
/**
 * This class used by the JAXB compiler for the XML tree Element generation,.
 * @author Uttesh Kumar T.H.
 */
@XmlRootElement(name = "ReportData")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportData {

    @XmlElement(name = "CreatedBy")
    String createdBy;
    
    @XmlElement(name = "ReportTitle")
    String reportTitle;
    
    @XmlElement(name = "ReportLocation")
    String reportLocation;
    
    @XmlElement(name = "Chart")
    String chart;

    @XmlElement(name = "Table")
    private List<Table> table;
    
    @XmlElement(name = "ExceptionMeta")
    private List<ExceptionMeta> exceptionMeta;

    @XmlTransient
    public List<Table> getTable() {
        return table;
    }

    public void setTable(List<Table> table) {
        this.table = table;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public String getChart() {
        return chart;
    }

    public void setChart(String chart) {
        this.chart = chart;
    }

    public String getReportLocation() {
        return reportLocation;
    }

    public void setReportLocation(String reportLocation) {
        this.reportLocation = reportLocation;
    }

    public List<ExceptionMeta> getExceptionMeta() {
        return exceptionMeta;
    }

    public void setExceptionMeta(List<ExceptionMeta> exceptionMeta) {
        this.exceptionMeta = exceptionMeta;
    }

    
}
