package me.linkcube.skea.ui.exercise;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Timer;
import java.util.TimerTask;

import cn.ervin.bluetooth.EasyBluetooth;
import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;
import me.linkcube.skea.core.KeyConst;
import me.linkcube.skea.core.excercise.Bar;
import me.linkcube.skea.core.excercise.ExerciseController;
import me.linkcube.skea.core.excercise.ExerciseScoreCounter;


public class ExerciseActivity extends BaseActivity implements ExerciseController.ExerciseScoreCallback {

    private static final String TAG = "ExerciseActivity";

    public boolean scroll = true;
    private LinearLayout frontGroup;
    private LinearLayout behindGroup;
    private ScrollView frontScrollView;
    private ScrollView behindScrollView;
    private ExerciseController controller;
    private ToggleButton shrinkButton;
    private Button initButton;
    private TextView leftTimeTextView;
    private TextView scoreTextView;
    private boolean shrink;

    //用于测试返回数据的TextView
    private TextView pressDataTextView;
    private Button receiveBtn;

    /**
     * 用于标记ScorllView 是否可以滑动
     * 此处是不允许滑动的
     */
    private boolean mScrollable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        initViews();

//
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                if (shrink) {
//                    ExerciseScoreCounter.getInstance().receiveSignal();
//                }
//
//            }
//        }, 1000, 50);//1000ms 以后每隔15ms执行一次
    }

    /**
     * 初始化控件及变量，注册事件
     */
    private void initViews() {
        leftTimeTextView = (TextView) findViewById(R.id.time_left);
        scoreTextView = (TextView) findViewById(R.id.score);
        frontScrollView = (ScrollView) findViewById(R.id.front_scrollView);
        behindScrollView = (ScrollView) findViewById(R.id.behind_scrollView);
        behindGroup = (LinearLayout) findViewById(R.id.behind_group);
        frontGroup = (LinearLayout) findViewById(R.id.exercise_group);
        shrinkButton = (ToggleButton) findViewById(R.id.shrink_button);
        initButton = (Button) findViewById(R.id.init_button);


        controller = new ExerciseController(this);
        controller.registerShrinkCallback(this);

        frontScrollView.setOnTouchListener(mScorllViewOnTouchListener);
        behindScrollView.setOnTouchListener(mScorllViewOnTouchListener);


        shrinkButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                shrink = isChecked;
            }
        });

        initButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (scroll) {
                    controller.init(getApplicationContext(), frontGroup, behindGroup);
                    scroll = false;
                } else {
                    controller.prepare(getApplicationContext(), frontScrollView, behindScrollView);
                    controller.start();

                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (shrink) {
                                ExerciseScoreCounter.getInstance().receiveSignal();
                            }

                        }
                    }, 1000, 25);//1000ms 以后每隔15ms执行一次

                }
            }
        });

        pressDataTextView = (TextView) findViewById(R.id.press_data);
        receiveBtn = (Button) findViewById(R.id.receive_data_button);
        receiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyBluetooth.getInstance().setOnDataReceivedListener(new EasyBluetooth.OnDataReceivedListener() {
                    @Override
                    public void onDataReceived(byte[] bytes, String message) {
                        pressDataTextView.setText(bytes.toString());
                        if (bytes[0] == KeyConst.GameFrame.PRESS_FRAME[0]
                                && bytes[1] == KeyConst.GameFrame.PRESS_FRAME[1]) {
                            Log.d(TAG, "onDataReceived");
                        }
                    }
                });
            }
        });
    }

    /**
     * ScorllView 的OnTouchListener事件，
     * 以便禁用其滑动事件
     */
    View.OnTouchListener mScorllViewOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (mScrollable)
                return false;
            else {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        }
    };

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_exercise;
    }

    private int timeCount = 0;

    @Override
    public void onResume() {
        super.onResume();


        final ExerciseProgressDialog progressDialog = new ExerciseProgressDialog(this, controller, frontGroup, behindGroup, frontScrollView, behindScrollView);
        progressDialog.setTitle("Note");
        progressDialog.setMessage("Game is loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(100);
        progressDialog.show();


        new Thread(new Runnable() {
            @Override
            public void run() {

                while (timeCount < 100) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressDialog.incrementProgressBy(1);
                    timeCount++;
                }
                progressDialog.dismiss();

            }
        }).start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exercise, menu);
        return true;
    }

    private boolean isPaused = true;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_pause) {
            if (isPaused) {
                controller.pause();
                isPaused = false;
                gameAlertDialog();

            } else {
//                controller.continueGame();
//                isPaused = true;
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void gameAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {//退出游戏
                    public void onClick(DialogInterface dialog, int id) {
                        ExerciseActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {//继续游戏
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        controller.continueGame();
                        isPaused = true;
                    }
                });
        //显示Dialog
        builder.create().show();
    }

    @Override
    public void startScore(Bar bar) {
        Log.d("startScore", "bar type = " + bar.getType());
        ExerciseScoreCounter.getInstance().startScore(bar);
    }

    @Override
    public void startCoolScore(Bar bar) {
        ExerciseScoreCounter.getInstance().startCoolScore(bar);
    }

    @Override
    public void startPerfectScore(Bar bar) {
        ExerciseScoreCounter.getInstance().startPerfectScore(bar);
    }

    @Override
    public void tickScore() {
        ExerciseScoreCounter.getInstance().tickScore();
    }

    @Override
    public void tickCoolScore() {
        ExerciseScoreCounter.getInstance().tickCoolScore();

    }

    @Override
    public void tickPerfectScore() {
        ExerciseScoreCounter.getInstance().tickPerfectScore();

    }

    @Override
    public void tickSecond(final int leftTime) {
        Log.d("tickSecond ", "" + leftTime);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                leftTimeTextView.setText(leftTime + "");
            }
        });

        //TODO 更新UI倒计时
    }

    @Override
    public void stopScore() {
        final int score = ExerciseScoreCounter.getInstance().stopScore();
        Log.d("stopScore ", "" + score);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                scoreTextView.setText(score + "");
            }
        });
        //TODO 更新UI分数
    }

    @Override
    public void stopCoolScore() {
        final int coolScore = ExerciseScoreCounter.getInstance().stopCoolScore();

    }

    @Override
    public void stopPerfectScore() {

        final int score = ExerciseScoreCounter.getInstance().stopPerfectScore();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                scoreTextView.setText(score + "");
            }
        });


    }
}

/**
 * 进入游戏时默认倒计时5s后开始游戏
 */
class ExerciseProgressDialog extends ProgressDialog {
    private Context context;
    private ExerciseController controller;
    private LinearLayout frontGroup;
    private LinearLayout behindGroup;
    private ScrollView frontScrollView;
    private ScrollView behindScrollView;

    public ExerciseProgressDialog(Context context, ExerciseController controller, LinearLayout frontGroup, LinearLayout behindGroup, ScrollView frontScrollView, ScrollView behindScrollView) {
        super(context);
        this.context = context;
        this.controller = controller;
        this.frontGroup = frontGroup;
        this.behindGroup = behindGroup;
        this.frontScrollView = frontScrollView;
        this.behindScrollView = behindScrollView;
    }

    @Override
    public void onStart() {
        super.onStart();

//        controller.init(context,frontGroup,behindGroup);

    }

    @Override
    protected void onStop() {
        super.onStop();
//        controller.prepare(context,frontScrollView,behindScrollView);
//        controller.start();
    }
}

