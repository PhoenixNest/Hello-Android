package com.dev.a0302parcelable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextMath;
    private EditText editTextEnglish;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextMath = (EditText) findViewById(R.id.editTextMath);
        editTextEnglish = (EditText) findViewById(R.id.editTextEnglish);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                MyApplication myApplication = (MyApplication) getApplication();

                String name = editTextName.getText().toString();
                int age = Integer.parseInt(editTextAge.getText().toString());
                int math = Integer.parseInt(editTextMath.getText().toString());
                int english = Integer.parseInt(editTextEnglish.getText().toString());
                Student student = new Student(name, age, new Score(math, english));

                // myApplication.student = student;

                Intent intent = new Intent(this, SecondActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("Student", student);
                intent.putExtra("data", bundle);
                startActivity(intent);

                break;
        }
    }
}
