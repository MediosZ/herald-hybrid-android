package cn.myseu.heraldapp.Components;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebViewClient;

import android.webkit.JavascriptInterface;
import android.widget.LinearLayout;

import java.text.AttributedCharacterIterator;
import io.reactivex.Observable;

import cn.myseu.heraldapp.R;
import io.reactivex.functions.Action;

public class AuthWebView extends WebView {

    private  WebSettings webSettings;
    private String token;

    public AuthWebView(Context context) {

        super(context);
        webSettings = getSettings();
        // 启用Javascript *默认不启用，你是魔鬼吗？
        webSettings.setJavaScriptEnabled(true);

        // 启用localStorage
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheMaxSize(1024*1024*8);
        String appCachePath = context.getCacheDir().getAbsolutePath();
        webSettings.setAppCachePath(appCachePath);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
    }

    public void setToken(String token){
        this.token = token;
    }

    public void injectToken(String token) {
        evaluateJavascript("window.injectToken('"+token+"')", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) { }
        });
    }

    public void pushRoute(String route) {
        evaluateJavascript("window.goto('" + route + "')", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {

            }
        });
    }


}
