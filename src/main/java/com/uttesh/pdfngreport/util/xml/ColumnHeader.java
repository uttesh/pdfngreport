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
public class ColumnHeader {

    @XmlElement(name = "Name")
    String Name;

    @XmlElement(name = "Width")
    String width;
    
    @XmlElement(name = "ColorCode")
    String colorCode;

    @XmlTransient
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @XmlTransient
    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    @XmlTransient
    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    
    
}
