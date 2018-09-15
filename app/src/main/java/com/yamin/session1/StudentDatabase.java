package com.yamin.session1;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Yamin on 9/10/2018.
 */

@Database(entities = { Student.class }, version = 1)
public abstract class StudentDatabase extends RoomDatabase {

    private static final String DB_NAME = "studentsDatabase.db";
    private static  StudentDatabase instance;

    static synchronized StudentDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static StudentDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                StudentDatabase.class,
                DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    public abstract StudentDao getStudentDao();
}