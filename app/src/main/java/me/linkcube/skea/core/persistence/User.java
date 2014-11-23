package me.linkcube.skea.core.persistence;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Ervin on 14/10/23.
 */
public class User extends SugarRecord<User>{

    private String email;

    private String nickName;

    private String password;

    private int height;

    private int weight;

    private Date birthday;


}
