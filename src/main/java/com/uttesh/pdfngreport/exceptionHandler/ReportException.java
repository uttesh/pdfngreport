/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uttesh.pdfngreport.exceptionHandler;

/**
 *
 * @author Rivet Systems
 */
public class ReportException extends RuntimeException {
    public ReportException(String string)
    {
        super(string);
    }

    
    public ReportException(String string, Throwable throwable)
    {
        super(string, throwable);
    }
}
