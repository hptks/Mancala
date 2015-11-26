package com.armadillo.team.sunkagame.Drawing;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class Shell extends GameObject {
    private Bitmap image;

    public synchronized void setTrayLocationSound() {
        Sounds.playSound(0);
    }

    public synchronized void moveSound(){
        Sounds.playSound(1);
    }

    public Shell(float randX, float randY, GuiTray trayLocation,Bitmap image) {
        setWidth(15);
        setHeight(15);

        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        random.nextFloat();
        float RandX = ((random.nextFloat()*randX) - (randX/2));
        float RandY = ((random.nextFloat()*randY) - (randY/2));

        System.out.println(RandX + "   " +RandY);
        setX(trayLocation.getX() + RandX);
        setY(trayLocation.getY() + RandY);
        this.image = Bitmap.createScaledBitmap(image,getWidth(),getHeight(),false);
    }

    @Override
    protected void touchEvent() {
        super.touchEvent();
    }

    public void update() {
        setX(getX() + getDx());
        setY(getY() + getDy());
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image,null,getRectangle(),null);
    }
}
