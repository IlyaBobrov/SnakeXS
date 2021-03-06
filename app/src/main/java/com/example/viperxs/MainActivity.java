package com.example.viperxs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import android.widget.LinearLayout;
import android.view.MotionEvent;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity /*implements View.OnTouchListener*/ {

    public final static String LOGIN = "LOGIN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    public void onClickRegistr(View view) {
        Intent intent = new Intent(this, RegistrActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextLogin);
        String login = editText.getText().toString();
        intent.putExtra(LOGIN, login);
        startActivity(intent);
    }


    public void onClickInput(View view) {

        Intent intentInput = new Intent(this, NewsActivity.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Ошибка ввода данных").setTitle("Упс");
        AlertDialog dialog = builder.create();

        EditText editLogin = (EditText) findViewById(R.id.editTextLogin);
        EditText editPass = (EditText) findViewById(R.id.editTextPass);

        editLogin.setText("Default");
        editPass.setText("Default");

        if (CheckData(editLogin,editPass)) {

            User user = new User(editLogin.getText().toString(),"Empty","Empty", editPass.getText().toString());
            intentInput.putExtra(User.class.getSimpleName(), user);

            startActivity(intentInput);
            this.finish();
        } else {
            dialog.show();
            return;
        }
    }

    private boolean CheckData(EditText editLogin, EditText editPass) {
        return true;
    }

    public void onClickBackPass(View view) {
        Intent intent = new Intent(this, BackPassActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextLogin);
        String login = editText.getText().toString();
        intent.putExtra(LOGIN, login);
        startActivity(intent);
    }

    public void onClickSicretInput(View view) {
        Intent intentInput = new Intent(this, NewsActivity.class);
        startActivity(intentInput);
        this.finish();
    }
}