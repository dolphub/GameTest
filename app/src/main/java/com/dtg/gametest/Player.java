package com.dtg.gametest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;

/**
 * Created by randy on 12/20/2015.
 */
public class Player extends GameObject {
    private Bitmap spritesheet;
    private int score;
    private boolean playing;
    private boolean up;
    private Animation animation;
    private long startTime;
    private final int DYA = 2; // Y acceleration
    private final int MAX_ACCERLATION = 14;

    public Player(Bitmap res, int w, int h, int numFrames) {

        animation = new Animation();
        x = 100;
        y = GamePanel.HEIGHT / 2 - (h / 2); // Start in center of screen
        dy = 0;
        score = 0;
        height = h;
        width = w;

        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;

        for( int i = 0; i < image.length; i++ ) {
            image[i] = Bitmap.createBitmap( spritesheet, i*width, 0, width, height );
        }

        animation.setFrames(image);
        animation.setDelay(50);
        startTime = System.nanoTime();
    }

    public void setUp(boolean b) { up = b; }

    public void update() {

        long elapsed = (System.nanoTime() - startTime) / 1000000 ;
        if( elapsed > 100 ) {
            score++;
            startTime = System.nanoTime();
        }
        animation.update();

        if( up ) {
            dy -= DYA;
        } else {
            dy += DYA;
        }

        if( dy > MAX_ACCERLATION ) dy = 14;
        if( dy < -MAX_ACCERLATION ) dy = -14;

        y += dy * 2;
    }

    public  void draw( Canvas canvas ) {
        canvas.drawBitmap( animation.getImage(), x, y, null );
    }
    public int getScore() {
        return this.score;
    }
    public boolean getPlaying() { return this.playing; }
    public void setPlaying( boolean b ) { this.playing = b; }
    public void resetScore() { this.score = 0; }


}
