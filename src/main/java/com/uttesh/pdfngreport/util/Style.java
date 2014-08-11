
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
