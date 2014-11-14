package me.linkcube.skea.core.excercise;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import custom.android.util.DensityUtils;
import custom.android.util.Timber;

/**
 * Created by Ervin on 14/11/2.
 */
public class ExerciseController {

    private Timer timer;

    private final int UNIT_TIME = 50;

    private int offset;

    private int activePosition = -1;

    private int nextPosition;

    private List<Bar> list;

    private TimerTask timerTask;

    private boolean active = false;

    private int leftTime;

    private ExerciseScoreCallback callback;

    /**
     * 用来分割计时点
     */
    private int count;

    private int second;

    public ExerciseController(Context context) {
        //TODO get user's level
        leftTime = BarGroupManager.getInstance().getBarGroupHeight(context, true) / BarConst.VIEW.SPEED;
        Log.d("left time = ", "" + leftTime);
        list = BarGenerator.getInstance().getBars();
        nextPosition = list.size() - 1;
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                BarGroupManager.getInstance().scrollBy(-BarConst.VIEW.UNIT_SPEED);
                offset = offset + BarConst.VIEW.UNIT_SPEED;
                checkActivePosition();
            }
        };

    }

    public void init(Context context, LinearLayout frontGroup, LinearLayout behindGroup) {
        BarGroupManager.getInstance().initBarGroup(context, frontGroup, behindGroup);
    }

    public void prepare(Context context, ScrollView frontScrollView, ScrollView behindScrollView) {
        BarGroupManager.getInstance().prepare(context, frontScrollView, behindScrollView);
    }

    public void test() {
        for (int i = list.size() - 1; i >= 0; i--) {
            Log.d("test", "" + list.get(i).getBeginActiveOffset());
            Log.d("test", "" + list.get(i).getEndActiveOffset());
        }
    }

    /**
     * 开始计算分数
     */
    public void shrink() {

    }

    public void relax() {

    }

    public void checkActivePosition() {
        //确定激活的Bar
        count++;
        second++;
        if (nextPosition >= 0) {
            Bar bar = list.get(nextPosition);
            if (bar.getBeginActiveOffset() <= offset && bar.getEndActiveOffset() >= offset) {
                if (!active) {
                    activePosition = nextPosition;
                    nextPosition = activePosition - 2;
                    Log.d("checkActivePosition", "bar type" + list.get(activePosition).getType());
                    Log.d("checkActivePosition", "activePosition = " + activePosition);
                    Log.d("checkActivePosition", "nextPosition = " + nextPosition);
                    if (callback != null) {
                        callback.startScore(list.get(activePosition));
                        count = 0;
                    }
                }
            } else if (activePosition != -1 && list.get(activePosition).getBeginActiveOffset() <= offset && list.get(activePosition).getEndActiveOffset() >= offset) {
                active = true;
//                Log.d("checkActivePosition", "activePosition = " + activePosition);
            } else {
                active = false;
                if (callback != null && activePosition != -1)
                    callback.tickScore();
                    callback.stopScore();

                activePosition = -1;
            }
        } else {
            timer.cancel();
        }

        if (count != 0 && count % 10 == 0) {
            count = 0;
            if (callback != null)
                callback.tickScore();
        }
        if (second % 20 == 0) {
            second = 0;
            if (callback != null) {
                leftTime -= 1;
                callback.tickSecond(leftTime);
            }
        }
    }

    public void pause() {
        timerTask.cancel();
    }

    public void start() {
        timer.schedule(timerTask, 0, UNIT_TIME);
    }

    public void resume() {
        timer.schedule(timerTask, 0, UNIT_TIME);
    }

    public interface ExerciseScoreCallback {

        void startScore(Bar bar);

        void tickScore();

        void tickSecond(int leftTime);

        void stopScore();

    }

    public void registerShrinkCallback(ExerciseScoreCallback callback) {
        this.callback = callback;
    }

}
