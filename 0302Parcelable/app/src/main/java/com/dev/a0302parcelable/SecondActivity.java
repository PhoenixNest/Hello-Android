package com.dev.a0302parcelable;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewAge;
    private TextView textViewMath;
    private TextView textViewEnglish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();

        // MyApplication myApplication = (MyApplication) getApplication();
        // Student student = myApplication.student;
        Bundle bundle = getIntent().getBundleExtra("data");
        Student student = bundle.getParcelable("Student");
        textViewName.setText(student.getName());
        textViewAge.setText(String.valueOf(student.getAge()));
        textViewMath.setText(String.valueOf(student.getScore().getMath()));
        textViewEnglish.setText(String.valueOf(student.getScore().getEnglish()));

    }

    private void initView() {
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewAge = (TextView) findViewById(R.id.textViewAge);
        textViewMath = (TextView) findViewById(R.id.textViewMath);
        textViewEnglish = (TextView) findViewById(R.id.textViewEnglish);
    }
}
