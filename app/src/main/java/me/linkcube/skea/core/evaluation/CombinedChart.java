package me.linkcube.skea.core.evaluation;

import java.util.List;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.LineChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;

import me.linkcube.skea.R;

/**
 * Combined chart.
 */
public class CombinedChart extends AbstractDemoChart {

    public GraphicalView getCombinedChartGraphicalView(Context context, String[] titles, List<double[]> x, List<double[]> values, XYSeries waterSeries) {
        // 曲线的颜色
        int[] colors = new int[]{Color.GREEN};

        // 曲线上点的样式
        PointStyle[] styles = new PointStyle[]{PointStyle.CIRCLE};

        XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
        //边缘空白背景
        renderer.setMarginsColor(context.getResources().getColor(R.color.white));

        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer r = (XYSeriesRenderer) renderer
                    .getSeriesRendererAt(i);
            r.setLineWidth(5.0f);
            r.setFillPoints(true);
            setXYSeriesRenderer(r);
        }

        setChartSettings(renderer, "Score Details", "Excise", "Score", 0.5,
                12.5, 0, 110, Color.LTGRAY, Color.LTGRAY);
        renderer.setBarSpacing(0.5);


        //柱状图
        XYSeriesRenderer waterRenderer = new XYSeriesRenderer();

        waterRenderer.setColor(Color.argb(250, 0, 210, 250));
        //柱状图上是否显示数字
       // waterRenderer.setDisplayChartValues(true);
        waterRenderer.setChartValuesTextSize(20);

        XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
        dataset.addSeries(0, waterSeries);

        renderer.addSeriesRenderer(0, waterRenderer);

        String[] types = new String[]{BarChart.TYPE, LineChart.TYPE,};

        GraphicalView chartGraphicalView = ChartFactory.getCombinedXYChartView(
                context, dataset, renderer, types);
        return chartGraphicalView;
    }

    /**
     * 设置renderer的相关属性
     *
     * @param renderer :要设置的对象
     */
    private void setXYSeriesRenderer(XYSeriesRenderer renderer) {
        // set some renderer properties
        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setFillPoints(true);

        // 图表中是否显示数字
        renderer.setDisplayChartValues(true);
        // 设置图表中数字字体大小
        renderer.setChartValuesTextSize(20.0f);
        // Sets chart values minimum distance.
        renderer.setDisplayChartValuesDistance(10);

    }

    /**
     * Executes the chart demo.
     *
     * @param context the context
     * @return the built intent
     */
    public GraphicalView getScatterChartGraphicalView(Context context,
                                                      List<double[]> x,
                                                      List<double[]> values, String[] titles) {
        int[] colors = new int[]{Color.BLUE, Color.RED, Color.GREEN};
        PointStyle[] styles = new PointStyle[]{PointStyle.DIAMOND,
                PointStyle.SQUARE, PointStyle.CIRCLE};
        XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
        //边缘空白背景
        renderer.setMarginsColor(context.getResources().getColor(R.color.white));

        setChartSettings(renderer, "Scatter chart", "日期", "数据", 0, 15, 0, 30,
                Color.GRAY, Color.LTGRAY);
        renderer.setXLabels(10);
        renderer.setYLabels(10);

        for (int i = 0; i < renderer.getSeriesRendererCount(); i++) {
            ((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
                    .setFillPoints(true);
        }
        return ChartFactory.getScatterChartView(context,
                buildDataset(titles, x, values), renderer);

    }
}
