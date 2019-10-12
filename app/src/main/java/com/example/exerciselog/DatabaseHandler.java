package com.example.exerciselog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class        DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String EXERCISE_TABLE = "Exercise";

    //EXERCISE COLUMNS
    public static final String COLUMN_ID = "exerciseID";
    public static final String COLUMN_NAME = "exerciseName";
    public static final String COLUMN_CATEGORY = "exerciseCategory";
    public static final String COLUMN_REPS = "exerciseReps";


    public static final String COLUMN_DATE = "exerciseDate";
    public static final String COLUMN_TIME = "exerciseTime";
    public static final String COLUMN_LENGTH = "exerciseLength";
    public static final String COLUMN_WEIGHT = "exerciseWeight";
    public static final String COLUMN_DISTANCE = "exerciseDistance";
    public static final String COLUMN_COMMENT = "exerciseComment";
    //TODO WEIGHT TABLE


    public DatabaseHandler(Context context) {
        super(context, EXERCISE_TABLE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO FIRST
        String CREATE_TABLE = "CREATE TABLE " + EXERCISE_TABLE +
                " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_CATEGORY + " TEXT," +
                COLUMN_REPS + " INTEGER,"+
                COLUMN_LENGTH + " TEXT,"+
                COLUMN_WEIGHT + " REAL,"+
                COLUMN_DISTANCE + " REAL,"+
                COLUMN_COMMENT + " TEXT," +
                COLUMN_TIME + " TEXT,"+
                COLUMN_DATE + " TEXT)";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public String loadHandler(String tableName){

        String result = "";
        String query = "Select*FROM " + tableName;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += result_0 + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
    public void addHandler(Exercise exercise){
        ContentValues val = new ContentValues();
        val.put(COLUMN_NAME, exercise.getExerciseName());
        val.put(COLUMN_CATEGORY, exercise.getExerciseCategory());
        val.put(COLUMN_REPS, exercise.getExerciseReps());
        val.put(COLUMN_WEIGHT, exercise.getExerciseWeight());
        val.put(COLUMN_DISTANCE, exercise.getExerciseDistance());
        val.put(COLUMN_TIME, exercise.getExerciseTime());
        val.put(COLUMN_DATE, exercise.getExerciseDate());
        val.put(COLUMN_LENGTH, exercise.getExerciseLength());
        val.put(COLUMN_COMMENT, exercise.getExerciseComment());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(EXERCISE_TABLE, null, val);
        db.close();
    }

    public List<Exercise> findHandler(String exerciseName){
        List<Exercise> results = new ArrayList<>();
      SQLiteDatabase  db =  this.getWritableDatabase();
        String sql = "SELECT * FROM " + EXERCISE_TABLE + " WHERE " + COLUMN_NAME+ " LIKE '%" + exerciseName + "%'";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.getCount() > 0){
            // means search has returned data
            if (cursor.moveToFirst()) {
                do {
                    Exercise ex = new Exercise();

                    ex.setExerciseId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                    ex.setExerciseName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                    ex.setExerciseCategory(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
                    ex.setExerciseReps(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_REPS))));
                    ex.setExerciseLength(cursor.getString(cursor.getColumnIndex(COLUMN_LENGTH)));
                    ex.setExerciseWeight(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_WEIGHT))));
                    ex.setExerciseDistance(Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_DISTANCE))));
                    ex.setExerciseComment(cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT)));
                    ex.setExerciseTime(cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
                    ex.setExerciseDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));

                    results.add(ex);

                // display your search result
                } while (cursor.moveToNext());
            }
        } else {
                System.err.println("Could not find exercise!");
            }
        cursor.close();
        db.close();
        return results;
    }

    //TODO
    public boolean deleteHandler(int exerciseId){
        boolean result = false;
        String query = "Select*FROM" + EXERCISE_TABLE + "WHERE" + COLUMN_ID + "= '" + exerciseId + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Exercise exercise = new Exercise();
        if (cursor.moveToFirst()) {
            exercise.setExerciseId(Integer.parseInt(cursor.getString(0)));
            db.delete(EXERCISE_TABLE, COLUMN_ID + "=?",
                    new String[] {
                String.valueOf(exercise.getExerciseId())});

            cursor.close();

            result = true;

        }

        db.close();

        return result;


    }

    public boolean updateHandler(int id,
                                 String name,String category, int reps, float weight,
                                 float distance, String time, String date, String length,
                                 String comment){
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
        val.put(COLUMN_LENGTH, length);
        val.put(COLUMN_COMMENT, comment);
        return db.update(EXERCISE_TABLE, val,  COLUMN_ID + "=" +id ,null) > 0;
    }

    // Get Exercise Details
    public ArrayList<HashMap<String, String>> GetExercise(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> exerciseList = new ArrayList<>();
        String query = "SELECT * FROM "+ EXERCISE_TABLE;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> val = new HashMap<>();
            val.put(COLUMN_ID, cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
            val.put(COLUMN_NAME, cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            val.put(COLUMN_CATEGORY, cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
            val.put(COLUMN_REPS, cursor.getString(cursor.getColumnIndex(COLUMN_REPS)));
            val.put(COLUMN_WEIGHT, cursor.getString(cursor.getColumnIndex(COLUMN_WEIGHT)));
            val.put(COLUMN_DISTANCE, cursor.getString(cursor.getColumnIndex(COLUMN_DISTANCE)));
            val.put(COLUMN_TIME, cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
            val.put(COLUMN_DATE, cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
            val.put(COLUMN_LENGTH, cursor.getString(cursor.getColumnIndex(COLUMN_LENGTH)));
            val.put(COLUMN_COMMENT, cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT)));
            exerciseList.add(val);
        }
        return  exerciseList;
    }
}
