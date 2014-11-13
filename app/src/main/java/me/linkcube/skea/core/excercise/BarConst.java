package me.linkcube.skea.core.excercise;

/**
 * Created by Ervin on 14/11/4.
 */
public class BarConst {

    public static class TYPE {

        public final static int LONG = 3;

        public final static int MEDIUM = 2;

        public final static int SHORT = 1;

        public final static int SLOT = 0;

    }

    public static class VIEW {

        public static final int UNIT_HEIGHT = 40;

        public static final int UNIT_WIDTH = 70;
    }

    public static class LEVEL {

        public static final int[] NUM = new int[]{0, 15, 20, 30, 40};

        /**
         * 每种类型和上面的TYPE是对应的
         */
        public static final int[] TIME = new int[]{2, 5, 7, 12};

    }
}