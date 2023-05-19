package com.avssolution.fancylivecricketscore.CricketModel;

import androidx.core.app.NotificationCompat;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class News implements Serializable {
    @SerializedName(NotificationCompat.CATEGORY_MESSAGE)
    @Expose
    private String msg;
    @SerializedName("NewsList")
    @Expose
    private List<NewsList> newsList = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<NewsList> getNewsList() {
        return this.newsList;
    }

    public void setNewsList(List<NewsList> list) {
        this.newsList = list;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean bool) {
        this.success = bool;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public class NewsList implements Serializable {
        @SerializedName("author")
        @Expose
        private String author;
        @SerializedName(FirebaseAnalytics.Param.CONTENT)
        @Expose
        private String content;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("PublishedAT")
        @Expose
        private String publishedAT;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("URL")
        @Expose
        private String uRL;
        @SerializedName("URLToImage")
        @Expose
        private String uRLToImage;

        public NewsList() {
        }

        public String getAuthor() {
            return this.author;
        }

        public void setAuthor(String str) {
            this.author = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public String getURL() {
            return this.uRL;
        }

        public void setURL(String str) {
            this.uRL = str;
        }

        public String getURLToImage() {
            return this.uRLToImage;
        }

        public void setURLToImage(String str) {
            this.uRLToImage = str;
        }

        public String getPublishedAT() {
            return this.publishedAT;
        }

        public void setPublishedAT(String str) {
            this.publishedAT = str;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String str) {
            this.content = str;
        }
    }
}
