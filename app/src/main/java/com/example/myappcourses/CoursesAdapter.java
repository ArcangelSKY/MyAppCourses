package com.example.myappcourses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {

    List<CoursesEntityRoom> coursesList;
    Context context;

    public CoursesAdapter(List<CoursesEntityRoom> coursesList, Context context) {
        this.coursesList = coursesList;
        this.context = context;
    }

    @NonNull
    @Override
    public CoursesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_course,parent,false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesAdapter.ViewHolder holder, int position) {
        final CoursesEntityRoom course = coursesList.get(position);
        try {

            Picasso.get()
                    .load(coursesList.get(position).getImage())
                    .into(holder.ivCourses);

            holder.tvTitleCourses
                    .setText(coursesList.get(position).getTitle());

            holder.tvStartCourses
                    .setText(coursesList.get(position).getStart());

            holder.itemView.setOnClickListener(new View.OnClickListener() {               //@Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, CoursesDetailsActivity.class)
                            .putExtra("id",coursesList.get(position).getId())
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {return coursesList.size();}


    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivCourses;
        public TextView tvTitleCourses;
        public TextView tvStartCourses;

        public ViewHolder(View itemView){
            super(itemView);
            ivCourses = (ImageView)itemView.findViewById(R.id.ivCourse);
            tvTitleCourses = (TextView)itemView.findViewById(R.id.tvTittleCourses);
            tvStartCourses = (TextView)itemView.findViewById(R.id.tvStartCourses);

        }
    }
}
