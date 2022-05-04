package com.currencycalulator2022;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

// This class represent DatabaseHelper with inheritance from SQLiteOpenHelper
public class DatabaseHelper extends SQLiteOpenHelper {

    // Using final indicate a constant value (database info) - cannot be changed
    public static final String DATABASE_NAME = "CurrencyDB";
    public static final String TABLE_NAME = "CurrencyData";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_AMOUNT = "amountInEuro";
    public static final String COLUMN_SELECTED_CURRENCY = "selectedCurrency";
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
                        COLUMN_SELECTED_CURRENCY + " TEXT, " +
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
    public boolean insert(String amountEuro, String selectCurr, String calcVal) {
        SQLiteDatabase db = null;

        try {

            // Using writable method for writing data in database.
            db = this.getWritableDatabase();

            // Creating variable for content values
            ContentValues contentValues = new ContentValues();

            // Passing all values with its key and value pair
            contentValues.put(COLUMN_AMOUNT, amountEuro);
            contentValues.put(COLUMN_SELECTED_CURRENCY, selectCurr);
            contentValues.put(COLUMN_CALC_VALUE, calcVal);

            // Adding passed values to database table
            db.insert(TABLE_NAME, null, contentValues);
        }
        catch (Exception e){

            // Method for handle exceptions and errors
            e.printStackTrace();
        }finally {

            // Close access to SQLite database
            db.close();
        }
        return true;
    }

    // Using Arraylist and Cursor for reading all stored data.
    public List<String> getAllstoredInDB(){
        ArrayList datalist = new ArrayList<>();

        // Create sql query with SELECT request
        String sqlQuery = "SELECT " + COLUMN_AMOUNT + ", " + COLUMN_SELECTED_CURRENCY + ", " + COLUMN_CALC_VALUE + " FROM " + TABLE_NAME;
        SQLiteDatabase db = null;

        try {

            db = this.getReadableDatabase();

            // Creating a cursor with the created sql query to read data from database.
            Cursor cursor = db.rawQuery(sqlQuery, null);
            if(cursor.moveToFirst()){
                do {
                    datalist.add(cursor.getString(0));
                    datalist.add(cursor.getString(1));
                    datalist.add(cursor.getString(2));
                }while (cursor.moveToNext());
            }
        }catch (Exception e){

            // Method for handle exceptions and errors
            e.printStackTrace();
        }finally {

            // Close access to SQLite database
            db.close();
        }
        return datalist;
    }
}
