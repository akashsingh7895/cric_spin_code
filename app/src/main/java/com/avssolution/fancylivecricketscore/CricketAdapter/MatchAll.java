package com.avssolution.fancylivecricketscore.CricketAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.avssolution.fancylivecricketscore.CricketModel.DataJason;
import com.avssolution.fancylivecricketscore.CricketModel.DataRun;
import com.avssolution.fancylivecricketscore.CricketModel.MainData;
import com.avssolution.fancylivecricketscore.CricketModel.MainRunData;
import com.avssolution.fancylivecricketscore.CricketModel.AllMatch;
import com.avssolution.fancylivecricketscore.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MatchAll extends RecyclerView.Adapter<MatchAll.ViewHolder> {
    private Context context;
    private String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    private String imageUrl;
    ArrayList<AllMatch> upcomingMatches;

    public MatchAll(Context context2, ArrayList<AllMatch> arrayList, String str) {
        this.upcomingMatches = arrayList;
        this.context = context2;
        this.imageUrl = str;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.all_matches_adapter, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        try {
            AllMatch cricimultimatchpojo = this.upcomingMatches.get(i);
            MainData cricimainjsondata = null;
            if (cricimultimatchpojo.getJsondata().length() > 0) {
                cricimainjsondata = (MainData) new Gson().fromJson(cricimultimatchpojo.getJsondata(), MainData.class);
            }
            TextView textView = viewHolder.txtTeamAName;
            textView.setText(cricimultimatchpojo.getTeama() + "");
            TextView textView2 = viewHolder.txtTeamBName;
            textView2.setText(cricimultimatchpojo.getTeamb() + "");
            if (cricimultimatchpojo.getTeamaimage() != null) {
                RequestManager with = Glide.with(this.context);
                with.load(this.imageUrl + cricimultimatchpojo.getTeamaimage()).apply((BaseRequestOptions<?>) ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().placeholder(R.mipmap.ic_launcher)).centerInside()).error(R.mipmap.ic_launcher))).into(viewHolder.imgTeamA);
            }
            if (cricimultimatchpojo.getTeambimage() != null) {
                RequestManager with2 = Glide.with(this.context);
                with2.load(this.imageUrl + cricimultimatchpojo.getTeambimage()).apply((BaseRequestOptions<?>) ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().placeholder(R.mipmap.ic_launcher)).centerInside()).error(R.mipmap.ic_launcher))).into(viewHolder.imgTeamB);
            }
            if (cricimultimatchpojo.getResult().trim().length() > 0) {
                viewHolder.liveTxt.setVisibility(View.VISIBLE);
                viewHolder.liveTxt.setText("finished");
                viewHolder.liveTxt.setTextColor(Color.parseColor("#000000"));
                viewHolder.rateLL.setVisibility(View.GONE);
                viewHolder.overs.setVisibility(View.GONE);
                viewHolder.oversb.setVisibility(View.GONE);
                viewHolder.txtResult.setVisibility(View.VISIBLE);
                TextView textView3 = viewHolder.txtResult;
                textView3.setText(cricimultimatchpojo.getResult() + "");
                viewHolder.scorea.setVisibility(View.GONE);
                viewHolder.scoreb.setVisibility(View.GONE);
                viewHolder.datagoji.setVisibility(View.GONE);

            } else {
                if (this.currentDate.trim().equals(cricimultimatchpojo.getMatchdate().trim())) {
                    viewHolder.liveTxt.setText("Upcoming");
                    viewHolder.scorea.setVisibility(View.GONE);
                    viewHolder.scoreb.setVisibility(View.GONE);
                    viewHolder.rateLL.setVisibility(View.GONE);
                    viewHolder.overs.setVisibility(View.GONE);
                    viewHolder.oversb.setVisibility(View.GONE);
                    viewHolder.txtResult.setVisibility(View.GONE);
                    viewHolder.datagoji.setVisibility(View.GONE);
                } else {
                    viewHolder.liveTxt.setText("Upcoming");
                    viewHolder.scorea.setVisibility(View.GONE);
                    viewHolder.scoreb.setVisibility(View.GONE);
                    viewHolder.rateLL.setVisibility(View.GONE);
                    viewHolder.overs.setVisibility(View.GONE);
                    viewHolder.oversb.setVisibility(View.GONE);
                    viewHolder.txtResult.setVisibility(View.GONE);
                    viewHolder.datagoji.setVisibility(View.GONE);
                }
                if (cricimainjsondata != null && cricimultimatchpojo.getJsondata().length() > 0) {
                    DataJason jsondata = cricimainjsondata.getJsondata();
                    DataRun jsonruns = ((MainRunData) new Gson().fromJson(cricimultimatchpojo.getJsonruns(), MainRunData.class)).getJsonruns();
                    if (!jsondata.getBowler().equalsIgnoreCase("0")) {
                        viewHolder.liveTxt.setText("Live");
                        viewHolder.rateLL.setVisibility(View.VISIBLE);
                        viewHolder.scorea.setVisibility(View.VISIBLE);
                        viewHolder.scoreb.setVisibility(View.VISIBLE);
                        viewHolder.overs.setVisibility(View.VISIBLE);
                        viewHolder.oversb.setVisibility(View.GONE);
                        viewHolder.txtResult.setVisibility(View.GONE);
                        viewHolder.datagoji.setVisibility(View.VISIBLE);
                    }
                    TextView textView4 = viewHolder.scorea;
                    textView4.setText(jsondata.getWicketA() + "");
                    TextView textView5 = viewHolder.scoreb;
                    textView5.setText(jsondata.getWicketB() + "");
                    TextView textView6 = viewHolder.overs;
                    textView6.setText("Overs (" + jsondata.getOversA().trim() + ")");
                    TextView textView7 = viewHolder.oversb;
                    textView7.setText("Overs (" + jsondata.getOversB().trim() + ")");
                    TextView textView8 = viewHolder.rate;
                    textView8.setText(jsonruns.getRateA() + "");
                    TextView textView9 = viewHolder.rate2;
                    textView9.setText(jsonruns.getRateB() + "");
                    TextView textView10 = viewHolder.txtTeamAName;
                    textView10.setText(jsondata.getTeamA() + "");
                    TextView textView11 = viewHolder.txtTeamBName;
                    textView11.setText(jsondata.getTeamB() + "");
                    TextView textView12 = viewHolder.teamnaem;
                    textView12.setText(jsondata.getTeamA() + "");
                    TextView textView13 = viewHolder.teamnaem2;
                    textView13.setText(jsondata.getTeamB() + "");
                    if (jsondata.getTeamABanner() != null) {
                        RequestManager with3 = Glide.with(this.context);
                        with3.load(this.imageUrl + jsondata.getTeamABanner()).apply((BaseRequestOptions<?>) ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().placeholder(R.mipmap.ic_launcher)).centerInside()).error(R.mipmap.ic_launcher))).into(viewHolder.imgTeamA);
                    }
                    if (jsondata.getTeamBBanner() != null) {
                        RequestManager with4 = Glide.with(this.context);
                        with4.load(this.imageUrl + jsondata.getTeamBBanner()).apply((BaseRequestOptions<?>) ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().placeholder(R.mipmap.ic_launcher)).centerInside()).error(R.mipmap.ic_launcher))).into(viewHolder.imgTeamB);
                    }
                    if (jsonruns.getFav().equalsIgnoreCase(jsondata.getTeamA())) {
                        viewHolder.favTeamA.setVisibility(View.VISIBLE);
                        viewHolder.favTeamB.setVisibility(View.GONE);
                        viewHolder.teamnaem.setVisibility(View.VISIBLE);
                        viewHolder.teamnaem2.setVisibility(View.GONE);
                    } else if (jsonruns.getFav().equalsIgnoreCase(jsondata.getTeamB())) {
                        viewHolder.favTeamA.setVisibility(View.GONE);
                        viewHolder.favTeamB.setVisibility(View.VISIBLE);
                        viewHolder.teamnaem.setVisibility(View.GONE);
                        viewHolder.teamnaem2.setVisibility(View.VISIBLE);
                  
                    } else {
                        viewHolder.favTeamA.setVisibility(View.VISIBLE);
                        viewHolder.favTeamB.setVisibility(View.VISIBLE);
                        viewHolder.teamnaem.setVisibility(View.VISIBLE);
                        viewHolder.teamnaem2.setVisibility(View.VISIBLE);
                    }
                    if (jsonruns.getRateA().equalsIgnoreCase("0")) {
                        viewHolder.rateLL.setVisibility(View.GONE);
                        viewHolder.favTeamA.setVisibility(View.GONE);
                        viewHolder.favTeamB.setVisibility(View.GONE);
                        viewHolder.teamnaem.setVisibility(View.GONE);
                        viewHolder.teamnaem2.setVisibility(View.GONE);
                    }
                    if (jsondata.getMatchtype().equals("Test")) {
                        viewHolder.rateLL.setVisibility(View.GONE);
                    } else {
                        viewHolder.rateLL.setVisibility(View.VISIBLE);
                    }
                }
            }
            TextView textView12 = viewHolder.txtTitle;
            textView12.setText(cricimultimatchpojo.getTitle() + "");
            TextView textView13 = viewHolder.txtTime;
            textView13.setText(cricimultimatchpojo.getMatchtime() + "");
        } catch (Exception e) {

        }
    }

    public Object getItem(int i) {
        return this.upcomingMatches.get(i);
    }

    @Override
    public int getItemCount() {
        return this.upcomingMatches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView favTeamA, favTeamB, imgTeamA, imgTeamB;
        TextView liveTxt, overs, oversb, rate, rate2, scorea, scoreb, txtResult, txtTeamAName,teamnaem,teamnaem2, txtTeamBName, txtTime, txtTitle;
        LinearLayout rateLL;
        RelativeLayout datagoji;

        public ViewHolder(View view) {
            super(view);
            datagoji =  view.findViewById(R.id.datagoji);
            txtTime =  view.findViewById(R.id.txtTime);
            favTeamA = view.findViewById(R.id.favTeamA);
            favTeamB = view.findViewById(R.id.favTeamB);
            txtResult =  view.findViewById(R.id.txtResult);
            rateLL =  view.findViewById(R.id.rateLL);
            teamnaem =  view.findViewById(R.id.teamnaem);
            teamnaem2 =  view.findViewById(R.id.teamnaem2);
            liveTxt =  view.findViewById(R.id.liveTxt);
            txtTitle =  view.findViewById(R.id.txtTitle);
            scorea =  view.findViewById(R.id.scorea);
            scoreb =  view.findViewById(R.id.scoreb);
            rate =  view.findViewById(R.id.rate);
            rate2 =  view.findViewById(R.id.rate2);
            overs =  view.findViewById(R.id.overs);
            oversb =  view.findViewById(R.id.oversb);
            txtTeamAName =  view.findViewById(R.id.txtLeftSideTeam);
            txtTeamBName =  view.findViewById(R.id.txtRightSideTeam);
            imgTeamA = view.findViewById(R.id.leftSideTeamImg);
            imgTeamB = view.findViewById(R.id.rightSideTeamImg);
        }
    }
}
