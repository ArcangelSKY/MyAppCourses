package com.example.myappcourses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Courses> listCourses;
    List<CoursesDetails> listCourseDetails;
    List<CoursesEntityRoom> listCoursesEntity;
    RecyclerView rvCourses;
    CoursesAdapter coursesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCourses = new ArrayList<Courses>();
        listCourseDetails = new ArrayList<CoursesDetails>();
        rvCourses = (RecyclerView)findViewById(R.id.rvCourses);
        rvCourses.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        loadCourse();
    }

    private void loadCourse() {
        new LoadCourse().execute();
    }

    private class LoadCourse extends AsyncTask<String,Void,String>{


        @Override
        protected String doInBackground(String... strings) {


            try {
                listCourses = getRetrofict(0).create(CoursesAPI.class)
                        .getCourses("courses")
                        .execute()
                        .body();

                for (int i = 0; i < listCourses.size();i++){

                    CoursesDetails coursesDetails = getRetrofict(1).create(CoursesAPI.class).getCoursesDetails(Integer.toString(listCourses.get(i).getId())).execute().body();

                    listCourseDetails.add(coursesDetails);
                }
                loadCoursesIntoDB();
                listCoursesEntity = CoursesDatabaseApp.databaseApp.coursesDao().getAllCourses();
            }catch (IOException e){
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(String s) {

            coursesAdapter = new CoursesAdapter(listCoursesEntity,getApplicationContext());
            rvCourses.setAdapter(coursesAdapter);
        }




        private void loadCoursesIntoDB(){
            try {
                for (int i = 0; i < listCourses.size();i++){
                    CoursesDatabaseApp.databaseApp
                                    .coursesDao()
                                    .addCourses(new CoursesEntityRoom(
                                            listCourses.get(i).getId(),
                                            listCourses.get(i).getTitle(),
                                            listCourses.get(i).getPreviewDescription(),
                                            listCourses.get(i).getImage(),
                                            listCourses.get(i).getWeeks(),
                                            listCourses.get(i).getStart()
                                    ));
                }

                for (int i = 0; i < listCourseDetails.size();i++){
                    CoursesDatabaseApp.databaseApp
                            .coursesDao()
                            .addCoursesDetails(new CoursesDetailsEntityRoom(
                                    listCourseDetails.get(i).getId(),
                                    listCourseDetails.get(i).getTitle(),
                                    listCourseDetails.get(i).getPreviewDescription(),
                                    listCourseDetails.get(i).getImage(),
                                    listCourseDetails.get(i).getWeeks(),
                                    listCourseDetails.get(i).getStart()
                            ));
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }

        // Conectar con API utilizando RETROFIT
        public Retrofit getRetrofict(int mode){

            if(mode==0){
                return new Retrofit
                        .Builder()
                        .baseUrl(getString(R.string.endPoint_Courses))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }else{
                return new Retrofit
                        .Builder()
                        .baseUrl(getString(R.string.endPoint_CoursesDetail))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

            }
        }

    }

}