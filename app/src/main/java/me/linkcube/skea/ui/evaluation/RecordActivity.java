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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import custom.android.util.PreferenceUtils;
import custom.android.widget.Toaster;
import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;
import me.linkcube.skea.core.KeyConst;
import me.linkcube.skea.core.evaluation.CombinedChart;
import me.linkcube.skea.core.excercise.BarConst;
import me.linkcube.skea.db.DayRecord;
import me.linkcube.skea.view.NumberCircleProgressBar;

public class RecordActivity extends BaseActivity {

    public static final String EXERCISE_SCORE_KEY = "com.linkcube.skea.ui.evaluation.RecordActivity.score_key";
    public static final String EXERCISE_TYPE_KEY = "com.linkcube.skea.ui.evaluation.RecordActivity.type_key";

    private int light_blue ;
    //记数
    int count = 0;
    //声明控件
    private TextView date_level_tv;
    private TextView evaluate_tv;
    private TextView explosive_force_tv;
    private TextView persistance_tv;
    private TextView score_tv;
    private TextView duration_tv;
    private SpannableString mSpanableString;
    private GraphicalView mCombinedChartView;
    private GraphicalView mScatterChartView;

    //总得分
    private double current_total_score = 0.0;
    private int full_total_score = 0;
    private int current_correct_rate=0;

    //持久力得分
    private double current_persistance_total_score=0.0;
    private int full_total_persistance_score=0;
    private int persistance_correct_rate=0;

    //爆发力得分

    private double current_explosive_total_score=0.0;
    private int full_total_explosive_score=0;
    private int explosive_correct_rate=0;

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
//            Log.i("CXC", "year-month-day:" + year + "-" + monthOfYear + "-" + dayOfMonth);

            /***
             *
             *在这里Load选中日期的用户的Record。。。
             *
             ***/
            loadRecord(mYear, mMonth, mDays);
        }
    };


    public void setTheNumberProgressBar(int correct_rate) {

        final NumberCircleProgressBar bnp = (NumberCircleProgressBar) findViewById(R.id.number_circle_progress_bar);
        bnp.setProgress(correct_rate);

        setEvaluateTextView(correct_rate);
//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                            if(!bnp.isFinished()){
//                                bnp.incrementProgressBy(2);
//                                Log.i("CXC","----progress:"+bnp.getProgress());
//                                Log.i("CXC","----ProgressMax:"+bnp.getMax());
//
//                            }else {
//                                timer.cancel();
//                            }
//
//                    }
//                });
//            }
//        }, 1000, 100);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDate();
        initViews();



        //得到Game数据
        Intent intent = getIntent();
        barType = intent.getIntArrayExtra(RecordActivity.EXERCISE_TYPE_KEY);
        barScore = intent.getDoubleArrayExtra(RecordActivity.EXERCISE_SCORE_KEY);
        if (barType != null && barScore != null) {//接收用户锻炼数据并展示
            addConbinedChart();
        } else {//获取ExerciseActivity传递的数据有问题，，，提示用户“出错了”
            //to-do

            //获取“当天”的运动数据，若没有相关记录的话，则提示无相关数据。
            //test
            barType = new int[]{1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 2};
            barScore = new double[]{92.3, 72.5, 83.8, 96.8, 83.4, 74.4, 81.4, 75.1, 65.6, 90.3, 97.2, 93.9, 92.3, 72.5, 83.8, 96.8, 83.4, 74.4, 81.4,
                    75.1, 65.6, 90.3, 97.2, 93.9, 78.0, 86.0, 78, 6};
            addConbinedChart();

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

        //得到相关资源
        light_blue= getResources().getColor(R.color.text_light_blue);

        //得到控件
        date_level_tv =(TextView) findViewById(R.id.date_level_tv);
        date_level_tv = (TextView) findViewById(R.id.date_level_tv);
        evaluate_tv = (TextView) findViewById(R.id.evaluate_tv);
        explosive_force_tv = (TextView) findViewById(R.id.explosive_force_tv);
        persistance_tv = (TextView) findViewById(R.id.persistance_tv);
        score_tv = (TextView) findViewById(R.id.score_tv);
        duration_tv = (TextView) findViewById(R.id.duration_tv);
        chart = (LinearLayout) findViewById(R.id.chart);

        setDateAndLevelTextView(mYear+"-"+(mMonth+1)+"-"+mDays, PreferenceUtils.getInt(this, KeyConst.SKEA_EXERCISE_LEVEL_KEY,4)+"");

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

    private void setDateAndLevelTextView(String dateString,String levelString){
        setTextViewTextWithSpannableString(dateString+"    ","Level "+levelString,Color.WHITE,Color.WHITE,1.0f,1.0f, date_level_tv);
    }

    private void setScoreTextView(int score){
        setTextViewTextWithSpannableString(getString(R.string.record_score_with_colon), String.valueOf(score), Color.BLACK, light_blue, 1.0f, 1.2f, score_tv);
    }
    private void setTimeTextView(String timeString){

        setTextViewTextWithSpannableString(getString(R.string.record_duration_with_colon), timeString, Color.BLACK, light_blue, 1.0f, 1.2f, duration_tv);
    }

    private void setEvaluateTextView( int correct_rate){

        int evaluateStringID=0;
        if(correct_rate<40){//虚弱

            evaluateStringID=R.string.record_weak;
        }else if(correct_rate<80){//普通

            evaluateStringID=R.string.record_good;
        }else if(correct_rate<95){//优异
            evaluateStringID=R.string.record_cool;

        }else {//真棒

            evaluateStringID=R.string.record_perfect;
        }
        setTextViewTextWithSpannableString("", getString(evaluateStringID), Color.WHITE, getResources().getColor(R.color.light_yellow), 1.0f, 2.5f, evaluate_tv);
    }

    private void setExplosiveTextView(int correct_rate){
        int evaluateStringID=-1;
        if(correct_rate<40){//虚弱

            evaluateStringID=R.string.record_weak;
        }else if(correct_rate<80){//普通

            evaluateStringID=R.string.record_good;
        }else if(correct_rate<95){//优异
            evaluateStringID=R.string.record_cool;
        }else {//真棒
            evaluateStringID=R.string.record_perfect;
        }
        setTextViewTextWithSpannableString(getString(R.string.record_explosive_force), getString(evaluateStringID), Color.WHITE, light_blue, 1.0f, 1.2f, explosive_force_tv);
    }

    private void setPersistanceTextView(int correct_rate){
        int evaluateStringID=-1;
        if(correct_rate<40){//虚弱

            evaluateStringID=R.string.record_weak;
        }else if(correct_rate<80){//普通

            evaluateStringID=R.string.record_good;
        }else if(correct_rate<95){//优异
            evaluateStringID=R.string.record_cool;

        }else {//真棒

            evaluateStringID=R.string.record_perfect;
        }
        setTextViewTextWithSpannableString(getString(R.string.record_persistance_force), getString(evaluateStringID), Color.WHITE, light_blue, 1.0f, 1.2f, persistance_tv);
    }


    private int countNum = 0;
    private double[] xx, yy, zz;
    //柱形图
    private XYSeries waterSeries = new XYSeries(" Type ");



    private void calculateExerciseResult() {
        countNum = barType.length;
        xx = new double[countNum];
        yy = new double[countNum];
        zz = new double[countNum];


        for (int i = 0; i < countNum; i++) {
            xx[i] = (double) i;
            current_total_score += barScore[i];

            switch (barType[i]) {
                case BarConst.TYPE.SHORT:
                    yy[i] = 100 * barScore[i] / BarConst.SCORE.SHORT_FULL_SCORE;
                    full_total_score += BarConst.SCORE.SHORT_FULL_SCORE;
                    current_explosive_total_score+=barScore[i];
                    full_total_explosive_score+= BarConst.SCORE.LONG_FULL_SCORE;
                    zz[i] = 30.0;
                    break;
                case BarConst.TYPE.MEDIUM:
                    yy[i] = 100 * barScore[i] / BarConst.SCORE.MEDIUM_FULL_SCORE;
                    full_total_score += BarConst.SCORE.MEDIUM_FULL_SCORE;
                    zz[i] = 60.0;
                    break;
                case BarConst.TYPE.LONG:
                    yy[i] = 100 * barScore[i] / BarConst.SCORE.LONG_FULL_SCORE;
                    full_total_score += BarConst.SCORE.LONG_FULL_SCORE;
                    current_persistance_total_score+=barScore[i];
                    full_total_persistance_score+= BarConst.SCORE.LONG_FULL_SCORE;
                    zz[i] = 90.0;
                    break;
                default:
                    break;
            }
            waterSeries.add(i, zz[i]);

        }
        //总的Correct Rate
        current_correct_rate=(int) Math.rint(100*current_total_score/full_total_score);

        persistance_correct_rate=(int)Math.rint(100*current_persistance_total_score/full_total_persistance_score);

        explosive_correct_rate=(int)Math.rint(100*current_explosive_total_score/full_total_explosive_score);


        //评诂的各个指标计算，其中总体评诂放在setTheNumberProgressBar()中进行了

        setExplosiveTextView(explosive_correct_rate);
        setPersistanceTextView(persistance_correct_rate);

        //显示Score
        setScoreTextView((int)Math.rint(current_total_score));

        setTimeTextView(String.valueOf(412));
        //显示correct rate---四舍五入取整
        setTheNumberProgressBar(current_correct_rate);//百分比
//        setTheNumberProgressBar(39);
    }

    /**
     * 增加图表
     */
    private void addConbinedChart() {

        calculateExerciseResult();
        String[] titles = new String[]{"  Rate  "};
        // 横轴
        List<double[]> x = new ArrayList<double[]>();
        x.add(xx);


        // 纵轴
        List<double[]> values = new ArrayList<double[]>();

        values.add(yy);

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

        String[] titleStrings = new String[]{"  5 S  ", "   7 S  ", "   12 S  "};
        List<double[]> x = new ArrayList<double[]>();
        List<double[]> values = new ArrayList<double[]>();
        x.add(new double[]{1.0, 2.0, 3.0, 4.0, 5.0});
        x.add(new double[]{6.0, 7.0, 8.0, 9.0, 10.0});
        x.add(new double[]{11.0, 12.0, 13.0, 14.0, 15.0});

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
        switch (item.getItemId()){
            case R.id.action_date://日期选择
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RecordActivity.this, myDateSetListener, mYear, mMonth,
                        mDays);
                datePickerDialog.show();
//                Log.i("CXC", "++++date_record");
                return true;
//            break;
            default:
                break;
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


    /**
     * 展示相应日期（mYear-mMonth-mDays）的数据
     * 包括 Level,"Good",Explosive force ,Persistance,Correct Rate ，等
     */
    private void loadRecord(int mYear, int mMonth, int mDays) {
        String selectDateString=new SimpleDateFormat("yyyy-MM-dd").format(new Date(mYear-1900,mMonth,mDays));
        List<DayRecord> dayRecords=DayRecord.find(DayRecord.class,"today=?",selectDateString);
        if(dayRecords.size()<=0){//无选定日期的运动记录
            //弹框提示之
            Toaster.showShort(this,"无相关记录");

        }else{//存在相关的运动记录－－展示最新的数据（即：选中日期当天的最后一次的）
            DayRecord dayRecord=dayRecords.get(dayRecords.size()-1);
            setDateAndLevelTextView(dayRecord.getToday(),dayRecord.getLevel()+"");
           try{
               if(dayRecord.getRecord().size()<=0){//为什么写入的锻炼数据只有“record”为空呢？？？！！！
                   //弹框提示之
                   Toaster.showShort(this,"相关记录不完整！！！");

               }else {
                   barType=new int[dayRecord.getRecord().size()];
                   barScore=new double[dayRecord.getRecord().size()];
                   for(int i=0;i<dayRecord.getRecord().size();i++){
                       barType[i]=dayRecord.getRecord().get(i).getType();
                       barScore[i]=dayRecord.getRecord().get(i).getScore();
                   }
//                calculateExerciseResult();
                   addConbinedChart();
               }
           }catch (NullPointerException exception){
               Toaster.showShort(this,"数据读取失败！！！");
           }
        }
    }

}
