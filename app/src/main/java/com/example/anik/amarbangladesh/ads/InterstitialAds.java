package com.example.anik.amarbangladesh.ads;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by Anik Mazumder on 5/22/2018.
 */

public class InterstitialAds {

    private InterstitialAd interstitialAd;

    public void displayInterstialAds() {

        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        }
    }


    public void adLoad(Context c, String id) {

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("1142E31929193760F01F8F160D1313DA")
                .build();

        interstitialAd = new InterstitialAd(c);
        interstitialAd.setAdUnitId(id);

        interstitialAd.loadAd(adRequest);

        interstitialAd.setAdListener(new AdListener() {

                                         @Override
                                         public void onAdLoaded() {
                                             displayInterstialAds();
                                         }
                                     }


        );
    }


}
