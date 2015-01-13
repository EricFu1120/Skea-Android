package me.linkcube.skea.ui.exercise;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.ervin.bluetooth.EasyBluetooth;
import me.linkcube.skea.R;
import me.linkcube.skea.base.ui.BaseActivity;
import me.linkcube.skea.core.KeyConst;
import me.linkcube.skea.core.excercise.Bar;
import me.linkcube.skea.core.excercise.BarConst;
import me.linkcube.skea.core.excercise.ExerciseController;
import me.linkcube.skea.core.excercise.ExerciseScoreCounter;
import me.linkcube.skea.ui.evaluation.RecordActivity;


public class ExerciseActivity extends BaseActivity implements ExerciseController.ExerciseScoreCallback {

    private static final String TAG = "ExerciseActivity";

    public boolean isGameInitialized = true;
    private LinearLayout frontGroup;
    private LinearLayout behindGroup;
    private ScrollView frontScrollView;
    private ScrollView behindScrollView;
    private ExerciseController controller;
//    private ToggleButton shrinkButton;
    private TextView leftTimeTextView;
    private TextView scoreTextView;
    private ImageView perfectCoolImageView;
    private boolean shrink;

    private Timer testSignalTimer;

    public UpdateImageViewPicHandler updateTextViewTextHandler;

    private InitGameHandler initGameHandler;

    private TestShrinkHandler testShrinkHandler;



    private int previousScore=0;


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


        updateTextViewTextHandler = new UpdateImageViewPicHandler(perfectCoolImageView);
        initGameHandler = new InitGameHandler();


        testShrinkHandler = new TestShrinkHandler();

        testSignalTimer=new Timer();
        testSignalTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                //使用Handler发送消息，以检测当前是否有挤压
                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putBoolean(TestShrinkHandler.TEST_SIGNAL, true);
                msg.setData(bundle);
                testShrinkHandler.sendMessage(msg);
            }
        }, 1000, 15);//1000ms 以后每隔15ms执行一次

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
//        shrinkButton = (ToggleButton) findViewById(R.id.shrink_button);

        //显示perfect cool 文字特效
        perfectCoolImageView = (ImageView) findViewById(R.id.perfect_cool_iv);


        controller = new ExerciseController(this);
        controller.registerShrinkCallback(this);

        frontScrollView.setOnTouchListener(mScorllViewOnTouchListener);
        behindScrollView.setOnTouchListener(mScorllViewOnTouchListener);


//        shrinkButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                shrink = isChecked;
//            }
//        });
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
        //可以将Game的初始化放在ExerciseProgressDialog的onStart(),和onStop()方法中。
        //但是现在放进去会出现“错位”，有空调整之
//        final ExerciseProgressDialog progressDialog = new ExerciseProgressDialog(this,initGameHandler);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Note");
        progressDialog.setMessage("Game is loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(100);
        progressDialog.show();


        new Thread(new Runnable() {
            @Override
            public void run() {

                //初始化Bar－－－调用 controller.init(getApplicationContext(), frontGroup, behindGroup);
                sendMessage_to_handler(initGameHandler,InitGameHandler.INIT_GAME_HANDLER_EXERCISE_KEY,InitGameHandler.INIT_EXERCISE);

                //进入游戏后，倒计时5秒钟开始
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

                //启动游戏－－－调用 controller.prepare(getApplicationContext(), frontScrollView, behindScrollView); 和controller.start();
                sendMessage_to_handler(initGameHandler,InitGameHandler.INIT_GAME_HANDLER_EXERCISE_KEY,InitGameHandler.PREPARE_EXERCISE);

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
                        ExerciseActivity.this.stopTheExercise();
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
    public void startGameScore(Bar bar) {
        Log.d("startScore", "bar type = " + bar.getType());
        ExerciseScoreCounter.getInstance().startGameScore(bar);
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
    public void tickGameScore() {
        if(ExerciseScoreCounter.getInstance().tickGameScore()){
            showPerfectCool(R.drawable.text_starts);
        }
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
    public void stopGameScore() {
        final int score = ExerciseScoreCounter.getInstance().stopGameScore();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                scoreTextView.setText(score + "");
            }
        });
        //TODO 更新UI分数
        previousScore=score;
    }

    @Override
    public void stopCoolScore() {
        final int score = ExerciseScoreCounter.getInstance().stopCoolScore();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                scoreTextView.setText(score + "");
            }
        });
        if(score==previousScore+BarConst.SCORE.COOL_SCORE){
            previousScore=score;
            showPerfectCool(R.drawable.text_cool);
        }

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
        if(score==previousScore+ BarConst.SCORE.PERFECT_SCORE){
            previousScore=score;
            showPerfectCool(R.drawable.text_perfect);

        }
    }

    @Override
    public void showPerfectCool(int imgID) {
        AlphaAnimation perfect_cool_anim = new AlphaAnimation(1.0f, 0.0f);
        perfect_cool_anim.setDuration(1000);//1000ms


        perfect_cool_anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                perfectCoolImageView.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                perfectCoolImageView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        //使用Handler发送消息，以更新UI
        sendMessage_to_handler(updateTextViewTextHandler,UpdateImageViewPicHandler.PERFECT_COOL_IMAGEVIEW_PIC_MESSAGE_KEY,imgID);
        //注册动画
        perfectCoolImageView.setAnimation(perfect_cool_anim);

    }

    @Override
    public void startMissScore() {

        ExerciseScoreCounter.getInstance().startMissScore();

    }

    @Override
    public void tickMissScore() {
        if(ExerciseScoreCounter.getInstance().tickMissScore()){
            showPerfectCool(R.drawable.text_miss);
        }

    }

    @Override
    public void stopMissScore() {
        ExerciseScoreCounter.getInstance().stopMissScore();

    }

    @Override
    public void showExerciseResult(List<Bar> list) {
        Intent showResultIntent = new Intent(this, RecordActivity.class);

        //保存Score
        double[] barScore = new double[list.size()];
        //保存Type
        int[] barType = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            barScore[i] = (double) (list.get(i).getScore());
            barType[i] = list.get(i).getType();
        }

        showResultIntent.putExtra(RecordActivity.EXERCISE_TYPE_KEY, barType);
        showResultIntent.putExtra(RecordActivity.EXERCISE_SCORE_KEY, barScore);

        startActivity(showResultIntent);
    }

    @Override
    public void stopTheExercise() {


        testSignalTimer.cancel();
        sendMessage_to_handler(initGameHandler, InitGameHandler.INIT_GAME_HANDLER_EXERCISE_KEY, InitGameHandler.STOP_EXERCISE);
        this.finish();
    }


    /**
     * @param handler:发送消息的Handler(或子类)
     * @param key:发送数据和接收数据时用到的key
     * @param value:key所对应的value
     * */
    public void sendMessage_to_handler(Handler handler, String key, int value){
        //使用Handler发送消息
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putInt(key,value);
        msg.setData(bundle);
        handler.sendMessage(msg);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){
            //重写物理“返回键”事件，以防止用户通过它退出游戏时，再次进入节奏变快
            Log.i("CXC","*****back----out");
            stopTheExercise();

        }
        return super.onKeyDown(keyCode, event);
    }

    class InitGameHandler extends Handler {
        public static final String INIT_GAME_HANDLER_EXERCISE_KEY="com.linkcube.skea.ui.excise.init_game_handler";
        public static final int INIT_EXERCISE=10000001;


        public static final int PREPARE_EXERCISE=10000002;
        public static final int STOP_EXERCISE=10000003;


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            switch ( msg.getData().getInt(this.INIT_GAME_HANDLER_EXERCISE_KEY)){
                case INIT_EXERCISE:
                    controller.init(getApplicationContext(), frontGroup, behindGroup);
                    break;
                case PREPARE_EXERCISE:
                    controller.prepare(getApplicationContext(), frontScrollView, behindScrollView);
                    controller.start();
                    //游戏开始后，手机便开始接收信号
                    EasyBluetooth.getInstance().setOnDataReceivedListener(new EasyBluetooth.OnDataReceivedListener() {
                        @Override
                        public void onDataReceived(byte[] bytes, String message) {
//                        pressDataTextView.setText(bytes.toString());
                            Log.i("CXC", "&&&&&&&&&&&&onDataReceived：" + bytes.toString());
                            if (bytes[0] == KeyConst.GameFrame.PRESS_FRAME[0]
                                    && bytes[1] == KeyConst.GameFrame.PRESS_FRAME[1]) {
                                shrink = true;
                            }
                        }
                    });
                    break;
                case STOP_EXERCISE:
                    controller.stop();
                    break;
                default:
                    break;
            }
        }
    }



    /**
     * Timer定时给UI发送消息，让UI Thread 去检测是否有挤压
     */
    class TestShrinkHandler extends Handler {
        public static final String TEST_SIGNAL = "com.linkcube.skea.ui.excise.test_signal";

        public TestShrinkHandler() {
            super();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            if (bundle.getBoolean(TEST_SIGNAL)) {//接收到Timer定时发送来的消息，检测此时是否有“挤压”
                if (shrink) {//有挤压
                    ExerciseScoreCounter.getInstance().receiveSignal();
                } else {//无挤压

                }
            }
            //重置信号标志－－－这一点很重要
            shrink = false;
        }
    }

}


/**
 * 进入游戏时默认倒计时5s后开始游戏
 */
class ExerciseProgressDialog extends ProgressDialog {
    private Context context;
    private ExerciseActivity.InitGameHandler initGameHandler;

    public ExerciseProgressDialog(Context context, ExerciseActivity.InitGameHandler initGameHandler) {
        super(context);
        this.context = context;
        this.initGameHandler = initGameHandler;
    }

    @Override
    public void onStart() {
        super.onStart();

//        initGameHandler.sendEmptyMessage(0);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        initGameHandler.sendEmptyMessage(0);

    }


}

/**
 * 用于更新ImageView中文图片的Handler
 * 游戏中Perfect,Cool,Miss 等特效的显示
 */
class UpdateImageViewPicHandler extends android.os.Handler {

    public static final String PERFECT_COOL_IMAGEVIEW_PIC_MESSAGE_KEY = "com.linkcube.skea.ui.exercise.UpdateImageViewPicHandler.message_key";
    private ImageView perfectCoolImageView;

    public UpdateImageViewPicHandler(ImageView iv) {
        super();
        this.perfectCoolImageView = iv;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        //得到传递的参数
        Bundle bundle = msg.getData();
        int imgID  = bundle.getInt(PERFECT_COOL_IMAGEVIEW_PIC_MESSAGE_KEY);
        this.perfectCoolImageView.setImageResource(imgID);
    }
}

