package com.pongenib.newpong;


public class Mur extends Forme {

    double coefficient;

    public Mur(int x, int y, int hauteur, int largeur) {
        super(x, y, hauteur, largeur, 0);
    }

    @Override
    void dessine() {
        // TODO
        System.out.printf("Mur(%d, %d)[%d, %d]%n", x, y, hauteur, largeur);
    }
}
