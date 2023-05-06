package com.gsbusiness.fancylivecricketscore;

public class Comman {
    static {
        System.loadLibrary("hello-jni");
    }


    public static native String TestTeamApiData();
    public static final String TestTeamApiData = TestTeamApiData();

    public static native String BaseAPIData();
    public static final String BaseAPIData = BaseAPIData();

    public static native String ImageURLApiData();
    public static final String ImageURLApiData = ImageURLApiData();

    public static native String TeamImageApiData();
    public static final String TeamImageApiData = TeamImageApiData();


}

