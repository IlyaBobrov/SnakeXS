package com.example.viperxs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

    final String SAVED_TEXT_H1 = "saved_text_h1";
    final String SAVED_TEXT_P = "saved_text_p";
    SharedPreferences lastNotSaveItem;

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
        //восстановление данных
        //TODO: сделать умный механизм восстановления данных item
        try {
            if (getPreferences(MODE_PRIVATE) != null) {
                if (!getPreferences(MODE_PRIVATE).getString(SAVED_TEXT_H1, "").equals("") ||
                        !getPreferences(MODE_PRIVATE).getString(SAVED_TEXT_P, "").equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Востановить предыдущий комменатрий?").setTitle("Buffer");
                    builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            loadBufferItem();
                        }
                    }).setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) { }});
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            } else {
                Toast.makeText(this, "null", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e){
            Toast.makeText(this, "Ошибка: code 1", Toast.LENGTH_LONG).show();
        }
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

    //Сохранение и добавление item в список
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
            Toast.makeText(ViewDetailItemActivity.this, "Без изменений", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (!textH1.getText().toString().isEmpty() || !textP.getText().toString().isEmpty()) {
                saveBufferItem();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка сохранения в буфер: code 2", Toast.LENGTH_LONG).show();
        }

    }

    public void onClickSaveComment(View view) {
    Intent intent = new Intent();
    intent.putExtra("date", "date");
    intent.putExtra("h1", textH1.getText().toString());
    intent.putExtra("p", textP.getText().toString());
    setResult(RESULT_OK, intent);
    finish();
    }

    private void saveBufferItem() {
        lastNotSaveItem = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = lastNotSaveItem.edit();
        editor.putString(SAVED_TEXT_H1, textH1.getText().toString());
        editor.putString(SAVED_TEXT_P, textP.getText().toString());
        editor.apply();
        Toast.makeText(this, "Save in buffer", Toast.LENGTH_SHORT).show();
    }
    private void loadBufferItem() {
        lastNotSaveItem = getPreferences(MODE_PRIVATE);
        textH1.setText(lastNotSaveItem.getString(SAVED_TEXT_H1, ""));
        textP.setText(lastNotSaveItem.getString(SAVED_TEXT_P, ""));
        Toast.makeText(this, "Restore from buffer", Toast.LENGTH_SHORT).show();
    }
}

