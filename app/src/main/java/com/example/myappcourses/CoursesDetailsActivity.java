package com.example.myappcourses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CoursesDetailsActivity extends AppCompatActivity {
    int coursesID;
    TextView tvCoursesTitleDetails;
    TextView tvCoursesDescriptionDetails;
    TextView tvStartDescriptionDetail;
    ImageView ivCoursesImageDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_details);

        coursesID = getIntent().getIntExtra("id",-1);
        tvCoursesTitleDetails = (TextView)findViewById(R.id.tvCoursesTitleDetail);
        tvCoursesDescriptionDetails = (TextView)findViewById(R.id.tvCoursesDescriptionDetails);
        tvStartDescriptionDetail = (TextView)findViewById(R.id.tvStartDescriptionDetail);
        ivCoursesImageDetail = (ImageView)findViewById(R.id.ivCoursesImageDetail);

        new LoadCoursesDetails().execute();


    }

    private class LoadCoursesDetails extends AsyncTask<String,Void,String>{
        CoursesDetailsEntityRoom coursesDetailsEntityRoom;

        @Override
        protected String doInBackground(String... strings) {
            coursesDetailsEntityRoom = CoursesDatabaseApp.databaseApp
                                                        .coursesDao()
                                                        .getAllCoursesDetailsById(coursesID);
            return null;
        }
        protected void onPostExecute(String result) {
            Picasso.get().load(coursesDetailsEntityRoom.getImage()).into(ivCoursesImageDetail);
            tvCoursesTitleDetails.setText(coursesDetailsEntityRoom.getTitle());
            tvStartDescriptionDetail.setText(coursesDetailsEntityRoom.getStart());
            tvCoursesDescriptionDetails.setText(coursesDetailsEntityRoom.getPreviewDescription());
        }
    }
}