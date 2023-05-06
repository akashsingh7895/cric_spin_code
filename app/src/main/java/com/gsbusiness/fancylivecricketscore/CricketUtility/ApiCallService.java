package com.gsbusiness.fancylivecricketscore.CricketUtility;

import com.gsbusiness.fancylivecricketscore.CricketModel.NewsCricket;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCallService {
    @GET("everything")
    Call<NewsCricket> getEverything(@Query("q") String str, @Query("apiKey") String str2);

    @GET("top-headlines")
    Call<NewsCricket> getHeadlines(@Query("country") String str, @Query("category") String str2, @Query("apiKey") String str3);
}
