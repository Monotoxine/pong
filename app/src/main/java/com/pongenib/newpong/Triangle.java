package com.pongenib.newpong;


public class Triangle extends Mobile {

    public Triangle(int x, int y, int hauteur, int largeur, double orientation) {
        super(x, y, hauteur, largeur, orientation);
    }

    @Override
    void dessine() {
        System.out.printf("Triangle(%d, %d)[%d, %d]%n", x, y, hauteur, largeur);
    }
}
