package com.example.viperxs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CalculateActivity extends AppCompatActivity {

    private EditText etAmount;
    private EditText etPeople;
    private EditText etTipOther;

    private RadioGroup radioGroupTips;

    private Button btnReset;
    private Button btnCalc;

    private TextView textTipAmount;
    private TextView textTipToPay;
    private TextView textTotalPer;

    private int radioClickedId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        etAmount = findViewById(R.id.calc_et1);
        etAmount.requestFocus();
        etPeople = findViewById(R.id.calc_et2);
        etTipOther = findViewById(R.id.calc_et3);
        etTipOther.setEnabled(false);

        radioGroupTips = findViewById(R.id.calc_rbGroup);

        btnReset = findViewById(R.id.calc_btn1);
        btnCalc = findViewById(R.id.calc_btn2);
        btnCalc.setEnabled(false);

        textTipAmount = findViewById(R.id.calc_tv_out1);
        textTipToPay = findViewById(R.id.calc_tv_out2);
        textTotalPer = findViewById(R.id.calc_tv_out3);

        radioGroupTips.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.calc_rb1 || checkedId == R.id.calc_rb2 || checkedId == R.id.calc_rb3) {
                    etTipOther.setEnabled(false);
                    btnCalc.setEnabled(etAmount.getText().length() > 0
                            && etPeople.getText().length() > 0);
                }
                if (checkedId == R.id.calc_rb4) {
                    etTipOther.setEnabled(true);
                    etTipOther.requestFocus();
                    btnCalc.setEnabled(etAmount.getText().length() > 0
                            && etPeople.getText().length() > 0
                            && etTipOther.getText().length() > 0);
                }
                radioClickedId = checkedId;
            }
        });

        etAmount.setOnKeyListener(mKeyListener);
        etPeople.setOnKeyListener(mKeyListener);
        etTipOther.setOnKeyListener(mKeyListener);

        btnCalc.setOnClickListener(mClickListener);
        btnReset.setOnClickListener(mClickListener);
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
             if (v.getId() == R.id.calc_btn2) {
                 calculate();
             } else {
                 reset();
             }
        }
    };

    private View.OnKeyListener mKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            switch (v.getId()) {
                case R.id.calc_et1:
                case R.id.calc_et2:
                    btnCalc.setEnabled(etAmount.getText().length() > 0
                            && etPeople.getText().length() > 0);
                    break;
                case R.id.calc_et3:
                    btnCalc.setEnabled(etAmount.getText().length() > 0
                            && etPeople.getText().length() > 0
                            && etTipOther.getText().length() > 0);
                    break;
            }
            return false;
        }
    };

    private void reset() {
        etAmount.setText("");
        etPeople.setText("");
        etTipOther.setText("");

        textTipAmount.setText("");
        textTipToPay.setText("");
        textTotalPer.setText("");

        radioGroupTips.clearCheck();
        radioGroupTips.check(R.id.calc_rb1);

        etAmount.requestFocus();
    }

    private void calculate() {
        Double billAmount = Double.parseDouble(etAmount.getText().toString());
        Double totalPeople = Double.parseDouble(etPeople.getText().toString());
        Double percent = null;
        boolean isError = false;
        if (billAmount < 1.0) {
            showErrorAlert("Некорректная сумма", etAmount.getId());
            isError = true;
        }
        if (totalPeople < 1.0) {
            showErrorAlert("Некорректное кол-во человек", etPeople.getId());
            isError = true;
        }
        if (radioClickedId == -1) {
            radioClickedId = radioGroupTips.getCheckedRadioButtonId();
        }
        if (radioClickedId == R.id.calc_rb1) {
            percent = 5.00;
        } else if (radioClickedId == R.id.calc_rb2) {
            percent = 10.00;
        } else if (radioClickedId == R.id.calc_rb3) {
            percent = 15.00;
        } else if (radioClickedId == R.id.calc_rb4) {
            percent = Double.parseDouble(etTipOther.getText().toString());
            if (percent < 1.0) {
                showErrorAlert("Некорректное ко-во процентов", etTipOther.getId());
                isError = true;
            }
        }
        if (!isError) {
            Double sumPercent = billAmount * percent / 100;
            Double allSum = billAmount + sumPercent;
            Double onePerson = allSum / totalPeople;
            textTipAmount.setText(sumPercent.toString());
            textTipToPay.setText(allSum.toString());
            textTotalPer.setText(onePerson.toString());
        }
    }

    private void showErrorAlert(String errorMessage, final int fieldId) {
        new AlertDialog.Builder(this).setTitle("Ошибка").setMessage(errorMessage).setNeutralButton("Закрыть", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                findViewById(fieldId).requestFocus();
            }
        }).show();
    }

}