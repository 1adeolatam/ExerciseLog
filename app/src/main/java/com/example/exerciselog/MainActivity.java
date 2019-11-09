package com.example.exerciselog;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class MainActivity extends AppCompatActivity {
    DatabaseHandler db;
    EditText ExerciseInp,CategoryInp,
            WeightInp,RepsInp,CommentInp,DistanceInp,TimeInp;
    Button btnSubmit, viewData;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DatabaseHandler(MainActivity.this);

        setContentView(R.layout.activity_main);
        DistanceInp = findViewById(R.id.DistanceView);
        TimeInp = findViewById(R.id.TimeView);
        CommentInp = findViewById(R.id.CommentView);
        ExerciseInp = findViewById(R.id.NameView);
        CategoryInp = findViewById(R.id.CategoryView);
        WeightInp = findViewById(R.id.WeightView);
        RepsInp = findViewById(R.id.RepsView);

        btnSubmit =  findViewById(R.id.btnSubmit);

        viewData = findViewById(R.id.viewData);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name;
                if (ExerciseInp.getText().toString().equals("")) {
                    name = "N/A" + "\n";
                } else {
                    name = ExerciseInp.getText().toString() + "\n";
                }

                String category;
                if (CategoryInp.getText().toString().equals("")) {
                    category = "N/A" + "\n";
                } else {
                    category = CategoryInp.getText().toString() + "\n";
                }

                String comment;
                if (CommentInp.getText().toString().equals("")) {
                    comment = "N/A" + "\n";
                } else {
                    comment = CommentInp.getText().toString() + "\n";
                }

                Float distance;
                if (DistanceInp.getText().toString().equals("")) {
                    distance = Float.parseFloat("0.0f");
                } else {
                    distance = Float.parseFloat(DistanceInp.getText().toString());
                }
                Integer reps;
                if (RepsInp.getText().toString().equals("")) {
                    reps = Integer.parseInt("0");
                } else {
                    reps = Integer.parseInt(RepsInp.getText().toString());
                }
                Float weight;
                if (WeightInp.getText().toString().equals("")) {
                    weight = Float.parseFloat("0.0f");
                } else {
                    weight = Float.parseFloat(WeightInp.getText().toString());
                }

                String dateStr;
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                dateStr = dateFormat.format(date) + "\n";


                String time;
                DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                time = timeFormat.format(date) + "\n";


                Boolean isInserted = db.insertData(name, category, reps, weight, distance, time, dateStr, comment);

                if (isInserted) {
                    Toast.makeText(getApplicationContext(), "Exercise Inserted Successfully",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Exercise NOT Inserted Successfully",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void displayData() {
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.getAllData();
                if (res.getColumnCount() == 0) {
                    //Empty
                    Toast.makeText(getApplicationContext(), "Exercise Log Empty",
                            Toast.LENGTH_SHORT).show();

                    return;
                }
                StringBuffer data = new StringBuffer();
                while (res.moveToNext()) {
                    data.append("Name " + res.getString(1) + "\n");
                    data.append("Category " + res.getString(2) + "\n");
                    data.append("Reps " + res.getString(3) + "\n");
                    data.append("Weight " + res.getString(4) + "\n");
                    data.append("Distance " + res.getString(5) + "\n");
                    data.append("Comment " + res.getString(6) + "\n");
                    data.append("Time " + res.getString(7) + "\n");
                    data.append("Date " + res.getString(8) + "\n\n");
                }

                intent = new Intent(MainActivity.this, ExerciseActivity.class);
                startActivity(intent);

            }
        });
    }
}
