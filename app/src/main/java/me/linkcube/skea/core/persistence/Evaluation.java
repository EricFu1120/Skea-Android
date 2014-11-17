package me.linkcube.skea.core.persistence;

import java.util.Date;

/**
 * 实体类
 * 用于保存测试时的各项参数
 * Created by CXC on 14-11-15.
 *
 */
public class Evaluation {

    /**生日*/
    private Date birthday;
    /**身高*/
    private int height;
    /**体重*/
    private int weight;

    /**Reproductive History Level*/
    private int reproductive_level;
    /**Sexual Activity Level*/
    private int sexual_level;
    /**Urinary Incontinence Level*/
    private int urinary_level;
    /**Mental Status Level*/
    private int mental_level;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public int getReproductive_level() {
        return reproductive_level;
    }

    public void setReproductive_level(int reproductive_level) {
        this.reproductive_level = reproductive_level;
    }

    public int getSexual_level() {
        return sexual_level;
    }

    public void setSexual_level(int sexual_level) {
        this.sexual_level = sexual_level;
    }

    public int getUrinary_level() {
        return urinary_level;
    }

    public void setUrinary_level(int urinary_level) {
        this.urinary_level = urinary_level;
    }

    public int getMental_level() {
        return mental_level;
    }

    public void setMental_level(int mental_level) {
        this.mental_level = mental_level;
    }
}
