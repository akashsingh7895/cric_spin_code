package com.gsbusiness.fancylivecricketscore.CricketFragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gsbusiness.fancylivecricketscore.MyApplications;
import com.gsbusiness.fancylivecricketscore.CricketActivity.MainActivity;
import com.gsbusiness.fancylivecricketscore.CricketModel.PlayerData;
import com.gsbusiness.fancylivecricketscore.CricketAdapter.ViewPager;
import com.gsbusiness.fancylivecricketscore.CricketUtility.SwipePagerView;
import com.gsbusiness.fancylivecricketscore.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerFragment extends MainCenterFragment {
    public Context context;
    List<Fragment> fragments = new Vector();
    SharedPreferences mPrefrences;
    LinearLayout mainLL, noDataLy;
    ViewPager pagerAdapter;
    ArrayList<PlayerData.Playerslist> playersA;
    ArrayList<PlayerData.Playerslist> playersB;
    ProgressDialog progressBar;
    SwipeRefreshLayout swipeView;
    TabLayout tabLayout;
    SwipePagerView viewPager;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_player, viewGroup, false);
        this.mPrefrences = MyApplications.getInstance().getPrefrences();
        mInitRes(inflate);
        return inflate;
    }

    private void mInitRes(View view) {
        this.context = getActivity();
        mainLL = view.findViewById(R.id.mainLL);
        noDataLy = view.findViewById(R.id.noDataLy);
        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.pager);
        swipeView = view.findViewById(R.id.swipeRefreshLayout);
        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {


            @Override
            public void onRefresh() {
                PlayerFragment.this.getAllPlayers();
            }
        });
        swipeView.setColorSchemeResources(R.color.purple_200, R.color.colorPrimaryDark, R.color.colorPrimary, R.color.purple_200);
        getAllPlayers();
        this.fragments.clear();
        this.fragments.add(new TeamFirstFragment());
        this.fragments.add(new TeamSecondFragment());
        ViewPager cricipager = new ViewPager(getActivity().getSupportFragmentManager(), this.tabLayout.getTabCount(), this.fragments);
        this.pagerAdapter = cricipager;
        this.viewPager.setAdapter(cricipager);
        this.tabLayout.setOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.OnTabSelectedListener() {


            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                PlayerFragment.this.viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }

    public void refreshTabs(String str, String str2) {
        try {
            if (this.tabLayout.getTabCount() == 0) {
                TabLayout tabLayout2 = this.tabLayout;
                tabLayout2.addTab(tabLayout2.newTab().setText(str));
                TabLayout tabLayout3 = this.tabLayout;
                tabLayout3.addTab(tabLayout3.newTab().setText(str2));
            } else if (this.tabLayout.getTabCount() >= 0) {
                TabLayout.Tab tabAt = this.tabLayout.getTabAt(0);
                if (tabAt != null) {
                    tabAt.setText(str);
                }
                TabLayout.Tab tabAt2 = this.tabLayout.getTabAt(1);
                if (tabAt2 != null) {
                    tabAt2.setText(str2);
                }
            }
        } catch (Exception unused) {
        }
        this.tabLayout.setTabGravity(0);
    }

    public void getAllPlayers() {
        try {
            if (getActivity() != null) {
                if (isNetworkAvailable()) {
                    showProgress(this.swipeView);
                    HashMap hashMap = new HashMap();
                    hashMap.put("MatchId", "" + MainActivity.MatchId);
                    mGetRetroObject(this.baseURL).getAllPlayers(hashMap).enqueue(new Callback<PlayerData>() {


                        @Override
                        public void onResponse(Call<PlayerData> call, Response<PlayerData> response) {
                            try {
                                if (response.body().getSuccess().booleanValue()) {
                                    PlayerFragment.this.swipeView.setVisibility(View.VISIBLE);
                                    PlayerFragment.this.mainLL.setVisibility(View.VISIBLE);
                                    PlayerFragment.this.noDataLy.setVisibility(View.GONE);
                                    PlayerFragment.this.playersA = new ArrayList<>();
                                    PlayerFragment.this.playersB = new ArrayList<>();
                                    int size = response.body().getPlayerslist().size();
                                    if (size > 0) {
                                        String str = null;
                                        String str2 = null;
                                        for (int i = 0; i < size; i++) {
                                            if (response.body().getPlayerslist().get(i).getTeamSide().equalsIgnoreCase("Team A")) {
                                                str = response.body().getPlayerslist().get(i).getTeamName() + " (" + response.body().getPlayerslist().get(i).getTeamRuns() + ") ";
                                                PlayerFragment.this.playersA.add(response.body().getPlayerslist().get(i));
                                            } else {
                                                str2 = response.body().getPlayerslist().get(i).getTeamName() + " (" + response.body().getPlayerslist().get(i).getTeamRuns() + ") ";
                                                PlayerFragment.this.playersB.add(response.body().getPlayerslist().get(i));
                                            }
                                        }
                                        PlayerFragment.this.refreshTabs(str, str2);
                                        ((TeamFirstFragment) PlayerFragment.this.pagerAdapter.getItem(0)).updatePlayerList(PlayerFragment.this.playersA);
                                        ((TeamSecondFragment) PlayerFragment.this.pagerAdapter.getItem(1)).updatePlayerList(PlayerFragment.this.playersB);
                                        PlayerFragment criciplayingxifragment = PlayerFragment.this;
                                        criciplayingxifragment.hideProgress(criciplayingxifragment.swipeView);
                                        return;
                                    }
                                    PlayerFragment criciplayingxifragment2 = PlayerFragment.this;
                                    criciplayingxifragment2.hideProgress(criciplayingxifragment2.swipeView);
                                    PlayerFragment.this.noDataLy.setVisibility(View.VISIBLE);
                                    PlayerFragment.this.mainLL.setVisibility(View.GONE);
                                    PlayerFragment.this.swipeView.setVisibility(View.GONE);
                                }
                            } catch (Exception e) {
                                PlayerFragment criciplayingxifragment3 = PlayerFragment.this;
                                criciplayingxifragment3.hideProgress(criciplayingxifragment3.swipeView);

                            }
                        }

                        @Override
                        public void onFailure(Call<PlayerData> call, Throwable th) {
                            PlayerFragment criciplayingxifragment = PlayerFragment.this;
                            criciplayingxifragment.hideProgress(criciplayingxifragment.swipeView);
                            PlayerFragment criciplayingxifragment2 = PlayerFragment.this;
                            criciplayingxifragment2.showToast(criciplayingxifragment2.context, PlayerFragment.this.getString(R.string.inter_conn_weak));
                        }
                    });
                    return;
                }
                showToast(this.context, getString(R.string.no_internet));
            }
        } catch (Exception e) {

        }
    }
}
