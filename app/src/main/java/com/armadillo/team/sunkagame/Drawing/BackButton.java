package com.armadillo.team.sunkagame.Drawing;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class BackButton extends GameObject {
    private Paint buttonPaint = new Paint();
    private Paint textPaint = new Paint();
    public boolean closeWindow = false;
    private Bitmap image;

    public BackButton(int x, int y, Bitmap img) {
        setWidth(150);
        setHeight(75);
        setX(x);
        setY(y);
        this.image=Bitmap.createScaledBitmap(img,getWidth(),getHeight(),false);

        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(32);
        textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
    }

    @Override
    protected void touchEvent() {
        super.touchEvent();
        buttonPaint.setColor(Color.BLUE);
        closeWindow = true;
    }

    public void draw(Canvas canvas) {

        canvas.drawBitmap(image,null,getRectangle(),buttonPaint);
        canvas.drawText("Back",getX()-(getWidth()/4),getY()+(getHeight()/4),textPaint);
    }
}