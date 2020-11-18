package com.example.viperxs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.number.Precision;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class ProductCalcActivity extends AppCompatActivity {

    private final String LOG_TAG = "eat";

    ListView listOfProducts;
    ProductAdapter adapter;
    ArrayList<Product> products = new ArrayList<Product>();

    Product product;

    private FloatingActionButton btnSend;
    private Button btnReCalc;

    private EditText eName;
    private EditText ePrice;
    private EditText eWeight;
    private EditText eAnother;

    private TextView tName;
    private TextView tPrice;
    private TextView tWeight;
    private TextView tRes;
    private TextView tResAnother;

    private double forOneGram = 0;
    private double forAnotherGram = 0;


    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    ContentValues contentValues;
    Cursor cursor;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_calc_product, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_calc);

        eName = findViewById(R.id.prod_ed_name);

        ePrice = findViewById(R.id.prod_ed_price);
        eWeight = findViewById(R.id.prod_ed_weight);
        eWeight.requestFocus();
        eAnother = findViewById(R.id.prod_ed_set_weight);
        tName = findViewById(R.id.product_item_name);
        tPrice = findViewById(R.id.product_item_price);
        tWeight = findViewById(R.id.product_item_weight);
        tRes = findViewById(R.id.product_item_res);
        tResAnother = findViewById(R.id.product_item_res_another);
        btnSend = findViewById(R.id.mess_btnSend);
        btnReCalc = findViewById(R.id.button_set_weight);

        dbHelper = new DatabaseHelper(this);

        adapter = new ProductAdapter(this, products);
        listOfProducts = findViewById(R.id.prod_list);
        listOfProducts.setAdapter(adapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                onClickSend(v);
            }
        });
        eWeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(eWeight, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        btnSend.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                onClickSend(v);
            }
        });
        btnReCalc.setClickable(false);
        btnReCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eAnother.getText().toString().isEmpty()) eAnother.setText("1");
                calculateAnother();
            }
        });
        Log.d(LOG_TAG, "onCreate - EndMethod");
    }

    private void onClickSend(View v) {
        if (!checkEditField()) return;

        db = dbHelper.getWritableDatabase(); //открыть и вернуть экземпляр базы данных
        contentValues = new ContentValues(); //класс для добавления новых строк в таблицу

        forOneGram = calcOneGram();
        Log.d(LOG_TAG, "один грамм " + forOneGram);
        forAnotherGram = calcAnotherGram();
        Log.d(LOG_TAG, "другое кол-во" + forAnotherGram);

        displayProduct();
        dbAddProduct(); //добавление в бд
        Log.d(LOG_TAG, "onClick - EndMethod");
        eName.setText("item");
        ePrice.setText("");
        eWeight.setText("");
        eWeight.requestFocus();

    }

    private double calcOneGram() {
        return round(Double.parseDouble(ePrice.getText().toString()) / Integer.parseInt(eWeight.getText().toString()), 2);
    }

    private double calcAnotherGram() {
        return round(Double.parseDouble(ePrice.getText().toString()) / Integer.parseInt(eWeight.getText().toString()), 2) *
                Integer.parseInt(eAnother.getText().toString());
    }

    private void displayProduct() {
        products.add(new Product(eName.getText().toString(),
                Integer.parseInt(ePrice.getText().toString()),
                Long.parseLong(eWeight.getText().toString()),
                forOneGram, forAnotherGram));
        adapter = new ProductAdapter(this, products);
        listOfProducts.setAdapter(adapter);

        Log.d(LOG_TAG, "displayProduct - EndMethod");
    }

    private void calculateAnother() {
    }

    private void dbAddProduct() {
        contentValues.put(DatabaseHelper.KEY_P_NAME, eName.getText().toString());
        contentValues.put(DatabaseHelper.KEY_P_PRICE, Double.parseDouble(ePrice.getText().toString()));
        contentValues.put(DatabaseHelper.KEY_P_WEIGHT, Integer.parseInt(eWeight.getText().toString()));
        contentValues.put(DatabaseHelper.KEY_P_ONE_GRAM, forOneGram);
        contentValues.put(DatabaseHelper.KEY_P_ANOTHER, forAnotherGram);
        db.insert(DatabaseHelper.DB_NAME, null, contentValues);
    }

    private boolean checkEditField() {
        if (eName.getText().toString().isEmpty()||
            ePrice.getText().toString().isEmpty()||
            eWeight.getText().toString().isEmpty()||
            eAnother.getText().toString().isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Double.parseDouble(eWeight.getText().toString()) == 0) {
            Toast.makeText(this, "Укажите вес", Toast.LENGTH_SHORT).show();
            return false;
        }
     return true;
    }

    public void onClickActionProductClear(MenuItem item) {
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}