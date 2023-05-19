package com.avssolution.fancylivecricketscore.CricketActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.avssolution.fancylivecricketscore.CricketFragment.RealFragment;
import com.avssolution.fancylivecricketscore.CricketFragment.PlayerFragment;
import com.avssolution.fancylivecricketscore.CricketFragment.PlayerTestFragment;
import com.avssolution.fancylivecricketscore.CricketFragment.QuizMatchMainFragment;
import com.avssolution.fancylivecricketscore.CricketFragment.OddMatchFragment;
import com.avssolution.fancylivecricketscore.CricketModel.DataJason;
import com.avssolution.fancylivecricketscore.CricketModel.DataRun;
import com.avssolution.fancylivecricketscore.CricketModel.MainData;
import com.avssolution.fancylivecricketscore.CricketModel.MainRunData;
import com.avssolution.fancylivecricketscore.CricketModel.LiveData;
import com.avssolution.fancylivecricketscore.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import io.hamed.floatinglayout.FloatingLayout;
import io.hamed.floatinglayout.callback.FloatingListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends NetworkActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    public static String MatchId;
    public String MatchBetn;
    private String MatchT;
    private String MatchType;
    private BottomNavigationView bNavigationView;
    public int currentTabIndex = 0;
    public FloatingLayout floatingLayout;
    private int intCount = 0;
    private SharedPreferences mPrefrences;
    public ScheduledFuture<?> mScheduledFuture;
    public ScheduledExecutorService mScheduler;
    SharedPreferences.Editor pref_edit;
    public TextView title;
    ImageView backapp;


    KProgressHUD hudads;

    InterstitialAd mMobInterstitialAds;


    public void LoadDreamTeamAds() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RequestConfiguration configuration = new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("1ADAD30F02CD84CDE72190C2ABE5EB5E")).build();
        MobileAds.setRequestConfiguration(configuration);
        InterstitialAd.load(getApplicationContext(), getString(R.string.MainActivity_Interstitial), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                MainActivity.this.mMobInterstitialAds = interstitialAd;
                interstitialAd.setFullScreenContentCallback(
                        new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {

                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {

                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                            }
                        });
                if (hudads != null) {
                    hudads.dismiss();
                }
                MainActivity.this.mMobInterstitialAds.show(MainActivity.this);
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                if (hudads != null) {
                    hudads.dismiss();
                }

            }
        });

    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_main);


        hudads = KProgressHUD.create(MainActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setAnimationSpeed(2)
                .setLabel("Ads Loading...")
                .setDetailsLabel("Please wait")
                .setCancellable(true)
                .setDimAmount(0.5f)
                .show();

        LoadDreamTeamAds();

        MatchId = getIntent().getStringExtra("MatchId");
        MatchBetn = getIntent().getStringExtra("Match");
        MatchType = getIntent().getStringExtra("MatchType");
        MatchT = getIntent().getStringExtra("MatchT");

        mInitResources();
    }

    private void mInitResources() {
        backapp = findViewById(R.id.backapp);
        title =  findViewById(R.id.mainTitle);
        TextView textView =  findViewById(R.id.oupOut);
        title.setText(MatchBetn.trim());
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        this.bNavigationView = bottomNavigationView;
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        if (MatchType.equals("Live")) {
            loadFragment(new RealFragment(), "LivelineFragment");
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MainActivity.this.checkPermission();
                }
            });
        } else {
            bNavigationView.getMenu().removeItem(R.id.navigation_home);
            loadFragment(new PlayerFragment(), "PlayingXiFragment");
        }
        backapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        String str;
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                this.currentTabIndex = menuItem.getItemId();
                fragment = new RealFragment();
                str = "LivelineFragment";
                break;
            case R.id.navigation_news:
            case R.id.navigation_result:
            default:
                str = null;
                break;
            case R.id.navigation_odds:
                this.currentTabIndex = menuItem.getItemId();
                fragment = new OddMatchFragment();
                str = "OddsFragment";
                break;
            case R.id.navigation_playing11:
                this.currentTabIndex = menuItem.getItemId();
                Log.e("cascscasc", "ccc:--  " + this.MatchT);
                if (this.MatchT.equals("Test")) {
                    Log.e("cascscasc", "innn:--  " + this.MatchT);
                    fragment = new PlayerTestFragment();
                    str = "TestPlayingXiFragment";
                    break;
                } else {
                    Log.e("cascscasc", "innn222:--  " + this.MatchT);
                    fragment = new PlayerFragment();
                    str = "PlayingXiFragment";
                    break;
                }
            case R.id.navigation_stats:
                this.currentTabIndex = menuItem.getItemId();
                fragment = new QuizMatchMainFragment();
                str = "StatFragment";
                break;
        }
        return loadFragment(fragment, str);
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    checkPermission();
                } else {
                    floatView();
                }
            }
        }
    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName())), 1);
                return;
            }
            floatView();
        }
    }

    private void floatView() {
        FloatingListener r0 = new FloatingListener() {


            @Override
            public void onCreateListener(View view) {
                MainActivity.this.finishAffinity();
                final TextView textView1 =  view.findViewById(R.id.matchScore);
                ((TextView) view.findViewById(R.id.matchName)).setText(MainActivity.this.MatchBetn);
                final TextView textView2 = (TextView) view.findViewById(R.id.TestTeamARate1);
                final TextView textView3 = (TextView) view.findViewById(R.id.TestTeamARate2);
                if (MainActivity.this.mScheduler == null) {
                    MainActivity.this.mScheduler = Executors.newSingleThreadScheduledExecutor();
                    MainActivity cricihomeactivity = MainActivity.this;
                    cricihomeactivity.mScheduledFuture = cricihomeactivity.mScheduler.scheduleAtFixedRate(new Runnable() {


                        public void run() {
                            MainActivity.this.runOnUiThread(new Runnable() {


                                public void run() {
                                    try {
                                        if (MainActivity.this.checkInternetConnection()) {
                                            HashMap hashMap = new HashMap();
                                            hashMap.put("MatchId", "" + MainActivity.MatchId);
                                            MainActivity.this.mGetRetroObject().getLiveLineDetail(hashMap).enqueue(new Callback<ArrayList<LiveData>>() {


                                                @Override
                                                public void onResponse(Call<ArrayList<LiveData>> call, Response<ArrayList<LiveData>> response) {
                                                    try {
                                                        if (response.code() == 200) {
                                                            MainData cricimainjsondata = (MainData) new Gson().fromJson(response.body().get(0).getJsondata(), MainData.class);
                                                            if (response.body().get(0).getJsondata() != null && response.body().get(0).getJsonruns() != null) {
                                                                DataJason jsondata = cricimainjsondata.getJsondata();
                                                                DataRun jsonruns = ((MainRunData) new Gson().fromJson(response.body().get(0).getJsonruns(), MainRunData.class)).getJsonruns();
                                                                if (jsondata.getMatchtype().equalsIgnoreCase("Test")) {
                                                                    textView2.setText(jsondata.getTestTeamARate1());
                                                                    textView3.setText(jsondata.getTestTeamARate2());
                                                                } else {
                                                                    textView2.setText(jsonruns.getRateA());
                                                                    textView3.setText(jsonruns.getRateB());
                                                                }
                                                                if (jsondata.getScore().length() > 0) {
                                                                    TextView textView = textView1;
                                                                    textView.setText(jsondata.getWicketA() + " Ovr :" + jsondata.getOversA());
                                                                }
                                                            }
                                                        }
                                                    } catch (Exception unused) {
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<ArrayList<LiveData>> call, Throwable th) {
                                                    Log.e("onFailure ", "" + th.getMessage());
                                                }
                                            });
                                        }
                                    } catch (Exception e) {
                                    }
                                }
                            });
                        }
                    }, 0, 1, TimeUnit.SECONDS);
                }
            }

            @Override
            public void onCloseListener() {
                MainActivity.this.stopTimer();
            }
        };
        FloatingLayout floatingLayout2 = new FloatingLayout(this, R.layout.ic_pop);
        this.floatingLayout = floatingLayout2;
        floatingLayout2.setFloatingListener(r0);
        this.floatingLayout.create();
    }

    public void stopTimer() {
        if (this.mScheduler != null) {
            this.mScheduledFuture.cancel(true);
            this.mScheduler = null;
        }
    }

    private boolean loadFragment(Fragment fragment, String str) {
        if (fragment == null) {
            return false;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment, str).commit();
        return true;
    }
}
