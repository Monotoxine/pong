package com.pongenib.newpong;

/**
 * Created by user on 02/11/2017.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoopThread gameLoopThread;
    private Balle balle;
    private Triangle triangle;
    private Raquette raquette;
    // cr?ation de la surface de dessin
    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        gameLoopThread = new GameLoopThread(this);

        // cr?ation d'un objet "balle" et autres

        balle = new Balle(this.getContext());
        triangle=new Triangle(this.getContext());

        balle.setTriangle(triangle); // Faire connaitre le triangle ? la balle
        triangle.setBalle(balle); // Faire connaitre la balle au triangle

        raquette=new Raquette(this.getContext());
    }


    public void doDraw(Canvas canvas) {
        if(canvas==null) {return;}

        canvas.drawColor(Color.BLACK);

        // on dessine la balle
        balle.draw(canvas);
        triangle.draw(canvas);
        raquette.draw(canvas);
    }


    public void update() {
        balle.moveWithCollisionDetection();
        triangle.moveWithCollisionDetection();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if(gameLoopThread.getState()==Thread.State.TERMINATED) {
            gameLoopThread=new GameLoopThread(this);
        }
        gameLoopThread.setRunning(true);
        gameLoopThread.start();
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        gameLoopThread.setRunning(false);
        while (retry) {
            try {
                gameLoopThread.join();
                retry = false;
            }
            catch (InterruptedException e) {}
        }
    }

    // G?re les touch?s sur l'?cran
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int currentX = (int)event.getX();
        int currentY = (int)event.getY();

        switch (event.getAction()) {

            // code ex?cut? lorsque le doigt touche l'?cran.
            case MotionEvent.ACTION_DOWN:
                // si le doigt touche la balle :
                if(currentX >= balle.getX() &&
                        currentX <= balle.getX()+balle.getBalleW() &&
                        currentY >= balle.getY() && currentY <= balle.getY()+balle.getBalleH() ) {
                    // on arr?te de d?placer la balle
                    balle.setMove(false);
                }
                break;


            case MotionEvent.ACTION_MOVE:

                if(!balle.isMoving()) {
                    balle.setX(currentX);
                    balle.setY(currentY);
                }
                break;


            case MotionEvent.ACTION_UP:
                balle.setMove(true);
        }

        return true;
    }


    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int w, int h) {
        balle.resize(w,h);
        triangle.resize(w,h);
    }
}