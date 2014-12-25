package me.linkcube.skea.core.persistence;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * 实体类
 * 用于保存测试时的各项参数
 */
public class Evaluation extends SugarRecord<Evaluation> {

    String evalationDate;

    String birthday;

    int level;

    int score;

    int age;

    int height;

    int weight;

    int meanBMI;

    int menopausal;

    int children;

    int smoking;

    int surgery;

    int heavyWork;

    int POP;

    int motherWithPOP;

    int bulge;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public int getMeanBMI() {
        return meanBMI;
    }

    public void setMeanBMI(int meanBMI) {
        this.meanBMI = meanBMI;
    }

    public int getMenopausal() {
        return menopausal;
    }

    public void setMenopausal(int menopausal) {
        this.menopausal = menopausal;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getSmoking() {
        return smoking;
    }

    public void setSmoking(int smoking) {
        this.smoking = smoking;
    }

    public int getSurgery() {
        return surgery;
    }

    public void setSurgery(int surgery) {
        this.surgery = surgery;
    }

    public int getHeavyWork() {
        return heavyWork;
    }

    public void setHeavyWork(int heavyWork) {
        this.heavyWork = heavyWork;
    }

    public int getPOP() {
        return POP;
    }

    public void setPOP(int POP) {
        this.POP = POP;
    }

    public int getMotherWithPOP() {
        return motherWithPOP;
    }

    public void setMotherWithPOP(int motherWithPOP) {
        this.motherWithPOP = motherWithPOP;
    }

    public int getBulge() {
        return bulge;
    }

    public void setBulge(int bulge) {
        this.bulge = bulge;
    }
}

