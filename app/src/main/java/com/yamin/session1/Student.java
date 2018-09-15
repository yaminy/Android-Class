package com.yamin.session1;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Yamin on 9/10/2018.
 */
@Entity(tableName = "danesh")
public class Student {

    String name ;
    @NonNull
    @PrimaryKey
    String id ;
    String phone;

    public Student(String name, String id, String phone) {
        this.name = name;
        this.id = id;
        this.phone = phone;
    }
}
