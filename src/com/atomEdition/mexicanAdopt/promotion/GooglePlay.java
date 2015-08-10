package com.atomEdition.mexicanAdopt.promotion;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import com.atomEdition.mexicanAdopt.R;


/**
 * Created by FruityDevil on 23.12.14.
 */
public class GooglePlay extends ContextWrapper {

    private String url = "market://details?id=com.atomEdition.mexicanAdopt";

    public GooglePlay(Context baseContext){
        super(baseContext);
    }

    public void rate(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        try{
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(getBaseContext(), R.string.connection_failure,
                    Toast.LENGTH_SHORT).show();
        }
    }
}
