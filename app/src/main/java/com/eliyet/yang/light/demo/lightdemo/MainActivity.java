package com.eliyet.yang.light.demo.lightdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.ClientCertRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    private String myUrl = "https://www.stylewe.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWebView();
        mWebView.loadUrl(myUrl);
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        mWebView = (WebView) findViewById(R.id.activity_main_wv);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                MainActivity.this.setTitle(view.getTitle());
            }
        });
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        mWebView.addJavascriptInterface(new Object() {
//            public void clickOnAndroid() {
//            }
//        }, "demo");
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            //or call your JS method
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
