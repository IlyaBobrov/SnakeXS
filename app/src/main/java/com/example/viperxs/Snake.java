package com.example.viperxs;

import android.content.Context;

public class Snake extends GardenBody {
    public Snake(Context context) {
        bitmapId = R.drawable.snake;
        size = 2;
        y = GameView.maxY - size - 1;
        speed = (float) 0.3;

        init(context); // init snake
    }

    @Override
    void update() {
        if(MainActivity.isLeftPressed && x >= 0) {
            x -=speed;
        }
        if(MainActivity.isRightPressed && x <= GameView.maxX - size) {
            x +=speed;
        }
    }
}
