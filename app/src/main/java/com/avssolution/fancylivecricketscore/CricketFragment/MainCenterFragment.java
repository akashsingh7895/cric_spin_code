package com.avssolution.fancylivecricketscore.CricketFragment;

import static com.avssolution.fancylivecricketscore.Comman.BaseAPIData;
import static com.avssolution.fancylivecricketscore.Comman.ImageURLApiData;
import static com.avssolution.fancylivecricketscore.Comman.TeamImageApiData;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.avssolution.fancylivecricketscore.CricketUtility.ApiCallInterface;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainCenterFragment extends Fragment {
    private AlertDialog alertDialog;
    public String baseURL = BaseAPIData;
    public String baseURLReg = BaseAPIData;
    public String imageURL = ImageURLApiData;
    private Context mContext;
    public ProgressDialog mProgressDialog;
    public Retrofit retrofit;
    public String teamURL = TeamImageApiData;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getContext();
    }

    public void showProgress(SwipeRefreshLayout swipeRefreshLayout) {
        if (swipeRefreshLayout != null && !swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    public void mStartProgress(String str) {
        ProgressDialog progressDialog = new ProgressDialog(this.mContext);
        this.mProgressDialog = progressDialog;
        progressDialog.setIndeterminate(true);
        this.mProgressDialog.setMessage(str);
        this.mProgressDialog.setCanceledOnTouchOutside(false);
        this.mProgressDialog.show();
    }

    public void mProgressClose() {
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.mProgressDialog.dismiss();
        }
    }

    public void hideProgress(SwipeRefreshLayout swipeRefreshLayout) {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    public boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo;
        return (getActivity() == null || (activeNetworkInfo = ((ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) ? false : true;
    }




    public Retrofit mSetRetroFitObject(String str) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Retrofit build = new Retrofit.Builder().baseUrl(str).addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().connectTimeout(180, TimeUnit.SECONDS).readTimeout(180, TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor).retryOnConnectionFailure(true).build()).build();
        this.retrofit = build;
        return build;
    }

    public Retrofit mSetRetroFitObjectMulti() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Retrofit build = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().connectTimeout(180, TimeUnit.SECONDS).readTimeout(180, TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor).retryOnConnectionFailure(true).build()).build();
        this.retrofit = build;
        return build;
    }

    public void showToast(Context context, String str) {
        if (getActivity() != null) {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }

    public ApiCallInterface mGetRetroObject(String str) {
        return (ApiCallInterface) mSetRetroFitObject(str).create(ApiCallInterface.class);
    }

    public ApiCallInterface mGetRetroObjectMulti() {
        return (ApiCallInterface) mSetRetroFitObjectMulti().create(ApiCallInterface.class);
    }

    public void showToast(String str) {
        Toast.makeText(this.mContext, str, Toast.LENGTH_LONG).show();
    }



    public void mSendIntent(String str) {
        try {
            if (!TextUtils.isEmpty(str) && str.length() > 0) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                getActivity().startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
