package com.currencycalulator2022;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// This class represent DatabaseHelper with inheritance from SQLiteOpenHelper
public class DatabaseHelper extends SQLiteOpenHelper {

    // Using final indicate a constant value (database info) - cannot be changed
    public static final String DATABASE_NAME = "CurrencyDB";
    public static final String CONTACTS_TABLE_NAME = "CurrencyData";

    // Create constructor
    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    // Using onCreate method for creating SQLite Database table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table "+ CONTACTS_TABLE_NAME +"(id integer primary key, Currency text, calcValue text)"
        );
    }

    // Using onUpgrade method for updating SQLite Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+CONTACTS_TABLE_NAME);
        onCreate(db);
    }

    // Using insert for adding data to SQLite database
    public boolean insert(String curr, String calcVal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fromCurrency", curr);
        contentValues.put("textView", calcVal);
        db.insert(CONTACTS_TABLE_NAME, null, contentValues);
        return true;
    }
}
