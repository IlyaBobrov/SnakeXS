package com.example.viperxs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    public static boolean isLeftPressed = false, isRightPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameView gameView = new GameView(this);
        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.gameLayout);
        gameLayout.addView(gameView);

        Button leftBtn = (Button) findViewById(R.id.btnLeft);
        Button rightBtn = (Button) findViewById(R.id.btnRight);

        leftBtn.setOnTouchListener(this);
        rightBtn.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View button, MotionEvent motionEvent) {
        switch (button.getId()){
            case R.id.btnLeft:
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        isLeftPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isLeftPressed = false;
                        break;
                }
                break;
            case R.id.btnRight:
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        isRightPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isRightPressed = false;
                        break;
                }
                break;
        }
        return false;
    }
}