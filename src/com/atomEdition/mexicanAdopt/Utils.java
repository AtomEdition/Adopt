package com.atomEdition.mexicanAdopt;

import java.util.Random;

/**
 * Created by FruityDevil on 30.12.2014.
 */
public abstract class Utils {

    public static Integer[][] MEXICANS = {{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0}};
    public static Integer[] JOLY = {0,0,0,0};
    public static Integer POSITION_CURRENT = 0, POSITION_DEFAULT = 1, SCORE = 0;
    public static Integer LIVES_CURRENT = 0, LIVES_MAXIMUM = 3;

    public static Integer DELAY_TOTAL = 6, DELAY_CURRENT = 0;
    public static final Integer DELAY_INCREMENT_INTERVAL = 5;
    public static final Integer DELAY_MINIMUM = 5; //MAXIMUM - DELAY_MINIMUM
    public static Integer TIMER_DECREMENT_INTERVAL = 6;
    public static final Integer TIMER_INTERVAL_MINIMUM = 2;

    public static final String PREFERENCES_HIGH_SCORE = "high_score";
    public static final String PREFERENCES = "settings";

    public static final int LAYOUT_GAME_HEIGHT = 340;
    public static final int LAYOUT_GAME_TOP_BORDERS = 140;
    public static final int LAYOUT_GAME_WIDTH = 400;
    public static final int LAYOUT_GAME_SIDES_BORDERS = 400;
    public static final int JOLY_HEIGHT = 100;
    public static final int JOLY_WIDTH = 60;
    public static final int MEXICAN_SIZE = 35;
    public static final int PITT_HEIGHT = 100;
    public static final int PITT_WIDTH = 100;

    public static final int JOLY_MARGIN_TOP_BASE = 80;
    public static final int JOLY_MARGIN_TOP_ADDITION = 65;
    public static final int JOLY_MARGIN_LEFT_BASE = 110;
    public static final int JOLY_MARGIN_LEFT_ADDITION = 35;
    public static final int MEXICAN_MARGIN_TOP_BASE = 20;
    public static final int MEXICAN_MARGIN_TOP_ADDITION_LEVEL = 90;
    public static final int MEXICAN_MARGIN_TOP_ADDITION_STEP = 10;
    public static final int MEXICAN_MARGIN_TOP_LAST = 250;
    public static final int MEXICAN_MARGIN_LEFT_BASE = 10;
    public static final int MEXICAN_MARGIN_LEFT_ADDITION_LEVEL = 30;
    public static final int MEXICAN_MARGIN_LEFT_ADDITION_STEP = 20;
    public static final int MEXICAN_MARGIN_LEFT_ADDITION_LAST = 15;
    public static final int PITT_MARGIN_SIDE = 30;
    public static final int PITT_MARGIN_BOTTOM = 10;

    public static final int CHANCE_PITT = 20;

    public static boolean chanceChecker(int chance){
        Random random = new Random();
        int result = random.nextInt(101);
        return result <= chance;
    }
}
