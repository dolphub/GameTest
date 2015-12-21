package com.dtg.gametest;

import android.graphics.Rect;

/**
 * Created by randy on 12/20/2015.
 */
public abstract class GameObject {
    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
    protected int width;
    protected int height;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getHeight() {
        return this.height;
    }
    public int getWidth() {
        return this.width;
    }

    public Rect getRectangle() {
        return new Rect(x, y, x+width, y+height);
    }



}
