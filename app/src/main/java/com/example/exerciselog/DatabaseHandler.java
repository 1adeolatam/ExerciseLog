package com.example.exerciselog;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;

import android.content.ContentValues;

import android.database.Cursor;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HealthLog.db";
    public static final String EXERCISE_TABLE = "Exercise";
    public static final String WEIGHT_TABLE = "Weight";

    //EXERCISE COLUMNS
    public static final String COLUMN_ID = "exerciseID";
    public static final String COLUMN_NAME = "exerciseName";
    public static final String COLUMN_CATEGORY = "exerciseCategory";
    public static final String COLUMN_COMMENT = "exerciseComment";
    public static final String COLUMN_DATE = "exerciseDate";
    public static final String COLUMN_TIME = "exerciseTime";
    public static final String COLUMN_LENGTH = "exerciseLength";
    public static final String COLUMN_WEIGHT = "exerciseWeight";
    public static final String COLUMN_DISTANCE = "exerciseDistance";
    public static final String COLUMN_REPS = "exerciseReps";

    //TODO WEIGHT TABLE


    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO FIRST
        String CREATE_TABLE = "CREATE TABLE " + EXERCISE_TABLE +
                "( " + COLUMN_ID + "INTEGER PRIMARYKEY," +
                COLUMN_NAME + " TEXT," +
                COLUMN_CATEGORY + " TEXT," +
                COLUMN_REPS + " INTEGER,"+
                COLUMN_LENGTH + " TEXT,"+
                COLUMN_WEIGHT + " REAL,"+
                COLUMN_DISTANCE + " REAL,"+
                COLUMN_TIME + " TEXT,"+
                COLUMN_DATE + " TEXT )";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //TODO LOAD HANDLER Modular fo rweight and exercises
    public String loadHandler(String tableName){

        String result = "";
        String query = "Select*FROM " + tableName;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
    public void addHandler(Exercise exercise){
        ContentValues val = new ContentValues();
        val.put(COLUMN_ID, exercise.getExerciseId());
        val.put(COLUMN_NAME, exercise.getExerciseName());
        val.put(COLUMN_CATEGORY, exercise.getExerciseCategory());
        val.put(COLUMN_REPS, exercise.getExerciseReps());
        val.put(COLUMN_WEIGHT, exercise.getExerciseWeight());
        val.put(COLUMN_TIME, exercise.getExerciseTime());
        val.put(COLUMN_DATE, exercise.getExerciseDate());
        val.put(COLUMN_LENGTH, exercise.getExerciseLength());
        val.put(COLUMN_COMMENT, exercise.getExerciseComment());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(EXERCISE_TABLE, null, val);
        db.close();
    }
    public void addHandler(Weightlog weightLog){

    }
    public Exercise[] findHandler(Date day, String exerciseName){

    }

    //TODO
    public boolean deleteHandler(int exerciseId){};
    public boolean updateHandler(int exerciseId){};
}
