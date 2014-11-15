package me.linkcube.skea.core.persistence;

/**
 * Created by Ervin on 14/11/15.
 */
public class UserManager {

    private static UserManager instance;

    private boolean login;

    public static UserManager getInstance() {
        if (instance == null)
            instance = new UserManager();
        return instance;
    }

    private UserManager() {

    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}