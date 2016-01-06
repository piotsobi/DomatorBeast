package com.demon.domatorbeast.modules;

import android.app.Activity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.demon.domatorbeast.R;
import com.demon.domatorbeast.data.Exercise;
import com.demon.domatorbeast.useful.ProgressBarDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.Realm;

/**
 * Created by Piotr on 2015-11-30.
 */
@EActivity(R.layout.training)
public class TrainingActivity extends Activity{


    int[] trainingArray = {1,2,3};
    int seriesCounter = 1;
    @ViewById
    TextView textExName;
    @ViewById
    TextView textSeries;
    @ViewById
    TextView textRepet;



    @Click(R.id.btnPause)
    void btnPauseClicked(){
        //TODO Przejscie do nastepnego cwiczenia


    }

    @Click(R.id.btnNext)
    void btnNextClicked(){
        //TODO Pauza
        if(seriesCounter <3) {
            ProgressBarDialog progressBarDialog = new ProgressBarDialog(TrainingActivity.this);
            progressBarDialog.show();
            textSeries.setText(String.valueOf(++seriesCounter));
        } else{
            readFromRealm(trainingArray[2]);
        }
    }

    @AfterViews
    void init(){
        //Realm

        readFromRealm(trainingArray[0]);
        textSeries.setText(String.valueOf(seriesCounter));
    }

    void readFromRealm(int id){
        Realm mRealm = Realm.getInstance(getApplicationContext());
        Exercise mExercise = mRealm.where(Exercise.class).equalTo("id",id).findFirst();
        if(mExercise==null){
            Toast.makeText(this,"No to jest null",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"No to nie był null",Toast.LENGTH_SHORT).show();
            textExName.setText(mExercise.getName());
            textRepet.setText(String.valueOf(mExercise.getSteps()));
        }
    }

    void makeDialogBoxTimer(){

    }


}
