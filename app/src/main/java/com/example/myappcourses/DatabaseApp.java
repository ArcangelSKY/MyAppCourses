package com.example.myappcourses;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CoursesEntityRoom.class,CoursesDetailsEntityRoom.class},version = 1)
public abstract class DatabaseApp extends RoomDatabase {
    public abstract CoursesDAO coursesDao();
}
