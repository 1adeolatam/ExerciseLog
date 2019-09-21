package com.example.exerciselog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
                com.example.exerciselog.Exercise ex = new Exercise();
                ex.setExerciseName(ExerciseInp.getText().toString());
                ex.setExerciseCategory(CategoryInp.getText().toString());
                ex.setExerciseComment(CommentInp.getText().toString());
                ex.setExerciseLength(TimeInp.getText().toString());
                ex.setExerciseDistance(Float.parseFloat(DistanceInp.getText().toString()));
                ex.setExerciseReps(Integer.parseInt(RepsInp.getText().toString()));
                ex.setExerciseWeight(Float.parseFloat(WeightInp.getText().toString()));

                DatabaseHandler db = new DatabaseHandler(MainActivity.this);
                db.addHandler(ex);
                intent = new Intent(MainActivity.this, ExerciseActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Exercise Inserted Successfully",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }
}
