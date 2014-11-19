package me.linkcube.skea.ui.user;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;

import custom.android.app.dialog.SimpleDialogFragment;
import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    private TextView clauseTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clauseTextView = (TextView) findViewById(R.id.clause_textView);
        clauseTextView.setOnClickListener(this);

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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clause_textView:
                SimpleDialogFragment.createBuilder(this, getSupportFragmentManager()).setTitle("用户协议").setMessage("ActionBar actionBar = getSupportActionBar();\n" +
                        "        actionBar.setDisplayHomeAsUpEnabled(true);\n" +
                        "        actionBar.setDisplayShowHomeEnabled(false);\n" +
                        "        actionBar.setDisplayUseLogoEnabled(false);\n" +
                        "        actionBar.setHomeAsUpIndicator(R.drawable.layer_actionbar_up_indicator_black);ActionBar actionBar = getSupportActionBar();\n" +
                        "        actionBar.setDisplayHomeAsUpEnabled(true);\n" +
                        "        actionBar.setDisplayShowHomeEnabled(false);\n" +
                        "        actionBar.setDisplayUseLogoEnabled(false);\n" +
                        "        actionBar.setHomeAsUpIndicator(R.drawable.layer_actionbar_up_indicator_black);").show();
                DialogFragment dialogFragment = new DialogFragment();
                break;
            default:
                break;
        }
    }
}
