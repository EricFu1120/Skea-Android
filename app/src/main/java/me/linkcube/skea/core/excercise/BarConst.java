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

        /**
         * 每种类型和上面的TYPE是对应的
         */
        public static final int[] BAR_TIME = new int[]{2+1, 7+1, 12+1, 1-1, 2-1, 3-1};


    }
    public static class SCORE{
        /*训练Bar对应的满分*/
        public final static int SHORT_FULL_SCORE=78;
        public final static int MEDIUM_FULL_SCORE=224;
        public final static int LONG_FULL_SCORE=474;
        public final static int COOL_SCORE=30;
        public final static int PERFECT_SCORE=50;
    }

    public static class VIEW {

        /**
         * 每五十毫秒走的px
         * 0.05s*80px/s=4px
         */
        public static final int UNIT_TIME = 50;//ms


        public static final int SPEED = 80;// 80px/s

        public static final int UNIT_SPEED = 4;//SPEED * UNIT_TIME/1000

        public static final int UNIT_WIDTH = 70;//
    }

    public static class LEVEL {

        public static final int LEVEL_ONE=1;
        public static final int LEVEL_TWO=2;
        public static final int LEVEL_THREE=3;
        public static final int LEVEL_FOUR=4;
        public static final int LEVEL_FIVE=5;

        public static final int[] BAR_UNIT_NUM = new int[]{0, 15, 20, 30, 40,50};



    }
    public static int ACTIVE_BEGIN_OFFSET_MARGIN = 52;//52px
    public static int ACTIVE_END_OFFSET_MARGIN = 28;//28px
}