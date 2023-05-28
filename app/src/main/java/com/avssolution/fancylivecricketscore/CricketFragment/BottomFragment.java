package com.avssolution.fancylivecricketscore.CricketFragment;

import static com.avssolution.fancylivecricketscore.LiveSeries.RetrofitIntance.*;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.avssolution.fancylivecricketscore.LiveSeries.RetrofitIntance;
import com.avssolution.fancylivecricketscore.LiveSeries.SeriesAdapter;
import com.avssolution.fancylivecricketscore.LiveSeries.SeriesModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.avssolution.fancylivecricketscore.CricketActivity.MainActivity;
import com.avssolution.fancylivecricketscore.CricketActivity.BottomActivity;
import com.avssolution.fancylivecricketscore.CricketAdapter.MatchAll;
import com.avssolution.fancylivecricketscore.CricketAdapter.TeamResult;
import com.avssolution.fancylivecricketscore.CricketModel.DataJason;
import com.avssolution.fancylivecricketscore.CricketModel.MainData;
import com.avssolution.fancylivecricketscore.CricketModel.AllMatch;
import com.avssolution.fancylivecricketscore.CricketModel.ResultData;
import com.avssolution.fancylivecricketscore.CricketUtility.RecyclerTouchListener;
import com.avssolution.fancylivecricketscore.CricketUtility.UserPreferData;
import com.avssolution.fancylivecricketscore.R;
import com.google.gson.Gson;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomFragment extends MainCenterFragment implements DiscreteScrollView.OnItemChangedListener<MatchAll.ViewHolder> {

    public ArrayList<AllMatch> dataMatches;
    LinearLayout loaddaata;
    public int mAdOneTimer = 0;
    MatchAll mAdapter;
    SharedPreferences mPrefrences;
    ScheduledFuture<?> mScheduledFuture;
    ScheduledExecutorService mScheduler;
    DiscreteScrollView recycler;
    RecyclerView recyclerResult, seriesRecyclerview;
    TeamResult resultMatchAdapter;
    ArrayList<ResultData.AllMatch> resultMatches;
    SwipeRefreshLayout swipeView;
    View view;
    ViewPager viewPager;
    ImageView image1, image2, image3, image4;

    ///////////////////////
    List<SeriesModel> allSeriesList;


    public void onCurrentItemChanged(MatchAll.ViewHolder viewHolder, int i) {
        if (i == 0) {
            image1.setBackgroundResource(R.drawable.ic_yello_ball);
            image2.setBackgroundResource(R.drawable.ic_brown_ball);
            image3.setBackgroundResource(R.drawable.ic_brown_ball);
            image4.setBackgroundResource(R.drawable.ic_brown_ball);
        } else if (i == 1) {
            image1.setBackgroundResource(R.drawable.ic_brown_ball);
            image2.setBackgroundResource(R.drawable.ic_yello_ball);
            image3.setBackgroundResource(R.drawable.ic_brown_ball);
            image4.setBackgroundResource(R.drawable.ic_brown_ball);
        } else if (i == 2) {
            image1.setBackgroundResource(R.drawable.ic_brown_ball);
            image2.setBackgroundResource(R.drawable.ic_brown_ball);
            image3.setBackgroundResource(R.drawable.ic_yello_ball);
            image4.setBackgroundResource(R.drawable.ic_brown_ball);
        } else if (i == 3) {
            image1.setBackgroundResource(R.drawable.ic_brown_ball);
            image2.setBackgroundResource(R.drawable.ic_brown_ball);
            image3.setBackgroundResource(R.drawable.ic_brown_ball);
            image4.setBackgroundResource(R.drawable.ic_yello_ball);
        }
    }

    static int access$208(BottomFragment cricimultiplematchfragment) {
        int i = cricimultiplematchfragment.mAdOneTimer;
        cricimultiplematchfragment.mAdOneTimer = i + 1;
        return i;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(R.layout.ic_navigation_bottom, viewGroup, false);
        image1 = view.findViewById(R.id.image1);
        image2 = view.findViewById(R.id.image2);
        image3 = view.findViewById(R.id.image3);
        image4 = view.findViewById(R.id.image4);
        mInitResources();
        showProgress(this.swipeView);
        mCallApi();
        mProgressClose();
        getAllResultMatches(1, 10);
        getSeriesData();

        return this.view;


    }

    private void mInitResources() {

        this.dataMatches = new ArrayList<>();
        this.recycler = (DiscreteScrollView) this.view.findViewById(R.id.recyclerMatches);
        this.loaddaata = this.view.findViewById(R.id.loaddaata);
        this.recycler.setSlideOnFling(true);

        this.recycler.setItemTransformer(new ScaleTransformer.Builder().setMinScale(0.8f).setPivotX(Pivot.X.CENTER).setPivotY(Pivot.Y.CENTER).build());
        this.recycler.addOnItemChangedListener(this);
        MatchAll cricimultiplematchadapter = new MatchAll(getActivity(), this.dataMatches, this.teamURL);
        this.mAdapter = cricimultiplematchadapter;
        this.recycler.setAdapter(cricimultiplematchadapter);

        this.seriesRecyclerview = this.view.findViewById(R.id.series_recycler_view);
        seriesRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        this.recycler.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), this.recycler, new RecyclerTouchListener.ClickListener() {


            @Override
            public void onLongClick(View view, int i) {
            }

            @Override
            public void onClick(View view, int i) {
                String str;
                try {
                    Intent intent = new Intent(BottomFragment.this.getActivity(), MainActivity.class);
                    intent.putExtra("MatchId", BottomFragment.this.dataMatches.get(i).getMatchid() + "");
                    AllMatch cricimultimatchpojo = BottomFragment.this.dataMatches.get(i);
                    if (cricimultimatchpojo.getJsondata().length() > 0) {
                        MainData cricimainjsondata = (MainData) new Gson().fromJson(cricimultimatchpojo.getJsondata(), MainData.class);
                        if (cricimainjsondata != null) {
                            DataJason jsondata = cricimainjsondata.getJsondata();
                            if (jsondata != null) {
                                if (jsondata.getTitle().contains("Match")) {
                                    str = jsondata.getTitle().substring(0, jsondata.getTitle().indexOf("Match"));
                                } else if (jsondata.getTitle().substring(0, jsondata.getTitle().indexOf("|")).contains("C.RR")) {
                                    String[] split = jsondata.getTitle().substring(0, jsondata.getTitle().indexOf("|")).split("C.RR");
                                    if (split[0] != null) {
                                        str = split[0].substring(0, split[0].length() - 1) + "";
                                    } else {
                                        str = "";
                                    }
                                } else {
                                    str = jsondata.getTitle().substring(0, jsondata.getTitle().indexOf("|")) + "";
                                }
                                intent.putExtra("Match", str);
                                intent.putExtra("MatchType", "Live");
                            }
                        } else {
                            intent.putExtra("MatchType", "Result");
                            intent.putExtra("Match", BottomFragment.this.dataMatches.get(i).getTitle());
                        }
                    } else {
                        intent.putExtra("MatchType", "Result");
                        intent.putExtra("Match", BottomFragment.this.dataMatches.get(i).getTitle());
                    }
                    intent.putExtra("MatchT", BottomFragment.this.dataMatches.get(i).getMatchtype() + "");
                    BottomFragment.this.startActivity(intent);
                } catch (Exception unused) {
                }
            }
        }));
        RecyclerView recyclerView = (RecyclerView) this.view.findViewById(R.id.recyclerUpcommingMatches);
        this.recyclerResult = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerResult.setItemAnimator(new DefaultItemAnimator());
        this.recyclerResult.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), this.recyclerResult, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onLongClick(View view, int i) {
            }

            @Override
            public void onClick(View view, int i) {
                Intent intent = new Intent(BottomFragment.this.getActivity(), MainActivity.class);
                intent.putExtra("MatchId", BottomFragment.this.resultMatches.get(i).getMatchId() + "");
                intent.putExtra("MatchType", "Result");
                intent.putExtra("MatchT", BottomFragment.this.resultMatches.get(i).getMatchtype());
                intent.putExtra("Match", BottomFragment.this.resultMatches.get(i).getTitle() + "");
                BottomFragment.this.startActivity(intent);
            }
        }));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) this.view.findViewById(R.id.swipeRefreshLayout);
        this.swipeView = swipeRefreshLayout;
        swipeRefreshLayout.setRefreshing(false);
        this.swipeView.setEnabled(false);
        this.swipeView.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorAccent);
    }

    private void getSeriesData(){

        getInstance().apiInterface.getSeries().enqueue(new Callback<List<SeriesModel>>() {
            @Override
            public void onResponse(Call<List<SeriesModel>> call, Response<List<SeriesModel>> response) {
                allSeriesList = response.body();
                seriesRecyclerview.setAdapter(new SeriesAdapter(getContext(),allSeriesList));
                for (int i=0;i<allSeriesList.size();i++){
                    Log.e("SeriesData","onSuccess: "+ allSeriesList.get(i).getSeriesid());

                    // Toast.makeText(getContext(), "success" + allSeriesList.get(i).getSeriesname(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<SeriesModel>> call, Throwable t) {

                Log.e("SeriesData","onFailure: "+ t.getLocalizedMessage());

            }
        });
    }

    private void mCallApi() {
        try {
            if (this.mScheduler == null) {
                ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
                this.mScheduler = newSingleThreadScheduledExecutor;
                this.mScheduledFuture = newSingleThreadScheduledExecutor.scheduleAtFixedRate(new Runnable() {


                    public void run() {
                        BottomFragment.access$208(BottomFragment.this);
                        BottomFragment.this.getAllMatches();
                    }
                }, 0, 1, TimeUnit.SECONDS);
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onResume() {
        mCallApi();
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopTimer();
    }

    private void stopTimer() {
        if (this.mScheduler != null) {
            this.mScheduledFuture.cancel(true);
            this.mScheduler.shutdownNow();
            this.mScheduler = null;
        }
    }

    public void getAllMatches() {
        try {
            if (isNetworkAvailable()) {
                mGetRetroObject(this.baseURL).getAllLiveMatchs().enqueue(new Callback<ArrayList<AllMatch>>() {


                    @Override
                    public void onResponse(Call<ArrayList<AllMatch>> call, Response<ArrayList<AllMatch>> response) {
                        MainData cricimainjsondata;
                        try {
                            if (response.code() == 200) {
                                BottomFragment.this.dataMatches.clear();
                                for (int i = 0; i < response.body().size(); i++) {
                                    if (response.body().get(i).getJsondata().length() > 0 && (cricimainjsondata = (MainData) new Gson().fromJson(response.body().get(i).getJsondata(), MainData.class)) != null) {
                                        DataJason jsondata = cricimainjsondata.getJsondata();
                                        if (!TextUtils.isEmpty(jsondata.getCriclivefooter())) {
                                            BottomFragment.this.mSetSharedData(UserPreferData.FOOTER_MAIN, jsondata.getCriclivefooter());
                                        }
                                        if (!TextUtils.isEmpty(jsondata.getCriclivefooterurl())) {
                                            BottomFragment.this.mSetSharedData(UserPreferData.FOOTER_URL, jsondata.getCriclivefooterurl());
                                            if (BottomFragment.this.mAdOneTimer == 3) {
                                                BottomFragment.this.mGetSharedFooterValue();
                                            }
                                        }
                                        if (!TextUtils.isEmpty(jsondata.getCriclivefooterredirect())) {
                                            BottomFragment.this.mSetSharedData(UserPreferData.FOOTER_RED, jsondata.getCriclivefooterredirect());
                                        }
                                    }
                                    BottomFragment.this.dataMatches.add(response.body().get(i));
                                }
                                BottomFragment.this.mAdapter.notifyDataSetChanged();
                                BottomFragment cricimultiplematchfragment = BottomFragment.this;
                                cricimultiplematchfragment.hideProgress(cricimultiplematchfragment.swipeView);
                            }
                        } catch (Exception unused) {
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<AllMatch>> call, Throwable th) {
                        Log.e("onFailure ", "" + th.getMessage());
                    }
                });
            }
        } catch (Exception unused) {
        }
    }

    public void mSetSharedData(String str, String str2) {
        SharedPreferences sharedPreferences = this.mPrefrences;
        if (sharedPreferences != null) {
            if (TextUtils.isEmpty(sharedPreferences.getString(str, ""))) {
                SharedPreferences.Editor edit = this.mPrefrences.edit();
                edit.putString(str, str2);
                edit.apply();
            } else if (!this.mPrefrences.getString(str, "").equals(str2)) {
                SharedPreferences.Editor edit2 = this.mPrefrences.edit();
                edit2.putString(str, str2);
                edit2.apply();
            }
        }
    }

    public void mGetSharedFooterValue() {
        try {
            SharedPreferences sharedPreferences = this.mPrefrences;
            if (sharedPreferences != null && !TextUtils.isEmpty(sharedPreferences.getString(UserPreferData.FOOTER_MAIN, ""))) {
                if (this.mPrefrences.getString(UserPreferData.FOOTER_MAIN, "").equals("VERSION_1")) {
                    getActivity().runOnUiThread(new Runnable() {

                        public void run() {
                            try {
                                BottomActivity.adCustomeFooter.setVisibility(View.VISIBLE);

                                ((RequestBuilder) ((RequestBuilder) Glide.with(BottomFragment.this.getActivity()).load(BottomFragment.this.mPrefrences.getString(UserPreferData.FOOTER_URL, "")).skipMemoryCache(true)).diskCacheStrategy(DiskCacheStrategy.NONE)).into(BottomActivity.adCustomeFooter);
                                BottomActivity.adCustomeFooter.setOnClickListener(new View.OnClickListener() {


                                    public void onClick(View view) {
                                        if (!TextUtils.isEmpty(BottomFragment.this.mPrefrences.getString(UserPreferData.FOOTER_RED, "")) && BottomFragment.this.getActivity() != null) {
                                            BottomFragment.this.mSendIntent(BottomFragment.this.mPrefrences.getString(UserPreferData.FOOTER_RED, ""));
                                        }
                                    }
                                });
                            } catch (Exception e) {

                            }
                        }
                    });
                } else {
                    getActivity().runOnUiThread(new Runnable() {


                        public void run() {

                            BottomActivity.adCustomeFooter.setVisibility(View.GONE);
                        }
                    });
                }
            }
        } catch (Exception e) {

        }
    }

    private void getAllResultMatches(final int i, int i2) {
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
                                BottomFragment.this.resultMatches = new ArrayList<>();
                                if (response.body().getAllMatch().size() > 0) {
                                    for (int i = 0; i < response.body().getAllMatch().size(); i++) {
                                        BottomFragment.this.resultMatches.add(response.body().getAllMatch().get(i));
                                    }
                                    BottomFragment cricimultiplematchfragment = BottomFragment.this;
                                    cricimultiplematchfragment.resultMatchAdapter = new TeamResult(cricimultiplematchfragment.getActivity(), BottomFragment.this.resultMatches, BottomFragment.this.teamURL);
                                    BottomFragment.this.recyclerResult.setAdapter(BottomFragment.this.resultMatchAdapter);
                                    BottomFragment.this.recyclerResult.smoothScrollToPosition(i);
                                    BottomFragment.this.resultMatchAdapter.notifyDataSetChanged();
                                    BottomFragment cricimultiplematchfragment2 = BottomFragment.this;
                                    cricimultiplematchfragment2.hideProgress(cricimultiplematchfragment2.swipeView);
                                }
                                BottomFragment.this.resultMatchAdapter.notifyDataSetChanged();
                                BottomFragment cricimultiplematchfragment3 = BottomFragment.this;
                                cricimultiplematchfragment3.hideProgress(cricimultiplematchfragment3.swipeView);
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
