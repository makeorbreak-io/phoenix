package phoenix.uniquizandroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import phoenix.uniquizandroid.R;
import phoenix.uniquizandroid.dto.FieldDTO;

import phoenix.uniquizandroid.dto.SubjectDTO;


/**
 * Created by Luis Gouveia on 08/09/2017.
 */

public class SubjectCardAdapter extends RecyclerView.Adapter<SubjectCardAdapter.ViewHolder>{


    private SubjectDTO[] mSubjects;

    public SubjectCardAdapter(SubjectDTO[] subjects){
        mSubjects = subjects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_card_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SubjectDTO subject = mSubjects[position];
        holder.itemName.setText(subject.getSubjectName());
    }

    @Override
    public int getItemCount() {
        return mSubjects.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemName;
        public ViewHolder(View itemView) {
            super(itemView);

            itemName = (TextView) itemView.findViewById(R.id.item_name);
        }
    }
}

