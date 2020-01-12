package com.example.exerciselog;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "exercise.db";
    protected static final String TABLE_NAME = "exercises";

    //EXERCISE COLUMNS
    public static final String COLUMN_ID = "exerciseID";
    public static final String COLUMN_NAME = "exerciseName";
    public static final String COLUMN_CATEGORY = "exerciseCategory";
    public static final String COLUMN_REPS = "exerciseReps";
    public static final String COLUMN_DATE = "exerciseDate";
    public static final String COLUMN_WEIGHT = "exerciseWeight";
    public static final String COLUMN_DISTANCE = "exerciseDistance";
    public static final String COLUMN_COMMENT = "exerciseComment";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL," +
                COLUMN_CATEGORY + " TEXT NOT NULL," +
                COLUMN_REPS + " INTEGER NOT NULL," +
                COLUMN_WEIGHT + " REAL NOT NULL," +
                COLUMN_DISTANCE + " REAL NOT NULL," +
                COLUMN_COMMENT + " TEXT NOT NULL," +
                COLUMN_DATE + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" + ");";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);

    }


    // Get Exercise Details
    public ArrayList<HashMap<String, String>> GetExercises() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> exerciseList = new ArrayList<>();
        String query = "SELECT * FROM " + DATABASE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> val = new HashMap<>();
            val.put(COLUMN_NAME, cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            val.put(COLUMN_CATEGORY, cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
            val.put(COLUMN_REPS, cursor.getString(cursor.getColumnIndex(COLUMN_REPS)));
            val.put(COLUMN_WEIGHT, cursor.getString(cursor.getColumnIndex(COLUMN_WEIGHT)));
            val.put(COLUMN_DISTANCE, cursor.getString(cursor.getColumnIndex(COLUMN_DISTANCE)));
            val.put(COLUMN_COMMENT, cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT)));
            exerciseList.add(val);
        }
        return  exerciseList;
    }

    // Get Exercise Details from id
    public ArrayList<HashMap<String, String>> GetExercises(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> exerciseList = new ArrayList<>();
        Cursor cursor = db.query(DATABASE_NAME, new String[]{
                COLUMN_NAME, COLUMN_CATEGORY, COLUMN_REPS, COLUMN_WEIGHT, COLUMN_DISTANCE,
                COLUMN_DATE, COLUMN_COMMENT}, COLUMN_ID + " = ? ", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor.moveToNext()) {
            HashMap<String, String> val = new HashMap<>();
            val.put(COLUMN_NAME, cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            val.put(COLUMN_CATEGORY, cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
            val.put(COLUMN_REPS, cursor.getString(cursor.getColumnIndex(COLUMN_REPS)));
            val.put(COLUMN_WEIGHT, cursor.getString(cursor.getColumnIndex(COLUMN_WEIGHT)));
            val.put(COLUMN_DISTANCE, cursor.getString(cursor.getColumnIndex(COLUMN_DISTANCE)));
            val.put(COLUMN_DATE, cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
            val.put(COLUMN_COMMENT, cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT)));
            exerciseList.add(val);
        }
        return exerciseList;
    }
}

