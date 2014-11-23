package me.linkcube.skea.core.persistence;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Ervin on 14/10/23.
 */
public class User extends SugarRecord<User> {

    String email;

    String nickname;

    String password;

    int height;

    int weight;

    Date birthday;

    public User() {

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


}
