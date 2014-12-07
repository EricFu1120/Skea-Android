package me.linkcube.skea.core.excercise;

/**
 * Created by Ervin on 14/11/4.
 */
public class BarConst {

    public static class TYPE {

        public final static int SHORT = 0;
        public final static int MEDIUM = 1;
        public final static int LONG = 2;


        public final static int SLOT_SHORT = 3;
        public final static int SLOT_MEDIUM = 4;
        public final static int SLOT_LONG = 5;

    }

    public static class VIEW {

        /**
         * 每五十毫秒走的px
         * 0.05s*100px/s=5px
         */
        public static final int UNIT_SPEED = 5;

        public static final int SPEED = 100;

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