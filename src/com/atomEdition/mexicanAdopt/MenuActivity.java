package com.atomEdition.mexicanAdopt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.atomEdition.mexicanAdopt.ad.AdService;
import com.atomEdition.mexicanAdopt.promotion.FollowActivity;

public class MenuActivity extends Activity {

    private static long back_pressed;
    private AdService adService = AdService.getInstance();

    private void initialization(){
        getHighScore();
        setButtonListeners();
        adService.showBanner(this);
    }

    /**
     * Gets saved best score from android's preferences. Calling from onCreate method.
     */
    private void getHighScore(){
        SharedPreferences settings = getSharedPreferences(Utils.PREFERENCES, Context.MODE_PRIVATE);
        TextView textView = (TextView)findViewById(R.id.textViewHighScore);
        int high_score = settings.getInt(Utils.PREFERENCES_HIGH_SCORE, 0);
        textView.setText("Best: " + high_score);
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initialization();
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis())
            super.onBackPressed();
        else
            Toast.makeText(getBaseContext(), "Press again to exit",
                    Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }

    public void onClickStart(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickOther(View view) {
        Intent intent = new Intent(this, FollowActivity.class);
        startActivity(intent);
        finish();
    }

    private void setButtonListeners(){
        new ButtonListener(this, this).setImageButtonsListeners(R.id.button_start);
        new ButtonListener(this, this).setImageButtonsListeners(R.id.button_other);
    }

}
