package com.pongenib.newpong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

public abstract class Forme extends View {
    protected int x;
    protected int y;
    protected int hauteur;
    protected int largeur;
    protected double orientation;

    protected Pong pong;

    public Forme(Context context, int x, int y, int largeur, int hauteur, double orientation) {
        super(context);
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.orientation = orientation;
    }

    abstract void dessine(Canvas canvas);

    public Pong getPong() {
        return pong;
    }

    public void setPong(Pong pong) {
        this.pong = pong;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    public Rect getRect() {
        Rect rect = new Rect();
        rect.set((int) getX(), (int) getY(), (int) getX() + getLargeur(), (int) getY() + getHauteur());
        return rect;
    }
}
