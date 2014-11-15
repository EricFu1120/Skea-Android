package me.linkcube.skea.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

import me.linkcube.skea.R;

public class RegisterActivity extends BaseActivity {


    private TextView clauseTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clauseTextView = (TextView) findViewById(R.id.clause_textView);
        setLinkClickIntercept(clauseTextView);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_register;
    }

    public void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setHomeAsUpIndicator(R.drawable.layer_actionbar_up_indicator_black);
    }

    // 直接拷贝这些代码到你希望的位置，然后在TextView设置了文本之后调用就ok了
    private void setLinkClickIntercept(TextView tv) {
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence text = tv.getText();
        if (text instanceof Spannable) {
            int end = text.length();
            Spannable sp = (Spannable) tv.getText();
            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
            if (urls.length == 0) {
                return;
            }

            SpannableStringBuilder spannable = new SpannableStringBuilder(text);
            spannable.removeSpan(urls);
            // 只拦截 http:// URI
            LinkedList<String> myurls = new LinkedList<String>();
            for (URLSpan uri : urls) {
                String uriString = uri.getURL();
                if (uriString.indexOf("http://") == 0) {
                    myurls.add(uriString);
                }
            }
            //循环把链接发过去
            for (URLSpan uri : urls) {
                String uriString = uri.getURL();
                if (uriString.indexOf("http://") == 0) {
                    MyURLSpan myURLSpan = new MyURLSpan(uriString, myurls);
                    spannable.setSpan(myURLSpan, sp.getSpanStart(uri),
                            sp.getSpanEnd(uri), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                }
            }
            tv.setText(spannable);
        }
    }

    /**
     * 处理TextView中的链接点击事件
     * 链接的类型包括：url，号码，email，地图
     * 这里只拦截url，即 http:// 开头的URI
     */
    private class MyURLSpan extends ClickableSpan {
        private String mUrl;                    // 当前点击的实际链接
        private LinkedList<String> mUrls; // 根据需求，一个TextView中存在多个link的话，这个和我求有关，可已删除掉

        // 无论点击哪个都必须知道该TextView中的所有link，因此添加改变量
        MyURLSpan(String url, LinkedList<String> urls) {
            mUrl = url;
            mUrls = urls;
        }

        @Override
        public void onClick(View widget) {
            // 这里你可以做任何你想要的处理
            // 比如在你自己的应用中用webview打开，而不是打开系统的浏览器
            String info = new String();
            if (mUrls.size() == 1) {
                // 只有一个url，根据策略弹出提示对话框
                info = mUrls.get(0);
            } else {
                // 多个url，弹出选择对话框，意思一下
                info = mUrls.get(0) + "\n" + mUrls.get(1);
            }
            Toast.makeText(RegisterActivity.this, info, Toast.LENGTH_SHORT).show();
//            Uri uri = Uri.parse(mUrl);
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            RegisterActivity.this.startActivity(intent);
        }

    }

}
