package com.gsbusiness.fancylivecricketscore.CricketAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gsbusiness.fancylivecricketscore.CricketModel.NewsItems;
import com.gsbusiness.fancylivecricketscore.R;

import java.util.List;

public class StoriesOne extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<NewsItems> listItems;

    public long getItemId(int i) {
        return (long) i;
    }

    public StoriesOne(Activity activity2, List<NewsItems> list) {
        this.activity = activity2;
        this.listItems = list;
    }

    public int getCount() {
        return this.listItems.size();
    }

    public Object getItem(int i) {
        return this.listItems.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (this.inflater == null) {
            this.inflater = (LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null) {
            view = this.inflater.inflate(R.layout.ic_news_item, (ViewGroup) null);
        }
        TextView textView = (TextView) view.findViewById(R.id.latestTime);
        NewsItems newsItems = this.listItems.get(i);
        ((TextView) view.findViewById(R.id.news_title)).setText(newsItems.getNews_title());
        ((TextView) view.findViewById(R.id.news_description)).setText(newsItems.getNews_description());
        Glide.with(this.activity.getApplicationContext()).load(newsItems.getNews_image()).into((ImageView) view.findViewById(R.id.news_image));
        return view;
    }
}
