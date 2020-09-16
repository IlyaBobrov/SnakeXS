package com.example.viperxs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import android.widget.LinearLayout;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity /*implements View.OnTouchListener*/ {

    //public static boolean isLeftPressed = false, isRightPressed = false;

    public final static String LOGIN = "LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        /*GameView gameView = new GameView(this);
        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.gameLayout);
        gameLayout.addView(gameView);

        Button leftBtn = (Button) findViewById(R.id.btnLeft);
        Button rightBtn = (Button) findViewById(R.id.btnRight);

        leftBtn.setOnTouchListener(this);
        rightBtn.setOnTouchListener(this);*/
    }

    /*@Override
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
    }*/

    public void onClickRegistr(View view) {
        Intent intent = new Intent(this, RegistrActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextLogin);
        String login = editText.getText().toString();
        intent.putExtra("LOGIN", login);
        startActivity(intent);
    }

    public void onClickInput(View view) {
        TextView errorView = (TextView) findViewById(R.id.textErrorView);
        errorView.setText("");
        Intent intent = new Intent(this, AppActivity.class);
        EditText editLogin = (EditText) findViewById(R.id.editTextLogin);
        EditText editPass = (EditText) findViewById(R.id.editTextPass);
        if (CheckData(editLogin,editPass)) {
            startActivity(intent);
        } else {
            errorView.setText("Ошибка ввода данных!");
        }
    }

    private boolean CheckData(EditText editLogin, EditText editPass) {
        return false;
    }

    public void onClickBackPass(View view) {
        Intent intent = new Intent(this, BackPassActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextLogin);
        String login = editText.getText().toString();
        intent.putExtra(LOGIN, login);
        startActivity(intent);
    }
}