package phoenix.uniquizandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import phoenix.uniquizandroid.R;
import phoenix.uniquizandroid.activity.QuizzesActivity;
import phoenix.uniquizandroid.dto.CourseDTO;

/**
 * Created by Luis Gouveia on 10/09/2017.
 */

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {

    private Context mContext;
    private CourseDTO[] mCourses;

    public CoursesAdapter(Context context, CourseDTO[] courses) {
        mContext = context;
        mCourses = courses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);
        return new ViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(ViewHolder view, int position) {

        ViewHolder holder = (ViewHolder) view;
        CourseDTO course = mCourses[position];
        holder.position = position;
        holder.courseName.setText(course.getCourseName());
        holder.extras = new Intent(mContext, QuizzesActivity.class);
        holder.extras.putExtra("course", course);

    }


    @Override
    public int getItemCount() {
        return mCourses.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        int position;
        TextView courseName;
        Intent extras;

        public ViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(extras);
                }
            });
            courseName = (TextView) itemView.findViewById(R.id.course_name);
        }
    }
}