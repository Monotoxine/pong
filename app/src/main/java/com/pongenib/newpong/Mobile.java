package com.pongenib.newpong;

import android.content.Context;

public abstract class Mobile extends Forme {
    protected int vitesse;

    public Mobile(Context context, int x, int y, int hauteur, int largeur, double orientation) {
        super(context, x, y, hauteur, largeur, orientation);
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    void deplace() {
        if (anime()) {
            this.setX(Math.max(0, (int) Math.floor(this.x + vitesse * Math.cos(orientation))));
            this.setY(Math.max(0, (int) Math.floor(this.y + vitesse * Math.sin(orientation))));
            this.pong.collision(this);
        }
    }

    public boolean anime() {
        return this.vitesse > 0;
    }
}
