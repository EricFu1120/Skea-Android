package me.linkcube.skea.ui.record;

import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import org.w3c.dom.Text;

import me.linkcube.skea.R;

public class RecordActivity extends Activity {
    //声明控件
    private TextView level_tv;
    private TextView evaluate_tv;
    private TextView explosive_force_tv;
    private TextView persistance_tv;
    private TextView score_tv;
    private TextView duration_tv;

    private SpannableString mSpanableString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        init();
    }

    /**
     * 初始化，并注册相应事件
     */
    private void init() {

        //得到控件
        level_tv = (TextView) findViewById(R.id.level_tv);
        evaluate_tv = (TextView) findViewById(R.id.evaluate_tv);
        explosive_force_tv = (TextView) findViewById(R.id.explosive_force_tv);
        persistance_tv = (TextView) findViewById(R.id.persistance_tv);
        score_tv = (TextView) findViewById(R.id.score_tv);
        duration_tv = (TextView) findViewById(R.id.duration_tv);

        //设置文字特效
        setTextViewTextWithSpannableString("Today(Level 4)","",Color.WHITE,Color.BLUE,1.5f,level_tv);
        setTextViewTextWithSpannableString("","Good!",Color.WHITE,Color.YELLOW,3.0f,evaluate_tv);
        setTextViewTextWithSpannableString("Explosive force :","Great",Color.WHITE,Color.BLUE,1.5f,explosive_force_tv);
        setTextViewTextWithSpannableString("Persistance :","Fair",Color.WHITE,Color.BLUE,1.5f,persistance_tv);
        setTextViewTextWithSpannableString("Score :","1573",Color.BLACK,Color.BLUE,1.5f,score_tv);
        setTextViewTextWithSpannableString("Time :","24 Min",Color.BLACK,Color.BLUE,1.5f,duration_tv);



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
     * @param size         :
     *                     内容字体大小的倍数
     * @param tv           :
     *                     TextView
     */
    private void setTextViewTextWithSpannableString(String title, String context, int titleColor, int contextColor, float size, TextView tv) {
        mSpanableString = new SpannableString(title + context);
        int title_length = title.length();
        int context_length = context.length();
        //设置字体大小（相对值,单位：像素） 参数表示为默认字体大小的多少倍
        mSpanableString.setSpan(new RelativeSizeSpan(size), title_length, title_length + context_length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //2.0f表示默认字体大小的一半

        //设置字体前景色
        //标题
        mSpanableString.setSpan(new ForegroundColorSpan(titleColor), 0, title_length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //内容
        mSpanableString.setSpan(new ForegroundColorSpan(contextColor), title_length, title_length + context_length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置前景色为洋红色

        tv.setText(mSpanableString);
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
}
