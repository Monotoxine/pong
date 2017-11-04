package com.pongenib.newpong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by user on 04/11/2017.
 */
public class Cercle extends Mobile {
    public Cercle(Context context, int x, int y, int cote, double orientation) {
        super(context, x, y, cote, cote, orientation);
    }

    @Override
    void dessine(Canvas canvas) {
        draw(canvas);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
//        Drawable dr = getContext().getResources().getDrawable(R.mipmap.ball);
//        assert dr != null;
//        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
//        canvas.drawBitmap(new BitmapDrawable(getContext().getResources(),
//                Bitmap.createScaledBitmap(bitmap, getLargeur(), getHauteur(), true)).getBitmap(), x, y, null);

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getX() + getLargeur() / 2, getY() + getHauteur() / 2, getHauteur(), paint);
    }

    @Override
    public String toString() {
        return String.format("Cercle(%d, %d)[%d, %d, %d]", x, y, hauteur, largeur, vitesse);
    }
}
