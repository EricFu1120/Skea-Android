package me.linkcube.skea.ui.evaluation;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.achartengine.GraphicalView;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;
import me.linkcube.skea.core.evaluation.CombinedChart;
import me.linkcube.skea.core.excercise.BarConst;
import me.linkcube.skea.ui.exercise.ExerciseActivity;
import me.linkcube.skea.view.NumberCircleProgressBar;

public class RecordActivity extends BaseActivity {

    public static final String EXERCISE_SCORE_KEY = "com.linkcube.skea.ui.evaluation.BecordActivity.score_key";
    public static final String EXERCISE_TYPE_KEY = "com.linkcube.skea.ui.evaluation.BecordActivity.type_key";
    //记数
    int count = 0;
    //声明控件
    private TextView level_tv;
    private TextView evaluate_tv;
    private TextView explosive_force_tv;
    private TextView persistance_tv;
    private TextView score_tv;
    private TextView duration_tv;
    //private TextView correct_rate_tv;
    private SpannableString mSpanableString;
    private GraphicalView mCombinedChartView;
    private GraphicalView mScatterChartView;
    private XYSeriesRenderer renderer;
    private XYSeries series;


    private double barScore[];
    private int barType[];
    /**
     * 存放GraphicalView的LinearLayout
     */
    private LinearLayout chart;


    private Timer timer;
    private boolean isFinish = false;

    //日历相关
    private int mYear;
    private int mMonth;
    private int mDays;

    /**
     * 得当前的年月日,以便初始化日历
     */
    private void getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDays = calendar.get(Calendar.DAY_OF_MONTH);
    }

    DatePickerDialog.OnDateSetListener myDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub

            // 更新年月日，以便下次启动DatePickerDialog时，显示的是上一次设置的值
            mYear = year;
            mMonth = monthOfYear;
            mDays = dayOfMonth;
            Log.i("CXC", "year-month-day:" + year + "-" + monthOfYear + "-" + dayOfMonth);

            /***
             *
             *在这里Load选中日期的用户的Record。。。
             *
             ***/
        }
    };


    public void setTheNumberProgressBar() {

        final NumberCircleProgressBar bnp = (NumberCircleProgressBar) findViewById(R.id.numbercircleprogress_bar);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bnp.incrementProgressBy(2);
                    }
                });
            }
        }, 1000, 100);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();

        setTheNumberProgressBar();

        getDate();

        //得到Game数据
        Intent intent = getIntent();
        barType = intent.getIntArrayExtra(RecordActivity.EXERCISE_TYPE_KEY);
        barScore = intent.getDoubleArrayExtra(RecordActivity.EXERCISE_SCORE_KEY);
        if (barType != null && barScore != null) {//接收用户锻炼数据并展示
            addConbinedChart();
        }else{//获取ExerciseActivity传递的数据有问题，，，提示用户“出错了”
            //to-do

        }
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
        //correct_rate_tv = (TextView) findViewById(R.id.correct_rate_tv);
        chart = (LinearLayout) findViewById(R.id.chart);

        int light_blue = getResources().getColor(R.color.text_light_blue);
        int light_blue2 = getResources().getColor(R.color.light_blue2);

        //设置文字特效
        setTextViewTextWithSpannableString("Today(Level 4)", "", Color.WHITE, Color.BLUE, 1.5f, 1.0f, level_tv);
        setTextViewTextWithSpannableString("", "Good!", Color.WHITE, getResources().getColor(R.color.light_yellow), 1.0f, 2.5f, evaluate_tv);
        setTextViewTextWithSpannableString("Explosive force :", "Great", Color.WHITE, light_blue, 1.0f, 1.2f, explosive_force_tv);
        setTextViewTextWithSpannableString("Persistance :", "Fair", Color.WHITE, light_blue, 1.0f, 1.2f, persistance_tv);
        setTextViewTextWithSpannableString("Score :", "1573", Color.BLACK, light_blue, 1.0f, 1.2f, score_tv);
        setTextViewTextWithSpannableString("Time :", "24 Min", Color.BLACK, light_blue, 1.0f, 1.2f, duration_tv);


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

    /**
     * 增加图表
     */
    private void addConbinedChart() {
        String[] titles = new String[]{"  Score  "};
        // 横轴
        List<double[]> x = new ArrayList<double[]>();

        int countNum = barType.length / 2 + 1;
        double[] xx = new double[countNum];
        double[] yy = new double[countNum];
        double[] zz = new double[countNum];

        //柱形图
        XYSeries waterSeries = new XYSeries(" Time ");
        for (int i = 0; i < countNum; i++) {
            xx[i] = (double) (i + 1);

            switch (barType[2 * i]) {
                case BarConst.TYPE.SHORT:
                    yy[i] = barScore[2 * i] / 2;//假设最高分分别为200，300，500 ,百分比再乘以100
                    zz[i] = 50.0;
                    break;
                case BarConst.TYPE.MEDIUM:
                    yy[i] = barScore[2 * i] / 3;
                    zz[i] = 70.0;
                    break;
                case BarConst.TYPE.LONG:
                    yy[i] = barScore[2 * i] / 5;
                    zz[i] = 90.0;
                    break;
                default:
                    break;
            }

            waterSeries.add(i, zz[i]);

        }
//        for (int i = 0; i < titles.length; i++) {
//            x.add(new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
//        }

        x.add(xx);

        // 纵轴
        List<double[]> values = new ArrayList<double[]>();
//        values.add(new double[]{92.3, 72.5, 83.8, 96.8, 83.4, 74.4, 81.4,
//                75.1, 65.6, 90.3, 97.2, 93.9});

        values.add(yy);

        // Water Temperature 柱状图
//        XYSeries waterSeries = new XYSeries(" Time ");
//        waterSeries.add(1, 20);
//        waterSeries.add(2, 50);
//        waterSeries.add(3, 70);
//        waterSeries.add(4, 20);
//        waterSeries.add(5, 70);
//        waterSeries.add(6, 50);
//        waterSeries.add(7, 70);
//        waterSeries.add(8, 20);
//        waterSeries.add(9, 70);
//        waterSeries.add(10, 50);
//        waterSeries.add(11, 20);
//        waterSeries.add(12, 70);


        //线图＋柱状图
        mCombinedChartView = new CombinedChart()
                .getCombinedChartGraphicalView(getApplicationContext(), titles, x, values, waterSeries);
        chart.addView(mCombinedChartView, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
    }

    /**
     * 增加图表
     */
    private void addScatterChart() {

        //scatter Chart

        String[] titleStrings = new String[]{"2 S", "7 S", "12 S"};
        List<double[]> x = new ArrayList<double[]>();
        List<double[]> values = new ArrayList<double[]>();
        x.add(new double[]{1.0, 2.0, 3.0, 4.0, 5.0});
        x.add(new double[]{1.0, 2.0, 3.0, 5.0, 8.0});
        x.add(new double[]{2.0, 3.0, 4.0, 5.0, 7.0});

        values.add(new double[]{10.0, 12.0, 16.0, 4.0, 23.0});
        values.add(new double[]{12.0, 11.0, 12.0, 7.0, 10.0});
        values.add(new double[]{6.0, 4.0, 12.0, 13.0, 13.0});

        mScatterChartView = new CombinedChart()
                .getScatterChartGraphicalView(getApplicationContext(), x, values, titleStrings);
        chart.addView(mScatterChartView, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));


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
        if (id == R.id.action_date) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    RecordActivity.this, myDateSetListener, mYear, mMonth,
                    mDays);
            datePickerDialog.show();
            Log.i("CXC", "++++date_record");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
        Log.i("CXC", "---onSaveInstanceState()");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
    }

    /**
     * 只是为了测试图表显示，，，以后删除之
     */

    public void changeChart(View v) {

        if (count % 2 == 0) {
            chart.removeView(mCombinedChartView);
            addScatterChart();

        } else {
            chart.removeView(mScatterChartView);
            addConbinedChart();

        }
        count++;
    }

}
