package com.atomEdition.mexicanAdopt;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by FruityDevil on 23.12.14.
 */
public class ButtonListener extends ContextWrapper {

    private Activity activity;

    public ButtonListener(Context baseContext, Activity activity){
        super(baseContext);
        this.activity = activity;
    }

    public void setImageButtonsListeners(int id){
        final ImageButton imageButton = (ImageButton)this.activity.findViewById(id);
        imageButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    imageButton.setBackgroundResource(R.drawable.button_on);
                }
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    imageButton.setBackgroundResource(R.drawable.button_off);
                }
                return false;
            }
        });
    }

}
