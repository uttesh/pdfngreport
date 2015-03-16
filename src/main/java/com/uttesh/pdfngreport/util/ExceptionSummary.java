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

package com.uttesh.pdfngreport.util;

import com.uttesh.pdfngreport.exceptionHandler.ReportException;
import com.uttesh.pdfngreport.util.xml.ExceptionMeta;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public class ExceptionSummary {
    
    PDFCache cache = new PDFCache();
    /**
     * This method will create exception summary page.
     */
    public List<ExceptionMeta> getSummary() {
        List<ExceptionMeta> list = new ArrayList<ExceptionMeta>();
        try {
            Set<Object> keys = cache.getAllKeys();
            ExceptionMeta exceptionMeta = null;
            for (Object key : keys) {
                exceptionMeta = new ExceptionMeta();
                ExceptionBean exceptionBean = (ExceptionBean)cache.get(key);
                exceptionMeta.setErrorCode((Long)key);
                exceptionMeta.setDescription(getStackTraceString(exceptionBean.getThrowable()));
                StackTraceElement[] elems = exceptionBean.getThrowable().getStackTrace();
                exceptionMeta.setHeading(exceptionBean.getLable());
                for (StackTraceElement stackTraceElement : elems) {
                    exceptionMeta.setStackTrace(stackTraceElement.toString());
                }
                if (exceptionMeta != null
                        && exceptionMeta.getDescription().length() > 0
                        && exceptionMeta.getErrorCode() > 0
                        && exceptionMeta.getStackTrace().length() > 0) {
                    list.add(exceptionMeta);
                }
            }
            return list;
        } catch (Exception e) {
            throw new ReportException("Exception Summary", e);
        }
    }

    private static String getStackTraceString(Throwable e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e.toString());
        sb.append("\n");

        StackTraceElement[] stack = e.getStackTrace();
        if (stack != null) {
            for (StackTraceElement stackTraceElement : stack) {
                sb.append("\tat ");
                sb.append(stackTraceElement.toString());
                sb.append("\n");
            }
        }


        Throwable cause = e.getCause();
        if (cause != null) {
            sb.append("Caused by: ");
            sb.append(getStackTraceString(cause));
        }

        return sb.toString();
    }

}
