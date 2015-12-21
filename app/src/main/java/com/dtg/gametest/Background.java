package com.dtg.gametest;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {

    private Bitmap image;
    private int x, y, dx;


    public Background(Bitmap res)
    {
        image = res;
        System.out.println("IMAGE WIDTH: " + image.getWidth() );
        System.out.println("IMAGE HEIGHT: " + image.getHeight() );
        x = 0;
        y = 0;
    }
    public void update()
    {
        x+=dx;
        if(x<-GamePanel.WIDTH){
            x=0;
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
    public void setVector(int dx)
    {
        this.dx = dx;
    }
}