package me.linkcube.skea.core.excercise;

/**
 * Created by Ervin on 14/11/4.
 */
public class BarConst {

    /*Bar的类型*/
    public static class TYPE {

        /*训练Bar的类型*/
        public final static int SHORT = 0;
        public final static int MEDIUM = 1;
        public final static int LONG = 2;


        /*与训练Bar相对应的间隔Bar*/
        public final static int SLOT_SHORT = 3;
        public final static int SLOT_MEDIUM = 4;
        public final static int SLOT_LONG = 5;


    }
    public static class SCORE{
        /*训练Bar对应的满分*/
        public final static int SHORT_FULL_SCORE=125;
        public final static int MEDIUM_FULL_SCORE=186;
        public final static int LONG_FULL_SCORE=420;
    }

    public static class VIEW {

        /**
         * 每五十毫秒走的px
         * 0.05s*80px/s=4px
         */
        public static final int UNIT_TIME = 50;//ms


        public static final int SPEED = 80;// 80px/s

        public static final int UNIT_SPEED = 4;//SPEED * UNIT_TIME/1000

        public static final int UNIT_WIDTH = 70;
    }

    public static class LEVEL {

        public static final int[] NUM = new int[]{0, 15, 20, 30, 40};

        /**
         * 每种类型和上面的TYPE是对应的
         */
        public static final int[] TIME = new int[]{5, 7, 12, 2, 4, 6};

    }

    public static int ACTIVE_BEGIN_OFFSET_MARGIN = 52;//52px
    public static int ACTIVE_END_OFFSET_MARGIN = 18;//18px
}