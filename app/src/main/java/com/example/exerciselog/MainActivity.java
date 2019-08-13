package com.example.exerciselog;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


//Added intent
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final int  BODYMODE= 800;
    private static final int STRENGTHMODE = 401;
    private static final int CARDIOMODE = 748;

    private int currentMode =BODYMODE;

    EditText ExerciseInp;
    EditText CategoryInp;
    EditText WeightInp;
    EditText RepsInp;
    EditText CommentInp;
    EditText BodyWeightInp;
    EditText DistanceInp;
    EditText TimeInp;

    String Exercise;
    String Category;
    Double  Weight;
    Double BodyWeight;
    Double Distance;
    Integer Reps;
    String Comment;
    String TimeforCardio;

    Spinner entryType;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        entryType = findViewById(R.id.entryType);
        btnSubmit =  findViewById(R.id.btnSubmit);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.exercise_type_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        entryType.setAdapter(adapter);



        //BodyWeight
        //TODO AUTOMATIC TIME FOR BODY WEIGHT ENTRY FROM PHONE LOCAL
        // DATE AUTOMATIC
        BodyWeightInp = findViewById(R.id.BodyWeightView);
        CommentInp =  findViewById(R.id.CommentView);


        //Cardio
        //TODO DATE AUTOMATIC
        ExerciseInp = findViewById(R.id.ExerciseView);
        DistanceInp =   findViewById(R.id.DistanceView);
        TimeInp = findViewById(R.id.TimeView);
        CommentInp = findViewById(R.id.CommentView);


        //Strength
        //TODO DATE AUTOMATIC
        ExerciseInp = findViewById(R.id.ExerciseView);
        CategoryInp =  findViewById(R.id.CategoryView);
        WeightInp = findViewById(R.id.WeightView);
        RepsInp =  findViewById(R.id.RepsView);
        CommentInp =  findViewById(R.id.CommentView);
        makeAllViewsInvisible();
        clearAllInput();


        addListenerOnSpinnerItemSelection();
        //TODO DATABASE
        //Upload day's exercise automatically at midnight
    }
    private void clearAllInput(){
         ExerciseInp.getText().clear();
         CategoryInp.getText().clear();
         WeightInp.getText().clear();
         RepsInp.getText().clear();
         CommentInp.getText().clear();
         BodyWeightInp.getText().clear();
         DistanceInp.getText().clear();
         TimeInp.getText().clear();
    }

    private void makeAllViewsInvisible(){
        ExerciseInp.setVisibility(View.INVISIBLE);
        CategoryInp.setVisibility(View.INVISIBLE);
        WeightInp.setVisibility(View.INVISIBLE);
        RepsInp.setVisibility(View.INVISIBLE);
        CommentInp.setVisibility(View.INVISIBLE);
        BodyWeightInp.setVisibility(View.INVISIBLE);
        DistanceInp.setVisibility(View.INVISIBLE);
        TimeInp.setVisibility(View.INVISIBLE);
    }

    private void changeMode(){
        //Changes visibllity based on current mode
        clearAllInput();
        makeAllViewsInvisible();
        if(currentMode == BODYMODE){
            BodyWeightInp.setVisibility(View.VISIBLE);
            CommentInp.setVisibility(View.VISIBLE);
        }else if(currentMode == STRENGTHMODE){
            ExerciseInp.setVisibility(View.VISIBLE);
            CategoryInp.setVisibility(View.VISIBLE);
            RepsInp.setVisibility(View.VISIBLE);
            WeightInp.setVisibility(View.VISIBLE);
            CommentInp.setVisibility(View.VISIBLE);
        } else{
            ExerciseInp.setVisibility(View.VISIBLE);
            DistanceInp.setVisibility(View.VISIBLE);
            TimeInp.setVisibility(View.VISIBLE);
            CommentInp.setVisibility(View.VISIBLE);
        }

    }

    private void addListenerOnSpinnerItemSelection(){
        entryType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String selection = (String) adapterView.getItemAtPosition(pos);
                if(selection == "Cardio"){
                    currentMode = CARDIOMODE;
                }else if(selection == "Body"){
                    currentMode = BODYMODE;
                }else{
                    currentMode = STRENGTHMODE;
                }
            }
        });
        changeMode();
    }


    public void addListenerOnButton() {

        //Save current set to Local file
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Submit exercise to database

                Toast.makeText(MyAndroidAppActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) +
                        Toast.LENGTH_SHORT).show();
            }

        });
    }
}
