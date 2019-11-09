package com.example.exerciselog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String EXERCISE_TABLE = "Exercise";

    //EXERCISE COLUMNS
    public static final String COLUMN_ID = "exerciseID";
    public static final String COLUMN_NAME = "exerciseName";
    public static final String COLUMN_CATEGORY = "exerciseCategory";
    public static final String COLUMN_REPS = "exerciseReps";


    public static final String COLUMN_DATE = "exerciseDate";
    public static final String COLUMN_TIME = "exerciseTime";
    public static final String COLUMN_WEIGHT = "exerciseWeight";
    public static final String COLUMN_DISTANCE = "exerciseDistance";
    public static final String COLUMN_COMMENT = "exerciseComment";


    public DatabaseHandler(Context context) {
        super(context, EXERCISE_TABLE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + EXERCISE_TABLE +
                " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_CATEGORY + " TEXT," +
                COLUMN_REPS + " INTEGER,"+
                COLUMN_WEIGHT + " REAL,"+
                COLUMN_DISTANCE + " REAL,"+
                COLUMN_COMMENT + " TEXT," +
                COLUMN_TIME + " TEXT,"+
                COLUMN_DATE + " TEXT)";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + EXERCISE_TABLE);
        onCreate(db);

    }

    public Boolean insertData(String name, String category, Integer reps, Float weight, Float distance, String time, String date, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(COLUMN_NAME, name);
        val.put(COLUMN_CATEGORY, category);
        val.put(COLUMN_REPS, reps);
        val.put(COLUMN_WEIGHT, weight);
        val.put(COLUMN_DISTANCE, distance);
        val.put(COLUMN_TIME, time);
        val.put(COLUMN_DATE, date);
        val.put(COLUMN_COMMENT, comment);


        long res = db.insert(EXERCISE_TABLE, null, val);

        return res != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + EXERCISE_TABLE, null);

        return result;
    }

    public void deleteHandler(int exerciseId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EXERCISE_TABLE, COLUMN_ID + " = ?", new String[]{String.valueOf(exerciseId)});
        db.close();
    }

    public int updateHandler(int id,
                             String name, String category, int reps, float weight,
                             float distance, String time, String date,
                             String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();

        val.put(COLUMN_ID, id);
        val.put(COLUMN_NAME, name);
        val.put(COLUMN_CATEGORY, category);
        val.put(COLUMN_REPS, reps);
        val.put(COLUMN_WEIGHT, weight);
        val.put(COLUMN_DISTANCE, distance);
        val.put(COLUMN_TIME, time);
        val.put(COLUMN_DATE, date);
        val.put(COLUMN_COMMENT, comment);

        int count = db.update(EXERCISE_TABLE, val, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        return count;
    }

    // Get Exercise Details
    public ArrayList<HashMap<String, String>> GetExercises() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> exerciseList = new ArrayList<>();
        String query = "SELECT * FROM "+ EXERCISE_TABLE;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> val = new HashMap<>();
            val.put(COLUMN_NAME, cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            val.put(COLUMN_CATEGORY, cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
            val.put(COLUMN_REPS, cursor.getString(cursor.getColumnIndex(COLUMN_REPS)));
            val.put(COLUMN_WEIGHT, cursor.getString(cursor.getColumnIndex(COLUMN_WEIGHT)));
            val.put(COLUMN_DISTANCE, cursor.getString(cursor.getColumnIndex(COLUMN_DISTANCE)));
            val.put(COLUMN_TIME, cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
            val.put(COLUMN_DATE, cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
            val.put(COLUMN_COMMENT, cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT)));
            exerciseList.add(val);
        }
        return  exerciseList;
    }

    // Get Exercise Details from id
    public ArrayList<HashMap<String, String>> GetExercises(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> exerciseList = new ArrayList<>();
        Cursor cursor = db.query(EXERCISE_TABLE, new String[]{
                COLUMN_NAME, COLUMN_CATEGORY, COLUMN_REPS, COLUMN_WEIGHT, COLUMN_DISTANCE,
                COLUMN_TIME, COLUMN_DATE, COLUMN_COMMENT}, COLUMN_ID + " = ? ", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor.moveToNext()) {
            HashMap<String, String> val = new HashMap<>();
            val.put(COLUMN_NAME, cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            val.put(COLUMN_CATEGORY, cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
            val.put(COLUMN_REPS, cursor.getString(cursor.getColumnIndex(COLUMN_REPS)));
            val.put(COLUMN_WEIGHT, cursor.getString(cursor.getColumnIndex(COLUMN_WEIGHT)));
            val.put(COLUMN_DISTANCE, cursor.getString(cursor.getColumnIndex(COLUMN_DISTANCE)));
            val.put(COLUMN_TIME, cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
            val.put(COLUMN_DATE, cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
            val.put(COLUMN_COMMENT, cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT)));
            exerciseList.add(val);
        }
        return exerciseList;
    }
}

