package com.gsbusiness.fancylivecricketscore.CricketFragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gsbusiness.fancylivecricketscore.MyApplications;
import com.gsbusiness.fancylivecricketscore.CricketActivity.MainActivity;
import com.gsbusiness.fancylivecricketscore.CricketAdapter.ViewSwipe;
import com.gsbusiness.fancylivecricketscore.CricketModel.OddData;
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

public class OddMatchFragment extends MainCenterFragment {
    public Context context;
    public List<Fragment> fragments = new Vector();
    ArrayList<OddData.Matchst> innigsA;
    ArrayList<OddData.Matchst> innigsB;
    SharedPreferences mPrefrences;
    LinearLayout mainLy;
    LinearLayout noDataLy;
    ViewSwipe pagerAdapter;
    ProgressDialog progressBar;
    SwipeRefreshLayout swipeView;
    TabLayout tabLayout;
    SwipePagerView viewPager;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragmnet_odd_match, viewGroup, false);
        this.mPrefrences = MyApplications.getInstance().getPrefrences();
        mInitRes(inflate);
        return inflate;
    }

    private void mInitRes(View view) {
        this.context = getActivity();
        this.mainLy = view.findViewById(R.id.mainLy);
        this.noDataLy = view.findViewById(R.id.noDataLy);
        this.tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        this.viewPager = (SwipePagerView) view.findViewById(R.id.pager);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        this.swipeView = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                OddMatchFragment.this.getMatchOdds();
            }
        });
        this.swipeView.setColorSchemeResources(R.color.purple_200, R.color.colorPrimaryDark, R.color.colorPrimary, R.color.purple_200);
        getMatchOdds();
        this.fragments.clear();
        this.fragments.add(new QuizFirstFragment());
        this.fragments.add(new QuizSecondFragment());
        ViewSwipe cricipager = new ViewSwipe(getActivity().getSupportFragmentManager(), this.tabLayout.getTabCount(), this.fragments);
        this.pagerAdapter = cricipager;
        this.viewPager.setAdapter(cricipager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                OddMatchFragment.this.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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

    public void getMatchOdds() {
        try {
            if (getActivity() != null) {
                if (isNetworkAvailable()) {
                    showProgress(this.swipeView);
                    HashMap hashMap = new HashMap();
                    hashMap.put("MatchId", "" + MainActivity.MatchId);
                    mGetRetroObject(this.baseURL).getMatchOdds(hashMap).enqueue(new Callback<OddData>() {

                        @Override
                        public void onResponse(Call<OddData> call, Response<OddData> response) {
                            try {
                                Log.e(" response.body() ", "" + response.body().toString());
                                if (response.body().getSuccess().booleanValue()) {
                                    OddMatchFragment.this.innigsA = new ArrayList<>();
                                    OddMatchFragment.this.innigsB = new ArrayList<>();
                                    int size = response.body().getPlayerslist().size();
                                    if (size > 0) {
                                        for (int i = 0; i < size; i++) {
                                            if (response.body().getPlayerslist().get(i).getIsfirstinning().equalsIgnoreCase("1")) {
                                                OddMatchFragment.this.innigsA.add(response.body().getPlayerslist().get(i));
                                            } else {
                                                OddMatchFragment.this.innigsB.add(response.body().getPlayerslist().get(i));
                                            }
                                        }
                                        OddMatchFragment.this.refreshTabs("1st Innings", "2nd Innings");
                                        ((QuizFirstFragment) OddMatchFragment.this.pagerAdapter.getItem(0)).updateMatchOddsList(OddMatchFragment.this.innigsA);
                                        ((QuizSecondFragment) OddMatchFragment.this.pagerAdapter.getItem(1)).updateMatchOddsList(OddMatchFragment.this.innigsB);
                                    }
                                    if (size > 0) {
                                        OddMatchFragment.this.mainLy.setVisibility(View.VISIBLE);
                                        OddMatchFragment.this.noDataLy.setVisibility(View.GONE);
                                    } else {
                                        OddMatchFragment.this.mainLy.setVisibility(View.GONE);
                                        OddMatchFragment.this.noDataLy.setVisibility(View.VISIBLE);
                                    }
                                    OddMatchFragment cricimatchoddsfragment = OddMatchFragment.this;
                                    cricimatchoddsfragment.hideProgress(cricimatchoddsfragment.swipeView);
                                    return;
                                }
                                OddMatchFragment cricimatchoddsfragment2 = OddMatchFragment.this;
                                cricimatchoddsfragment2.showToast(cricimatchoddsfragment2.context, OddMatchFragment.this.getString(R.string.api_error));
                            } catch (Exception e) {
                                OddMatchFragment cricimatchoddsfragment3 = OddMatchFragment.this;
                                cricimatchoddsfragment3.hideProgress(cricimatchoddsfragment3.swipeView);

                            }
                        }

                        @Override
                        public void onFailure(Call<OddData> call, Throwable th) {
                            OddMatchFragment cricimatchoddsfragment = OddMatchFragment.this;
                            cricimatchoddsfragment.hideProgress(cricimatchoddsfragment.swipeView);
                            OddMatchFragment cricimatchoddsfragment2 = OddMatchFragment.this;
                            cricimatchoddsfragment2.showToast(cricimatchoddsfragment2.context, OddMatchFragment.this.getString(R.string.inter_conn_weak));
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
