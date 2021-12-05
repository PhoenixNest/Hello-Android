package com.dev.a0302json;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Student student = new Student("Test", 18, new Score(18, 19, 20));
        Student student1 = new Student("Test1", 28, new Score(128, 129, 120));

        Student[] students = {student, student1};
        String json = new Gson().toJson(students);
        Student[] jsonArray = new Gson().fromJson(json, Student[].class);
        List<Student> arrayList = Arrays.asList(jsonArray);

        Student student2 = new Student("Test3", 18, new Score(18, 19, 20));
        Student student3 = new Student("Test4", 28, new Score(128, 129, 120));
        List<Student> studentList = new ArrayList<>();
        studentList.add(student2);
        studentList.add(student3);
        String jsonList = new Gson().toJson(studentList);
        Type typeStudent = new TypeToken<List<Student>>() {
        }.getType();
        List<Student> list = new Gson().fromJson(jsonList, typeStudent);

        Student student4 = new Student("Test5", 35, new Score(256, 512, 1024));
        String json1 = new Gson().toJson(student4);
    }
}
