package me.linkcube.skea.core.evaluation;

/**
 * Created by Ervin on 14/11/18.
 */
public class EvaluationScore {


    public static int getAgeScore(int age) {
        if (age <= 49)
            return 0;
        else if (age >= 50 && age <= 54)
            return 3;
        else if (age >= 55 && age <= 59)
            return 6;
        else if (age >= 60 && age <= 64)
            return 9;
        else if (age >= 70 && age <= 74)
            return 13;
        else if (age >= 75 && age <= 79)
            return 16;
        else if (age >= 80 && age <= 84)
            return 22;
        else
            return 25;
    }

    /**
     * BMI = Weight / ( Height^2 )，国际单位制
     *
     * @return
     */
    public static int getMeanBMI(int weight, int height) {
        int BMI = weight / (height * height);
        return BMI;
    }

    /**
     * 绝经前期	绝经后期或停经>1年
     *
     * @param menopause
     * @return
     */
    public static int getMenopauseScore(boolean menopause) {
        return menopause ? 15 : 0;
    }

    public static int getChildrenScore(int children) {
        if (children == 0)
            return 0;
        else if (children == 1)
            return 3;
        else if (children == 2)
            return 19;
        else
            return 17;
    }

    public static int getSmokingScore(boolean smoking) {
        return smoking ? 0 : 8;
    }

    public static int getPelvicFloorSurgeryScore(boolean surgery) {
        return surgery ? 14 : 0;
    }

    public static int getCurrentHeavyWorkScore(boolean work) {
        return work ? 8 : 0;
    }

    public static int getPelvicFloorProblemsScore(boolean problem) {
        return problem ? 6 : 0;
    }

    public static int getMotherWithPOPScore(boolean problem) {
        return problem ? 12 : 0;
    }

    public static int getSeeingBulgeScore(boolean bulge) {
        return bulge ? 24 : 0;
    }
}
