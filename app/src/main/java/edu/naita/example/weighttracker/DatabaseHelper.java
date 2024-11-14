package edu.naita.example.weighttracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "8Gate.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "habit_tracker";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DATE = "w_date";
    private static final String COLUMN_WEIGHT = "weight";

    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String habitQuery = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_WEIGHT + " TEXT);";

        db.execSQL(habitQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void addweight(String date, String weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvw = new ContentValues();

        cvw.put(COLUMN_DATE, date);
        cvw.put(COLUMN_WEIGHT, weight);

        long result_w = db.insert(TABLE_NAME, null, cvw);
        if(result_w == -1){
            Toast.makeText(context, "Adding Failed", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllDataW() {
        String habitQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(habitQuery, null);
        }
        return cursor;
    }

    void updateWeightData(String w_row_id,String date, String weight){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvw = new ContentValues();
        cvw.put(COLUMN_DATE, date);
        cvw.put(COLUMN_WEIGHT, weight);

        long result = db.update(TABLE_NAME, cvw, "_id=?", new String[]{w_row_id});

        if(result == -1){
            Toast.makeText(context, "Failed Update !!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated !!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRowWeight(String w_row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{w_row_id});

        if(result == -1){
            Toast.makeText(context, "Delete Failed !!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted !!", Toast.LENGTH_SHORT).show();
        }
    }



}
