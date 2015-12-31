package com.dtg.gametest;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {

    private Bitmap image;
    private int x, y, dx;


    public Background(Bitmap res)
    {
        image = res;
        dx = GamePanel.MOVESPEED;
    }
    public void update(Player.Direction direction)
    {
        switch( direction ) {
            case RIGHT:
                x += dx;
                if( x < -GamePanel.WIDTH ) {
                    x = 0;
                }
                break;
            case LEFT:
                x -= dx;
                if( x >  GamePanel.WIDTH ) {
                    x = GamePanel.WIDTH;
                }
                break;
        }
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(image, x, y, null);
        if(x<0)
        {
            canvas.drawBitmap(image, x+GamePanel.WIDTH, y, null);
        }
    }

}