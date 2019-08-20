package com.example.exerciselog;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ExerciseActivity extends AppCompatActivity {
    Intent intent;

    Button backButton;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.details);
        DatabaseHandler db = new DatabaseHandler(this, DatabaseHandler.EXERCISE_TABLE);

        //TODO Create view for specific exercise

    }
}
