package com.currencycalulator2022;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// This class represent DatabaseHelper with inheritance from SQLiteOpenHelper
public class DatabaseHelper extends SQLiteOpenHelper {

    // Using final indicate a constant value (database info) - cannot be changed
    public static final String DATABASE_NAME = "CurrencyDB";
    public static final String TABLE_NAME = "CurrencyData";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_AMOUNT = "amountInEuro";
    public static final String COLUMN_CALC_VALUE = "calcValue";

    // Create constructor
    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    // Using onCreate method for creating SQLite Database table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table "+ TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_AMOUNT + " TEXT, " +
                        COLUMN_CALC_VALUE + " TEXT) "
        );
    }

    // Using onUpgrade method for updating SQLite Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Using insert for adding data to SQLite database
    public boolean insert(String amountEuro, String calcVal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_AMOUNT, amountEuro);
        contentValues.put(COLUMN_CALC_VALUE, calcVal);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }
}
