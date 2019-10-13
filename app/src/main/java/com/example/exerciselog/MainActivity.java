package com.example.exerciselog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



//Added intent

public class MainActivity extends AppCompatActivity {

    EditText ExerciseInp,CategoryInp,
            WeightInp,RepsInp,CommentInp,DistanceInp,TimeInp;
    Button btnSubmit;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DistanceInp = findViewById(R.id.DistanceView);
        TimeInp = findViewById(R.id.TimeView);
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


                DatabaseHandler db = new DatabaseHandler(MainActivity.this);
                db.addHandler(name, category, reps, weight, distance, time, dateStr, comment);
                intent = new Intent(MainActivity.this, ExerciseActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Exercise Inserted Successfully",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }
}
