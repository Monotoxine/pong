package com.pongenib.newpong;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by user on 04/11/2017.
 */
public class PongThread extends Thread {
    private Pong pong;
    private boolean stop = false;

    public PongThread(Pong pong) {
        this.pong = pong;
    }

    @Override
    public void run() {
        while (!stop) {
            SurfaceHolder holder = pong.getHolder();
            Canvas canvas = holder.lockCanvas();
            if (canvas == null || !holder.getSurface().isValid())
                continue;
            pong.execute();
            try {
                pong.draw(canvas);
            } finally {
                holder.unlockCanvasAndPost(canvas);
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
