/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
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
package me.linkcube.skea.core.evaluation;

import android.graphics.Paint.Align;

import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.Date;
import java.util.List;

/**
 * An abstract class for the demo charts to extend. It contains some methods for
 * building datasets and renderers.
 */
public abstract class AbstractDemoChart {

    /**
     * Builds an XY multiple dataset using the provided values.
     *
     * @param titles  the series titles
     * @param xValues the values for the X axis
     * @param yValues the values for the Y axis
     * @return the
     * multiple dataset
     */
    protected XYMultipleSeriesDataset buildDataset(String[] titles,
                                                   List<double[]> xValues, List<double[]> yValues) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        addXYSeries(dataset, titles, xValues, yValues, 0);
        return dataset;
    }

    public void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles,
                            List<double[]> xValues, List<double[]> yValues, int scale) {
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            XYSeries series = new XYSeries(titles[i], scale);
            double[] xV = xValues.get(i);
            double[] yV = yValues.get(i);
            int seriesLength = xV.length;
            for (int k = 0; k < seriesLength; k++) {
                series.add(xV[k], yV[k]);
            }
            dataset.addSeries(series);
        }
    }

    /**
     * Builds an XY multiple series renderer.
     *
     * @param colors the series rendering colors
     * @param styles the series point styles
     * @return the XY multiple series renderers
     */
    protected XYMultipleSeriesRenderer buildRenderer(int[] colors,
                                                     PointStyle[] styles) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        setRenderer(renderer, colors, styles);
        return renderer;
    }

    protected void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors,
                               PointStyle[] styles) {
        // // 图表背景色
        // renderer.setApplyBackgroundColor(true);
        // renderer.setBackgroundColor(Color.argb(100, 50, 50, 50));

        // (X,Y)坐标轴名称(Month ,Temperature)字体大小
        renderer.setAxisTitleTextSize(30);

        // Chart标题(Weather data)字体大小
        renderer.setChartTitleTextSize(40);
        // 设置X,Y坐标的刻度字体大小
        renderer.setLabelsTextSize(20);
        // 设置图线说明字体大小
        renderer.setLegendTextSize(20);
        // 图表中点的大小
        renderer.setPointSize(8.0f);
        // 放大，缩小按钮是否显示
        renderer.setZoomButtonsVisible(false);
        // this order: top, left, bottom, right
        renderer.setMargins(new int[]{60, 50, 0, 30});//60,50,80,30
        // // 设置空白边缘颜色
        // renderer.setMarginsColor(color.transparent);

        // Sets the approximate number of labels for the X axis.
        renderer.setXLabels(10);//10
        // Sets the approximate number of labels for the Y axis.
        renderer.setYLabels(10);

        // 图表中是否显示网络
        renderer.setShowGrid(true);
        // Sets the X axis labels alignment.
        renderer.setXLabelsAlign(Align.RIGHT);

        renderer.setYLabelsAlign(Align.RIGHT);

//        renderer.setPanLimits(new double[]{0, 30, 0, 110});//x,y轴的最大，最小显示范围*****设置为根据训练数据进行更改
        // Values: [zoomMinimumX, zoomMaximumX, zoomMinimumY, zoomMaximumY]
//        renderer.setZoomLimits(new double[]{0, 20, 0, 50});//0,20,0,50
        //不让用户放大，缩小图表显示
        renderer.setZoomEnabled(false,false);
        int length = colors.length;
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(colors[i]);
            r.setPointStyle(styles[i]);
            renderer.addSeriesRenderer(r);
        }
    }

    /**
     * Sets a few of the series renderer settings.
     *
     * @param renderer    the renderer to set the properties to
     * @param title       the chart title
     * @param xTitle      the title for the X axis
     * @param yTitle      the title for the Y axis
     * @param xMin        the minimum value on the X axis
     * @param xMax        the maximum value on the X axis
     * @param yMin        the minimum value on the Y axis
     * @param yMax        the maximum value on the Y axis
     * @param axesColor   the axes color
     * @param labelsColor the labels color
     */
    protected void setChartSettings(XYMultipleSeriesRenderer renderer,
                                    String title, String xTitle, String yTitle, double xMin,
                                    double xMax, double yMin, double yMax, int axesColor,
                                    int labelsColor,double xMaxPanLimit) {
        renderer.setChartTitle(title);
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setXAxisMin(xMin);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setAxesColor(axesColor);
        renderer.setLabelsColor(labelsColor);
        //xMaxPanLimit表示，x轴能显示的最大值
        //这里设置为一个参数是为了根据训练数据来动态更改
        //因为每个等级的训练个数是不一样的。
        renderer.setPanLimits(new double[]{0, xMaxPanLimit, 0, 105});
        renderer.setBarSpacing(0.5);
    }

}
