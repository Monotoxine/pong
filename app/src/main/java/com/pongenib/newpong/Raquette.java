package com.pongenib.newpong;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

public class Raquette extends View {

    Paint paint = new Paint();
    public Raquette(Context context) {
        super(context);
    }
    @Override
    public void onDraw(Canvas canvas){

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        canvas.drawRect(90, 90, 90, 90, paint);


    }
}
