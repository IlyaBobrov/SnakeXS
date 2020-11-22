package com.example.viperxs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "usersDB";
    public static final String TABLE_USERS = "users";

    public static final String KEY_ID = "_id";
    public static final String KEY_LOGIN = "login";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_PASS = "pass";

    public static final String TABLE_PRODUCTS = "products";

    public static final String KEY_P_ID = "_id";
    public static final String KEY_P_NAME = "name";
    public static final String KEY_P_PRICE = "price";
    public static final String KEY_P_WEIGHT = "weight";
    public static final String KEY_P_ONE_GRAM = "gram";
    public static final String KEY_P_ANOTHER = "another";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USERS + "(" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_LOGIN + " TEXT, " +
                KEY_EMAIL + " TEXT, " +
                KEY_PHONE + " TEXT, " +
                KEY_PASS + " TEXT" +
                ")");

        db.execSQL("CREATE TABLE " + TABLE_PRODUCTS + "(" +
                KEY_P_ID + " INTEGER PRIMARY KEY, " +
                KEY_P_NAME + " TEXT, " +
                KEY_P_PRICE + " REAL, " +
                KEY_P_WEIGHT + " INTEGER, " +
                KEY_P_ONE_GRAM + " REAL, " +
                KEY_P_ANOTHER + " REAL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

}
