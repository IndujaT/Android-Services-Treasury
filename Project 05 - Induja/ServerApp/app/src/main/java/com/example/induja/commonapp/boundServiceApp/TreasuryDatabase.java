package com.example.induja.commonapp.boundServiceApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Induja on 4/30/2018.
 */

public class TreasuryDatabase extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "Treasury.db";
    public static final String TABLE_NAME = "treasury_data";
    public static final String COL_1 = "YEAR";
    public static final String COL_2 = "MONTH";
    public static final String COL_3 = "DATE";
    public static final String COL_4 = "DAY";
    public static final String COL_5 = "STARTAMT";
    public static final String COL_6 = "ENDAMT";
    public static final String COL_7 = "FIELDDATE";

    public TreasuryDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + TABLE_NAME +" (KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, YEAR INTEGER,MONTH INTEGER,DATE INTEGER,DAY TEXT,STARTAMT INTEGER,ENDAMT INTEGER, FIELDDATE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(Integer year, Integer month, Integer date, String day, Integer startamt, Integer endamt){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,year);
        contentValues.put(COL_2,month);
        contentValues.put(COL_3,date);
        contentValues.put(COL_4,day);
        contentValues.put(COL_5,startamt);
        contentValues.put(COL_6,endamt);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean statusOfDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {COL_1};

        Cursor res = db.query(TABLE_NAME, columns,null,null,null,null,null,"1"); //checking by extracting one column from the table just created so as to ensure that the created properly and data is accessable
        if(res != null)  //if data is there  -> hence database is created.
            return true;
        else
            return false;
    }

    public Cursor getDailyData(int yyyy, int mm, int dd, int workingDays) {

        String key_id = null;

        SQLiteDatabase db = this.getWritableDatabase();

        String[] args = { String.valueOf(yyyy),String.valueOf(mm),String.valueOf(dd)};

        Cursor cus = db.query(true, TABLE_NAME, new String[]{"KEY_ID"},"YEAR=? AND MONTH=? AND DATE=?",args,null,null,null,null);
        //key_id is fetched: the only column,
        if (cus != null) {
            try {
                while (cus.moveToNext()){
                    key_id = cus.getString(0);
                }
            }finally {
                cus.close();
            }
        }

        String[] columns ={COL_1, COL_2,COL_3, COL_4, COL_5, COL_6};



        String[] args1 = { String.valueOf(key_id), String.valueOf(Integer.parseInt(key_id)+workingDays)};

        Cursor res = db.query(true,TABLE_NAME, columns,"KEY_ID>=? AND KEY_ID<?",args1,null,null,null,String.valueOf(Integer.parseInt(key_id)+workingDays));

        return res;

    }
}
