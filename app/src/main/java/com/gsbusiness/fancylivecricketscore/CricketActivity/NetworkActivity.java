package com.gsbusiness.fancylivecricketscore.CricketActivity;

import android.content.Context;
import android.net.ConnectivityManager;

import androidx.appcompat.app.AppCompatActivity;

import com.gsbusiness.fancylivecricketscore.CricketUtility.ApiCallInterface;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkActivity extends AppCompatActivity {
    public String baseURL;

    public boolean checkInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isAvailable() && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public ApiCallInterface mGetRetroObject() {
        return (ApiCallInterface) retrofitCall().create(ApiCallInterface.class);
    }


    public Retrofit retrofitCall() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new Retrofit.Builder().baseUrl(this.baseURL).client(new OkHttpClient.Builder().readTimeout(180, TimeUnit.SECONDS).connectTimeout(180, TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor).build()).addConverterFactory(GsonConverterFactory.create()).build();
    }

}
