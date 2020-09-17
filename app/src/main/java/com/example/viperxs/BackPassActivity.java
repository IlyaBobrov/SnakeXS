package com.example.viperxs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BackPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_pass);

        Intent intent = getIntent();
        String login = intent.getStringExtra(MainActivity.LOGIN);
        EditText editText = findViewById(R.id.editFieldForBackUpPass);
        editText.setText(login);

    }


    public void onBackUp(View view) {
        EditText editText = findViewById(R.id.editFieldForBackUpPass);
        TextView textView = findViewById(R.id.textNameForBackUpPass);
        if (editText.getText().toString().equals("")) {
            textView.setTextColor(Color.parseColor("#ff0000"));
            return;
        } else {
            textView.setTextColor(Color.parseColor("#000000"));
        }
        CheckBox checkBox = findViewById(R.id.checkBoxForBackUpPass);
        if (!checkBox.isChecked()) {
            checkBox.setTextColor(Color.parseColor("#ff0000"));
            return;
        } else {
            checkBox.setTextColor(Color.parseColor("#000000"));
        }
        /*код проверки в бд*/
        /*код отправки пароля на почту*/

        Toast toast = Toast.makeText(getApplicationContext(), "Новый пароль был отправлен на почту", Toast.LENGTH_LONG);
        toast.show();
        this.finish();
    }
}