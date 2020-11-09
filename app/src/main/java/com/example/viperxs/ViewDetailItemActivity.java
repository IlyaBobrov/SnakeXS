package com.example.viperxs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class ViewDetailItemActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    final static int REQUEST_CODE_COMMENT = 1;

    TextView textDay;
    Button btnDate;
    Button btnComment;
    TextView textH1;
    TextView textP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail_item);
        textDay = (TextView)findViewById(R.id.textView1);
        btnDate = (Button) findViewById(R.id.button2);
        btnComment = findViewById(R.id.buttonCreateComment);

        textH1 = (TextView) findViewById(R.id.textViewCommentH1);
        textP = (TextView) findViewById(R.id.textViewCommentP);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        textDay.setText(currentDateString);
    }


    public void onClickAddComment(View view) {
        Intent intent = new Intent(this, CreateNewItemActivity.class);
        intent.putExtra("h1_", textH1.getText().toString());
        intent.putExtra("p_", textP.getText().toString());
        startActivityForResult(intent, REQUEST_CODE_COMMENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_CODE_COMMENT) {
                String h1 = data.getStringExtra("h1");
                String p = data.getStringExtra("p");
                textH1.setText(h1);
                textP.setText(p);
            }
        } else {
            Toast.makeText(ViewDetailItemActivity.this, "Изменения не сохранены", Toast.LENGTH_SHORT).show();
        }
    }
}

