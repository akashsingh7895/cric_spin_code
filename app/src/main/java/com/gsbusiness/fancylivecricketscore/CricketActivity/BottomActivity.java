package com.gsbusiness.fancylivecricketscore.CricketActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.gsbusiness.fancylivecricketscore.CricketFragment.BottomFragment;
import com.gsbusiness.fancylivecricketscore.CricketFragment.ResultsFragment;
import com.gsbusiness.fancylivecricketscore.CricketFragment.UpcomingFragment;
import com.gsbusiness.fancylivecricketscore.CricketUtility.UserPreferData;
import com.gsbusiness.fancylivecricketscore.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomActivity extends NetworkActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static ImageView adCustomeFooter;
    private static int sTheme;
    private BottomNavigationView bNavigationView;
    public int currentTabIndex = 0;
    public SharedPreferences.Editor mPrefEdit;
    public SharedPreferences mPrefrences;
    boolean doubleBackToExitPressedOnce = false;

    FirebaseFirestore firebaseFirestore;
    String tgUrl;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_bottom);




        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("tg")
                        .document("url")
                                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.isSuccessful()){
                            DocumentSnapshot snapshot = task.getResult();
                            tgUrl = (String) snapshot.get("url");
                            Log.e("tgUrl", String.valueOf(tgUrl));

                        }else {
                            Toast.makeText(BottomActivity.this, "somethings went wrong"+task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        mInitResources();
        mGetSharedFooterValue();
    }

    private void mGetSharedFooterValue() {
        try {
            SharedPreferences sharedPreferences = this.mPrefrences;
            if (sharedPreferences != null && !TextUtils.isEmpty(sharedPreferences.getString(UserPreferData.FOOTER_MAIN, ""))) {
                if (this.mPrefrences.getString(UserPreferData.FOOTER_MAIN, "").equals("VERSION_1")) {
                    runOnUiThread(new Runnable() {


                        public void run() {
                            try {
                                BottomActivity.adCustomeFooter.setVisibility(View.VISIBLE);

                                ((RequestBuilder) ((RequestBuilder) Glide.with((FragmentActivity) BottomActivity.this).load(BottomActivity.this.mPrefrences.getString(UserPreferData.FOOTER_URL, "")).skipMemoryCache(true)).diskCacheStrategy(DiskCacheStrategy.NONE)).into(BottomActivity.adCustomeFooter);
                                BottomActivity.adCustomeFooter.setOnClickListener(new View.OnClickListener() {


                                    public void onClick(View view) {
                                        if (!TextUtils.isEmpty(BottomActivity.this.mPrefrences.getString(UserPreferData.FOOTER_RED, ""))) {
                                            BottomActivity.this.mSendIntent(BottomActivity.this.mPrefrences.getString(UserPreferData.FOOTER_RED, ""));
                                        }
                                    }
                                });
                            } catch (Exception e) {
                            }
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {


                        public void run() {

                            BottomActivity.adCustomeFooter.setVisibility(View.GONE);
                        }
                    });
                }
            }
        } catch (Exception e) {
        }
    }

    public void mSendIntent(String str) {
        try {
            if (!TextUtils.isEmpty(str) && str.length() > 0) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mInitResources() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        this.bNavigationView = bottomNavigationView;
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        adCustomeFooter =  findViewById(R.id.adCustomeFooter);
        loadFragment(new BottomFragment(), "MultipleMatchFragment");
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        String str;
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                this.currentTabIndex = menuItem.getItemId();
                fragment = new BottomFragment();
                str = "MultipleMatchFragment";
                break;
            case R.id.navigation_news:
                startActivity(new Intent(BottomActivity.this, NewsDataActivity.class));

                str = "RegisterFragment";
                break;
            case R.id.navigation_odds:
            case R.id.navigation_playing11:
            case R.id.navigation_stats:
            default:
                str = null;
                break;
            case R.id.navigation_result:
                this.currentTabIndex = menuItem.getItemId();
                fragment = new ResultsFragment();
                str = "ResultsFragment";
                break;
            case R.id.navigation_upcoming:
                this.currentTabIndex = menuItem.getItemId();
                fragment = new UpcomingFragment();
                str = "UpcomingMatchFragment";
                break;
            case R.id.navigation_join_telegram:
                this.currentTabIndex = menuItem.getItemId();

                if (tgUrl.equals("")){
                    Toast.makeText(this, "Please Wait", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tgUrl));
                        PackageManager pm = getPackageManager();
                        if (intent.resolveActivity(pm) != null) {
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Error message", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception ignored) {
                    }
                }

                str = "Join Telegram";
                break;
        }
        return loadFragment(fragment, str);
    }

    private boolean loadFragment(Fragment fragment, String str) {
        if (fragment == null) {
            return false;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment, str).commit();
        return true;
    }

    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "double tap to exit!", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

}
