package me.linkcube.skea.core;

/**
 * Created by Ervin on 14/11/13.
 */
public class InitManager {

    private static InitManager instance;

    public InitManager getInstance() {
        if (instance == null)
            instance = new InitManager();
        return instance;
    }

    private InitManager() {

    }

}
