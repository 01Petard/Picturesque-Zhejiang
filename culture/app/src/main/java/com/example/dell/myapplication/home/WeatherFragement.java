package com.example.dell.myapplication.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.dell.myapplication.BaseFragment;
import com.example.dell.myapplication.R;



public class WeatherFragement extends BaseFragment {

    private View view;
    private WebView webView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_weather, container, false);
        webView=(WebView) view.findViewById(R.id.Web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://t.pae.baidu.com/s?s=bai-todaxm");
        webView.setInitialScale(57*4);
        return view;
    }
}
