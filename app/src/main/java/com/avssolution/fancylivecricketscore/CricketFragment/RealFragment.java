package com.avssolution.fancylivecricketscore.CricketFragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.NestedScrollView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.avssolution.fancylivecricketscore.CricketActivity.MainActivity;
import com.avssolution.fancylivecricketscore.CricketModel.DataJason;
import com.avssolution.fancylivecricketscore.CricketModel.DataRun;
import com.avssolution.fancylivecricketscore.CricketModel.MainData;
import com.avssolution.fancylivecricketscore.CricketModel.MainRunData;
import com.avssolution.fancylivecricketscore.CricketModel.LiveData;
import com.avssolution.fancylivecricketscore.CricketUtility.BounceAnimation;
import com.avssolution.fancylivecricketscore.R;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RealFragment extends MainCenterFragment {

    LinearLayout CardViewPlayers;
    CheckBox Speaker;
    AlertDialog alertDialog;
    Animation animBounce, animLive;
    TextView TestTeamA, TestTeamARate1, TestTeamARate2, TestTeamB, TestTeamBRate1, TestTeamBRate2, addContent, batsman, bowler_status, ccRR, drawRateA, drawRateB, f145RR, fav;
    TextView nonstriker4s, nonstrikerSix, nonstrikerballs, nonstrikerruns, overs, overb, rate, ratee, sbatsmen, score, scorea, scoreb, session, sessionOver, sessionb, sessionballx, sessionrunx, srOther, srStriker, striker4s, strikerSix, strikerballs, strikerruns, summary;
    TextView teama, teamanam, teamb, teambnam, title, txtBall1, txtBall2, txtBall3, txtBall4, txtBall5, txtBall6, txtBallFive, txtBallFour, txtBallOne, txtBallSix, txtBallThree, txtBallTwo, txtLive;
    TextView textView;
    ImageView asdsd;
    public Context context;
    public boolean isSpeak = false;
    LinearLayout layout6Balls;
    LinearLayout llODI;
    RelativeLayout llTest;
    public int mAdOneTimer = 0;
    public SharedPreferences mPrefrences;
    private ScheduledFuture<?> mScheduledFuture;
    private ScheduledExecutorService mScheduler;
    public String mSessionURL = "";
    public String mTeamAUrl = "";
    public String mTeamBUrl = "";
    private LinearLayout mainLLLy;
    public String new_score = "";
    LottieAnimationView lottieAnimView;

    public String previous_score = "";
    private ProgressDialog progressBar;

    public boolean score_flag;

    private NestedScrollView scrollView;

    public SwipeRefreshLayout swipeView;

    ImageView teamAImg, teamBImg;

    private View view;
    TextToSpeech f146t1;


    static int access$108(RealFragment cricilivelinefragment) {
        int i = cricilivelinefragment.mAdOneTimer;
        cricilivelinefragment.mAdOneTimer = i + 1;
        return i;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(R.layout.fragment_real, viewGroup, false);
        mInitResources(this.view);
        RotateAnimation rotate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(5000);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setRepeatMode(Animation.RESTART);
        rotate.setInterpolator(new LinearInterpolator());
        asdsd.setAnimation(rotate);

        callAPI();

        return this.view;
    }

    @Override
    public void onResume() {
        callAPI();
        f146t1 = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != -1) {
                    f146t1.setLanguage(Locale.UK);
                }
            }
        });

        super.onResume();
    }

    private synchronized void callAPI() {
        try {
            if (getActivity() != null && isNetworkAvailable()) {
                try {
                    if (this.mScheduler == null) {
                        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
                        this.mScheduler = newSingleThreadScheduledExecutor;
                        this.mScheduledFuture = newSingleThreadScheduledExecutor.scheduleAtFixedRate(new Runnable() {


                            public void run() {
                                if (RealFragment.this.getActivity() != null) {
                                    RealFragment.this.getActivity().runOnUiThread(new Runnable() {


                                        public void run() {
                                            try {
                                                RealFragment.access$108(RealFragment.this);
                                                RealFragment.this.getALiveLineData();
                                            } catch (Exception e) {

                                            }
                                        }
                                    });
                                }
                            }
                        }, 0, 1, TimeUnit.SECONDS);
                    }
                } catch (Exception e) {

                }
            }
        } catch (Exception e2) {
        }
        return;
    }

    private void mInitResources(View view2) {
        llTest = (RelativeLayout) view2.findViewById(R.id.llTest);
        llODI = view2.findViewById(R.id.llODI);
        TestTeamA = view2.findViewById(R.id.TestTeamA);
        TestTeamB = view2.findViewById(R.id.TestTeamB);
        TestTeamARate1 = view2.findViewById(R.id.TestTeamARate1);
        TestTeamARate2 = view2.findViewById(R.id.TestTeamARate2);
        TestTeamBRate1 = view2.findViewById(R.id.TestTeamBRate1);
        TestTeamBRate2 = view2.findViewById(R.id.TestTeamBRate2);
        drawRateA = view2.findViewById(R.id.drawRateA);
        drawRateB = view2.findViewById(R.id.drawRateB);
        striker4s = view2.findViewById(R.id.striker4s);
        nonstriker4s = view2.findViewById(R.id.nonstriker4s);
        strikerSix = view2.findViewById(R.id.strikerSix);
        nonstrikerSix = view2.findViewById(R.id.nonstrikerSix);
        lottieAnimView = view2.findViewById(R.id.lottieAnimView);

        context = getActivity();
        mainLLLy = view2.findViewById(R.id.mainLLLy);
        Speaker = (CheckBox) view2.findViewById(R.id.Speaker);
        f145RR = view2.findViewById(R.id.RRR);
        scrollView = (NestedScrollView) view2.findViewById(R.id.scrollView);
        summary = view2.findViewById(R.id.summary);
        srStriker = view2.findViewById(R.id.srStriker);
        srOther = view2.findViewById(R.id.srOther);
        addContent = view2.findViewById(R.id.addContent);
        teama = view2.findViewById(R.id.teama);
        teama.setSelected(true);
        teamb = view2.findViewById(R.id.teamb);
        teamb.setSelected(true);
        overs = view2.findViewById(R.id.overs);
        overb = view2.findViewById(R.id.overb);
        ccRR = view2.findViewById(R.id.ccRR);
        scorea = view2.findViewById(R.id.score);
        scorea.setSelected(true);
        scoreb = view2.findViewById(R.id.scoreb);
        scoreb.setSelected(true);
        txtLive = view2.findViewById(R.id.liveTxt);
        rate = view2.findViewById(R.id.rateA);
        ratee = view2.findViewById(R.id.rateB);
        fav = view2.findViewById(R.id.fav);
        sessionOver = view2.findViewById(R.id.sessionover);
        session = view2.findViewById(R.id.session);
        sessionb = view2.findViewById(R.id.sessionb);
        sessionrunx = view2.findViewById(R.id.sessionrunx);
        sessionballx = view2.findViewById(R.id.sessionballx);
        title = view2.findViewById(R.id.title);
        bowler_status = view2.findViewById(R.id.bowlerstatus);
        sbatsmen = view2.findViewById(R.id.strikerbatsman);
        batsman = view2.findViewById(R.id.batsman);
        strikerruns = view2.findViewById(R.id.strikerruns);
        strikerballs = view2.findViewById(R.id.strikerballs);
        nonstrikerruns = view2.findViewById(R.id.nonstrikerruns);
        nonstrikerballs = view2.findViewById(R.id.nonstrikerballs);
        layout6Balls = view2.findViewById(R.id.layout6Balls);
        swipeView = (SwipeRefreshLayout) view2.findViewById(R.id.swipeRefreshLayout);
        txtBallOne = view2.findViewById(R.id.textFirstballScore);
        txtBallTwo = view2.findViewById(R.id.textSecondballScore);
        txtBallThree = view2.findViewById(R.id.textThirdballScore);
        txtBallFour = view2.findViewById(R.id.textFourthballScore);
        txtBallFive = view2.findViewById(R.id.textFifthballScore);
        txtBallSix = view2.findViewById(R.id.textSixthballScore);
        teamAImg = view2.findViewById(R.id.img_teamA);
        teamBImg = view2.findViewById(R.id.img_teamB);
        CardViewPlayers = view2.findViewById(R.id.card_view2222);
        textView = view2.findViewById(R.id.textView99);
        asdsd = view2.findViewById(R.id.asdsd);
        score = textView;
        textView.setMovementMethod(new ScrollingMovementMethod());


        Speaker.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                if (RealFragment.this.Speaker.isChecked()) {
                    RealFragment.this.isSpeak = true;
                } else {
                    RealFragment.this.isSpeak = false;
                }
            }
        });
        scrollView.setOnTouchListener(new View.OnTouchListener() {


            public boolean onTouch(View view, MotionEvent motionEvent) {
                RealFragment.this.score.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });

        score.setOnTouchListener(new View.OnTouchListener() {


            public boolean onTouch(View view, MotionEvent motionEvent) {
                RealFragment.this.score.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        animBounce = AnimationUtils.loadAnimation(getActivity(), R.anim.updown);
        animLive = AnimationUtils.loadAnimation(getActivity(), R.anim.round);
        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {


            @Override
            public void onRefresh() {
                RealFragment.this.getALiveLineData();
            }
        });
        swipeView.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimaryDark, R.color.colorAccent, R.color.colorPrimary);
    }

    @Override
    public void onStop() {
        super.onStop();
        stopTimer();
    }

    private void stopTimer() {
        if (this.mScheduler != null) {
            mScheduledFuture.cancel(true);
            mScheduler.shutdownNow();
            mScheduler = null;
        }
    }


    private void getALiveLineData() {
        if (isNetworkAvailable()) {
            HashMap hashMap = new HashMap();
            hashMap.put("MatchId", "" + MainActivity.MatchId);
            mGetRetroObject(this.baseURL).getLiveLineDetail(hashMap).enqueue(new Callback<ArrayList<LiveData>>() {

                @Override
                public void onResponse(Call<ArrayList<LiveData>> call, Response<ArrayList<LiveData>> response) {
                    String str;
                    String str2;
                    String substring;
                    String[] split;
                    String[] split2;
                    String str3;
                    String str4 = IdManager.DEFAULT_VERSION_NAME;
                    try {
                        if (response.code() == 200) {
                            MainData cricimainjsondata = (MainData) new Gson().fromJson(response.body().get(0).getJsondata(), MainData.class);
                            Integer.parseInt(cricimainjsondata.getJsondata().getAppversion());
                            if (response.body().get(0).getJsondata() != null && response.body().get(0).getJsonruns() != null) {
                                DataJason jsondata = cricimainjsondata.getJsondata();
                                DataRun jsonruns = ((MainRunData) new Gson().fromJson(response.body().get(0).getJsonruns(), MainRunData.class)).getJsonruns();
                                RealFragment.this.striker4s.setText(jsondata.getS4());
                                RealFragment.this.nonstriker4s.setText(jsondata.getNs4());
                                RealFragment.this.strikerSix.setText(jsondata.getS6());
                                RealFragment.this.nonstrikerSix.setText(jsondata.getNs6());

                                if (jsondata.getMatchtype().equalsIgnoreCase("Test")) {
                                    RealFragment.this.llODI.setVisibility(View.GONE);
                                    RealFragment.this.llTest.setVisibility(View.VISIBLE);
                                    RealFragment.this.TestTeamA.setText(jsondata.getTestTeamA());
                                    RealFragment.this.TestTeamB.setText(jsondata.getTestTeamB());
                                    RealFragment.this.TestTeamARate1.setText(jsondata.getTestTeamARate1());
                                    RealFragment.this.TestTeamARate2.setText(jsondata.getTestTeamARate2());
                                    RealFragment.this.TestTeamBRate1.setText(jsondata.getTestTeamBRate1());
                                    RealFragment.this.TestTeamBRate2.setText(jsondata.getTestTeamBRate2());
                                    RealFragment.this.drawRateA.setText(jsondata.getTestdrawRate1());
                                    RealFragment.this.drawRateB.setText(jsondata.getTestdrawRate2());
                                } else {
                                    RealFragment.this.llTest.setVisibility(View.GONE);
                                    RealFragment.this.llODI.setVisibility(View.VISIBLE);
                                }

                                try {
                                    if (!RealFragment.this.strikerruns.getText().toString().contains("-") && !TextUtils.isEmpty(RealFragment.this.strikerruns.getText())) {
                                        Log.d("previous_score", previous_score + "");
                                        if (RealFragment.this.previous_score.equalsIgnoreCase("")) {
                                            RealFragment.this.score_flag = true;
                                            RealFragment.this.previous_score = jsondata.getScore();

                                        } else {
                                            RealFragment.this.new_score = jsondata.getScore();
                                            if (!RealFragment.this.new_score.equalsIgnoreCase(RealFragment.this.previous_score)) {
                                                RealFragment.this.score_flag = true;
                                                RealFragment.this.previous_score = jsondata.getScore();
                                            } else {
                                                RealFragment.this.score_flag = false;
                                            }
                                        }


                                    }

                                    RealFragment.this.srOther.setText(str4);
                                    RealFragment.this.srStriker.setText(str4);
                                    RealFragment.this.scorea.setText(jsondata.getWicketA());
                                    RealFragment.this.scoreb.setText(jsondata.getWicketB());
                                    RealFragment.this.overs.setText("Overs : " + jsondata.getOversA());
                                    RealFragment.this.overb.setText("Overs : " + jsondata.getOversB());
                                    RealFragment.this.setData(jsondata);
                                    RealFragment.this.mTeamAUrl = jsondata.getTeamABanner();
                                    RequestManager with6 = Glide.with(RealFragment.this.context);
                                    with6.load(RealFragment.this.teamURL + jsondata.getTeamABanner()).apply((BaseRequestOptions<?>) ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().placeholder(R.mipmap.ic_launcher)).centerCrop()).error(R.mipmap.ic_launcher))).into(RealFragment.this.teamAImg);
                                    RealFragment.this.mTeamBUrl = jsondata.getTeamBBanner();
                                    RequestManager with7 = Glide.with(RealFragment.this.context);
                                    with7.load(RealFragment.this.teamURL + jsondata.getTeamBBanner()).apply((BaseRequestOptions<?>) ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().placeholder(R.mipmap.ic_launcher)).centerCrop()).error(R.mipmap.ic_launcher))).into(RealFragment.this.teamBImg);
                                    jsondata.getTitle().substring(0, jsondata.getTitle().indexOf("|")).contains("C.RR");
                                    RealFragment.this.sbatsmen.setText(jsondata.getBatsman().substring(0, jsondata.getBatsman().indexOf("|")));
                                    RealFragment.this.batsman.setText(jsondata.getBatsman().substring(jsondata.getBatsman().indexOf("|") + 1));
                                    String substring4 = jsondata.getOversB().substring(0, jsondata.getOversB().indexOf("|"));
                                    int length3 = substring4.split(",").length;
                                    jsondata.getOversB().substring(jsondata.getOversB().indexOf("|") + 1);
                                    int length4 = substring4.split(",").length;
                                    RealFragment.this.bowler_status.setText(jsondata.getBowler());
                                    jsondata.getTotalballs();
                                    RealFragment.this.rate.setText(jsonruns.getRateA());
                                    RealFragment.this.ratee.setText(jsonruns.getRateB());
                                    RealFragment.this.session.setText(jsonruns.getSessionA());
                                    RealFragment.this.sessionb.setText(jsonruns.getSessionB());
                                    RealFragment.this.sessionOver.setText(jsonruns.getSessionOver());
                                    RealFragment.this.fav.setText(jsonruns.getFav());
                                    RealFragment.this.summary.setText(jsonruns.getSummary().trim());
                                    RealFragment.this.sessionrunx.setText(jsonruns.getRunxa());
                                    RealFragment.this.sessionballx.setText(jsonruns.getRunxb());
                                    jsondata.getBowler().equalsIgnoreCase("0");
                                    TextUtils.isEmpty(jsondata.getCriclivefooter());
                                    TextUtils.isEmpty(jsondata.getCriclivefooterurl());
                                    TextUtils.isEmpty(jsondata.getCriclivefooterredirect());
                                    RealFragment cricilivelinefragment4 = RealFragment.this;
                                    cricilivelinefragment4.hideProgress(cricilivelinefragment4.swipeView);
                                    if (jsondata.getTitle().substring(0, jsondata.getTitle().indexOf("|")).contains("C.RR")) {
                                        String[] split3 = jsondata.getTitle().substring(0, jsondata.getTitle().indexOf("|")).split("C.RR")[1].split("R.RR");
                                        if (split3[0].contains(":")) {
                                            str3 = split3[0].replace(":", "");
                                            if (str3.contains(",")) {
                                                str3 = str3.replace(",", "");
                                            }
                                        } else {
                                            str3 = split3[0];
                                        }
                                        TextView textView3 = RealFragment.this.ccRR;
                                        textView3.setText("C.R.R : " + str3.trim() + "");
                                        try {
                                            if (split3.length >= 2 && split3[1] != null) {
                                                if (split3[1].contains(":")) {
                                                    str4 = split3[1].replace(":", "");
                                                    if (str4.contains(",")) {
                                                        str4 = str4.replace(",", "");
                                                    }
                                                } else {
                                                    str4 = split3[1];
                                                }
                                            }
                                        } catch (Exception e) {

                                        }
                                        TextView textView4 = RealFragment.this.f145RR;
                                        textView4.setText("R.R.R : " + str4.trim());

                                    }
                                    substring = jsondata.getOversB().substring(0, jsondata.getOversB().indexOf("|"));
                                    Log.d("rundata", "onResponse: " + substring.toString());
                                    if (substring != null && substring.length() > 0) {
                                        split2 = substring.split(",");
                                        if (split2.length > 0) {
                                            Log.d("rundata1", "onResponse: " + split2.toString());
                                            if (split2[0] != null) {
                                                RealFragment.this.nonstrikerruns.setText(split2[0]);
                                            }
                                            if (split2[1] != null) {
                                                RealFragment.this.strikerruns.setText(split2[1]);
                                            }

                                        }
                                    }
                                    String substring2 = jsondata.getOversB().substring(jsondata.getOversB().indexOf("|") + 1);
                                    Log.d("balldata", "onResponse: " + substring2.toString());
                                    if (substring2 != null && substring2.length() > 0) {
                                        split = substring.split(",");
                                        if (split.length > 0) {

                                            if (split[0] != null) {
                                                RealFragment.this.nonstrikerballs.setText(substring2.split(",")[0]);
                                            }
                                            if (split[1] != null) {
                                                RealFragment.this.strikerballs.setText(substring2.split(",")[1]);
                                            }
                                        }

                                    }

                                    Log.d("balldata", "onResponse: " + strikerballs.toString());
                                    double parseInt = (double) Integer.parseInt(RealFragment.this.strikerruns.getText().toString().trim());
                                    double parseInt2 = (double) Integer.parseInt(RealFragment.this.strikerballs.getText().toString().trim());
                                    Double.isNaN(parseInt);
                                    Double.isNaN(parseInt2);
                                    double d = (double) ((int) ((parseInt / parseInt2) * 100.0d));
                                    srStriker.setText(d + "");

                                    double parseInt3 = (double) Integer.parseInt(RealFragment.this.nonstrikerruns.getText().toString().trim());
                                    double parseInt4 = (double) Integer.parseInt(RealFragment.this.nonstrikerballs.getText().toString().trim());
                                    Double.isNaN(parseInt3);
                                    Double.isNaN(parseInt4);
                                    double dd = (double) ((int) ((parseInt3 / parseInt4) * 100.0d));
                                    srOther.setText(dd + "");


                                } catch (Exception e) {


                                }

                            }
                        } else {
                            RealFragment cricilivelinefragment5 = RealFragment.this;
                            cricilivelinefragment5.showToast(cricilivelinefragment5.context, RealFragment.this.getString(R.string.api_error));
                        }
                    } catch (Exception unused2) {
                        RealFragment cricilivelinefragment6 = RealFragment.this;
                        cricilivelinefragment6.hideProgress(cricilivelinefragment6.swipeView);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<LiveData>> call, Throwable th) {
                    RealFragment cricilivelinefragment = RealFragment.this;
                    cricilivelinefragment.hideProgress(cricilivelinefragment.swipeView);
                    Log.e("onFailure ", "" + th.getMessage());
                }
            });
        }
    }

    public void setData(DataJason cricijsondata) {
        score.setText(cricijsondata.getScore());
        teama.setText(cricijsondata.getTeamA());
        teamb.setText(cricijsondata.getTeamB());
        if (cricijsondata.getLast6Balls() != null || !TextUtils.isEmpty(cricijsondata.getLast6Balls())) {
            String[] split = cricijsondata.getLast6Balls().split("-");

            if (split.length > 0) {
                Log.d("balldata", "setData: " + split[0].toLowerCase());
                txtBallOne.setText(split[0].toLowerCase());
                if (split.length > 1) {
                    txtBallTwo.setText(split[1].toLowerCase());
                }
                if (split.length > 2) {
                    txtBallThree.setText(split[2].toLowerCase());
                }
                if (split.length > 3) {
                    txtBallFour.setText(split[3].toLowerCase());
                }
                if (split.length > 4) {
                    txtBallFive.setText(split[4].toLowerCase());
                }
                if (split.length > 5) {
                    txtBallSix.setText(split[5].toLowerCase());
                }
            }
        }

        if (this.score_flag) {
            LottieAnimationView lottieAnimationView = this.lottieAnimView;
            if (lottieAnimationView != null) {
                lottieAnimationView.setAnimation(String.valueOf(lottieAnimView));
                lottieAnimView.playAnimation();
                lottieAnimView.loop(true);
                dataanimation(cricijsondata.getScore());
            } else if (this.lottieAnimView != null) {
                lottieAnimView.pauseAnimation();

            }


            Animation animation = this.animLive;
            if (animation != null) {
                animation.setInterpolator(new BounceAnimation(0.1d, 15.0d));
                score.startAnimation(this.animLive);
                textToSpeechdata(cricijsondata.getScore());


                Log.d("speakerdata", "setData: " + cricijsondata.getScore());

            }
        } else if (this.animLive != null) {
            score.clearAnimation();

        }


    }

    private void dataanimation(String strf) {
        int ssds = 0;
        if (strf.equalsIgnoreCase("4-4-4")) {

            textView.setVisibility(View.GONE);
            lottieAnimView.setVisibility(View.VISIBLE);
            ssds = R.raw.four;

        } else if (strf.equalsIgnoreCase("0")) {

            textView.setVisibility(View.GONE);
            lottieAnimView.setVisibility(View.VISIBLE);
            ssds = R.raw.zero;

        } else if (strf.equalsIgnoreCase("1")) {
            textView.setVisibility(View.GONE);
            lottieAnimView.setVisibility(View.VISIBLE);
            ssds = R.raw.one;

        } else if (strf.equalsIgnoreCase("2")) {
            textView.setVisibility(View.GONE);
            lottieAnimView.setVisibility(View.VISIBLE);
            ssds = R.raw.two;

        } else if (strf.equalsIgnoreCase("3")) {
            textView.setVisibility(View.GONE);
            lottieAnimView.setVisibility(View.VISIBLE);
            ssds = R.raw.three;

        } else if (strf.equalsIgnoreCase("Ball")) {
            textView.setVisibility(View.GONE);
            lottieAnimView.setVisibility(View.VISIBLE);
            ssds = R.raw.dead_ball;

        } else if (strf.equalsIgnoreCase("Over")) {
            textView.setVisibility(View.GONE);
            lottieAnimView.setVisibility(View.VISIBLE);
            ssds = R.raw.over;

        } else if (strf.equalsIgnoreCase("Wicket")) {
            textView.setVisibility(View.GONE);
            lottieAnimView.setVisibility(View.VISIBLE);
            ssds = R.raw.wicket;


        } else if (strf.equalsIgnoreCase("Wide Ball")) {
            textView.setVisibility(View.GONE);
            lottieAnimView.setVisibility(View.VISIBLE);
            ssds = R.raw.wide;


        } else if (strf.equalsIgnoreCase("Third Umpire")) {
            textView.setVisibility(View.GONE);
            lottieAnimView.setVisibility(View.VISIBLE);
            ssds = R.raw.third_umpire;


        } else if (strf.equalsIgnoreCase("6-6-6")) {
            textView.setVisibility(View.GONE);
            lottieAnimView.setVisibility(View.VISIBLE);
            ssds = R.raw.six;


        } else {
            ssds = R.raw.loading;
            lottieAnimView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);

        }

        lottieAnimView.setAnimation(ssds);


    }


    private void textToSpeechdata(String str) {
        String str2;
        if (this.isSpeak) {
            if (str.equalsIgnoreCase("4-4-4")) {
                str2 = "4 run";
            } else if (str.equalsIgnoreCase("0")) {
                str2 = "Dot Ball";
            } else if (str.equalsIgnoreCase("1")) {
                str2 = "1 run";
            } else if (str.equalsIgnoreCase("2")) {
                str2 = "2 run";
            } else if (str.equalsIgnoreCase("3")) {
                str2 = "3 run";
            } else if (str.equalsIgnoreCase("Ball")) {
                str2 = " Ball";
            } else if (str.equalsIgnoreCase("Over")) {
                str2 = " Over";
            } else {
                str2 = str.equalsIgnoreCase("6-6-6") ? "6 run" : str;

            }

            TextToSpeech textToSpeech = this.f146t1;
            textToSpeech.speak(str2, TextToSpeech.QUEUE_FLUSH, null);
        }

    }


    @Override
    public void onPause() {
        stopTimer();
        TextToSpeech textToSpeech = this.f146t1;

        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }

        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}