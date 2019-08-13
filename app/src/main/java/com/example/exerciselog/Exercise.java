package com.example.exerciselog;

import java.sql.Date;
import java.sql.Time;


public class Exercise {

    private int exerciseId;
    private String exerciseName;
    private String exerciseCategory;
    private String exerciseComment;
    private String exerciseDate;
    private String exerciseTime;
    private String exerciseLength;
    private Float exerciseWeight;
    private Float exerciseDistance;
    private int exerciseReps;


    public Exercise() {
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseCategory() {
        return exerciseCategory;
    }

    public void setExerciseCategory(String exerciseCategory) {
        this.exerciseCategory = exerciseCategory;
    }

    public String getExerciseComment() {
        return exerciseComment;
    }

    public void setExerciseComment(String exerciseComment) {
        this.exerciseComment = exerciseComment;
    }

    public String getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(String exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public String getExerciseTime() {
        return exerciseTime;
    }

    public void setExerciseTime(String exerciseTime) {
        this.exerciseTime = exerciseTime;
    }

    public String getExerciseLength() {
        return exerciseLength;
    }

    public void setExerciseLength(String exerciseLength) {
        this.exerciseLength = exerciseLength;
    }

    public Float getExerciseWeight() {
        return exerciseWeight;
    }

    public void setExerciseWeight(Float exerciseWeight) {
        this.exerciseWeight = exerciseWeight;
    }

    public Float getExerciseDistance() {
        return exerciseDistance;
    }

    public void setExerciseDistance(Float exerciseDistance) {
        this.exerciseDistance = exerciseDistance;
    }

    public int getExerciseReps() {
        return exerciseReps;
    }

    public void setExerciseReps(int exerciseReps) {
        this.exerciseReps = exerciseReps;
    }
}


