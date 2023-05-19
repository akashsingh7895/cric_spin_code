package com.avssolution.fancylivecricketscore.CricketAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.avssolution.fancylivecricketscore.CricketModel.Upcomming;
import com.avssolution.fancylivecricketscore.R;

import java.util.ArrayList;

public class Matches extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    boolean mCheckLive;
    LayoutInflater mLayoutInflater;
    ArrayList<Upcomming.AllMatch> upcomingMatches;

    public Matches(Context context2, ArrayList<Upcomming.AllMatch> arrayList, boolean z) {
        this.upcomingMatches = arrayList;
        this.context = context2;
        this.mCheckLive = z;
        this.mLayoutInflater = (LayoutInflater) context2.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        return new ViewHolder(this.mLayoutInflater.inflate(R.layout.ic_real_matches, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        try {
            if (getItemViewType(i) == 1) {
                ViewHolder viewHolder2 = (ViewHolder) viewHolder;
                Upcomming.AllMatch allMatch = this.upcomingMatches.get(i);
                viewHolder2.txtTitle.setText(allMatch.getTitle());
                Log.e(" getVenue ", "" + allMatch.getVenue());
                viewHolder2.txtVenue.setText(allMatch.getVenue());
                viewHolder2.txtMatchTime.setText(allMatch.getMatchtime());
                viewHolder2.txtTeamAName.setText(allMatch.getTeamA());
                viewHolder2.txtTeamBName.setText(allMatch.getTeamB());
                if (allMatch.getTeamAImage() != null) {
                    RequestManager with = Glide.with(this.context);
                    with.load(allMatch.getImageUrl() + allMatch.getTeamAImage()).apply((BaseRequestOptions<?>) ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().placeholder(R.mipmap.ic_launcher)).centerInside()).error(R.mipmap.ic_launcher))).into(viewHolder2.imgTeamA);
                }
                if (allMatch.getTeamBImage() != null) {
                    RequestManager with2 = Glide.with(this.context);
                    with2.load(allMatch.getImageUrl() + allMatch.getTeamBImage()).apply((BaseRequestOptions<?>) ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().placeholder(R.mipmap.ic_launcher)).centerInside()).error(R.mipmap.ic_launcher))).into(viewHolder2.imgTeamB);
                }
                if (allMatch.getResult() != null) {
                    if (allMatch.getResult().length() > 0) {
                        viewHolder2.txtResult.setText(allMatch.getResult());
                        return;
                    }
                }
                viewHolder2.txtResult.setVisibility(View.GONE);
            } else if (getItemViewType(i) == 2) {
            }
        } catch (Exception e) {

        }
    }

    @Override
    public int getItemViewType(int i) {
        return (i == 0 || i % 4 != 0) ? 1 : 2;
    }

    public Object getItem(int i) {
        return this.upcomingMatches.get(i);
    }

    @Override
    public int getItemCount() {
        return this.upcomingMatches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgTeamA, imgTeamB;
        TextView txtMatchTime, txtResult, txtTeamAName, txtTeamBName, txtTitle, txtVenue;

        public ViewHolder(View view) {
            super(view);
            txtTitle = view.findViewById(R.id.txtTitle);
            txtResult = view.findViewById(R.id.txtResult);
            txtVenue = view.findViewById(R.id.txtVenue);
            txtMatchTime = view.findViewById(R.id.txtMatchTime);
            txtTeamAName = view.findViewById(R.id.txtLeftSideTeam);
            txtTeamBName = view.findViewById(R.id.txtRightSideTeam);
            imgTeamA = view.findViewById(R.id.leftSideTeamImg);
            imgTeamB = view.findViewById(R.id.rightSideTeamImg);
        }
    }


}
