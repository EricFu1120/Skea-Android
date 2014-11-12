package me.linkcube.skea.ui.record;

import android.os.Bundle;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.graphics.Color;
import android.text.style.ForegroundColorSpan;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.util.Log;


import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import me.linkcube.skea.R;
import me.linkcube.skea.ui.BaseActivity;

public class RecordActivity extends BaseActivity {
    //声明控件
    private TextView level_tv;
    private TextView evaluate_tv;
    private TextView explosive_force_tv;
    private TextView persistance_tv;
    private TextView score_tv;
    private TextView duration_tv;
    private TextView correct_rate_tv;


    private SpannableString mSpanableString;




    /** The main dataset that includes all the series that go into a chart. */
    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
    /** The main renderer that includes all the renderers customizing a chart. */
    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
    /** The chart view that displays the data. */
    private GraphicalView mChartView;

    private XYSeriesRenderer renderer;

    private XYSeries series;

    /** 存放GraphicalView的LinearLayout */
    private LinearLayout chart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_record;
    }

    /**
     * 初始化，并注册相应事件
     */
    private void initViews() {

        //得到控件
        level_tv = (TextView) findViewById(R.id.level_tv);
        evaluate_tv = (TextView) findViewById(R.id.evaluate_tv);
        explosive_force_tv = (TextView) findViewById(R.id.explosive_force_tv);
        persistance_tv = (TextView) findViewById(R.id.persistance_tv);
        score_tv = (TextView) findViewById(R.id.score_tv);
        duration_tv = (TextView) findViewById(R.id.duration_tv);
        correct_rate_tv = (TextView) findViewById(R.id.correct_rate_tv);

        //设置文字特效
        setTextViewTextWithSpannableString("Today(Level 4)", "", Color.WHITE, Color.BLUE, 1.5f, 1.0f, level_tv);
        setTextViewTextWithSpannableString("", "Good!", Color.WHITE, Color.YELLOW, 1.0f, 2.5f, evaluate_tv);
        setTextViewTextWithSpannableString("Explosive force :", "Great", Color.WHITE, Color.BLUE, 1.0f, 1.0f, explosive_force_tv);
        setTextViewTextWithSpannableString("Persistance :", "Fair", Color.WHITE, Color.BLUE, 1.0f, 1.0f, persistance_tv);
        setTextViewTextWithSpannableString("Score :", "1573", Color.BLACK, Color.BLUE, 1.0f, 1.0f, score_tv);
        setTextViewTextWithSpannableString("Time :", "24 Min", Color.BLACK, Color.BLUE, 1.0f, 1.0f, duration_tv);
        setTextViewTextWithSpannableString("79", "%", Color.RED, Color.RED, 4.0f, 2.0f, correct_rate_tv);

        //

        setUpChart();


    }

    /**
     * 设置TextView 文字特效
     *
     * @param title        :
     *                     标题
     * @param context      :
     *                     内容
     * @param titleColor   :
     *                     标题的颜色
     * @param contextColor :
     *                     内容的颜色
     * @param titleSize    :
     *                     标题字体大小的倍数
     * @param contextSize  :
     *                     内容字体大小的倍数
     * @param tv           :
     *                     TextView
     */
    private void setTextViewTextWithSpannableString(String title, String context, int titleColor, int contextColor, float titleSize, float contextSize, TextView tv) {

        mSpanableString = new SpannableString(title + context);

        int title_length = title.length();
        int context_length = context.length();
        mSpanableString.setSpan(new RelativeSizeSpan(titleSize), 0, title_length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置字体大小（相对值,单位：像素） 参数表示为默认字体大小的多少倍
        mSpanableString.setSpan(new RelativeSizeSpan(contextSize), title_length, title_length + context_length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //2.0f表示默认字体大小的一半

        //设置字体前景色
        //标题
        mSpanableString.setSpan(new ForegroundColorSpan(titleColor), 0, title_length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //内容
        mSpanableString.setSpan(new ForegroundColorSpan(contextColor), title_length, title_length + context_length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置前景色为洋红色

        tv.setText(mSpanableString);
    }



    /**设置表的相关属性*/
    private void setUpChart(){
        setXYMultipleSeriesRenderer(mRenderer);

        String seriesTitle = "锻练时间分布";
        // create a new series of data
        series = new XYSeries(seriesTitle);
        mDataset.addSeries(series);

        // create a new renderer for the new series
        renderer = new XYSeriesRenderer();
        mRenderer.addSeriesRenderer(renderer);

        setXYSeriesRenderer(renderer);

        // 将图表添加到LinerLayout中
        chart = (LinearLayout) findViewById(R.id.chart);
        mChartView = ChartFactory.getLineChartView(this, mDataset, mRenderer);
        chart.addView(mChartView, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));

        // 加载数据
        // just for abc_activity
        series.add(10.0, 25.0);
        series.add(20.0, 48.0);
        series.add(30.0, 15.0);
        series.add(40.0, 70.0);
        series.add(50.0, 30.0);
        series.add(60.0, 40.0);
        series.add(70.0, 80.0);
        series.add(80.0, 40.0);
        series.add(90.0, 100.0);

        mChartView.repaint();
    }
    /**
     * 设置mRenderer的相关属性
     *
     * @param mRenderer
     *            :要设置的对象
     *
     * */

    private void setXYMultipleSeriesRenderer(XYMultipleSeriesRenderer mRenderer) {
        // set some properties on the main renderer

        // 图表背景色
        // mRenderer.setApplyBackgroundColor(true);
        // mRenderer.setBackgroundColor(Color.argb(100, 50, 50, 50));

        // 坐标轴字体大小
        mRenderer.setAxisTitleTextSize(30);
        // X,Y坐标轴名称
        mRenderer.setXTitle("时间");
        mRenderer.setYTitle("次数");

        // mRenderer.setChartTitleTextSize(20);
        // 设置X,Y坐标的刻度字体大小
        mRenderer.setLabelsTextSize(20);
        // 设置图线说明字体大小
        mRenderer.setLegendTextSize(30);
        // this order: top, left, bottom, right
        //mRenderer.setMargins(new int[] { 15, 40, 15, 15 });
        // 设置空白边缘颜色
        mRenderer.setMarginsColor(getResources().getColor(android.R.color.transparent));
        // 放大，缩小按钮是否显示
        // mRenderer.setZoomButtonsVisible(true);

        // 图表中点的大小
        mRenderer.setPointSize(10);
    }

    /**
     * 设置renderer的相关属性
     *
     * @param renderer
     *            :要设置的对象
     *
     * */
    private void setXYSeriesRenderer(XYSeriesRenderer renderer) {
        // set some renderer properties
        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setFillPoints(true);

        // 图表中是否显示数字
        renderer.setDisplayChartValues(true);
        // 设置图表中数字字体大小
        renderer.setChartValuesTextSize(25.0f);
        // Sets chart values minimum distance.
        renderer.setDisplayChartValuesDistance(10);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.record, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_connect_bluetooth) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
        Log.i("CXC", "---onSaveInstanceState()");
        // save the current data, for instance when changing screen orientation
        outState.putSerializable("dataset", mDataset);
        outState.putSerializable("renderer", mRenderer);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
        // restore the current data, for instance when changing the screen
        // orientation
        Log.i("CXC", "---onRestoreInstanceState()");
        mDataset = (XYMultipleSeriesDataset) savedState
                .getSerializable("dataset");
        mRenderer = (XYMultipleSeriesRenderer) savedState
                .getSerializable("renderer");
    }

}
