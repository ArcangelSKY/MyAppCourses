package com.example.myappcourses;

import android.app.Application;


import androidx.room.Room;

public class CoursesDatabaseApp extends Application {
    public static DatabaseApp databaseApp;

    @Override
    public void onCreate() {
        super.onCreate();
        CoursesDatabaseApp.databaseApp = Room.databaseBuilder(this, DatabaseApp.class,"app").build();

    }
}
