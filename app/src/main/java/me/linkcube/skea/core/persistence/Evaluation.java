package me.linkcube.skea.core.persistence;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * 实体类
 * 用于保存测试时的各项参数
 */
public class Evaluation extends SugarRecord<Evaluation>{

    private long id;

    private Date evalationDate;

    private Date birthday;

    private int height;

    private int weight;

    private boolean menopausal;

    private int children;

    private boolean smoking;

    private boolean surgery;

    private boolean heavyWork;

    private boolean POP;

    private boolean motherWithPOP;

    private boolean bulge;
}

