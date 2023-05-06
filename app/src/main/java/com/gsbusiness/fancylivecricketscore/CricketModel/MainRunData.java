package com.gsbusiness.fancylivecricketscore.CricketModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainRunData {
    @SerializedName("jsonruns")
    @Expose
    private DataRun jsonruns;

    public DataRun getJsonruns() {
        return this.jsonruns;
    }

    public void setJsonruns(DataRun cricijsonruns) {
        this.jsonruns = cricijsonruns;
    }
}
