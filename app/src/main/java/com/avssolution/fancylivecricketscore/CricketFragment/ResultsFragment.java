package com.avssolution.fancylivecricketscore.CricketFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avssolution.fancylivecricketscore.CricketActivity.MainActivity;
import com.avssolution.fancylivecricketscore.CricketAdapter.TeamResult;
import com.avssolution.fancylivecricketscore.CricketModel.ResultData;
import com.avssolution.fancylivecricketscore.CricketUtility.RecyclerTouchListener;
import com.avssolution.fancylivecricketscore.R;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultsFragment extends MainCenterFragment {
    public ArrayList<ResultData.AllMatch> dataMatches;
    public int f147to;
    public int from;
    public TeamResult mAdapter;
    public RecyclerView recycler;
    public SwipyRefreshLayout swipeView;
    private View view;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(R.layout.fragment_result, viewGroup, false);
        mInitResources();
        this.from = 1;
        this.f147to = 15;
        getAllMatches(1, 15);
        return this.view;
    }

    private void mInitResources() {
        this.dataMatches = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) this.view.findViewById(R.id.recyclerMatches);
        this.recycler = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recycler.setItemAnimator(new DefaultItemAnimator());
        this.recycler.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), this.recycler, new RecyclerTouchListener.ClickListener() {


            @Override
            public void onLongClick(View view, int i) {
            }

            @Override
            public void onClick(View view, int i) {
                Intent intent = new Intent(ResultsFragment.this.getActivity(), MainActivity.class);
                intent.putExtra("MatchId", ResultsFragment.this.dataMatches.get(i).getMatchId() + "");
                intent.putExtra("MatchType", "Result");
                Toast.makeText(getContext(), ""+ResultsFragment.this.dataMatches.get(i).getMatchId(), Toast.LENGTH_SHORT).show();
                intent.putExtra("MatchT", ResultsFragment.this.dataMatches.get(i).getMatchtype());
                intent.putExtra("Match", ResultsFragment.this.dataMatches.get(i).getTitle() + "");
                ResultsFragment.this.startActivity(intent);
            }
        }));
        SwipyRefreshLayout swipyRefreshLayout = (SwipyRefreshLayout) this.view.findViewById(R.id.swipeRefreshLayout);
        this.swipeView = swipyRefreshLayout;
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {


            @Override
            public void onRefresh(SwipyRefreshLayoutDirection swipyRefreshLayoutDirection) {
                ResultsFragment.this.swipeView.setRefreshing(false);
                ResultsFragment.this.from += 10;
                ResultsFragment.this.f147to += 10;
                ResultsFragment criciresultsfragment = ResultsFragment.this;
                criciresultsfragment.getAllMatches(criciresultsfragment.from, ResultsFragment.this.f147to);
            }
        });
        this.swipeView.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorAccent);
    }

    public void getAllMatches(final int i, int i2) {
        try {
            if (isNetworkAvailable()) {
                HashMap hashMap = new HashMap();
                hashMap.put("start", i + "");
                hashMap.put("end", i2 + "");
                mGetRetroObject(this.baseURL).getAllMatchesResult(hashMap).enqueue(new Callback<ResultData>() {


                    @Override 
                    public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                        try {
                            if (response.code() == 200) {
                                if (response.body().getAllMatch().size() > 0) {
                                    for (int i = 0; i < response.body().getAllMatch().size(); i++) {
                                        ResultsFragment.this.dataMatches.add(response.body().getAllMatch().get(i));
                                    }
                                    ResultsFragment criciresultsfragment = ResultsFragment.this;
                                    criciresultsfragment.mAdapter = new TeamResult(criciresultsfragment.getActivity(), ResultsFragment.this.dataMatches, ResultsFragment.this.teamURL);
                                    ResultsFragment.this.recycler.setAdapter(ResultsFragment.this.mAdapter);
                                    ResultsFragment.this.recycler.smoothScrollToPosition(i);
                                    ResultsFragment.this.mAdapter.notifyDataSetChanged();
                                }
                                ResultsFragment.this.mAdapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            
                        }
                    }

                    @Override 
                    public void onFailure(Call<ResultData> call, Throwable th) {
                        Log.e("onFailure ", "" + th.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            
        }
    }
}
