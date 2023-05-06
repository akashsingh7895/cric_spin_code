package com.gsbusiness.fancylivecricketscore.CricketModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainData {
    @SerializedName("jsondata")
    @Expose
    private DataJason jsondata;

    public DataJason getJsondata() {
        return this.jsondata;
    }

    public void setJsondata(DataJason cricijsondata) {
        this.jsondata = cricijsondata;
    }
}
