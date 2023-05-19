package com.avssolution.fancylivecricketscore.CricketActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.avssolution.fancylivecricketscore.CricketAdapter.StoriesOne;
import com.avssolution.fancylivecricketscore.CricketAdapter.StoriesTwo;
import com.avssolution.fancylivecricketscore.CricketAdapter.StoriesThree;
import com.avssolution.fancylivecricketscore.CricketModel.NewsCricket;
import com.avssolution.fancylivecricketscore.CricketModel.NewsItems;
import com.avssolution.fancylivecricketscore.CricketUtility.ApiCallService;
import com.avssolution.fancylivecricketscore.CricketUtility.RequestHandler;
import com.avssolution.fancylivecricketscore.CricketUtility.RetrofitClientInstance;
import com.avssolution.fancylivecricketscore.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDataActivity extends AppCompatActivity {
    static final String url = "https://newsapi.org/v2/everything";

    JSONArray jsonArray;
    RelativeLayout mFound;
    private StoriesOne mNewsAdapter;
    public StoriesTwo mNewsAdapter1;
    public StoriesThree mNewsAdapter2;
    private ListView mNewsListView;
    RecyclerView mostrecent;
    public List<NewsItems> newsItems = new ArrayList();
    public NewsCricket newsModel = new NewsCricket();
    ShimmerFrameLayout shimmerLayout1;
    public RecyclerView topstoryRecycler1;
    public RecyclerView topstoryRecycler2;
    ImageView ivBack;
    String Datakey;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024,1024);
        setContentView(R.layout.activity_data_news);
        this.ivBack = findViewById(R.id.ivBack);
        this.mostrecent = (RecyclerView) findViewById(R.id.mostrecentrv);
        this.mNewsListView = (ListView) findViewById(R.id.topstory);
        this.topstoryRecycler1 = (RecyclerView) findViewById(R.id.topstoryRecycler1);
        this.topstoryRecycler2 = (RecyclerView) findViewById(R.id.topstoryRecycler2);
        this.shimmerLayout1 = (ShimmerFrameLayout) findViewById(R.id.shimmerLayout1);
       
        this.mNewsListView.setAdapter((ListAdapter) this.mNewsAdapter);
        this.mFound = (RelativeLayout) findViewById(R.id.news_not_found);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        this.mNewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                String news_image = NewsDataActivity.this.newsItems.get(i).getNews_image();
                String news_title = NewsDataActivity.this.newsItems.get(i).getNews_title();
                String news_description = NewsDataActivity.this.newsItems.get(i).getNews_description();
                Intent intent = new Intent(NewsDataActivity.this, NewsDataDetailsActivity.class);
                intent.putExtra("image", news_image);
                intent.putExtra("title", news_title);
                intent.putExtra("description", news_description);
                NewsDataActivity.this.startActivity(intent);
            }
        });
        mFetchListFromAPI2();
        mFetchListFromAPI3();
    }

    public void mFetchListFromAPI2() {
        ((ApiCallService) RetrofitClientInstance.getRetrofitInstance(this).create(ApiCallService.class)).getEverything("cricket", "0e616f1090ae42c2a138fcc2b707e500").enqueue(new Callback<NewsCricket>() {


            @Override
            public void onResponse(Call<NewsCricket> call, Response<NewsCricket> response) {
                NewsDataActivity.this.newsModel = response.body();
               
                NewsDataActivity newsActivity = NewsDataActivity.this;
                NewsDataActivity newsActivity2 = NewsDataActivity.this;
                newsActivity.mNewsAdapter1 = new StoriesTwo(newsActivity2, newsActivity2.newsModel);
                NewsDataActivity.this.topstoryRecycler1.setAdapter(NewsDataActivity.this.mNewsAdapter1);
                NewsDataActivity.this.topstoryRecycler1.setLayoutManager(new LinearLayoutManager(NewsDataActivity.this));
                NewsDataActivity.this.shimmerLayout1.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<NewsCricket> call, Throwable th) {
                NewsDataActivity.this.mFetchListFromAPI2();
            }
        });
    }

    public void mFetchListFromAPI3() {
        ((ApiCallService) RetrofitClientInstance.getRetrofitInstance(this).create(ApiCallService.class)).getHeadlines("in", "sports", "0e616f1090ae42c2a138fcc2b707e500").enqueue(new Callback<NewsCricket>() {


            @Override
            public void onResponse(Call<NewsCricket> call, Response<NewsCricket> response) {
                NewsDataActivity.this.newsModel = response.body();
               
                NewsDataActivity newsActivity = NewsDataActivity.this;
                newsActivity.mNewsAdapter2 = new StoriesThree(newsActivity, newsActivity.newsModel);
                NewsDataActivity.this.topstoryRecycler2.setAdapter(NewsDataActivity.this.mNewsAdapter2);
                NewsDataActivity.this.topstoryRecycler2.setLayoutManager(new LinearLayoutManager(NewsDataActivity.this, RecyclerView.HORIZONTAL, false));
                NewsDataActivity.this.shimmerLayout1.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<NewsCricket> call, Throwable th) {
                NewsDataActivity.this.mFetchListFromAPI3();
            }
        });
    }

    private void mFetchMostRecentFromAPI() {
        RequestHandler.getInstance(this).addToRequestQueue(new StringRequest(0, url, new com.android.volley.Response.Listener<String>() {


            public void onResponse(String str) {
               
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    NewsDataActivity.this.jsonArray = jSONObject.getJSONArray("arts");
                    NewsDataActivity newsActivity = NewsDataActivity.this;
                    newsActivity.getMostData(newsActivity.jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError volleyError) {
               
                NewsDataActivity.this.mFound.setVisibility(View.VISIBLE);
                NewsDataActivity.this.mFetchListFromAPI();
            }
        }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("content-type", "application/json");
                hashMap.put("x-rapidapi-host", "livescore6.p.rapidapi.com");
                hashMap.put("x-rapidapi-key", Datakey);
                return hashMap;
            }
        });
    }

    public void getMostData(JSONArray jSONArray) {
        if (jSONArray.length() > 0) {
            this.mFound.setVisibility(View.GONE);
            for (int i = 0; i < jSONArray.length(); i++) {
                NewsItems newsItems2 = new NewsItems();
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    newsItems2.setNews_title(jSONObject.getString("tit"));
                    newsItems2.setNews_description(jSONObject.getString("des"));
                    newsItems2.setNews_image(jSONObject.getString("img"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.newsItems.add(newsItems2);
            }
            this.mNewsAdapter.notifyDataSetChanged();
            return;
        }
        this.mFound.setVisibility(View.GONE);
    }

    public void mFetchListFromAPI() {
        RequestHandler.getInstance(this).addToRequestQueue(new StringRequest(0, url, new com.android.volley.Response.Listener<String>() {


            public void onResponse(String str) {
               
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    NewsDataActivity.this.jsonArray = jSONObject.getJSONArray("arts");
                    NewsDataActivity newsActivity = NewsDataActivity.this;
                    newsActivity.getData(newsActivity.jsonArray);
                    NewsDataActivity.this.shimmerLayout1.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError volleyError) {
               
                NewsDataActivity.this.mFound.setVisibility(View.GONE);
                volleyError.printStackTrace();
                NewsDataActivity.this.mFetchListFromAPI();
            }
        }) {


            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("q", "cricket");
                hashMap.put("apiKey", "0e616f1090ae42c2a138fcc2b707e500");
                return hashMap;
            }
        });
    }

    public void getData(JSONArray jSONArray) {
        if (jSONArray.length() > 0) {
            this.mFound.setVisibility(View.GONE);
            for (int i = 0; i < jSONArray.length(); i++) {
                NewsItems newsItems2 = new NewsItems();
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    newsItems2.setNews_title(jSONObject.getString("tit"));
                    newsItems2.setNews_description(jSONObject.getString("des"));
                    newsItems2.setNews_image(jSONObject.getString("img"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.newsItems.add(newsItems2);
            }
            this.mNewsAdapter.notifyDataSetChanged();
            return;
        }
        this.mFound.setVisibility(View.GONE);
    }


}
