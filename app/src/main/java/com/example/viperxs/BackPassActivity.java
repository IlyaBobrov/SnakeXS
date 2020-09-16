package com.example.viperxs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BackPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_pass);

        Intent intent = getIntent();
        String login = intent.getStringExtra(MainActivity.LOGIN);

        TextView textLogin = new TextView(this);
        textLogin.setTextSize(40);
        textLogin.setText(login);

        setContentView(textLogin);
    }


}