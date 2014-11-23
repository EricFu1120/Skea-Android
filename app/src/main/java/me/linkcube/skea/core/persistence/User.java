package me.linkcube.skea.core.persistence;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Ervin on 14/10/23.
 */
public class User extends SugarRecord<User> {

    public String email;

    public String nickname;

    public String password;

    public int height;

    public int weight;

    public Date birthday;

    public User() {

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


}
