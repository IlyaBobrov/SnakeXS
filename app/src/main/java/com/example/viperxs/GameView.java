package com.example.viperxs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    public GameView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();

        gameThread = new Thread(this);
        gameThread.start();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public static int maxX = 20;
    public static int maxY = 30;
    public static float unitW = 0;
    public static float unitH = 0;

    private boolean firstTime = true;
    private boolean gameRunning  = true;
    private Snake snake;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;


    @Override
    public void run() {
        while (gameRunning) {
            update();
            draw();
            control();
        }
    }

    private void update() {
        if(!firstTime) {
            snake.update();
        }
    }


    private void draw() {
        if(surfaceHolder.getSurface().isValid()){//проверяем валидый ли surface

            if(firstTime) { //init first
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width()/maxX;
                unitH = surfaceHolder.getSurfaceFrame().height()/maxY;
                snake = new Snake(getContext());
            }
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            snake._draw(paint, canvas);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control(){
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
