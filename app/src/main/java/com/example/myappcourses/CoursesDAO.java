package com.example.myappcourses;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CoursesDAO {
    @Insert
    public void addCourses(CoursesEntityRoom courses);

    @Query("SELECT * from course_entity")
    public List<CoursesEntityRoom> getAllCourses();

    @Insert
    public void addCoursesDetails(CoursesDetailsEntityRoom coursesDetails);


    @Query("SELECT * from courses_details WHERE id=:id")
    public CoursesDetailsEntityRoom getAllCoursesDetailsById(int id);
}
