package me.linkcube.skea.ui.init;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import me.linkcube.skea.R;
import me.linkcube.skea.ui.MainActivity;

public class SplashActivity extends Activity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this, MainActivity.class));
    }
}
