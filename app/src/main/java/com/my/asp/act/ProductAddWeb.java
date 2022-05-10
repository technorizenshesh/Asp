package com.my.asp.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.my.asp.R;
import com.my.asp.databinding.ActivityProductAddWebBinding;

public class ProductAddWeb extends AppCompatActivity {

  //  private WebView webView;

    String webUrl ="ww.google.com";

    ActivityProductAddWebBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_product_add_web);

        binding.RRNav.setOnClickListener(v -> {
            onBackPressed();
        });

      Intent intent=getIntent();
        if(intent !=null)
        {
            webUrl= intent.getStringExtra("Url");
        }

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.setCancelable(false);

      /*  binding.webView.getSettings().setBuiltInZoomControls(true);

        // webView.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl("https://care-pad.uk/carepad/uploads/images/");

        binding.webView.loadUrl(webUrl);

        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        binding.webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100) {
                    progressDialog.show();
                }
                if (progress == 100) {
                    progressDialog.dismiss();
                }
            }
        });
        */


        //////
        binding.webView.getSettings().setBuiltInZoomControls(true);

        binding.webView.loadUrl(webUrl);

        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        binding.webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100) {
                    progressDialog.show();
                }
                if (progress == 100) {
                    progressDialog.dismiss();
                }
            }
        });

    }

}