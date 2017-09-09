package phoenix.uniquizandroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import phoenix.uniquizandroid.R;
import phoenix.uniquizandroid.dto.FieldDTO;


/**
 * Created by Luis Gouveia on 08/09/2017.
 */

public class FieldCardAdapter extends RecyclerView.Adapter<FieldCardAdapter.ViewHolder>{


    private FieldDTO[] mFields;

    public FieldCardAdapter(FieldDTO[] fields){
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
    }

    @Override
    public int getItemCount() {
        return mFields.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemName;
        public ViewHolder(View itemView) {
            super(itemView);

            itemName = (TextView) itemView.findViewById(R.id.item_name);
        }
    }
}
