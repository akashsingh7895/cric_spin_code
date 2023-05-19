package com.avssolution.fancylivecricketscore.CricketAdapter;

import android.content.Context;
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
import com.avssolution.fancylivecricketscore.CricketModel.ResultData;
import com.avssolution.fancylivecricketscore.R;

import java.util.ArrayList;

public class TeamResult extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    String imgeURL;
    LayoutInflater mLayoutInflater;
    ArrayList<ResultData.AllMatch> oddsMatches;

    public TeamResult(Context context2, ArrayList<ResultData.AllMatch> arrayList, String str) {
        this.oddsMatches = arrayList;
        this.context = context2;
        this.imgeURL = str;
        this.mLayoutInflater = (LayoutInflater) context2.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        return new ViewHolder(this.mLayoutInflater.inflate(R.layout.ic_result_team_adapter, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        try {
            ViewHolder viewHolder2 = (ViewHolder) viewHolder;
            ResultData.AllMatch allMatch = this.oddsMatches.get(i);
            viewHolder2.txtTitle.setText(allMatch.getTitle());
            viewHolder2.txtVenue.setText(allMatch.getVenue());
            viewHolder2.txtMatchTime.setText(allMatch.getMatchtime());
            viewHolder2.txtTeamAName.setText(allMatch.getTeamA());
            viewHolder2.txtTeamBName.setText(allMatch.getTeamB());
            viewHolder2.txtResult.setText(allMatch.getResult());
            if (allMatch.getTeamAImage() != null) {
                RequestManager with = Glide.with(this.context);
                with.load(this.imgeURL + allMatch.getTeamAImage()).apply((BaseRequestOptions<?>) ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().placeholder(R.drawable.ic_white_player)).centerInside()).error(R.drawable.ic_white_player))).into(viewHolder2.imgTeamA);
            }
            if (allMatch.getTeamBImage() != null) {
                RequestManager with2 = Glide.with(this.context);
                with2.load(this.imgeURL + allMatch.getTeamBImage()).apply((BaseRequestOptions<?>) ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().placeholder(R.drawable.ic_white_player)).centerInside()).error(R.drawable.ic_white_player))).into(viewHolder2.imgTeamB);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getItem(int i) {
        return this.oddsMatches.get(i);
    }

    @Override
    public int getItemViewType(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return this.oddsMatches.size();
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
