package com.example.dell.myapplication.function;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.dell.myapplication.R;
import com.example.dell.myapplication.BaseFragment;


public class FunctionRoute extends BaseFragment {

    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_function_route, container, false);

        webView = view.findViewById(R.id.route);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://m.tyg.youwandao.com/panorama?app=1#pauseaudio");
        webView.setInitialScale(57*4);
        // Inflate the layout for this fragment
        return view;
    }

}
