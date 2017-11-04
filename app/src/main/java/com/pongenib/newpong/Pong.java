package com.pongenib.newpong;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class Pong extends SurfaceView implements SurfaceHolder.Callback {
    int width;
    int height;
    private final List<Forme> formes = new ArrayList<>();
    private boolean stop = false;
    PongThread thread;

    public Pong(Context context, int width, int height) {
        super(context);
        this.width = width;
        this.height = height;
        getHolder().addCallback(this);
    }

    synchronized void execute() {
        for (Forme forme : this.formes) {
            if (forme instanceof Mobile) {
                Mobile mobile = (Mobile) forme;
                if (mobile.anime()) {
                    mobile.deplace();
                }
            }
        }
    }

    @Override
    public synchronized void draw(Canvas canvas) {
        super.draw(canvas);
        for (Forme forme : this.formes) {
            forme.dessine(canvas);
        }
    }

    void collision(Mobile mobile) {
        float x = mobile.getX();
        float y = mobile.getY();
        int largeur = mobile.getLargeur();
        int hauteur = mobile.getHauteur();
        double orientation = mobile.getOrientation();
        if (x <= 0 || x >= width - largeur) {
            mobile.setOrientation(Math.PI - orientation);
        }
        if (y <= 0 || y >= height - hauteur) {
            mobile.setOrientation(-orientation);
        }
        verifierCollision(mobile);
    }

    private void verifierCollision(Mobile mobile) {
        double orientation = mobile.getOrientation();
        int vitesse = mobile.getVitesse();
        for (Forme forme : formes) {
            if (forme.equals(mobile)) {
                continue;
            }
            int direction = intersect(mobile, forme);
            switch (direction) {
                case 1:
                case 2:
                    if (forme instanceof Mur) {
                        mobile.setVitesse((int) Math.floor(((Mur) forme).getCoefficient() * vitesse));
                    }
                    mobile.setOrientation(-orientation);
                    System.err.printf("Collision entre %d: %s et %s%n", direction, mobile, forme);
                    break;
                case 3:
                case 4:
                    if (forme instanceof Mur) {
                        mobile.setVitesse((int) Math.floor(((Mur) forme).getCoefficient() * vitesse));
                    }
                    mobile.setOrientation(Math.PI - orientation);
                    System.err.printf("Collision entre %d: %s et %s%n", direction, mobile, forme);
                    break;
                default:
            }
        }
    }

    private int intersect(Forme formeOne, Forme formeTwo) {
        boolean inter = formeOne.getRect().intersect(formeTwo.getRect());
        if (!inter) {
            return 0;
        }
        if (formeTwo instanceof Mur) {
            if (formeOne.getY() < formeTwo.getY()) {
                return 1;
            }
            if (formeOne.getY() + formeOne.getHauteur() >= formeTwo.getY() + formeTwo.getHauteur()) {
                return 2;
            }
            if (formeOne.getX() < formeTwo.getX()) {
                return 3;
            }
            if (formeOne.getX() + formeOne.getLargeur() >= formeTwo.getX() + formeTwo.getLargeur()) {
                return 4;
            }
        }
        float x = formeOne.getX();
        float y = formeOne.getY();
        if (y <= formeTwo.getY())
            return 1;
        if (y <= formeTwo.getY() + formeTwo.getHauteur())
            return 2;
        if (x <= formeTwo.getX())
            return 3;
        return 4;
    }

    public synchronized void add(Forme forme) {
        this.formes.add(forme);
        forme.setPong(this);
    }

    public synchronized void remove(Forme forme) {
        this.formes.remove(forme);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.stop = false;
        this.thread = new PongThread(this);
        this.thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        System.err.println("Destroying...");
        if (this.thread != null) {
            this.stop = true;
            this.thread.interrupt();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                double seed = Math.random();
                int x = (int) event.getX();
                int y = (int) event.getY();
                if (seed < 1. / 3) {
                    Triangle triangle = new Triangle(getContext(), x, y, 30, 30, seed);
                    add(triangle);
                    triangle.setVitesse(10);
                } else if (seed < 2. / 3) {
                    Cercle cercle = new Cercle(getContext(), x, y, 30, 0.67);
                    add(cercle);
                    cercle.setVitesse(10);
                } else {
                    double random = Math.random();
                    Mur mur = new Mur(getContext(), x, y, random < 0.5 ? 200 : 10, random >= 0.5 ? 200 : 10);
                    add(mur);
                    mur.setCoefficient(1);
                }
                return true;
            default:
                return false;
        }
    }

}
