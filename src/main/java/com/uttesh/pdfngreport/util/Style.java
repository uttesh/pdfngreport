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

package com.uttesh.pdfngreport.util;

import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPCell;
import java.awt.Color;

/**
 *
 * @author Uttesh kumar
 */


public class Style {
    public static void headerCellStyle(PdfPCell cell,Color color){
 
    // alignment
    //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
 
    // padding
        cell.setPaddingTop(0f);
        cell.setPaddingBottom(7f);
 
        // background color
        cell.setBackgroundColor(color);
 
        // border
        cell.setBorder(0);
        cell.setBorderWidthBottom(2f);
 
    }
    public static void labelCellStyle(PdfPCell cell){
    // alignment
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
 
        // padding
        cell.setPaddingLeft(3f);
        cell.setPaddingTop(0f);
 
        // background color
        cell.setBackgroundColor(Color.LIGHT_GRAY);
 
        // border
        cell.setBorder(0);
        cell.setBorderWidthBottom(1);
        cell.setBorderColorBottom(Color.GRAY);
 
        // height
        cell.setMinimumHeight(18f);
    }
 
    public static void valueCellStyle(PdfPCell cell){
    // alignment
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
 
        // padding
        cell.setPaddingTop(0f);
        cell.setPaddingBottom(5f);
 
        // border
        cell.setBorder(0);
        cell.setBorderWidthBottom(0.5f);
 
        // height
        cell.setMinimumHeight(18f);
    }
}
