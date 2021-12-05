package com.dev.a0302paging.db;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface StudentDao {
    @Insert
    void insertStudent(Student... student);

    @Query("DELETE FROM student_table")
    void deleteAllStudent();

    @Query("SELECT * FROM student_table ORDER BY id")
    DataSource.Factory<Integer, Student> getAllStudent();

}
