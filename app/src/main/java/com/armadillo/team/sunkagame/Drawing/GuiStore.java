package com.armadillo.team.sunkagame.Drawing;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.armadillo.team.sunkagame.Backend.Store;

import java.util.ArrayList;

public class GuiStore extends Store {
    private Paint paint;
    private Bitmap image;
    public ArrayList<Shell> shells = new ArrayList<>(98);
    public float scoreY;

    public GuiStore(int x, int y, Bitmap image) {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(35);
        setX(x);
        setY(y);
        setWidth(150);
        setHeight(300);
        this.image = Bitmap.createScaledBitmap(image, getWidth(), getHeight(), false);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, null, getRectangle(), null);
        canvas.drawText(Integer.toString(this.shells.size()), getX(), scoreY, paint);
    }
}
