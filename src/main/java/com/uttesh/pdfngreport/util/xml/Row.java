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
  * @author Uttesh Kumar T.H.
  */
@XmlRootElement
public class Row {
    
    @XmlElement(name = "RowMeta")
    RowMeta rowMeta;


    @XmlElement(name = "fontWeight")
    String fontWeight = "normal";

    @XmlElement(name = "fontSize")
    String fontSize = "0pt";

    @XmlElement(name = "bgcolor")
    String bgcolor = "white";
    
    @XmlTransient
    public String getFontWeight() {
        return fontWeight;
    }

    public void setFontWeight(String fontWeight) {
        this.fontWeight = fontWeight;
    }

    @XmlTransient
    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    @XmlTransient
    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    @XmlTransient
    public RowMeta getRowMeta() {
        return rowMeta;
    }

    public void setRowMeta(RowMeta rowMeta) {
        this.rowMeta = rowMeta;
    }

    
}
