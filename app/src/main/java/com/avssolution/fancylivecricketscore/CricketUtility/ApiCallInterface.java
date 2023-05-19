package com.avssolution.fancylivecricketscore.CricketUtility;

import com.avssolution.fancylivecricketscore.CricketModel.AllMatch;
import com.avssolution.fancylivecricketscore.CricketModel.LiveData;
import com.avssolution.fancylivecricketscore.CricketModel.Match;
import com.avssolution.fancylivecricketscore.CricketModel.Upcomming;
import com.avssolution.fancylivecricketscore.CricketModel.PlayerData;
import com.avssolution.fancylivecricketscore.CricketModel.News;
import com.avssolution.fancylivecricketscore.CricketModel.OddData;
import com.avssolution.fancylivecricketscore.CricketModel.ResultData;
import com.avssolution.fancylivecricketscore.CricketModel.StatusData;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiCallInterface {
    @Headers({"Content-Type:application/json"})
    @POST("Register")
    Call<Match> RegisterAPI(@Body Map<String, String> map);

    @GET("LiveLine")
    Call<ArrayList<AllMatch>> getAllLiveMatchs();

    @POST("MatchResults")
    Call<ResultData> getAllMatchesResult(@Body Map<String, String> map);

    @POST("GetAllPlayers")
    Call<PlayerData> getAllPlayers(@Body Map<String, String> map);

    @GET("LiveLine")
    Call<ArrayList<LiveData>> getLiveLine();

    @POST("LiveLine_Match")
    Call<ArrayList<LiveData>> getLiveLineDetail(@Body Map<String, String> map);

    @POST("MatchOdds")
    Call<OddData> getMatchOdds(@Body Map<String, String> map);

    @POST("MatchStats")
    Call<StatusData> getMatchStats(@Body Map<String, String> map);

    @GET("SportsNews")
    Call<News> getNews();

    @GET("upcomingMatches")
    Call<Upcomming> getUpcomingMatches();
}
