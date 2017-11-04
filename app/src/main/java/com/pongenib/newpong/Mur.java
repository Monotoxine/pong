package com.pongenib.newpong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Mur extends Forme {

    private double coefficient = 1;

    public Mur(Context context, int x, int y, int largeur, int hauteur) {
        super(context, x, y, largeur, hauteur, 0);
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    void dessine(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(getX(), getY(), getX() + getLargeur(), getY() + getHauteur(), paint);
    }

    @Override
    public String toString() {
        return String.format("Mur(%d, %d)[%d, %d]", x, y, hauteur, largeur);
    }
}
