package com.pongenib.newpong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class Triangle extends Mobile {

    public Triangle(Context context, int x, int y, int largeur, int hauteur, double orientation) {
        super(context, x, y, largeur, hauteur, orientation);
    }

    @Override
    void dessine(Canvas canvas) {
        //System.out.printf("Triangle(%d, %d)[%d, %d, %d]%n", x, y, hauteur, largeur, vitesse);

        draw(canvas);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);

        Path path = new Path();
        Point a = new Point((int) getX(), (int) getY() + getHauteur());
        Point b = new Point((int) getX() + getLargeur(), (int) getY() + getHauteur());
        Point c = new Point((int) getX() + getLargeur() / 2, (int) getY());
        path.moveTo(a.x, a.y);
        path.lineTo(b.x, b.y);
        path.lineTo(c.x, c.y);
        path.lineTo(a.x, a.y);
        canvas.drawPath(path, paint);
    }

    @Override
    public String toString() {
        return String.format("Triangle(%d, %d)[%d, %d, %d]", x, y, hauteur, largeur, vitesse);
    }
}
