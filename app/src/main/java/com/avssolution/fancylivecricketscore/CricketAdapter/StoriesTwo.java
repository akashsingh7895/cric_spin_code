package com.avssolution.fancylivecricketscore.CricketAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.avssolution.fancylivecricketscore.CricketModel.Article;
import com.avssolution.fancylivecricketscore.CricketActivity.NewsDataDetailsActivity;
import com.avssolution.fancylivecricketscore.CricketModel.NewsCricket;
import com.avssolution.fancylivecricketscore.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class StoriesTwo extends RecyclerView.Adapter<StoriesTwo.ViewHolder> {
    public final Context context;
    private final NewsCricket newsModel;

    public StoriesTwo(Context context2, NewsCricket newsModel2) {
        this.context = context2;
        this.newsModel = newsModel2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ic_news_item, viewGroup, false));
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
        return this.newsModel.getArticles().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView latestTime;
        TextView news_description;
        PorterShapeImageView news_image;
        TextView news_title;
        RelativeLayout parent;

        public ViewHolder(View view) {
            super(view);
            this.news_image = (PorterShapeImageView) view.findViewById(R.id.news_image);
            this.news_title = (TextView) view.findViewById(R.id.news_title);
            this.news_description = (TextView) view.findViewById(R.id.news_description);
            this.latestTime = (TextView) view.findViewById(R.id.latestTime);
            this.parent = (RelativeLayout) view.findViewById(R.id.parent);
        }

        public void setData(final Article article) throws ParseException {
            Glide.with(StoriesTwo.this.context).load(article.getUrlToImage()).into(this.news_image);
            this.news_title.setText(article.getTitle());
            this.news_description.setText(article.getDescription());
            this.parent.setOnClickListener(new View.OnClickListener() {


                public void onClick(View view) {
                    Intent intent = new Intent(StoriesTwo.this.context, NewsDataDetailsActivity.class);
                    intent.putExtra("data", article);
                    StoriesTwo.this.context.startActivity(intent);
                }
            });
            this.latestTime.setText(new SimpleDateFormat("MMM d, h:mm a").format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(article.getPublishedAt())));
        }
    }
}
