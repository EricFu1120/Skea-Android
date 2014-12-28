package me.linkcube.skea.core.persistence;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

/**
 * Created by Ervin on 14/10/23.
 */
public class User extends SugarRecord<User> {

    String email;

    String password;

    @Ignore
    String nickname;

    @Ignore
    int height;

    @Ignore
    int weight;

    /**
     * 格式为yyyy-mm-dd
     */

    @Ignore
    String birthday;

    @Ignore
    int age;

    public User() {

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "email: " + email + "\n" + "password: " + password + "\n" + "nickname: " + nickname + "\n" + "birthday: " + birthday + "\n" + "weight: " + weight + "\n" + "height: " + height + "\n" + "age: " + age;
    }
}
