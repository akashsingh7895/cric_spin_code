package com.avssolution.fancylivecricketscore.CricketFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.avssolution.fancylivecricketscore.CricketAdapter.Matches;
import com.avssolution.fancylivecricketscore.CricketModel.Upcomming;
import com.avssolution.fancylivecricketscore.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingFragment extends MainCenterFragment {
    public Context context;
    public RecyclerView recyclerUpcoming;
    public SwipeRefreshLayout swipeView;
    public Matches upcomingMatchAdapter;
    public ArrayList<Upcomming.AllMatch> upcomingMatches;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_upcomming, viewGroup, false);
        mInitResources(inflate);
        getUpcomingMatchesRetro();
        return inflate;
    }

    private void mInitResources(View view) {
        this.context = getActivity();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerUpcoming);
        this.recyclerUpcoming = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        this.recyclerUpcoming.setItemAnimator(new DefaultItemAnimator());
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        this.swipeView = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {


            @Override 
            public void onRefresh() {
                UpcomingFragment.this.getUpcomingMatchesRetro();
            }
        });
        this.swipeView.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorAccent);
    }

    public void getUpcomingMatchesRetro() {
        try {
            if (getActivity() != null) {
                if (isNetworkAvailable()) {
                    showProgress(this.swipeView);
                    mGetRetroObject(this.baseURL).getUpcomingMatches().enqueue(new Callback<Upcomming>() {


                        @Override 
                        public void onResponse(Call<Upcomming> call, Response<Upcomming> response) {
                            try {
                                UpcomingFragment criciupcomingmatchfragment = UpcomingFragment.this;
                                criciupcomingmatchfragment.hideProgress(criciupcomingmatchfragment.swipeView);
                                if (response.body().getSuccess().booleanValue()) {
                                    UpcomingFragment.this.upcomingMatches = new ArrayList<>();
                                    int size = response.body().getAllMatch().size();
                                    if (size > 0) {
                                        for (int i = 0; i < size; i++) {
                                            Upcomming.AllMatch allMatch = new Upcomming.AllMatch();
                                            allMatch.setTitle(response.body().getAllMatch().get(i).getTitle());
                                            allMatch.setMatchtime(response.body().getAllMatch().get(i).getMatchtime());
                                            allMatch.setVenue(response.body().getAllMatch().get(i).getVenue());
                                            allMatch.setResult(response.body().getAllMatch().get(i).getResult());
                                            allMatch.setTeamA(response.body().getAllMatch().get(i).getTeamA());
                                            allMatch.setTeamB(response.body().getAllMatch().get(i).getTeamB());
                                            allMatch.setTeamAImage(response.body().getAllMatch().get(i).getTeamAImage());
                                            allMatch.setTeamBImage(response.body().getAllMatch().get(i).getTeamBImage());
                                            allMatch.setImageUrl(response.body().getAllMatch().get(i).getImageUrl());
                                            UpcomingFragment.this.upcomingMatches.add(allMatch);
                                        }
                                        UpcomingFragment criciupcomingmatchfragment2 = UpcomingFragment.this;
                                        criciupcomingmatchfragment2.upcomingMatchAdapter = new Matches(criciupcomingmatchfragment2.getActivity(), UpcomingFragment.this.upcomingMatches, false);
                                        UpcomingFragment.this.recyclerUpcoming.setAdapter(UpcomingFragment.this.upcomingMatchAdapter);
                                        UpcomingFragment.this.upcomingMatchAdapter.notifyDataSetChanged();
                                        UpcomingFragment criciupcomingmatchfragment3 = UpcomingFragment.this;
                                        criciupcomingmatchfragment3.hideProgress(criciupcomingmatchfragment3.swipeView);
                                        return;
                                    }
                                    return;
                                }
                                UpcomingFragment criciupcomingmatchfragment4 = UpcomingFragment.this;
                                criciupcomingmatchfragment4.hideProgress(criciupcomingmatchfragment4.swipeView);
                                UpcomingFragment criciupcomingmatchfragment5 = UpcomingFragment.this;
                                criciupcomingmatchfragment5.showToast(criciupcomingmatchfragment5.context, UpcomingFragment.this.getString(R.string.api_error));
                            } catch (Exception unused) {
                                UpcomingFragment criciupcomingmatchfragment6 = UpcomingFragment.this;
                                criciupcomingmatchfragment6.hideProgress(criciupcomingmatchfragment6.swipeView);
                            }
                        }

                        @Override 
                        public void onFailure(Call<Upcomming> call, Throwable th) {
                            UpcomingFragment criciupcomingmatchfragment = UpcomingFragment.this;
                            criciupcomingmatchfragment.hideProgress(criciupcomingmatchfragment.swipeView);
                            UpcomingFragment criciupcomingmatchfragment2 = UpcomingFragment.this;
                            criciupcomingmatchfragment2.showToast(criciupcomingmatchfragment2.context, UpcomingFragment.this.getString(R.string.inter_conn_weak));
                        }
                    });
                    return;
                }
                showToast(this.context, getString(R.string.no_internet));
            }
        } catch (Exception unused) {
        }
    }
}
