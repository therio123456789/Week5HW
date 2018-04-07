package com.example.rio.week5hw;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rio.week5hw.Interface.RecyclerViewClickListener;

import java.util.ArrayList;

public class DataRecyclerViewAdapter extends RecyclerView.Adapter<DataRecyclerViewAdapter.DataViewHolder> {
    public static final String TAG = DataRecyclerViewAdapter.class.getSimpleName();
    private Context context;
    private RecyclerViewClickListener mListener;
    private ArrayList<Act> acts;

    public DataRecyclerViewAdapter(Context mContext,ArrayList<Act> mActs,RecyclerViewClickListener recyclerViewClickListener) {
        this.context = mContext;
        this.acts = mActs;
        this.mListener = recyclerViewClickListener;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        itemView = layoutInflater.inflate(R.layout.item_act, parent, false);
        return new DataViewHolder(itemView,mListener);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        Act act = acts.get(position);
        String name = act.getName();
        String deathline = "Due to " + act.getDeathLineString();
        String priority = act.getPriority();
        holder.txtName.setText(name);
        holder.txtDeathline.setText(deathline);
        holder.txtPriority.setText(priority);
    }

    @Override
    public int getItemCount() {
        return acts == null ? 0 : acts.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        private TextView txtName;
        private TextView txtDeathline;
        private TextView txtPriority;
        public DataViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            txtName = itemView.findViewById(R.id.txtName);
            txtDeathline = itemView.findViewById(R.id.txtDeathline);
            txtPriority = itemView.findViewById(R.id.txtPriority);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view,getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            mListener.onLongClick(view,getAdapterPosition());
            Log.d(TAG,"LongClick");
            return true;
        }
    }
}
