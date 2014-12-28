package me.linkcube.skea.core.persistence;

import com.orm.SugarRecord;

/**
 * 实体类
 * 用于保存测试时的各项参数
 */
public class Evaluation extends SugarRecord<Evaluation> {

    String evalationDate;

    String birthday;

    int level;

    int scoreTotal;

    int age;

    int height;

    int weight;

    int scoreAge;

    int scoreMeanBMI;

    int scoreMenopausal;

    int scoreChildren;

    int scoreSmoking;

    int scoreSurgery;

    int scoreWork;

    int scorePOP;

    int scoreProblems;

    int scoreBulge;

    User user;

    public Evaluation() {
    }

    public String getEvalationDate() {
        return evalationDate;
    }

    public void setEvalationDate(String evalationDate) {
        this.evalationDate = evalationDate;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(int scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getScoreAge() {
        return scoreAge;
    }

    public void setScoreAge(int scoreAge) {
        this.scoreAge = scoreAge;
    }

    public int getScoreMeanBMI() {
        return scoreMeanBMI;
    }

    public void setScoreMeanBMI(int scoreMeanBMI) {
        this.scoreMeanBMI = scoreMeanBMI;
    }

    public int getScoreMenopausal() {
        return scoreMenopausal;
    }

    public void setScoreMenopausal(int scoreMenopausal) {
        this.scoreMenopausal = scoreMenopausal;
    }

    public int getScoreChildren() {
        return scoreChildren;
    }

    public void setScoreChildren(int scoreChildren) {
        this.scoreChildren = scoreChildren;
    }

    public int getScoreSmoking() {
        return scoreSmoking;
    }

    public void setScoreSmoking(int scoreSmoking) {
        this.scoreSmoking = scoreSmoking;
    }

    public int getScoreSurgery() {
        return scoreSurgery;
    }

    public void setScoreSurgery(int scoreSurgery) {
        this.scoreSurgery = scoreSurgery;
    }

    public int getScoreWork() {
        return scoreWork;
    }

    public void setScoreWork(int scoreWork) {
        this.scoreWork = scoreWork;
    }

    public int getScorePOP() {
        return scorePOP;
    }

    public void setScorePOP(int scorePOP) {
        this.scorePOP = scorePOP;
    }

    public int getScoreProblems() {
        return scoreProblems;
    }

    public void setScoreProblems(int scoreProblems) {
        this.scoreProblems = scoreProblems;
    }

    public int getScoreBulge() {
        return scoreBulge;
    }

    public void setScoreBulge(int scoreBulge) {
        this.scoreBulge = scoreBulge;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

