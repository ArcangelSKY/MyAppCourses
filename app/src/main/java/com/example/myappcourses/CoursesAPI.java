package com.example.myappcourses;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CoursesAPI {

    @GET
    public Call<List<Courses>> getCourses(@Url String url);

    @GET
    public Call<CoursesDetails> getCoursesDetails(@Url String url);
}
