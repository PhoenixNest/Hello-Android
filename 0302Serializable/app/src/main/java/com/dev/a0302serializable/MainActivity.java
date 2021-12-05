package com.dev.a0302serializable;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewGrade;
    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextMath;
    private EditText editTextEnglish;
    private EditText editTextChinese;
    private Button buttonSave;
    private Button buttonLoad;

    private static final String FILE_NAME = "MY_FILE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        textViewGrade = (TextView) findViewById(R.id.textViewGrade);
        editTextName = (EditText) findViewById(R.id.editText);
        editTextAge = (EditText) findViewById(R.id.editText2);
        editTextMath = (EditText) findViewById(R.id.editText3);
        editTextEnglish = (EditText) findViewById(R.id.editText4);
        editTextChinese = (EditText) findViewById(R.id.editText5);
        buttonSave = (Button) findViewById(R.id.button);
        buttonLoad = (Button) findViewById(R.id.button2);

        buttonSave.setOnClickListener(this);
        buttonLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                int math = Integer.parseInt(editTextMath.getText().toString());
                int english = Integer.parseInt(editTextEnglish.getText().toString());
                int chinese = Integer.parseInt(editTextChinese.getText().toString());
                Score score = new Score(math, english, chinese);

                String name = editTextName.getText().toString();
                int age = Integer.parseInt(editTextAge.getText().toString());
                Student student = new Student(name, age, score);

                editTextMath.getText().clear();
                editTextEnglish.getText().clear();
                editTextChinese.getText().clear();
                editTextName.getText().clear();
                editTextAge.getText().clear();

                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(openFileOutput(FILE_NAME, MODE_PRIVATE));
                    objectOutputStream.writeObject(student);
                    objectOutputStream.flush();
                    objectOutputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.button2:
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(openFileInput(FILE_NAME));
                    Student student1 = (Student) objectInputStream.readObject();

                    editTextName.setText(student1.getName());
                    editTextAge.setText(String.valueOf(student1.getAge()));
                    editTextMath.setText(String.valueOf(student1.getScore().getMath()));
                    editTextEnglish.setText(String.valueOf(student1.getScore().getEnglish()));
                    editTextChinese.setText(String.valueOf(student1.getScore().getChinese()));
                    textViewGrade.setText(String.valueOf(student1.getScore().getGrade()));

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

}
