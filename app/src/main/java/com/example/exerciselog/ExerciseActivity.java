package com.example.exerciselog;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ExerciseActivity extends AppCompatActivity {
    Intent intent;

    Button backButton;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.details);
        DatabaseHandler db = new DatabaseHandler(this, DatabaseHandler.EXERCISE_TABLE);
        ArrayList<HashMap<String, String>> exerciseList = db.GetExercise();

        ListView lv = (ListView) findViewById(R.id.exercise_list);
        ListAdapter adapter = new SimpleAdapter(ExerciseActivity.this, exerciseList, R.layout.list_row,
                new String[]{"name","reps","weight","date"}, new int[]{R.id.name, R.id.rep, R.id.weight, R.id.date});
        lv.setAdapter(adapter);
        Button back = (Button)findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ExerciseActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}