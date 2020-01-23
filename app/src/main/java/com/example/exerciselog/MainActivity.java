package com.example.exerciselog;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.exerciselog.DatabaseHandler.COLUMN_CATEGORY;
import static com.example.exerciselog.DatabaseHandler.COLUMN_COMMENT;
import static com.example.exerciselog.DatabaseHandler.COLUMN_DATE;
import static com.example.exerciselog.DatabaseHandler.COLUMN_DISTANCE;
import static com.example.exerciselog.DatabaseHandler.COLUMN_ID;
import static com.example.exerciselog.DatabaseHandler.COLUMN_NAME;
import static com.example.exerciselog.DatabaseHandler.COLUMN_REPS;
import static com.example.exerciselog.DatabaseHandler.COLUMN_WEIGHT;
import static com.example.exerciselog.DatabaseHandler.TABLE_NAME;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private ExerciseLogAdapter exAdapter;
    private DatabaseHandler dbh;
    private EditText ExerciseInp, CategoryInp,
            WeightInp, RepsInp, CommentInp, DistanceInp;
    Button btnSubmit;
    Button btnSearch;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbh = new DatabaseHandler(MainActivity.this);
        database = dbh.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        exAdapter = new ExerciseLogAdapter(this, getAllItems());
        recyclerView.setAdapter(exAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                removeItem((long) viewHolder.itemView.getTag());

            }
        }).attachToRecyclerView(recyclerView);


        DistanceInp = findViewById(R.id.DistanceView);
        CommentInp = findViewById(R.id.CommentView);
        ExerciseInp = findViewById(R.id.NameView);
        CategoryInp = findViewById(R.id.CategoryView);
        WeightInp = findViewById(R.id.WeightView);
        RepsInp = findViewById(R.id.RepsView);

        btnSubmit =  findViewById(R.id.btnSubmit);



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name;
                if (ExerciseInp.getText().toString().trim().equals("")) {
                    name = "N/A" + "\n";
                } else {
                    name = ExerciseInp.getText().toString() + "\n";
                }

                String category;
                if (CategoryInp.getText().toString().trim().equals("")) {
                    category = "N/A" + "\n";
                } else {
                    category = CategoryInp.getText().toString() + "\n";
                }

                String comment;
                if (CommentInp.getText().toString().trim().equals("")) {
                    comment = "N/A" + "\n";
                } else {
                    comment = CommentInp.getText().toString() + "\n";
                }

                Float distance;
                if (DistanceInp.getText().toString().trim().equals("")) {
                    distance = Float.parseFloat("0.0f");
                } else {
                    distance = Float.parseFloat(DistanceInp.getText().toString());
                }
                Integer reps;
                if (RepsInp.getText().toString().trim().equals("")) {
                    reps = Integer.parseInt("0");
                } else {
                    reps = Integer.parseInt(RepsInp.getText().toString());
                }
                Float weight;
                if (WeightInp.getText().toString().trim().equals("")) {
                    weight = Float.parseFloat("0.0f");
                } else {
                    weight = Float.parseFloat(WeightInp.getText().toString());
                }

                insertData(name, category, reps, weight, distance, comment);
            }
        });

        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Send to new Activity
                searchData();

            }
        });



    }

    public void insertData(String name, String category, Integer reps, Float weight, Float distance, String comment) {
        ContentValues val = new ContentValues();
        val.put(COLUMN_NAME, name);
        val.put(COLUMN_CATEGORY, category);
        val.put(COLUMN_REPS, reps);
        val.put(COLUMN_WEIGHT, weight);
        val.put(COLUMN_DISTANCE, distance);
        val.put(COLUMN_COMMENT, comment);


        database.insert(TABLE_NAME, null, val);
        exAdapter.swapCursor(getAllItems());


        //   ExerciseInp.getText().clear();
        //  CategoryInp.getText().clear();
        //  WeightInp.getText().clear();
        //  RepsInp.getText().clear();
        CommentInp.getText().clear();
        DistanceInp.getText().clear();
    }

    public void searchData() {
        exAdapter.swapCursor(getSearch(ExerciseInp.getText().toString(), CategoryInp.getText().toString()));

        //Clear all input strings

        ExerciseInp.getText().clear();
        CategoryInp.getText().clear();
        WeightInp.getText().clear();
        RepsInp.getText().clear();
        CommentInp.getText().clear();
        DistanceInp.getText().clear();
    }

    private void removeItem(long id) {
        database.delete(TABLE_NAME,
                COLUMN_ID + "=" + id, null);
        exAdapter.swapCursor(getAllItems());
    }
    private Cursor getAllItems() {
        return database.query(TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                COLUMN_DATE + " DESC");
    }

    public Cursor getSearch(String name, String category) {
        //If both are null, just return entire database
        if (category.trim().equals("") && name.trim().equals("")) {
            return database.query(TABLE_NAME,
                    null,
                    null,
                    null,
                    null,
                    null,
                    COLUMN_DATE + " DESC");
        }


        if (name.trim().equals("")) {
            name = "N/A";
        }
        if (category.trim().equals("")) {
            category = "N/A";
        }


        return database.rawQuery("SELECT * from " + TABLE_NAME + " WHERE " + COLUMN_NAME + " LIKE '" + name.substring(0, 2) +
                "%' OR " + COLUMN_CATEGORY + " LIKE '" + category.substring(0, 2) + "%' ORDER BY " + COLUMN_DATE + " DESC ;", null);
    }


}
