package com.atomEdition.mexicanAdopt;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by FruityDevil on 18.01.2015.
 */
public class GameUtils extends ContextWrapper{

    private static Activity activity;
    private static int pittDelay = 1;

    public GameUtils(Context base) {
        super(base);
    }

    public static void setActivity(Activity inputActivity) {
        activity = inputActivity;
    }

    public void checkPitt(){
        ImageView imageView = (ImageView)activity.findViewById(R.id.imagePitt);
        if (Utils.chanceChecker(Utils.CHANCE_PITT)) {
            imageView.setVisibility(View.VISIBLE);
            pittDelay = 1;
        }
        else {
            imageView.setVisibility(View.INVISIBLE);
        }
    }

    public void hidePitt(){
        if (pittDelay > 0)
            pittDelay--;
        else {
            ImageView imageView = (ImageView) activity.findViewById(R.id.imagePitt);
            imageView.setVisibility(View.INVISIBLE);
        }
    }
}
