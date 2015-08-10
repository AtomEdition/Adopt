package com.atomEdition.mexicanAdopt.ad;

/**
 * Created by FruityDevil on 23.12.14.
 */
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class FullScreenBanner extends ContextWrapper {

    private Activity activity;

    public FullScreenBanner(Context baseContext, Activity activity){
        super(baseContext);
        this.activity = activity;
    }


}
