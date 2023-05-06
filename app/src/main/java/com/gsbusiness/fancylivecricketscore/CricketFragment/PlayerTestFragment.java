package com.gsbusiness.fancylivecricketscore.CricketFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.exifinterface.media.ExifInterface;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gsbusiness.fancylivecricketscore.MyApplications;
import com.gsbusiness.fancylivecricketscore.CricketActivity.MainActivity;
import com.gsbusiness.fancylivecricketscore.CricketAdapter.TestHead;
import com.gsbusiness.fancylivecricketscore.CricketAdapter.TestHeade;
import com.gsbusiness.fancylivecricketscore.CricketAdapter.TeamHeading;
import com.gsbusiness.fancylivecricketscore.CricketModel.PlayerData;
import com.gsbusiness.fancylivecricketscore.R;
import com.mindorks.placeholderview.ExpandablePlaceHolderView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerTestFragment extends MainCenterFragment {

    public Context context;
    public ExpandablePlaceHolderView mExpandableView;
    public HashMap<String, PlayerData.Playerslist> mHasMap;
    private SharedPreferences mPrefrences;
    ArrayList<PlayerData.Playerslist> mTitleList;
    ArrayList<PlayerData.Playerslist> playersAInnigs1;
    ArrayList<PlayerData.Playerslist> playersAInnigs2;
    ArrayList<PlayerData.Playerslist> playersBInnigs1;
    ArrayList<PlayerData.Playerslist> playersBInnigs2;
    SwipeRefreshLayout swipeView;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.ic_test_playing_xi, viewGroup, false);
        this.mPrefrences = MyApplications.getInstance().getPrefrences();
        mInitRes(inflate);
        return inflate;
    }

    private void mInitRes(View view) {
        this.context = getActivity();
        this.mExpandableView = (ExpandablePlaceHolderView) view.findViewById(R.id.expandableView);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        this.swipeView = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {


            @Override
            public void onRefresh() {
                PlayerTestFragment.this.getAllPlayers();
            }
        });
        getAllPlayers();
    }

    public void getAllPlayers() {
        Log.e("getAllPlayers", "011");
        try {
            if (getActivity() != null) {
                if (isNetworkAvailable()) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("MatchId", "" + MainActivity.MatchId);
                    mGetRetroObject(this.baseURL).getAllPlayers(hashMap).enqueue(new Callback<PlayerData>() {


                        @Override
                        public void onResponse(Call<PlayerData> call, Response<PlayerData> response) {
                            try {
                                if (response.body().getSuccess().booleanValue()) {
                                    Log.e("getAllPlayers", "02222");
                                    PlayerTestFragment.this.mExpandableView.removeAllViews();
                                    PlayerTestFragment.this.mTitleList = new ArrayList<>();
                                    PlayerTestFragment.this.mHasMap = new HashMap<>();
                                    PlayerTestFragment.this.playersAInnigs1 = new ArrayList<>();
                                    PlayerTestFragment.this.playersAInnigs2 = new ArrayList<>();
                                    PlayerTestFragment.this.playersBInnigs1 = new ArrayList<>();
                                    PlayerTestFragment.this.playersBInnigs2 = new ArrayList<>();
                                    if (response.body().getPlayerslist().size() > 0) {
                                        for (int i = 0; i < response.body().getPlayerslist().size(); i++) {
                                            PlayerTestFragment.this.mTitleList.add(response.body().getPlayerslist().get(i));
                                            if (response.body().getPlayerslist().get(i).getTeamSide().equalsIgnoreCase("Team A")) {
                                                if (response.body().getPlayerslist().get(i).getInning().intValue() == 1) {
                                                    PlayerTestFragment.this.playersAInnigs1.add(response.body().getPlayerslist().get(i));
                                                } else {
                                                    PlayerTestFragment.this.playersAInnigs2.add(response.body().getPlayerslist().get(i));
                                                }
                                            } else if (response.body().getPlayerslist().get(i).getTeamSide().equalsIgnoreCase("Team B")) {
                                                if (response.body().getPlayerslist().get(i).getInning().intValue() == 1) {
                                                    PlayerTestFragment.this.playersBInnigs1.add(response.body().getPlayerslist().get(i));
                                                } else {
                                                    PlayerTestFragment.this.playersBInnigs2.add(response.body().getPlayerslist().get(i));
                                                }
                                            }
                                            HashMap<String, PlayerData.Playerslist> hashMap = PlayerTestFragment.this.mHasMap;
                                            hashMap.put(response.body().getPlayerslist().get(i).getTeamSide() + "-" + response.body().getPlayerslist().get(i).getInning(), response.body().getPlayerslist().get(i));
                                        }
                                    }
                                    for (Map.Entry<String, PlayerData.Playerslist> entry : PlayerTestFragment.this.mHasMap.entrySet()) {
                                        PlayerTestFragment.this.mExpandableView.addView(new TeamHeading(PlayerTestFragment.this.getContext(), entry.getValue()));
                                        String[] split = entry.getKey().split("-");
                                        if (split[1].equals("VERSION_1") && split[0].equals("Team A")) {
                                            Collections.reverse(PlayerTestFragment.this.playersAInnigs1);
                                            Iterator<PlayerData.Playerslist> it = PlayerTestFragment.this.playersAInnigs1.iterator();
                                            while (it.hasNext()) {
                                                PlayerTestFragment.this.mExpandableView.addView(new TestHeade(PlayerTestFragment.this.getContext(), it.next()));
                                            }
                                        } else if (split[1].equals("VERSION_1") && split[0].equals("Team B")) {
                                            Collections.reverse(PlayerTestFragment.this.playersBInnigs1);
                                            Iterator<PlayerData.Playerslist> it2 = PlayerTestFragment.this.playersBInnigs1.iterator();
                                            while (it2.hasNext()) {
                                                PlayerTestFragment.this.mExpandableView.addView(new TestHeade(PlayerTestFragment.this.getContext(), it2.next()));
                                            }
                                        } else if (split[1].equals(ExifInterface.GPS_MEASUREMENT_2D) && split[0].equals("Team A")) {
                                            Collections.reverse(PlayerTestFragment.this.playersAInnigs2);
                                            Iterator<PlayerData.Playerslist> it3 = PlayerTestFragment.this.playersAInnigs2.iterator();
                                            while (it3.hasNext()) {
                                                PlayerTestFragment.this.mExpandableView.addView(new TestHeade(PlayerTestFragment.this.getContext(), it3.next()));
                                            }
                                        } else if (split[1].equals(ExifInterface.GPS_MEASUREMENT_2D) && split[0].equals("Team B")) {
                                            Collections.reverse(PlayerTestFragment.this.playersBInnigs2);
                                            Iterator<PlayerData.Playerslist> it4 = PlayerTestFragment.this.playersBInnigs2.iterator();
                                            while (it4.hasNext()) {
                                                PlayerTestFragment.this.mExpandableView.addView(new TestHeade(PlayerTestFragment.this.getContext(), it4.next()));
                                            }
                                        }
                                        PlayerTestFragment.this.mExpandableView.addView(new TestHead(PlayerTestFragment.this.getContext()));
                                    }
                                    PlayerTestFragment cricitestplayingxifragment = PlayerTestFragment.this;
                                    cricitestplayingxifragment.hideProgress(cricitestplayingxifragment.swipeView);
                                    return;
                                }
                                PlayerTestFragment cricitestplayingxifragment2 = PlayerTestFragment.this;
                                cricitestplayingxifragment2.showToast(cricitestplayingxifragment2.context, PlayerTestFragment.this.getString(R.string.api_error));
                            } catch (Exception unused) {
                            }
                        }

                        @Override
                        public void onFailure(Call<PlayerData> call, Throwable th) {
                            PlayerTestFragment cricitestplayingxifragment = PlayerTestFragment.this;
                            cricitestplayingxifragment.showToast(cricitestplayingxifragment.context, PlayerTestFragment.this.getString(R.string.inter_conn_weak));
                        }
                    });
                    return;
                }
                showToast(this.context, getString(R.string.no_internet));
            }
        } catch (Exception e) {
            Log.e("getAllPlayers", "eeee:--  " + e.getMessage());
        }
    }
}
