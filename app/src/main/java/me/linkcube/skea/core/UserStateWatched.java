package me.linkcube.skea.core;

import java.util.Observable;

/**
 * Created by Ervin on 14/11/23.
 */
public class UserStateWatched extends Observable {

    public boolean isLogin;

    public void setUserState(boolean isLogin) {
        if (isLogin == isLogin)
            return;
        else
            this.isLogin = isLogin;
        setChanged();
        notifyObservers(isLogin);
    }


}
