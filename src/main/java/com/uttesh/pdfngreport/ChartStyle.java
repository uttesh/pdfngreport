/*
 The MIT License (MIT)

 Copyright (c) <year> <copyright holders>

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 */
package com.uttesh.pdfngreport;

import java.awt.Color;
import java.awt.Font;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.ui.RectangleInsets;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public class ChartStyle {

    /**
     * this method will set the style theme for pie chart.
     * @see org.jfree.chart.StandardChartTheme
     * @param chart 
     */
    public static void theme(JFreeChart chart) {
        String fontName = "Lucida Sans";

        StandardChartTheme theme = (StandardChartTheme) org.jfree.chart.StandardChartTheme.createJFreeTheme();

        theme.setTitlePaint(Color.decode("#4572a7"));
        theme.setExtraLargeFont(new Font(fontName, Font.PLAIN, 16)); //title
        theme.setLargeFont(new Font(fontName, Font.BOLD, 15)); //axis-title
        theme.setRegularFont(new Font(fontName, Font.PLAIN, 11));
        theme.setRangeGridlinePaint(Color.decode("#C0C0C0"));
        theme.setPlotBackgroundPaint(Color.white);
        theme.setChartBackgroundPaint(Color.white);
        theme.setGridBandPaint(Color.red);
        theme.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
        theme.setBarPainter(new StandardBarPainter());
        theme.setAxisLabelPaint(Color.decode("#666666"));
        theme.apply(chart);
        chart.setTextAntiAlias(true);
        chart.setAntiAlias(true);
    }
}
