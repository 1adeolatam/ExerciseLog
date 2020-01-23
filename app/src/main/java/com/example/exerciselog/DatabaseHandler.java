package com.example.exerciselog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}

