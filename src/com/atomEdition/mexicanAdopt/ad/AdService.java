package com.atomEdition.mexicanAdopt.ad;

/**
 * Created by FruityDevil on 23.12.14.
 */
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import com.atomEdition.mexicanAdopt.R;
import com.google.android.gms.ads.*;

public class AdService {

    private static AdService instance;
    private InterstitialAd interstitialAd;

    private AdService(){
    }

    public static AdService getInstance(){
        if (instance == null) {
            instance = new AdService();
        }
        return instance;
    }

    public void showBanner(Activity activity){

        AdView adView = (AdView) activity.findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());
    }

    public void loadInterstitial(Context context){
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId("ca-app-pub-9550981282535152/2752998620");
        interstitialAd.loadAd(new AdRequest.Builder().build());
    }

    public void displayInterstitial(){
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        }
    }
}
