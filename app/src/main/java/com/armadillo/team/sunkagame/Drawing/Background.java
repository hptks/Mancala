package com.armadillo.team.sunkagame.Drawing;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {
    private Bitmap image;

    public Background(int width, int height, Bitmap image) {
        this.image = Bitmap.createScaledBitmap(image,width,height,false);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image,0,0,null);
    }
}
