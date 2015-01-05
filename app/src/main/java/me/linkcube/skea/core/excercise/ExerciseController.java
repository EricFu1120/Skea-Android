package me.linkcube.skea.core.excercise;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.linkcube.skea.R;

/**
 * Created by Ervin on 14/11/2.
 */
public class ExerciseController {

    private Timer timer;
    private int offset = 0;

    private int activePosition = 0;

    private List<Bar> list;

    private TimerTask timerTask;

    private boolean game_active = false;
    private boolean cool_active=false;
    private boolean perfect_active=false;

    private int leftTime;

    private ExerciseScoreCallback callback;

    /**
     * 用来分割计时点
     */
    private int count = 0;

    private int second = 0;

    public ExerciseController(Context context) {
        //TODO get user's level

        list = BarGenerator.getInstance().getBars();

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                BarGroupManager.getInstance().scrollBy(-BarConst.VIEW.UNIT_SPEED);
                offset = offset + BarConst.VIEW.UNIT_SPEED;
//                Log.i("CXC", "@@@@offset:" + offset);
                checkActivePosition();
            }
        };

    }

    public void init(Context context, LinearLayout frontGroup, LinearLayout behindGroup) {
        BarGroupManager.getInstance().initBarGroup(context, frontGroup, behindGroup);
        //将leftTime的计算从构造函数移到这里，是因为，getBlankHeight中返回的height只有在getHeaderOrFooterView执行过之后才是真正的高度，否则为未赋值的默认值0
        //进行向上取值
        leftTime = (int) Math.ceil(BarGroupManager.getInstance().getBarGroupHeight(context, true) / (double) BarConst.VIEW.SPEED);

        Log.i("CXC", "leftTime----" + leftTime);
    }

    public void prepare(Context context, ScrollView frontScrollView, ScrollView behindScrollView) {
        BarGroupManager.getInstance().prepare(context, frontScrollView, behindScrollView);
    }
    public void checkActivePosition() {
        second++;
        if (activePosition < list.size()) {
            Bar bar = list.get(activePosition);
            if (bar.getBeginActiveOffset() <= offset && offset <= bar.getRealEndActiveOffset()) {
                if (bar.getBeginActiveOffset() <= offset && offset < bar.getBeginActiveOffset() + 12) {
                    //cheat --防
//                    Log.i("CXC","@@@@@@cheat");

                } else if (bar.getBeginActiveOffset() + 12 <= offset && offset < bar.getBeginActiveOffset() + 32) {
                    //Cool
//                    Log.i("CXC","@@@@@@Cool");
                    if(!cool_active && callback !=null){
                        cool_active=true;
                        callback.startCoolScore(bar);
                    }
                    if(cool_active){
//                        callback.showPerfectCool(R.drawable.text_cool);
                        callback.tickCoolScore();
                    }

                } else if (bar.getBeginActiveOffset() + 32 <= offset && offset < bar.getRealBeginActiveOffset()) {
//                    //Perfect
                    if(cool_active){
                        callback.stopCoolScore();

                        cool_active=false;
                    }
                    if(!perfect_active && callback !=null){
                        perfect_active=true;
                        callback.startPerfectScore(bar);
                    }
                    if(perfect_active){
//                        callback.showPerfectCool(R.drawable.text_perfect);
                        callback.tickPerfectScore();
                    }
                } else {
                    // bar.getRealBeginActiveOffset() <= offset && offset <= bar.getRealEndActiveOffset()
                    //Game time

                    if(perfect_active){
                        callback.stopPerfectScore();
                        perfect_active=false;
                    }

                    if(!game_active &&callback !=null){
                        game_active =true;
                        callback.startScore(bar);
                        count=0;
                    }
                    if(game_active){
                        count++;
                        if (count != 0 && count % 10 == 0) {
                            callback.tickScore();
                            //开始下一个0.5s
                            count = 0;
                        }
                    }

                }

            } else if (bar.getRealEndActiveOffset() <= offset) {
                //当前条结束
                Log.i("CXC","----- Score &&& next");
                if(game_active){
                    callback.stopScore();
                    game_active = false;
                }
                activePosition+=2;

            } else {
                //offset<bar.getBeginActiveOffset();
                //Miss
                if(callback!=null){
                    callback.stopScore();
                    game_active =false;
                }
            }

        }

        //倒计时
        if (second % 20 == 0) {
            second = 0;
            if (callback != null) {
                leftTime -= 1;
                callback.tickSecond(leftTime);
            }
        }
        //以总时间倒计时来控制整个游戏
        if (leftTime <= 0) {
            timer.cancel();
            //游戏结束之后，显示本次游戏成绩－－跳转到运动记录界面进行显示

            /**
             * 启动运动记录Activity，并传入当前游戏用户的数据，以便显示
             **/
            callback.showExerciseResult(list);
        }
    }


    public void pause() {
        timerTask.cancel();
    }

    public void start() {
        timer.schedule(timerTask, 0, BarConst.VIEW.UNIT_TIME);
    }

    public void continueGame() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                BarGroupManager.getInstance().scrollBy(-BarConst.VIEW.UNIT_SPEED);
                offset = offset + BarConst.VIEW.UNIT_SPEED;
//                Log.i("CXC", "@@@@offset:" + offset);
                checkActivePosition();
            }
        };
        timer.schedule(timerTask, 0, BarConst.VIEW.UNIT_TIME);
    }

    public void resume() {
        timer.schedule(timerTask, 0, BarConst.VIEW.UNIT_TIME);
    }

    public void registerShrinkCallback(ExerciseScoreCallback callback) {
        this.callback = callback;
    }

    public interface ExerciseScoreCallback {

        void startScore(Bar bar);

        void startCoolScore(Bar bar);

        void startPerfectScore(Bar bar);

        void tickScore();

        void tickCoolScore();

        void tickPerfectScore();

        void tickSecond(int leftTime);

        void stopScore();
        void stopCoolScore();
        void stopPerfectScore();


        void showPerfectCool(int imgID);

        void showExerciseResult(List<Bar> list);

    }

}
