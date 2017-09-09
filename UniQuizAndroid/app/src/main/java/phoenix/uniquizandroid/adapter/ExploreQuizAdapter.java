package phoenix.uniquizandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import phoenix.uniquizandroid.R;
import phoenix.uniquizandroid.activity.QuizActivity;
import phoenix.uniquizandroid.dto.QuizDTO;
import phoenix.uniquizandroid.dto.SimplifiedQuizDTO;

/**
 * Created by Luis Gouveia on 09/09/2017.
 */

public class ExploreQuizAdapter extends RecyclerView.Adapter<ExploreQuizAdapter.ViewHolder>{

    private Context mContext;
    private QuizDTO[] mQuizzes;

    public ExploreQuizAdapter(Context context, QuizDTO[] quizzes){
        mContext = context;
        mQuizzes = quizzes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_card_view, parent, false);
        return new ViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(ViewHolder view, int position) {

        ViewHolder holder = (ViewHolder)view;
        QuizDTO quiz =  mQuizzes[position];
        holder.position = position;
        holder.quizName.setText(quiz.getTitle());
        holder.quizDifficulty.setText(quiz.getDifficulty());
        holder.extras = new Intent(mContext, QuizActivity.class);
        holder.extras.putExtra("quiz", quiz);

    }

    public void changeViewContent (QuizDTO[] newContent){
        mQuizzes = newContent;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mQuizzes.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        int position;
        TextView quizName;
        TextView quizDifficulty;
        Intent extras;

        public ViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(extras);
                }
            });
            quizName = (TextView) itemView.findViewById(R.id.quiz_name);
            quizDifficulty = (TextView) itemView.findViewById(R.id.quiz_difficulty);


        }
    }
}
