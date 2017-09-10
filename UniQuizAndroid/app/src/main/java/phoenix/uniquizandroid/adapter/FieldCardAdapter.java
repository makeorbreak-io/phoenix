package phoenix.uniquizandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import phoenix.uniquizandroid.R;
import phoenix.uniquizandroid.activity.CoursesActivity;
import phoenix.uniquizandroid.dto.FieldDTO;


/**
 * Created by Luis Gouveia on 08/09/2017.
 */

public class FieldCardAdapter extends RecyclerView.Adapter<FieldCardAdapter.ViewHolder>{


    private Context mContext;
    private FieldDTO[] mFields;

    public FieldCardAdapter(Context context, FieldDTO[] fields){
        mContext = context;
        mFields = fields;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_card_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FieldDTO field = mFields[position];
        holder.itemName.setText(field.getFieldName());
        holder.extras = new Intent(mContext, CoursesActivity.class);
        holder.extras.putExtra("field", field);
    }

    @Override
    public int getItemCount() {
        return mFields.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemName;
        Intent extras;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(extras);
                }
            });
            itemName = (TextView) itemView.findViewById(R.id.item_name);
        }
    }
}
