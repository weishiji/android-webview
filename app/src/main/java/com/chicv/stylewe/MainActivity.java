package com.chicv.stylewe;


import android.annotation.SuppressLint;
import com.appsflyer.AppsFlyerLib;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    private String myUrl = "https://www.stylewe.com";
    final Activity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppsFlyerLib.getInstance().startTracking(this.getApplication(),"5BQrHzb5t82fhgKjPhLkCN");

        initWebView();

        mWebView.loadUrl(myUrl);
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        //Add Progress bar Support
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);

        setContentView(R.layout.activity_main);

        // Makes Progress bar Visible
        this.getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);

        // Get Web view
        mWebView = (WebView) findViewById(R.id.activity_main_wv);

        //to the WebView in the main.xml
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);       //Zoom Control on web (You don't need this
        //if ROM supports Multi-Touch
        mWebView.getSettings().setBuiltInZoomControls(true); //Enab


        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                activity.setTitle(view.getTitle());
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                activity.setTitle("Loading...");
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                activity.setTitle(title);
            }
        });

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
