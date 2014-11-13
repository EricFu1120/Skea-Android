package me.linkcube.skea.core.excercise;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.demogame.DensityUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ervin on 14/11/2.
 */
public class ExerciseController {

    private Timer timer;

    private final int speed;

    private final int UNIT_TIME = 100;

    private int offset;

    private int activePosition = -1;

    private int nextPosition;

    private List<Bar> list;

    private TimerTask timerTask;

    private boolean active = false;

    private ScoreCallback callback;

    /**
     * 用来分割计时点
     */
    private int count;

    private int second;

    public ExerciseController(Context context) {
        //TODO get user's level
        speed = DensityUtils.dip2px(context, BarConst.VIEW.UNIT_HEIGHT);
        list = BarGenerator.getInstance().getBars();
        nextPosition = list.size() - 1;
        Log.d("The speed of bar is ", "" + speed);
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                BarGroupManager.getInstance().scrollBy(-speed);
                offset = offset + speed;
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
            Log.d("test", "" + list.get(i).getEndActiviteOffset());
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
        if (nextPosition >= 0) {
            Bar bar = list.get(nextPosition);
            if (bar.getBeginActiveOffset() <= offset && bar.getEndActiviteOffset() >= offset) {
                if (!active) {
                    activePosition = nextPosition;
                    nextPosition = activePosition - 2;
                    Log.d("checkActivePosition", "" + "bar type" + list.get(activePosition).getType());
                    Log.d("checkActivePosition", "" + "activePosition = " + activePosition);
                    Log.d("checkActivePosition", "" + "nextPosition = " + nextPosition);
                    if (callback != null)
                        callback.startScore(list.get(activePosition));
                } else {
                    count++;
                    second++;
                    if (count % 5 == 0) {
                        count = 0;
                    }
                    if (second % 10 == 0) {
                        second = 0;
                        if (callback != null)
                            callback.tickSecond();
                    }
                    active = true;
                }
            } else {
                active = false;
                if (callback != null)
                    callback.stopScore();
            }
        } else {
            timer.cancel();
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

    public interface ScoreCallback {

        void startScore(Bar bar);

        void tickScore();

        void tickSecond();

        void stopScore();

    }

    public void registerShrinkCallback(ScoreCallback callback) {
        this.callback = callback;
    }

}
