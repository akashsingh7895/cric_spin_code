package com.avssolution.fancylivecricketscore.CricketAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.avssolution.fancylivecricketscore.CricketModel.OddData;
import com.avssolution.fancylivecricketscore.R;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class MatchesOneThree extends RecyclerView.Adapter<MatchesOneThree.ViewHolder> {

    private Context context;
    ArrayList<OddData.Matchst> oddsMatches;

    public MatchesOneThree(Context context2, ArrayList<OddData.Matchst> arrayList) {
        this.oddsMatches = arrayList;
        this.context = context2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ic_odds_matches_adapter, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        try {
            OddData.Matchst matchst = this.oddsMatches.get(i);
            viewHolder.SessionA.setText(matchst.getSessionA());
            viewHolder.SessionB.setText(matchst.getSessionB());
            viewHolder.Battingteam.setText(matchst.getBattingteam());
            viewHolder.MrateA.setText(matchst.getMrateA());
            viewHolder.MrateB.setText(matchst.getMrateB());
            viewHolder.overs.setText(matchst.getOvers());
            viewHolder.Score.setText(matchst.getScore());
            if (matchst.getSubdate().contains("")) {
                viewHolder.time.setText(matchst.getSubdate().substring(matchst.getSubdate().indexOf(StringUtils.SPACE), matchst.getSubdate().lastIndexOf(":")));
            }
        } catch (Exception e) {

        }
    }

    public Object getItem(int i) {
        return this.oddsMatches.get(i);
    }

    @Override
    public int getItemCount() {
        return this.oddsMatches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView Battingteam, MrateA, MrateB, Score, SessionA, SessionB, overs, time;
        LinearLayout layt_frst;


        public ViewHolder(View view) {
            super(view);
            SessionA = view.findViewById(R.id.SessionA);
            SessionB = view.findViewById(R.id.SessionB);
            Battingteam = view.findViewById(R.id.Battingteam);
            MrateA = view.findViewById(R.id.MrateA);
            MrateB = view.findViewById(R.id.MrateB);
            overs = view.findViewById(R.id.oversions);
            Score = view.findViewById(R.id.Score);
            time = view.findViewById(R.id.time);
            layt_frst = view.findViewById(R.id.firstRow);
        }
    }
}
