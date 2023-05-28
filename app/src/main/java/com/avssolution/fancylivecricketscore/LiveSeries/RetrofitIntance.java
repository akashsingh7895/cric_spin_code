package com.avssolution.fancylivecricketscore.LiveSeries;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitIntance {

    public ApiInterface apiInterface;
    String api = "http://cricpro.cricnet.co.in/";

   public static RetrofitIntance intance;
    RetrofitIntance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

       apiInterface =  retrofit.create(ApiInterface.class);
    }

    public static RetrofitIntance getInstance(){
        if (intance==null){
            intance = new RetrofitIntance();
        }
        return intance;
    }

}
