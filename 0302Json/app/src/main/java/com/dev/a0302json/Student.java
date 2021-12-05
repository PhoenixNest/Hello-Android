package com.dev.a0302json;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Student implements Serializable {
    @SerializedName("student_name")
    private String name;

    @SerializedName("student_age")
    private int age;
    private Score score;

    public Student(String name, int age, Score score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Score getScore() {
        return score;
    }
}
