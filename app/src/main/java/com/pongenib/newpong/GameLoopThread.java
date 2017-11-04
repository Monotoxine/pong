package com.pongenib.newpong;

/**
 * Created by user on 02/11/2017.
 */

import android.graphics.Canvas;

public class GameLoopThread extends Thread {
    // on définit arbitrairement le nombre d'images par secondes à 30
    private final static int FRAMES_PER_SECOND = 30;


    private final static int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;

    private final GameView view;
    private boolean running = false;


    public GameLoopThread(GameView view) {
        this.view = view;
    }


    public void setRunning(boolean run) {
        running = run;
    }

    // démarrage du thread
    @Override
    public void run() {

        long startTime;
        long sleepTime;


        while (running) {

            startTime = System.currentTimeMillis();


            synchronized (view.getHolder()) {
                view.update();
            }


            Canvas c = null;
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    view.doDraw(c);
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }


            sleepTime = SKIP_TICKS - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime >= 0) {
                    sleep(sleepTime);
                }
            } catch (Exception e) {
            }
        }
    }
}
