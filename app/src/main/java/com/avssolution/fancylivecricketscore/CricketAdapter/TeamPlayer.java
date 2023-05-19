package com.avssolution.fancylivecricketscore.CricketAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
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
import com.avssolution.fancylivecricketscore.MyApplications;
import com.avssolution.fancylivecricketscore.CricketModel.PlayerData;
import com.avssolution.fancylivecricketscore.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TeamPlayer extends RecyclerView.Adapter<TeamPlayer.ViewHolder> {
    private Context context;
    String imgURL;
    SharedPreferences mPrefrences;
    ArrayList<PlayerData.Playerslist> playerList;
    String st_theme;

    public TeamPlayer(Context context2, ArrayList<PlayerData.Playerslist> arrayList, String str) {
        SharedPreferences prefrences = MyApplications.getInstance().getPrefrences();
        this.mPrefrences = prefrences;
        this.st_theme = prefrences.getString("theme", null);
        this.playerList = arrayList;
        this.context = context2;
        this.imgURL = str;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ic_similliar_adapter, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        try {
            PlayerData.Playerslist playerslist = this.playerList.get(i);
            if (playerslist.getIsnotout().intValue() == 1) {
                TextView textView = viewHolder.txtPlayerName;
                textView.setText(playerslist.getPlayerName() + "*");
            } else {
                viewHolder.txtPlayerName.setText(playerslist.getPlayerName());
                mSetColor(viewHolder, this.context.getResources().getColor(R.color.white));
            }
            if (playerslist.getOutby() == null || playerslist.getOutby().trim().length() <= 0) {
                viewHolder.outBy.setVisibility(View.GONE);
            } else {
                TextView textView2 = viewHolder.outBy;
                textView2.setText(playerslist.getOutby() + "");
                viewHolder.outBy.setVisibility(View.VISIBLE);
            }
            TextView textView3 = viewHolder.txtPlayerScore;
            textView3.setText(playerslist.getRuns() + "");
            TextView textView4 = viewHolder.txtPlayerBalls;
            textView4.setText(playerslist.getBalls() + "");
            TextView textView5 = viewHolder.txtPlayerFours;
            textView5.setText(playerslist.getFour() + "");
            TextView textView6 = viewHolder.txtPlayerSix;
            textView6.setText(playerslist.getSix() + "");
            if (playerslist.getRuns().intValue() != 0) {
                TextView textView7 = viewHolder.txtPlayerSR;
                StringBuilder sb = new StringBuilder();
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                double intValue = (double) playerslist.getRuns().intValue();
                double intValue2 = (double) playerslist.getBalls().intValue();
                Double.isNaN(intValue);
                Double.isNaN(intValue2);
                sb.append(decimalFormat.format((intValue / intValue2) * 100.0d));
                sb.append("");
                textView7.setText(sb.toString());
            } else {
                viewHolder.txtPlayerSR.setText("0.00");
            }
            if (!TextUtils.isEmpty(playerslist.getPlayerImage())) {
                RequestManager with = Glide.with(this.context);
                with.load(this.imgURL + playerslist.getPlayerImage()).apply((BaseRequestOptions<?>) ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().placeholder(R.mipmap.ic_launcher)).centerInside()).error(R.mipmap.ic_launcher))).into(viewHolder.imgPlayer);
            }
        } catch (Exception unused) {
        }
    }

    private void mSetColor(ViewHolder viewHolder, int i) {
        viewHolder.txtPlayerName.setTextColor(i);
        viewHolder.txtPlayerScore.setTextColor(i);
        viewHolder.txtPlayerBalls.setTextColor(i);
        viewHolder.txtPlayerFours.setTextColor(i);
        viewHolder.txtPlayerSix.setTextColor(i);
        viewHolder.txtPlayerSR.setTextColor(i);
    }

    public Object getItem(int i) {
        return this.playerList.get(i);
    }

    @Override
    public int getItemCount() {
        return this.playerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPlayer;
        TextView outBy, txtPlayerBalls, txtPlayerFours, txtPlayerName, txtPlayerSR, txtPlayerScore, txtPlayerSix;

        public ViewHolder(View view) {
            super(view);
            txtPlayerName = view.findViewById(R.id.txtPlayerName);
            txtPlayerScore = view.findViewById(R.id.txtPlayerScore);
            txtPlayerBalls = view.findViewById(R.id.txtPlayerBalls);
            txtPlayerFours = view.findViewById(R.id.txtPlayerFours);
            txtPlayerSix = view.findViewById(R.id.txtPlayerSix);
            txtPlayerSR = view.findViewById(R.id.txtPlayerSR);
            outBy = view.findViewById(R.id.outBy);
            imgPlayer = view.findViewById(R.id.img_player);
        }
    }
}
