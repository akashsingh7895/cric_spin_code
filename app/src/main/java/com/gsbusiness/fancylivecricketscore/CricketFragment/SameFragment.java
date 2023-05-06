package com.gsbusiness.fancylivecricketscore.CricketFragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gsbusiness.fancylivecricketscore.R;

public class SameFragment extends MainCenterFragment {

    private Activity mContext;
    public String mStatsData;
    public WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_same, viewGroup, false);
        this.mContext = getActivity();
        this.mWebView = (WebView) inflate.findViewById(R.id.webViewStats);
        if (getArguments() != null) {
            String string = getArguments().getString("webViewData");
            this.mStatsData = string;
            if (string != null) {
                this.mStatsData = "<!DOCTYPE html><head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> <html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=windows-1250\"><meta name=\"spanish press\" content=\"spain, spanish newspaper, news,economy,politics,sports\"><title></title></head><body id=\"body\">" + this.mStatsData + "</body></html>";
                this.mWebView.setWebViewClient(new WebViewClient() {


                    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                        super.onPageStarted(webView, str, bitmap);
                        SameFragment.this.mStartProgress("Loading..");
                    }

                    @Override
                    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                        SameFragment.this.mWebView.loadUrl(str);
                        return true;
                    }

                    public void onPageFinished(WebView webView, String str) {
                        super.onPageFinished(webView, str);
                        SameFragment.this.mProgressClose();
                    }

                    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                        super.onReceivedError(webView, webResourceRequest, webResourceError);
                        SameFragment.this.mProgressClose();
                    }
                });
                this.mWebView.clearCache(true);
                this.mWebView.clearHistory();
                this.mWebView.getSettings().setJavaScriptEnabled(true);
                this.mWebView.setHorizontalScrollBarEnabled(false);
                this.mWebView.loadData(this.mStatsData, "text/html", "UTF-8");
            }
        }
        return inflate;
    }
}
