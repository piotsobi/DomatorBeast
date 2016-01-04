package com.demon.domatorbeast.modules;

import android.app.Activity;

import com.demon.domatorbeast.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

/**
 * Created by Piotr on 2015-11-30.
 */
@EActivity(R.layout.training)
public class TrainingActivity extends Activity{

    @Click(R.id.btnPause)
    void btnPauseClicked(){
        //TODO Przejscie do nastepnego cwiczenia
    }

    @Click(R.id.btnNext)
    void btnNextClicked(){
        //TODO Pauza
    }


}
