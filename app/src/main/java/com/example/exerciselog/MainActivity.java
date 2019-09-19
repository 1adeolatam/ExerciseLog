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

    EditText ExerciseInp,CategoryInp,
            WeightInp,RepsInp,CommentInp,DistanceInp,TimeInp;

    String Exercise;
    String Category;
    Double  Weight;
    Double Distance;
    Integer Reps;
    String Comment;
    String TimeforCardio;


    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DistanceInp = (EditText)  findViewById(R.id.DistanceView);
        TimeInp = (EditText)findViewById(R.id.TimeView);
        CommentInp = (EditText)findViewById(R.id.CommentView);
        ExerciseInp = (EditText)findViewById(R.id.NameView);
        CategoryInp = (EditText) findViewById(R.id.CategoryView);
        WeightInp = (EditText) findViewById(R.id.WeightView);
        RepsInp = (EditText) findViewById(R.id.RepsView);

        btnSubmit =  findViewById(R.id.btnSubmit);







    }
   /* private void clearAllInput(){
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
*/
   /* private void changeMode(){
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

    }*/

/*    private void addListenerOnSpinnerItemSelection(){
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
        //changeMode();
    }*/


    public void addListenerOnButton() {

        //Save current set to Local file
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Submit exercise to database


            }

        });
    }
}
