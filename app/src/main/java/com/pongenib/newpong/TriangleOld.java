package com.pongenib.newpong;

/**
 * Created by user on 02/11/2017.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class TriangleOld {

    private BitmapDrawable img=null;
    private int x,y;
    private int balleW, balleH;
    private int wEcran,hEcran;
    private boolean move = true;


    private static final int INCREMENT = 5;
    private int speedX=INCREMENT, speedY=INCREMENT;


    private final Context mContext;

    private Balle balle;


    public TriangleOld(final Context c)
    {
        x=0; y=0; // position de d?part
        mContext=c;
    }

    public void setBalle(Balle balle) {
        this.balle = balle;
    }

    public BitmapDrawable setImage(final Context c, final int ressource, final int w, final int h)
    {
        Drawable dr = c.getResources().getDrawable(ressource);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        return new BitmapDrawable(c.getResources(), Bitmap.createScaledBitmap(bitmap, w, h, true));
    }


    public boolean isMoving() {
        return move;
    }


    public void setMove(boolean move) {
        this.move = move;
    }

    public void resize(int wScreen, int hScreen) {

        wEcran=wScreen;
        hEcran=hScreen;


        balleW=wScreen/10;
        balleH=wScreen/10;
        img = setImage(mContext,R.mipmap.ball,balleW,balleH);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBalleW() {
        return balleW;
    }

    public int getBalleH() {
        return balleH;
    }

    public void moveWithCollisionDetection()
    {

        if(!move) {return;}


        x+=speedX;
        y+=speedY;

        if(x+balleW > wEcran) {speedX=-INCREMENT;}


        if(y+balleH > hEcran) {speedY=-INCREMENT;}

        if(x<0) {speedX=INCREMENT;}

        if(y<0) {speedY=INCREMENT;}

        // Ajouter la collision avec la balle (en utilisant la position de la balle et sa dimension)
    }


    public void draw(Canvas canvas)
    {
        if(img==null) {return;}
        canvas.drawBitmap(img.getBitmap(), x, y, null);
    }

}
