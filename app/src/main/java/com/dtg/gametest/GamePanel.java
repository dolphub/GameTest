package com.dtg.gametest;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;
    public static final int MOVESPEED = -10;
    private MainThread thread;
    private Player player;
    private Background bg;

    public GamePanel(Context context)
    {
        super(context);
        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry)
        {
            try{
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

        // Scaled options for images are causing incorrect pixel resolution
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inScaled = false;
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.rsz_bg_image, o));
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.pilg_sprites, o), 108, 140, 8);

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if( event.getAction() == MotionEvent.ACTION_DOWN ) {
            if( !player.getPlaying()) {
                player.setPlaying(true);
            } else {
                System.out.println("++++++++++++++++++++");
                player.setUp(true);
            }
            return true;
        }

        if( event.getAction() == MotionEvent.ACTION_UP ) {
            System.out.println("-------------------");
            player.setUp(false);
            return true;
        }

        return super.onTouchEvent(event);
    }

    public void update()
    {
        if( player.getPlaying() ) {
            player.update();
            bg.update();
        }
    }
    @Override
    public void draw(Canvas canvas)
    {
        final float scaleFactorX = getWidth() / (WIDTH * 1.0f);
        final float scaleFactorY = getHeight() / (HEIGHT * 1.0f);
//        System.out.println("Width: " + getWidth() + " " + scaleFactorX +  "Height: " + getHeight() + " " + scaleFactorY );

        if(canvas!=null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
//            System.out.println("Canvas X: " + canvas.getWidth());
            bg.draw(canvas);
            player.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }

}