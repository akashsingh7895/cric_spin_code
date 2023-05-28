package com.avssolution.fancylivecricketscore.LiveSeries;

public class SeriesModel {
    String seriesname;
    int seriesid;
    String startdate;
    String enddate;

    public SeriesModel(String seriesname, int seriesid, String startdate, String enddate) {
        this.seriesname = seriesname;
        this.seriesid = seriesid;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public String getSeriesname() {
        return seriesname;
    }

    public void setSeriesname(String seriesname) {
        this.seriesname = seriesname;
    }

    public int getSeriesid() {
        return seriesid;
    }

    public void setSeriesid(int seriesid) {
        this.seriesid = seriesid;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}
