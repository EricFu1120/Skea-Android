package me.linkcube.skea.core.persistence;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.util.Date;

/**
 * Created by Ervin on 14/10/23.
 */
@Table(name = "User")
public class User {

    private String email;

    private String nickName;

    private String password;

    private int height;

    private int weight;

    private Date birthday;


}
