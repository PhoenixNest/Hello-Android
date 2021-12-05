package com.dev.a0302paging;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.a0302paging.db.Student;
import com.dev.a0302paging.db.StudentDao;
import com.dev.a0302paging.db.StudentDataBase;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private Button btn, btn2;
    private StudentDao studentDao;
    private StudentDataBase studentDataBase;

    private RVAdapter rvAdapter;

    private LiveData<PagedList<Student>> allStudentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.recyclerView);
        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        rvAdapter = new RVAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv.setAdapter(rvAdapter);
        studentDataBase = StudentDataBase.getINSTANCE(this);
        studentDao = studentDataBase.getStudentDao();

        allStudentList = new LivePagedListBuilder<>(studentDao.getAllStudent(), 20).build();
        allStudentList.observe(this, new Observer<PagedList<Student>>() {
            @Override
            public void onChanged(final PagedList<Student> students) {
                rvAdapter.submitList(students);
                students.addWeakCallback(null, new PagedList.Callback() {
                    @Override
                    public void onChanged(int position, int count) {
                        Log.d("TAG", "OnChange" + students);
                    }

                    @Override
                    public void onInserted(int position, int count) {

                    }

                    @Override
                    public void onRemoved(int position, int count) {

                    }
                });
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student[] student = new Student[1000];
                for (int i = 0; i < 1000; i++) {
                    Student student1 = new Student();
                    student1.setStudentNumber(i);
                    student[i] = student1;
                }
                new InsertAsyncTask(studentDao).execute(student);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ClearAsyncTask(studentDao).execute();
            }
        });
    }

    static class InsertAsyncTask extends AsyncTask<Student, Void, Void> {
        StudentDao studentDao;

        InsertAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insertStudent(students);
            return null;
        }
    }

    static class ClearAsyncTask extends AsyncTask<Void, Void, Void> {

        StudentDao studentDao;

        ClearAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentDao.deleteAllStudent();
            return null;
        }
    }

}
