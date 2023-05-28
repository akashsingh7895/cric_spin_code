package com.avssolution.fancylivecricketscore.LiveSeries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avssolution.fancylivecricketscore.R;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.viewHolder> {

    Context context;
    List<SeriesModel> list;

    public SeriesAdapter(Context context, List<SeriesModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.series_item_layout,parent,false);
        return new viewHolder(view);
       // return new viewHolder(LayoutInflater.from(context).inflate(R.layout.series_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.seriesName.setText(list.get(position).seriesname);
        holder.startDate.setText(list.get(position).startdate);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView seriesName,startDate;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            seriesName = itemView.findViewById(R.id.series_name);
            startDate = itemView.findViewById(R.id.start_date);
        }
    }
}
