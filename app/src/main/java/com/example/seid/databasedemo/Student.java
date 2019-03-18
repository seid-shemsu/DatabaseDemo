package com.example.seid.databasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SEID on 3/15/2019.
 */

public class Student extends SQLiteOpenHelper {

    public Student(Context context) {
        super(context, "stud.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student (name text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldTable, int newTable) {
        db.execSQL("drop table if exists student");
        onCreate(db);
    }
}
