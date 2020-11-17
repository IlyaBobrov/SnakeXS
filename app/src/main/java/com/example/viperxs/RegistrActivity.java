package com.example.viperxs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrActivity extends AppCompatActivity {

    User newUser;

    EditText[] fieldsEditText;
    TextView[] fieldsTextView;

    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    ContentValues contentValues;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr);

        fieldsEditText = new EditText[] {
                findViewById(R.id.editName), //0
                findViewById(R.id.editEmail),//1
                findViewById(R.id.editPhone),//2
                findViewById(R.id.editPass1),//3
                findViewById(R.id.editPass2) //4
        };
        fieldsTextView = new TextView[] {
                findViewById(R.id.textName), //0
                findViewById(R.id.textEmail),//1
                findViewById(R.id.textPhone),//2
                findViewById(R.id.textPass1),//3
                findViewById(R.id.textPass2) //4
        };

        dbHelper = new DatabaseHelper(this);

        fieldsEditText[0].setText("MyLogin");
        fieldsEditText[1].setText("MyEmail");
        fieldsEditText[2].setText("89123456789");
        fieldsEditText[3].setText("123");
        fieldsEditText[4].setText("123");
    }

    boolean flagValidField = true;
    
    public void onClickRegistration(View view) {
        checkEmptyFiled(fieldsEditText, fieldsTextView);
        checkValidEmail(fieldsEditText, fieldsTextView);
        checkValidPhone(fieldsEditText, fieldsTextView);
        checkValidPass(fieldsEditText, fieldsTextView);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.create();

        //если все поля заполнены корректно, проверяем в бд
        if (flagValidField) {
            builder.setMessage(R.string.error_input_data).setTitle(R.string.error).create().show();
        } else {
            //открыть и вернуть экземпляр базы данных
            db = dbHelper.getWritableDatabase();
            //класс для добавления новых строк в таблицу
            contentValues = new ContentValues();

            //если в бд нет совпадений, сохраняем пользователя
            dbCheckUser(fieldsEditText, fieldsTextView);

            //TODO: Переделать механизм проверки
            if (flagValidField) {
                builder.setMessage("Такой пользователь уже существет").setTitle("Упс").create().show();
            } else {
                dbAddUser(fieldsEditText);

                newUser = new User(
                        fieldsEditText[0].getText().toString(),
                        fieldsEditText[1].getText().toString(),
                        fieldsEditText[2].getText().toString(),
                        fieldsEditText[3].getText().toString() );

                Toast.makeText(getApplicationContext(), "Вы успешно зарегестрировались!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, NewsActivity.class);
                intent.putExtra(User.class.getSimpleName(), newUser);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);   //отчиска стека активити в тасках
                startActivity(intent);
                this.finish();
            }
        }
    }

    private void dbAddUser(EditText[] editTexts) {
        contentValues.put(DatabaseHelper.KEY_LOGIN, editTexts[0].getText().toString());
        contentValues.put(DatabaseHelper.KEY_EMAIL, editTexts[1].getText().toString());
        contentValues.put(DatabaseHelper.KEY_PHONE, editTexts[3].getText().toString());
        contentValues.put(DatabaseHelper.KEY_PASS, editTexts[4].getText().toString());
        db.insert(DatabaseHelper.DB_NAME, null, contentValues);
    }

    private void dbCheckUser(EditText[] editTexts, TextView[] textViews) {
        cursor = db.query(DatabaseHelper.TABLE_USERS, null, null, null, null,null,null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DatabaseHelper.KEY_ID);
            int loginIndex = cursor.getColumnIndex(DatabaseHelper.KEY_LOGIN);
            int emailIndex = cursor.getColumnIndex(DatabaseHelper.KEY_EMAIL);
            int phoneIndex = cursor.getColumnIndex(DatabaseHelper.KEY_PHONE);
            int passIndex = cursor.getColumnIndex(DatabaseHelper.KEY_PASS);
//            db.query(DatabaseHelper.TABLE_USERS, DatabaseHelper.KEY_LOGIN, );
            //TODO: поиск записи в базе...
            //...

        } else {
            Log.d("logs", "0 строк");
        }
        flagValidField = false;
    }

    private void checkEmptyFiled(EditText[] editTexts, TextView[] textViews) {
        for (int i = 0; i < editTexts.length; i++) {
            if (editTexts[i].getText().toString().isEmpty()) {
                redText(textViews[i]);
                flagValidField = true;
            } else {
                normalText(textViews[i]);
            }
        }
    }
    
    private void redText(TextView textView) {
        textView.setTextColor(Color.parseColor("#ff0000"));
        flagValidField = true;
    }

    private void normalText(TextView textView) {
        textView.setTextColor(Color.parseColor("#000000"));
        flagValidField = false;
    }

    private void checkValidEmail(EditText[] editTexts, TextView[] textViews) {
        if (!editTexts[1].getText().toString().matches("(\\w*@\\w*.\\w*)")) {
            redText(textViews[1]);
        } else {
            normalText(textViews[1]);
        }
    }

    private void checkValidPhone(EditText[] editTexts, TextView[] textViews) {
        if (!editTexts[2].getText().toString().matches("(\\+*)\\d{11}")) {
            redText(textViews[2]);
        } else {
            normalText(textViews[2]);
        }
    }
    
    private void checkValidPass(EditText[] editTexts, TextView[] textViews) {
        if (!editTexts[3].getText().toString().equals(editTexts[4].getText().toString()) || editTexts[4].getText().toString().isEmpty()) {
            redText(textViews[3]);
            redText(textViews[4]);
        } else {
            normalText(textViews[3]);
            normalText(textViews[4]);
        }
    }




}