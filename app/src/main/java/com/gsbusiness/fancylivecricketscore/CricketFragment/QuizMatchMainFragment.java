package com.gsbusiness.fancylivecricketscore.CricketFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.gsbusiness.fancylivecricketscore.CricketActivity.MainActivity;
import com.gsbusiness.fancylivecricketscore.CricketAdapter.Different;
import com.gsbusiness.fancylivecricketscore.CricketModel.StatusData;
import com.gsbusiness.fancylivecricketscore.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizMatchMainFragment extends MainCenterFragment {

    public Context context;
     List<Fragment> fragments = new Vector();
     ArrayList<StatusData.Entry> mDataList;
     LinearLayout mainLy, noDataLy;
     TabLayout tabLayout;
     ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_quiz_match_main, viewGroup, false);
        mInitRes(inflate);
        return inflate;
    }

    private void mInitRes(View view) {
        this.context = getActivity();
        mainLy = view.findViewById(R.id.mainLy);
        noDataLy = view.findViewById(R.id.noDataLy);
        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        ViewPager viewPager2 = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager = viewPager2;
        tabLayout.setupWithViewPager(viewPager2);
        getMatchStats();
    }

    public void setupViewPager() {
        Different cricisectionspageradapter = new Different(getActivity().getSupportFragmentManager());
        if (this.mDataList.get(0).getStat1name() != null && this.mDataList.get(0).getStat1name().length() > 0) {
            SameFragment cricicommonfragmentviewpager = new SameFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("webViewData", this.mDataList.get(0).getStat1descr());
            cricicommonfragmentviewpager.setArguments(bundle);
            cricisectionspageradapter.addFragment(cricicommonfragmentviewpager, this.mDataList.get(0).getStat1name(), this.mDataList.get(0).getStat1descr());
        }
        if (this.mDataList.get(0).getStat2name() != null && this.mDataList.get(0).getStat2name().length() > 0) {
            SameFragment cricicommonfragmentviewpager2 = new SameFragment();
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable("webViewData", this.mDataList.get(0).getStat2descr());
            cricicommonfragmentviewpager2.setArguments(bundle2);
            cricisectionspageradapter.addFragment(cricicommonfragmentviewpager2, this.mDataList.get(0).getStat2name(), this.mDataList.get(0).getStat2descr());
        }
        if (this.mDataList.get(0).getStat3name() != null && this.mDataList.get(0).getStat3name().length() > 0) {
            SameFragment cricicommonfragmentviewpager3 = new SameFragment();
            Bundle bundle3 = new Bundle();
            bundle3.putSerializable("webViewData", this.mDataList.get(0).getStat3descr());
            cricicommonfragmentviewpager3.setArguments(bundle3);
            cricisectionspageradapter.addFragment(cricicommonfragmentviewpager3, this.mDataList.get(0).getStat3name(), this.mDataList.get(0).getStat3descr());
        }
        this.viewPager.setAdapter(cricisectionspageradapter);
    }

    public void getMatchStats() {
        if (getActivity() != null) {
            getAllMatchStats();
        }
    }

    private void getAllMatchStats() {
        try {
            if (isNetworkAvailable()) {
                mStartProgress("Loading..");
                HashMap hashMap = new HashMap();
                hashMap.put("MatchId", "" + MainActivity.MatchId);
                mGetRetroObject(this.baseURL).getMatchStats(hashMap).enqueue(new Callback<StatusData>() {


                    @Override
                    public void onResponse(Call<StatusData> call, Response<StatusData> response) {
                        try {
                            if (response.body().getSuccess().booleanValue()) {
                                QuizMatchMainFragment.this.mDataList = new ArrayList<>();
                                if (response.body().getMatchst().size() > 0) {
                                    QuizMatchMainFragment.this.mDataList.add(response.body().getMatchst().get(0));
                                    QuizMatchMainFragment.this.setupViewPager();
                                }
                                if (QuizMatchMainFragment.this.mDataList.size() > 0) {
                                    QuizMatchMainFragment.this.mainLy.setVisibility(View.VISIBLE);
                                    QuizMatchMainFragment.this.noDataLy.setVisibility(View.GONE);
                                } else {
                                    QuizMatchMainFragment.this.mainLy.setVisibility(View.GONE);
                                    QuizMatchMainFragment.this.noDataLy.setVisibility(View.VISIBLE);
                                }
                                QuizMatchMainFragment.this.mProgressClose();
                                return;
                            }
                            QuizMatchMainFragment cricimatchstatsfragment = QuizMatchMainFragment.this;
                            cricimatchstatsfragment.showToast(cricimatchstatsfragment.context, QuizMatchMainFragment.this.getString(R.string.api_error));
                            QuizMatchMainFragment.this.mProgressClose();
                        } catch (Exception e) {
                            QuizMatchMainFragment.this.mProgressClose();

                        }
                    }

                    @Override
                    public void onFailure(Call<StatusData> call, Throwable th) {
                        QuizMatchMainFragment.this.mProgressClose();
                        QuizMatchMainFragment cricimatchstatsfragment = QuizMatchMainFragment.this;
                        cricimatchstatsfragment.showToast(cricimatchstatsfragment.context, QuizMatchMainFragment.this.getString(R.string.inter_conn_weak));
                    }
                });
                return;
            }
            showToast(this.context, getString(R.string.no_internet));
        } catch (Exception e) {

        }
    }
}
