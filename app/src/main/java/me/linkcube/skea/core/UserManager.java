package me.linkcube.skea.core;

/**
 * Created by Ervin on 14/11/15.
 */
public class UserManager {

    private static UserManager instance;

    private boolean login;

    private UserManager() {

    }

    public static UserManager getInstance() {
        if (instance == null)
            instance = new UserManager();
        return instance;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}
