package com.example.viperxs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CreateNewItemActivity extends AppCompatActivity {

    TextView h1;
    TextView p;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_item);
        h1 = findViewById(R.id.editTextH1);
        p = findViewById(R.id.editTextP);
        try {
            Bundle args = getIntent().getExtras();
            h1.setText(args.getString("h1_"));
            p.setText(args.getString("p_"));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Exception", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickCreateItem(View view) {
        create = findViewById(R.id.btnCreateItem1);
        Intent intent = new Intent();
        intent.putExtra("h1", h1.getText().toString());
        intent.putExtra("p", p.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}