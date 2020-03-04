package com.androidschool.denis.okuma;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> implements Filterable {

    private ArrayList<ExampleItem> mExampleList;
    private ArrayList<ExampleItem> exampleListFull;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mAlarmNumber;
        public TextView mAlarmName;
        public TextView mAlarmDescription;

        ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mAlarmNumber = itemView.findViewById(R.id.alarmNumber);
            mAlarmName = itemView.findViewById(R.id.alarmName);
            mAlarmDescription = itemView.findViewById(R.id.alarmDescription);

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

    ExampleAdapter(ArrayList<ExampleItem> mExampleList) {
        this.mExampleList = mExampleList; //add this
        exampleListFull = new ArrayList<>(mExampleList);
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);

        holder.mAlarmNumber.setText(currentItem.getAlarmNumber());
        holder.mAlarmName.setText(currentItem.getAlarmName());

        String desc = currentItem.getAlarmDescription();
        desc = desc.replace("\n", "<br>");
        holder.mAlarmDescription.setText(Html.fromHtml("<PRE>"+desc+"</PRE>"));
        //holder.mAlarmDescription.setText(Html.fromHtml("<PRE>"+currentItem.getAlarmDescription()+"</PRE>"));
        //holder.mAlarmDescription.setText(Html.fromHtml(Html.fromHtml("<PRE>"+currentItem.getAlarmDescription()+"</PRE>").toString()));
        //textView.setText(Html.fromHtml(Html.fromHtml(mHtmlString).toString()));
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public void filterList(ArrayList<ExampleItem> filteredList){
        mExampleList = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    public Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ExampleItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toUpperCase().trim();

                for (ExampleItem item : exampleListFull) {
                    if (item.getAlarmName().contains(filterPattern)||item.getAlarmNumber().contains(filterPattern)) {
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
            mExampleList.clear();
            mExampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


}

