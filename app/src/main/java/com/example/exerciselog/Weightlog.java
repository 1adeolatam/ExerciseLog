package com.example.exerciselog;

import java.sql.Time;
import java.util.Date;

public class Weightlog {
    private int weightLogId;
    private String exerciseComment;
    private Date exerciseDate;
    private Time exerciseTime;
    private Float exerciseWeight;

    public Weightlog() {
    }

    public int getWeightLogId() {
        return weightLogId;
    }

    public void setWeightLogId(int weightLogId) {
        this.weightLogId = weightLogId;
    }

    public String getExerciseComment() {
        return exerciseComment;
    }

    public void setExerciseComment(String exerciseComment) {
        this.exerciseComment = exerciseComment;
    }

    public Date getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public Time getExerciseTime() {
        return exerciseTime;
    }

    public void setExerciseTime(Time exerciseTime) {
        this.exerciseTime = exerciseTime;
    }

    public Float getExerciseWeight() {
        return exerciseWeight;
    }

    public void setExerciseWeight(Float exerciseWeight) {
        this.exerciseWeight = exerciseWeight;
    }
}
