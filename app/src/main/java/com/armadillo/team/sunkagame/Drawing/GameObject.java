package com.armadillo.team.sunkagame.Drawing;

import android.graphics.Rect;

public abstract class GameObject {
    private float x;
    private float y;
    private int dy;
    private int dx;
    private int width;
    private int height;

    public boolean executeTouch(int X, int Y) {
        if (getRectangle().contains(X, Y)) {
            touchEvent();
            return true;
        }
        return false;
    }

    protected void touchEvent() {
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rect getRectangle() {
        return new Rect((int) (x - (width / 2)), (int) (y - (height / 2)), (int) (x + (width / 2)), (int) (y + (height / 2)));
    }
}
