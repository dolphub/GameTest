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
    private Direction playerDirection;
    private Animation animatingRight;
    private Animation animatingLeft;
    private long startTime;
    private final int DYA = 2; // Y acceleration
    private final int MAX_ACCERLATION = 14;

    public static enum Direction { UP, DOWN, LEFT, RIGHT, NONE }
    public Player(Bitmap res, int w, int h, int numFrames) {

        animatingRight = new Animation();
        animatingLeft = new Animation();

        x = GamePanel.WIDTH / 4;
        y = GamePanel.FLOOR - w;

        dy = 0;
        score = 0;
        height = h;
        width = w;

        Bitmap[] imagesRight = new Bitmap[numFrames];
        Bitmap[] imagesLeft = new Bitmap[numFrames];
        spritesheet = res;

        for( int i = 0; i < imagesRight.length; i++ ) {
            imagesRight[i] = Bitmap.createBitmap( spritesheet, i*width, 0, width, height );
        }

        for( int i = 0; i < imagesLeft.length; i++ ) {
            imagesLeft[i] = Bitmap.createBitmap( spritesheet, i*width, height, width, height );
        }

        animatingRight.setFrames(imagesRight);
        animatingRight.setDelay(50);

        animatingLeft.setFrames(imagesLeft);
        animatingLeft.setDelay(50);

        startTime = System.nanoTime();
    }

    public void move( Direction d ) { playerDirection = d; }
    public Direction getDirection() { return playerDirection; }

    public void update() {

        long elapsed = (System.nanoTime() - startTime) / 1000000 ;
        if( elapsed > 100 ) {
            score++;
            startTime = System.nanoTime();
        }

        switch( playerDirection ) {
            case RIGHT:
                animatingRight.update();
            case LEFT:
                animatingLeft.update();
        }

//        if( up ) {
//            dy -= DYA;
//        } else {
//            dy += DYA;
//        }
//
//        if( dy > MAX_ACCERLATION ) dy = 14;
//        if( dy < -MAX_ACCERLATION ) dy = -14;
//
//        y += dy * 2;
    }

    public  void draw( Canvas canvas ) {
        canvas.drawBitmap( animatingRight.getImage(), x, y, null );
    }
    public int getScore() {
        return this.score;
    }
    public boolean getPlaying() { return this.playing; }
    public void setPlaying( boolean b ) { this.playing = b; }
    public void resetScore() { this.score = 0; }


}
