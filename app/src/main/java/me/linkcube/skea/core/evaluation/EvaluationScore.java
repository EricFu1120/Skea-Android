package me.linkcube.skea.core.evaluation;

/**
 * Created by Ervin on 14/11/18.
 */
public class EvaluationScore {


    private int getAgeScore(int age) {
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
     * @return
     */
    private int getMeanBMI() {
        return 0;
    }

    /**
     * 绝经前期	绝经后期或停经>1年
     *
     * @param menopause
     * @return
     */
    private int getMenopauseScore(boolean menopause) {
        return menopause ? 15 : 0;
    }

    private int getChildrenScore(int children) {
        if (children == 0)
            return 0;
        else if (children == 1)
            return 3;
        else if (children == 2)
            return 19;
        else
            return 17;
    }

    private int getSmokingScore(boolean smoking) {
        return smoking ? 0 : 8;
    }

    private int getPelvicFloorSurgeryScore(boolean surgery) {
        return surgery ? 14 : 0;
    }

    private int getCurrentHeavyWorkScore(boolean work) {
        return work ? 8 : 0;
    }

    private int getPelvicFloorProblemsScore(boolean problem) {
        return problem ? 6 : 0;
    }

    private int getMotherWithPOPScore(boolean problem) {
        return problem ? 12 : 0;
    }

    private int getSeeingBulgeScore(boolean bulge) {
        return bulge ? 24 : 0;
    }
}
