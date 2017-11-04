package com.pongenib.newpong;


public abstract class Mobile extends Forme {
    protected int vitesse;
    Pong pong;

    public Mobile(int x, int y, int hauteur, int largeur, double orientation) {
        super(x, y, hauteur, largeur, orientation);
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public Pong getPong() {
        return pong;
    }

    public void setPong(Pong pong) {
        this.pong = pong;
    }

    void deplace() {
        this.setX((int) (this.x + vitesse * Math.cos(orientation)));
        this.setY((int)(this.y + vitesse * Math.sin(orientation)));
        this.pong.collision(this);
    }
}
