package com.atomEdition.mexicanAdopt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.atomEdition.mexicanAdopt.ad.AdService;

import java.util.Random;

public class GameActivity extends Activity {

    private static long back_pressed;
    private static CountDownTimer countDownTimer;
    private AdService adService = AdService.getInstance();

    private void initialization(){
        GameUtils.setActivity(this);
        setValues();
        setScreenSize();
        doMexicans();
        setButtonListeners();
        getNewTimer(Utils.TIMER_DECREMENT_INTERVAL);
        countDownTimer.start();
    }

    private void setValues(){
        Utils.DELAY_TOTAL = 6;
        Utils.DELAY_CURRENT = 0;
        Utils.POSITION_CURRENT = Utils.POSITION_DEFAULT;
        Utils.SCORE = 0;
        Utils.LIVES_CURRENT = Utils.LIVES_MAXIMUM;
        clearJoly();
        moveJoly();
        doJoly();
        clearMexicans();
        Utils.TIMER_DECREMENT_INTERVAL = 6;
    }

    private void setScreenSize(){
        View view = findViewById(R.id.layoutGame);
        setLayoutSize(view);
        for(int i = 0; i < 4; i++){
            view = findViewById(getJolyByValue(i));
            setElementSize(view, Utils.JOLY_WIDTH, Utils.JOLY_HEIGHT, getJolyMarginTop(i), 0, getJolyMarginLeft(i), 0);
        }
        for(int i=0; i<4; i++)
            for(int j=0; j<7; j++){
                view = findViewById(getMexicanByValue(i, j));
                if(j == 6)
                    setElementSize(view, Utils.MEXICAN_SIZE * 2, Utils.MEXICAN_SIZE * 2, getMexicanMarginTop(i, j), 0,
                            getMexicanMarginLeft(i, j), 0);
                else setElementSize(view, Utils.MEXICAN_SIZE, Utils.MEXICAN_SIZE, getMexicanMarginTop(i, j), 0,
                        getMexicanMarginLeft(i, j), 0);
            }
        view = findViewById(R.id.imagePitt);
        setElementSize(view, Utils.PITT_WIDTH, Utils.PITT_HEIGHT, Utils.LAYOUT_GAME_HEIGHT - (Utils.PITT_MARGIN_BOTTOM +
                        Utils.PITT_HEIGHT), 0, Utils.LAYOUT_GAME_WIDTH - (Utils.PITT_MARGIN_SIDE + Utils.PITT_WIDTH), 0);
    }

    private int getJolyMarginTop(int i){
        switch (i){
            case 0:
                return Utils.JOLY_MARGIN_TOP_BASE + Utils.JOLY_MARGIN_TOP_ADDITION;
            case 1:
                return Utils.JOLY_MARGIN_TOP_BASE;
            case 2:
                return Utils.JOLY_MARGIN_TOP_BASE;
            case 3:
                return Utils.JOLY_MARGIN_TOP_BASE + Utils.JOLY_MARGIN_TOP_ADDITION;
        }
        return 0;
    }

    private int getJolyMarginLeft(int i){
        switch (i){
            case 0:
                return Utils.JOLY_MARGIN_LEFT_BASE;
            case 1:
                return Utils.JOLY_MARGIN_LEFT_BASE + Utils.JOLY_MARGIN_LEFT_ADDITION;
            case 2:
                return Utils.LAYOUT_GAME_WIDTH - (Utils.JOLY_MARGIN_LEFT_BASE + Utils.JOLY_MARGIN_LEFT_ADDITION + Utils.JOLY_WIDTH);
            case 3:
                return Utils.LAYOUT_GAME_WIDTH - (Utils.JOLY_MARGIN_LEFT_BASE + Utils.JOLY_WIDTH);
        }
        return 0;
    }

    private int getMexicanMarginTop(int i, int j){
        int margin = 1;
        switch (i){
            case 0:
                margin *= Utils.MEXICAN_MARGIN_TOP_BASE + Utils.MEXICAN_MARGIN_TOP_ADDITION_LEVEL;
                break;
            case 1:
                margin *= Utils.MEXICAN_MARGIN_TOP_BASE;
                break;
            case 2:
                margin *= Utils.MEXICAN_MARGIN_TOP_BASE;
                break;
            case 3:
                margin *= Utils.MEXICAN_MARGIN_TOP_BASE + Utils.MEXICAN_MARGIN_TOP_ADDITION_LEVEL;
                break;
        }
        if(j == 6)
            return Utils.MEXICAN_MARGIN_TOP_LAST;
        return margin + (j * Utils.MEXICAN_MARGIN_TOP_ADDITION_STEP);
    }

    private int getMexicanMarginLeft(int i, int j){
        int margin = 1;
        int coefficient = 1;
        switch (i){
            case 0:
                margin *= Utils.MEXICAN_MARGIN_LEFT_BASE;
                break;
            case 1:
                margin *= Utils.MEXICAN_MARGIN_LEFT_BASE + Utils.MEXICAN_MARGIN_LEFT_ADDITION_LEVEL;
                break;
            case 2:
                margin *= Utils.LAYOUT_GAME_WIDTH - (Utils.MEXICAN_MARGIN_LEFT_BASE
                        + Utils.MEXICAN_MARGIN_LEFT_ADDITION_LEVEL + Utils.MEXICAN_SIZE);
                coefficient = -1;
                break;
            case 3:
                margin *= Utils.LAYOUT_GAME_WIDTH - (Utils.MEXICAN_MARGIN_LEFT_BASE + Utils.MEXICAN_SIZE);
                coefficient = -1;
                break;
        }
        if(j == 6)
            return margin + coefficient * (5 * Utils.MEXICAN_MARGIN_LEFT_ADDITION_STEP +
                    Utils.MEXICAN_MARGIN_LEFT_ADDITION_LAST);
        return margin + coefficient * (j * Utils.MEXICAN_MARGIN_LEFT_ADDITION_STEP);
    }

    private Float getCoefficientX(){
        Display display = getWindowManager().getDefaultDisplay();
        return (float)display.getWidth() / convertToPx(Utils.LAYOUT_GAME_WIDTH + Utils.LAYOUT_GAME_SIDES_BORDERS);
    }

    private void setLayoutSize(View view){
        int width = (int)(convertToPx(Utils.LAYOUT_GAME_WIDTH) * getCoefficientX());
        int height = (int)(convertToPx(Utils.LAYOUT_GAME_HEIGHT) * getCoefficientX());
        android.widget.RelativeLayout.LayoutParams layoutParams =
                new android.widget.RelativeLayout.LayoutParams(width, height);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);;
        view.setLayoutParams(layoutParams);
    }

    private void setElementSize(View view, int width, int height, int topMargin, int bottomMargin, int leftMargin,
                                int rightMargin){
        width = convertToPx(width);
        height = convertToPx(height);
        topMargin = convertToPx(topMargin);
        bottomMargin = convertToPx(bottomMargin);
        leftMargin = convertToPx(leftMargin);
        rightMargin = convertToPx(rightMargin);
        width *= getCoefficientX();
        height *= getCoefficientX();
        topMargin *= getCoefficientX();
        bottomMargin *= getCoefficientX();
        leftMargin *= getCoefficientX();
        rightMargin *= getCoefficientX();
        android.widget.FrameLayout.LayoutParams layoutParams = new android.widget.FrameLayout.LayoutParams(width, height);
        layoutParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
        view.setLayoutParams(layoutParams);
    }

    public int convertToPx(Integer valueDp){
        return (int)(valueDp * getApplicationContext().getResources().getDisplayMetrics().density);
    }

    private void setButtonListeners(){
        new ButtonListener(this, this).setImageButtonsListeners(R.id.button_0);
        new ButtonListener(this, this).setImageButtonsListeners(R.id.button_1);
        new ButtonListener(this, this).setImageButtonsListeners(R.id.button_2);
        new ButtonListener(this, this).setImageButtonsListeners(R.id.button_3);
    }

    private void clearJoly(){
        for(int i : Utils.JOLY)
            i = 0;
    }

    private void clearMexicans(){
        for(int i=0; i<4; i++)
            for(int j=0; j<7; j++)
                Utils.MEXICANS[i][j] = 0;
    }

    private void getNewTimer(long interval){
        countDownTimer = new CountDownTimer(10000L, interval*100) {
            @Override
            public void onTick(long l) {
                checkPosition();
                checkLives();
                checkDifficulty();
                moveMexicans();
                doMexicans();
                setText();
                new GameUtils(getApplicationContext()).hidePitt();
            }

            @Override
            public void onFinish() {
                if(Utils.DELAY_TOTAL < 5)
                    if(Utils.TIMER_DECREMENT_INTERVAL > Utils.TIMER_INTERVAL_MINIMUM)
                        Utils.TIMER_DECREMENT_INTERVAL--;
                        countDownTimer.cancel();
                if(Utils.LIVES_CURRENT > 0) {
                    getNewTimer(Utils.TIMER_DECREMENT_INTERVAL);
                    countDownTimer.start();
                }
            }
        };
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            adService.loadInterstitial(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.game);
        initialization();

    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()){
            backToMenu();
        }
        else
            Toast.makeText(getBaseContext(), "Press again to finish the game",
                    Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }

    private void backToMenu(){
        countDownTimer.cancel();
        Utils.LIVES_CURRENT = 0;
        setHighScore();
        quit();
        adService.displayInterstitial();
    }

    private void quit(){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    private void setHighScore(){
        SharedPreferences settings = getSharedPreferences(Utils.PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        if(settings.getInt(Utils.PREFERENCES_HIGH_SCORE, 0) < Utils.SCORE){
            editor.putInt(Utils.PREFERENCES_HIGH_SCORE, Utils.SCORE);
            editor.commit();
        }
    }

    private Integer getJolyByValue(Integer value){
        return getResources().getIdentifier("joly_" + value, "id", getPackageName());
    }

    private Integer getMexicanByValue(Integer i, Integer j){
        return getResources().getIdentifier("mexican_" + i + "_" + j, "id", getPackageName());
    }

    private Integer getButtonById(Integer id){
        Integer value = 0;
        switch (id){
            case R.id.button_0:
                value = 0;
                break;
            case R.id.button_1:
                value = 1;
                break;
            case R.id.button_2:
                value = 2;
                break;
            case R.id.button_3:
                value = 3;
                break;
        }
        return value;
    }

    public void onButtonClick(View view) {
        Utils.POSITION_CURRENT = getButtonById(view.getId());
        moveJoly();
        doJoly();
    }

    private void moveMexicans(){
        for(int i = 0; i < 4; i++)
            for(int j = 5; j >= 0; j--){
                Utils.MEXICANS[i][j+1] = Utils.MEXICANS[i][j];
                Utils.MEXICANS[i][j] = 0;
            }
        Random random = new Random();
        if(Utils.DELAY_CURRENT > 0)
            Utils.DELAY_CURRENT--;
        else {
            Utils.MEXICANS[random.nextInt(4)][0] = 1;
            Utils.DELAY_CURRENT = Utils.DELAY_TOTAL;
        }
    }

    public void moveJoly(){
        for(int i=0; i<4; i++)
            Utils.JOLY[i] = 0;
        Utils.JOLY[Utils.POSITION_CURRENT] = 1;
    }

    public void doJoly(){
        for(int i=0; i<4; i++){
            ImageView imageView = (ImageView)findViewById(getJolyByValue(i));
            if(Utils.JOLY[i] == 0)
                imageView.setVisibility(View.INVISIBLE);
            else
                imageView.setVisibility(View.VISIBLE);
        }
    }

    public void doMexicans(){
        for(int i=0; i<4; i++)
            for(int j=0; j<7; j++){
                ImageView imageView = (ImageView)findViewById(getMexicanByValue(i, j));
                if(Utils.MEXICANS[i][j] == 1)
                    imageView.setVisibility(View.VISIBLE);
                else
                    imageView.setVisibility(View.INVISIBLE);
            }
    }

    public void checkPosition(){
        if(Utils.MEXICANS[Utils.POSITION_CURRENT][5] == 1){
            Utils.MEXICANS[Utils.POSITION_CURRENT][5] = 0;
            Utils.SCORE++;
            new GameUtils(getApplicationContext()).checkPitt();
        }
        for(int i=0; i<4; i++)
            if(Utils.MEXICANS[i][6] == 1)
                Utils.LIVES_CURRENT--;
    }

    public void checkLives(){
        if(Utils.LIVES_CURRENT < 1)
            backToMenu();
    }

    public void checkDifficulty(){
        if(Utils.SCORE < Utils.DELAY_MINIMUM * Utils.DELAY_INCREMENT_INTERVAL)
            Utils.DELAY_TOTAL = (6 * Utils.DELAY_INCREMENT_INTERVAL - Utils.SCORE) / Utils.DELAY_INCREMENT_INTERVAL;
    }

    private void setText(){
        TextView textView = (TextView)findViewById(R.id.textViewLives);
        textView.setText("Lives: " + Utils.LIVES_CURRENT);
        textView = (TextView)findViewById(R.id.textViewScore);
        textView.setText("Score: " + Utils.SCORE);
    }
}
