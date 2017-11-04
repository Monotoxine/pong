package com.pongenib.newpong;

/**
 * Created by user on 04/11/2017.
 */
public class Cercle extends Mobile {

    public Cercle(int x, int y, int hauteur, int largeur, double orientation) {
        super(x, y, hauteur, largeur, orientation);
    }

    @Override
    void dessine() {
        System.out.printf("Cercle(%d, %d)[%d, %d, %d]%n", x, y, hauteur, largeur, vitesse);
    }

    @Override
    public String toString() {
        return String.format("Cercle(%d, %d)[%d, %d, %d]", x, y, hauteur, largeur, vitesse);
    }
}
