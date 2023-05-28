package com.avssolution.fancylivecricketscore.LiveSeries;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("api/values/LiveSeries")
    Call<List<SeriesModel>> getSeries();

}
