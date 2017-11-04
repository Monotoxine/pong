package com.pongenib.newpong;


import java.util.ArrayList;
import java.util.List;

public class Pong extends Thread {
    int width;
    int height;
    private final List<Forme> formes = new ArrayList<>();
    private boolean stop = false;

    public Pong(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void run() {
        execute();
    }

    void execute() {
        while (!stop) {
            for (Forme forme : this.formes) {
                if (forme instanceof Mobile) {
                    ((Mobile) forme).deplace();
                }
                forme.dessine();
            }
            try {
                wait(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    void collision(Mobile mobile) {
        int x = mobile.getX();
        int y = mobile.getY();
        int largeur = mobile.getLargeur();
        int hauteur = mobile.getHauteur();
        double orientation = mobile.getOrientation();
        if(x <= 0 || x >= width - largeur) {
            mobile.setOrientation(Math.PI - orientation);
        }
        if(y <= 0 || y >= height - hauteur) {
            mobile.setOrientation(-orientation);
        }
    }

    public void add(Forme forme) {
        this.formes.add(forme);
    }

    public void remove(Forme forme) {
        this.formes.remove(forme);
    }
}
