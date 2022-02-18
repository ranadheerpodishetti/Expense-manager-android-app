package com.example.moneymind;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {



    public static final String COLUMN_ID = "ID";
    public static final String ENTRY_TABLE = "ENTRY_TABLE";
    public static final String COLUMN_TYPE = "TYPE";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_TIME = "TIME";
    public static final String COLUMN_PAYMENT = "PAYMENT";
    public static final String COLUMN_CATEGORY = "CATEGORY";
    //public static final String COLUMN_CATEGORY2 = "CATEGORY_ICON";
    public static final String COLUMN_AMOUNT = "AMOUNT";
    public static final String COLUMN_CURRENCY = "CURRENCY";
    public static final String COLUMN_NOTES = "NOTES";



    public DatabaseHelper( Context context) {
        super(context, "Money_Mind.db",null, 1);
            Log.e("DATABASE OPERATIONS","DATABASE CREATED");
    }


    //this is called the first time a database is accessed. There should be code in here to create a new database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + ENTRY_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TYPE + " TEXT, " + COLUMN_DATE + " TEXT, " + COLUMN_TIME + " TEXT, " + COLUMN_PAYMENT + " TEXT, " + COLUMN_CATEGORY + " TEXT, " + COLUMN_AMOUNT + " DOUBLE, " + COLUMN_CURRENCY + " TEXT, " + COLUMN_NOTES + " TEXT)";
        sqLiteDatabase.execSQL(createTableStatement); //,Time TEXT,Payment TEXT,Category TEXT,Amount TEXT,Currency TEXT,Note TEXT)");
        Log.e("DATABASE OPERATIONS","TABLE CREATED");

    }


    // this is called if the database version number changes. It prevents previous users apps from breaking when you change the database design
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ENTRY_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean addOne(EntryModel entryModel) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TYPE, entryModel.getCOL_2_Transaction());
        cv.put(COLUMN_DATE, entryModel.getCOL_3_Date());
        cv.put(COLUMN_TIME, entryModel.getCOL_4_Time());
        cv.put(COLUMN_PAYMENT, entryModel.getCOL_5_Payment());
        cv.put(COLUMN_CATEGORY, entryModel.getCOL_6_Category());
        //cv.put(COLUMN_CATEGORY2, entryModel.getCOL_6_5_Category2());
        cv.put(COLUMN_AMOUNT, entryModel.getCOL_7_Amount());
        cv.put(COLUMN_CURRENCY, entryModel.getCOL_8_Currency());
        cv.put(COLUMN_NOTES, entryModel.getCOL_9_Notes());

        long insert = sqLiteDatabase.insert(ENTRY_TABLE, null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    // method for deleting an entry
    public boolean deleteOne(EntryModel entryModel){
        // find entryModel in the database. if it found, show it and return true.
        // if it is not found, return false.

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "DELETE FROM " + ENTRY_TABLE + " WHERE " + COLUMN_ID + " = " + entryModel.getEntryID();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }
    }


    public List<EntryModel> getAllEntries() {

        List<EntryModel> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + ENTRY_TABLE;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if(cursor.moveToLast()){
            // loop through the cursor (result set) and create new customer objects. Put them into the return List.
            do {
                int entryID = cursor.getInt(0);
                String col_2_transaction = cursor.getString(1);
                String col_3_date = cursor.getString(2);
                String col_4_time = cursor.getString(3);
                String col_5_payment = cursor.getString(4);
                String col_6_category = cursor.getString(5);
                //String col_6_5_category2 = cursor.getString(6);
                Double col_7_amount = cursor.getDouble(6);
                String col_8_currency = cursor.getString(7);
                String col_9_notes = cursor.getString(8);

                EntryModel newEntry = new EntryModel(entryID, col_2_transaction, col_3_date, col_4_time, col_5_payment, col_6_category, col_7_amount, col_8_currency, col_9_notes);
                returnList.add(newEntry);

            } while (cursor.moveToPrevious() );

        }
        else {
            // failure. do not add anything to the list.
        }

        // close both the cursor and the db when done.
        cursor.close();
        sqLiteDatabase.close();

        return returnList;

    }



}



