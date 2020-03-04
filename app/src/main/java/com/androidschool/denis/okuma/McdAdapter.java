package com.androidschool.denis.okuma;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class McdAdapter extends RecyclerView.Adapter<McdAdapter.McdViewHolder> implements Filterable {

    private ArrayList<McdItem> mMcdList;
    private ArrayList<McdItem> mcdListFull;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public static class McdViewHolder extends RecyclerView.ViewHolder {

        public TextView mMcdName;
        public TextView mMcdDescription;

        McdViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            mMcdName = itemView.findViewById(R.id.mcdName);
            mMcdDescription = itemView.findViewById(R.id.mcdDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    McdAdapter(ArrayList<McdItem> mMcdList) {
        this.mMcdList = mMcdList; //add this
        mcdListFull = new ArrayList<>(mMcdList);
    }

    @NonNull
    @Override
    public McdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mcd_item, parent, false);
        McdViewHolder mvh = new McdViewHolder(v, mListener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull McdViewHolder holder, int position) {
        McdItem currentItem = mMcdList.get(position);


        holder.mMcdName.setText(currentItem.getMcdName());

        String desc = currentItem.getMcdDescription();
        desc = desc.replace("\n", "<br>");
        holder.mMcdDescription.setText(Html.fromHtml("<PRE>"+desc+"</PRE>"));

    }

    @Override
    public int getItemCount() {
        return mMcdList.size();
    }

    public void filterList(ArrayList<McdItem> filteredList){
        mMcdList = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return mcdFilter;
    }

    public Filter mcdFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<McdItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mcdListFull);
            } else {
                String filterPattern = constraint.toString().toUpperCase().trim();

                for (McdItem item : mcdListFull) {
                    if (item.getMcdName().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mMcdList.clear();
            mMcdList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


}

