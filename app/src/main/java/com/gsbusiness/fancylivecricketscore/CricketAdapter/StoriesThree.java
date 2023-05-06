package com.gsbusiness.fancylivecricketscore.CricketAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gsbusiness.fancylivecricketscore.CricketModel.Article;
import com.gsbusiness.fancylivecricketscore.CricketActivity.NewsDataDetailsActivity;
import com.gsbusiness.fancylivecricketscore.CricketModel.NewsCricket;
import com.gsbusiness.fancylivecricketscore.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class StoriesThree extends RecyclerView.Adapter<StoriesThree.ViewHolder> {
    public final Context context;
    private final NewsCricket newsModel;

    public StoriesThree(Context context2, NewsCricket newsModel2) {
        this.context = context2;
        this.newsModel = newsModel2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ic_news_item2, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        try {
            viewHolder.setData(this.newsModel.getArticles().get(i));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return Math.min(this.newsModel.getArticles().size(), 5);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView latestTime;
        TextView news_description;
        ImageView news_image;
        TextView news_title;
        RelativeLayout parent;

        public ViewHolder(View view) {
            super(view);
            this.news_image = (ImageView) view.findViewById(R.id.news_image);
            this.news_title = (TextView) view.findViewById(R.id.news_title);
            this.news_description = (TextView) view.findViewById(R.id.news_description);
            this.latestTime = (TextView) view.findViewById(R.id.latestTime);
            this.parent = (RelativeLayout) view.findViewById(R.id.parent);
        }

        public void setData(final Article article) throws ParseException {
            Glide.with(StoriesThree.this.context).load(article.getUrlToImage()).into(this.news_image);
            this.news_title.setText(article.getTitle());
            this.parent.setOnClickListener(new View.OnClickListener() {


                public void onClick(View view) {
                    Intent intent = new Intent(StoriesThree.this.context, NewsDataDetailsActivity.class);
                    intent.putExtra("data", article);
                    StoriesThree.this.context.startActivity(intent);
                }
            });
            this.latestTime.setText(new SimpleDateFormat("E, MMM d, yyyy, h:mm a").format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(article.getPublishedAt())));
        }
    }
}
