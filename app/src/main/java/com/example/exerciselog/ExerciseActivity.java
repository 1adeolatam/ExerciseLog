package com.example.exerciselog;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

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
        ArrayList<HashMap<String, String>> userList = db.GetExercise();

        ListView lv = (ListView) findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(DetailsActivity.this, userList, R.layout.list_row,new String[]{"name","designation","location"}, new int[]{R.id.list_row_name, R.id.designation, R.id.location});
        lv.setAdapter(adapter);
        Button back = (Button)findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DetailsActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}