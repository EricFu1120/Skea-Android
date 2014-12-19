package me.linkcube.skea.core.persistence;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 * 用于保存测试时的各项参数
 */
public class Evaluation extends SugarRecord<Evaluation>  {

    Date evalationDate;

    String birthday;

    int age;

    int height;

    int weight;

    boolean menopausal;

    int children;

    boolean smoking;

    boolean surgery;

    boolean heavyWork;

    boolean POP;

    boolean motherWithPOP;

    boolean bulge;

    public Date getEvalationDate() {
        return evalationDate;
    }

    public void setEvalationDate(Date evalationDate) {
        this.evalationDate = evalationDate;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public boolean isMenopausal() {
        return menopausal;
    }

    public void setMenopausal(boolean menopausal) {
        this.menopausal = menopausal;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public boolean isSurgery() {
        return surgery;
    }

    public void setSurgery(boolean surgery) {
        this.surgery = surgery;
    }

    public boolean isHeavyWork() {
        return heavyWork;
    }

    public void setHeavyWork(boolean heavyWork) {
        this.heavyWork = heavyWork;
    }

    public boolean isPOP() {
        return POP;
    }

    public void setPOP(boolean POP) {
        this.POP = POP;
    }

    public boolean isMotherWithPOP() {
        return motherWithPOP;
    }

    public void setMotherWithPOP(boolean motherWithPOP) {
        this.motherWithPOP = motherWithPOP;
    }

    public boolean isBulge() {
        return bulge;
    }

    public void setBulge(boolean bulge) {
        this.bulge = bulge;
    }
}

