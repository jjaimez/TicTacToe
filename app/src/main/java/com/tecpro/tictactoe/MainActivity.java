package com.tecpro.tictactoe;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {
    InterstitialAd mInterstitialAd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1344220021901214/6690741680");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                 super.onAdClosed();
            }
        });

        requestNewInterstitial();


    }

    public void startSettings(View v) {
        Intent i = new Intent(this, Game.class);
            switch(v.getId()) {
                case R.id.textOne:
                    i.putExtra("game", 1);
                    break;
                case R.id.textTwo:
                    i.putExtra("game", 2);
                    break;
                default:
                    throw new RuntimeException("Unknow button ID");

            }
            i.putExtra("player",1);
            i.putExtra("score1", 0);
            i.putExtra("score2", 0);
            startActivity(i);
    }

    public void pgWeb(View view) {
            Uri uri;
            PackageManager pm = this.getPackageManager();
            try {
                pm.getPackageInfo("com.facebook.katana", 0);
                // http://stackoverflow.com/a/24547437/1048340
                uri = Uri.parse("fb://facewebmodal/f?href=" + "https://www.facebook.com/TecProSoftware");
            } catch (PackageManager.NameNotFoundException e) {
                uri = Uri.parse("https://www.facebook.com/TecProSoftware");
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
             //   .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    protected void onResume() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        super.onResume();
    }

}

