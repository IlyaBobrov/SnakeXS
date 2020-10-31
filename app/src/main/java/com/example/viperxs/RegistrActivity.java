package com.example.viperxs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrActivity extends AppCompatActivity {

    User newUser;

    EditText[] fieldsEditText;
    TextView[] fieldsTextView;
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

        Toast toast;
        //если все поля заполнены корректно, проверяем в бд
        if (flagValidField) {
            builder.setMessage(R.string.error_input_data).setTitle(R.string.error).create().show();

        } else {
            checkDataInDB(fieldsEditText, fieldsTextView);
            //если в бд нет совпадений, сохраняем пользователя
            //TODO: Переделать механизм проверки
            if (flagValidField) {
                dialog = builder.setMessage("Такой пользователь уже существет").setTitle("Упс").create();
                dialog.show();
            } else {

                newUser = new User(
                        fieldsEditText[0].getText().toString(),
                        fieldsEditText[1].getText().toString(),
                        fieldsEditText[2].getText().toString(),
                        fieldsEditText[3].getText().toString() );

                toast = Toast.makeText(getApplicationContext(), "Вы успешно зарегестрировались!", Toast.LENGTH_LONG);
                toast.show();

                Intent intent = new Intent(this, NewsActivity.class);
                intent.putExtra(User.class.getSimpleName(), newUser);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                this.finish();

            }
        }
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

    private void checkDataInDB(EditText[] editTexts, TextView[] textViews) {
        /*ищем схожие записи в бд логин, почта и номер телефона*/

        /*if (editTexts[0].getText().toString().equals("1")) {
            flagValidField = false;
        } else {
            flagValidField = true;
        }*/
        flagValidField = false;
    }

    private void setDataInDB(EditText[] editTexts) {
        /*сохраняем данные с полей в базу данных*/
    }

}