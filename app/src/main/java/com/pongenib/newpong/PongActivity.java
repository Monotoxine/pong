package com.pongenib.newpong;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;


public class PongActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager manager = getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = manager.getDefaultDisplay();
        display.getMetrics(metrics);
        final Pong pong = new Pong(this, metrics.widthPixels, metrics.heightPixels);
        Mur mur = new Mur(this, 50, 50, 10, 600);
        pong.add(mur);
        mur.setCoefficient(1);
        Cercle cercle = new Cercle(this, 60, 150, 30, 0.67);
        pong.add(cercle);
        cercle.setVitesse(15);

        setContentView(pong);
    }
}
