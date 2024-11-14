package edu.naita.example.weighttracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BodyDatabaseHelper extends SQLiteOpenHelper {
    public static final String BDATABASE_NAME = "bodyDetails.db";
    public static final String BTABLE_NAME = "body_details_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "BODY_PART_NAME";
    public static final String COL_3 = "MEASUREMENT";
    public static final String COL_4 = "DATE";

    public BodyDatabaseHelper(@Nullable Context context) {
        super(context, BDATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase bodyDB) {
        bodyDB.execSQL("create table " + BTABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,BODY_PART_NAME TEXT,MEASUREMENT TEXT,DATE TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase bodyDB, int i, int i1) {
        bodyDB.execSQL("DROP TABLE IF EXISTS "+BTABLE_NAME);
        onCreate(bodyDB);
    }

    public boolean insertBodyData(String body_part_name,String measurement,String date) {
        SQLiteDatabase bodyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,body_part_name);
        contentValues.put(COL_3,measurement);
        contentValues.put(COL_4,date);
        long result = bodyDB.insert(BTABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase bodyDB = this.getWritableDatabase();
        Cursor res = bodyDB.rawQuery("select * from "+BTABLE_NAME,null);
        return res;
    }

    public Cursor getLeftBicepAllData() {
        SQLiteDatabase bodyDB = this.getWritableDatabase();
        Cursor res = bodyDB.rawQuery("SELECT * FROM " +BTABLE_NAME + " WHERE " + COL_2 + " = 'left bicep' ",null);
        return res;
    }

    public Cursor getRightBicepAllData() {
        SQLiteDatabase bodyDB = this.getWritableDatabase();
        Cursor res = bodyDB.rawQuery("SELECT * FROM " +BTABLE_NAME + " WHERE " + COL_2 + " = 'right bicep' ",null);
        return res;
    }

    public Cursor getChestAllData() {
        SQLiteDatabase bodyDB = this.getWritableDatabase();
        Cursor res = bodyDB.rawQuery("SELECT * FROM " +BTABLE_NAME + " WHERE " + COL_2 + " = 'chest' ",null);
        return res;
    }

    public Cursor getWaistAllData() {
        SQLiteDatabase bodyDB = this.getWritableDatabase();
        Cursor res = bodyDB.rawQuery("SELECT * FROM " +BTABLE_NAME + " WHERE " + COL_2 + " = 'waist' ",null);
        return res;
    }

    public Cursor getHipsAllData() {
        SQLiteDatabase bodyDB = this.getWritableDatabase();
        Cursor res = bodyDB.rawQuery("SELECT * FROM " +BTABLE_NAME + " WHERE " + COL_2 + " = 'hips' ",null);
        return res;
    }

    public Cursor getLeftThighAllData() {
        SQLiteDatabase bodyDB = this.getWritableDatabase();
        Cursor res = bodyDB.rawQuery("SELECT * FROM " +BTABLE_NAME + " WHERE " + COL_2 + " = 'left thigh' ",null);
        return res;
    }

    public Cursor getRightThighAllData() {
        SQLiteDatabase bodyDB = this.getWritableDatabase();
        Cursor res = bodyDB.rawQuery("SELECT * FROM " +BTABLE_NAME + " WHERE " + COL_2 + " = 'right thigh' ",null);
        return res;
    }

    public Cursor getLeftCalveAllData() {
        SQLiteDatabase bodyDB = this.getWritableDatabase();
        Cursor res = bodyDB.rawQuery("SELECT * FROM " +BTABLE_NAME + " WHERE " + COL_2 + " = 'left calve' ",null);
        return res;
    }

    public Cursor getRightCalveAllData() {
        SQLiteDatabase bodyDB = this.getWritableDatabase();
        Cursor res = bodyDB.rawQuery("SELECT * FROM " +BTABLE_NAME + " WHERE " + COL_2 + " = 'right calve' ",null);
        return res;
    }




    public boolean updateBodyData(String id,String body_part_name,String measurement,String date) {
        SQLiteDatabase bodyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,body_part_name);
        contentValues.put(COL_3,measurement);
        contentValues.put(COL_4,date);
        bodyDB.update(BTABLE_NAME, contentValues, "id = ?",new String[] { id });
        return true;
    }

    public Integer deleteBodyData (String id) {
        SQLiteDatabase bodyDB = this.getWritableDatabase();
        return bodyDB.delete(BTABLE_NAME, "id = ?",new String[] {id});
    }
}
