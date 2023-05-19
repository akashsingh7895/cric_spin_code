package com.avssolution.fancylivecricketscore.CricketModel;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataJason {
    @SerializedName("Criclivefooter")
    @Expose
    private String Criclivefooter;
    @SerializedName("Criclivefooterredirect")
    @Expose
    private String Criclivefooterredirect;
    @SerializedName("Criclivefooterurl")
    @Expose
    private String Criclivefooterurl;
    @SerializedName("Last6Balls")
    @Expose
    private String Last6Balls;
    @SerializedName("TeamABanner")
    @Expose
    private String TeamABanner;
    @SerializedName("TeamBBanner")
    @Expose
    private String TeamBBanner;
    @SerializedName("TestTeamA")
    @Expose
    private String TestTeamA;
    @SerializedName("TestTeamARate1")
    @Expose
    private String TestTeamARate1;
    @SerializedName("TestTeamARate2")
    @Expose
    private String TestTeamARate2;
    @SerializedName("TestTeamB")
    @Expose
    private String TestTeamB;
    @SerializedName("TestTeamBRate1")
    @Expose
    private String TestTeamBRate1;
    @SerializedName("TestTeamBRate2")
    @Expose
    private String TestTeamBRate2;
    @SerializedName("Testdraw")
    @Expose
    private String Testdraw;
    @SerializedName("TestdrawRate1")
    @Expose
    private String TestdrawRate1;
    @SerializedName("TestdrawRate2")
    @Expose
    private String TestdrawRate2;

    @SerializedName("appversion")
    @Expose
    private String appversion;

    @SerializedName("batsman")
    @Expose
    private String batsman;
    @SerializedName("bowler")
    @Expose
    private String bowler;
    @SerializedName("s4")
    @Expose
    private String f148s4;
    @SerializedName("s6")
    @Expose
    private String f149s6;
    @SerializedName("matchtype")
    @Expose
    private String matchtype;
    @SerializedName("ns4")
    @Expose
    private String ns4;
    @SerializedName("ns6")
    @Expose
    private String ns6;
    @SerializedName("oversA")
    @Expose
    private String oversA;
    @SerializedName("oversB")
    @Expose
    private String oversB;
    @SerializedName("rateA")
    @Expose
    private String rateA;
    @SerializedName(FirebaseAnalytics.Param.SCORE)
    @Expose
    private String score;
    @SerializedName("sessionA")
    @Expose
    private String sessionA;
    @SerializedName("sessionB")
    @Expose
    private String sessionB;
    @SerializedName("sessionOver")
    @Expose
    private String sessionOver;
    @SerializedName("teamA")
    @Expose
    private String teamA;
    @SerializedName("teamB")
    @Expose
    private String teamB;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("totalballs")
    @Expose
    private String totalballs;
    @SerializedName("wicketA")
    @Expose
    private String wicketA;
    @SerializedName("wicketB")
    @Expose
    private String wicketB;

    public String getMatchtype() {
        return this.matchtype;
    }

    public void setMatchtype(String str) {
        this.matchtype = str;
    }

    public String getTestTeamA() {
        return this.TestTeamA;
    }

    public void setTestTeamA(String str) {
        this.TestTeamA = str;
    }

    public String getTestTeamARate1() {
        return this.TestTeamARate1;
    }

    public void setTestTeamARate1(String str) {
        this.TestTeamARate1 = str;
    }

    public String getTestTeamARate2() {
        return this.TestTeamARate2;
    }

    public void setTestTeamARate2(String str) {
        this.TestTeamARate2 = str;
    }

    public String getTestTeamB() {
        return this.TestTeamB;
    }

    public void setTestTeamB(String str) {
        this.TestTeamB = str;
    }

    public String getTestTeamBRate1() {
        return this.TestTeamBRate1;
    }

    public void setTestTeamBRate1(String str) {
        this.TestTeamBRate1 = str;
    }

    public String getTestTeamBRate2() {
        return this.TestTeamBRate2;
    }

    public void setTestTeamBRate2(String str) {
        this.TestTeamBRate2 = str;
    }

    public String getTestdraw() {
        return this.Testdraw;
    }

    public void setTestdraw(String str) {
        this.Testdraw = str;
    }

    public String getTestdrawRate1() {
        return this.TestdrawRate1;
    }

    public void setTestdrawRate1(String str) {
        this.TestdrawRate1 = str;
    }

    public String getTestdrawRate2() {
        return this.TestdrawRate2;
    }

    public void setTestdrawRate2(String str) {
        this.TestdrawRate2 = str;
    }

    public String getS4() {
        return this.f148s4;
    }

    public void setS4(String str) {
        this.f148s4 = str;
    }

    public String getNs4() {
        return this.ns4;
    }

    public void setNs4(String str) {
        this.ns4 = str;
    }

    public String getS6() {
        return this.f149s6;
    }

    public void setS6(String str) {
        this.f149s6 = str;
    }

    public String getNs6() {
        return this.ns6;
    }

    public void setNs6(String str) {
        this.ns6 = str;
    }

    public String getCriclivefooter() {
        return this.Criclivefooter;
    }

    public void setCriclivefooter(String str) {
        this.Criclivefooter = str;
    }

    public String getCriclivefooterurl() {
        return this.Criclivefooterurl;
    }

    public void setCriclivefooterurl(String str) {
        this.Criclivefooterurl = str;
    }

    public String getCriclivefooterredirect() {
        return this.Criclivefooterredirect;
    }

    public void setCriclivefooterredirect(String str) {
        this.Criclivefooterredirect = str;
    }

    public String getTeamABanner() {
        return this.TeamABanner;
    }

    public void setTeamABanner(String str) {
        this.TeamABanner = str;
    }

    public String getTeamBBanner() {
        return this.TeamBBanner;
    }

    public void setTeamBBanner(String str) {
        this.TeamBBanner = str;
    }

    public String getLast6Balls() {
        return this.Last6Balls;
    }

    public void setLast6Balls(String str) {
        this.Last6Balls = str;
    }

    public String getBatsman() {
        return this.batsman;
    }

    public void setBatsman(String str) {
        this.batsman = str;
    }

    public String getBowler() {
        return this.bowler;
    }

    public void setBowler(String str) {
        this.bowler = str;
    }

    public String getOversA() {
        return this.oversA;
    }

    public void setOversA(String str) {
        this.oversA = str;
    }

    public String getOversB() {
        return this.oversB;
    }

    public void setOversB(String str) {
        this.oversB = str;
    }

    public String getRateA() {
        return this.rateA;
    }

    public void setRateA(String str) {
        this.rateA = str;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String str) {
        this.score = str;
    }

    public String getSessionA() {
        return this.sessionA;
    }

    public void setSessionA(String str) {
        this.sessionA = str;
    }

    public String getSessionB() {
        return this.sessionB;
    }

    public void setSessionB(String str) {
        this.sessionB = str;
    }

    public String getSessionOver() {
        return this.sessionOver;
    }

    public void setSessionOver(String str) {
        this.sessionOver = str;
    }

    public String getTeamA() {
        return this.teamA;
    }

    public void setTeamA(String str) {
        this.teamA = str;
    }

    public String getTeamB() {
        return this.teamB;
    }

    public void setTeamB(String str) {
        this.teamB = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getTotalballs() {
        return this.totalballs;
    }

    public void setTotalballs(String str) {
        this.totalballs = str;
    }

    public String getWicketA() {
        return this.wicketA;
    }

    public void setWicketA(String str) {
        this.wicketA = str;
    }

    public String getWicketB() {
        return this.wicketB;
    }

    public void setWicketB(String str) {
        this.wicketB = str;
    }

    public String getAppversion() {
        return this.appversion;
    }

    public void setAppversion(String str) {
        this.appversion = str;
    }
}
