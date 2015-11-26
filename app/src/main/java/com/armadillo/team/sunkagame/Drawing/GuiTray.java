package com.armadillo.team.sunkagame.Drawing;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.armadillo.team.sunkagame.Backend.Tray;

import java.util.ArrayList;

public class GuiTray extends Tray {
    private Paint paint;
    private Bitmap image;
    public ArrayList<Shell> shells = new ArrayList<>(98);
    public float scoreY;

    public GuiTray(int x, int y,Bitmap image) {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(35);
        setX(x);
        setY(y);
        setWidth(100);
        setHeight(100);
        this.image = Bitmap.createScaledBitmap(image, getWidth(), getHeight(), false);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, null, getRectangle(), null);
        canvas.drawText(Integer.toString(this.shells.size()), getX(), scoreY, paint);
    }
}
