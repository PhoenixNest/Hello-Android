package com.dev.a0302paging.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDataBase extends RoomDatabase {

    static StudentDataBase INSTANCE;

    public static synchronized StudentDataBase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, StudentDataBase.class, "student_database").build();
        }

        return INSTANCE;
    }

    public abstract StudentDao getStudentDao();
}
