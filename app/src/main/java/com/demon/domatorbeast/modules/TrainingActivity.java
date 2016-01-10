package com.demon.domatorbeast.modules;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.demon.domatorbeast.R;
import com.demon.domatorbeast.data.Exercise;
import com.demon.domatorbeast.data.Training;
import com.demon.domatorbeast.useful.ProgressBarDialog;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.Realm;

/**
 * Created by Piotr on 2015-11-30.
 */
@EActivity(R.layout.training)
public class TrainingActivity extends AppCompatActivity {

    private String baseUri = "android.resource://com.demon.domatorbeast/raw/";
    int[] trainingArray;
    int seriesCounter = 1;
    int exCounter = 1;
    @ViewById
    TextView textExName;
    @ViewById
    TextView textSeries;
    @ViewById
    TextView textRepet;
    @ViewById
    Toolbar app_bar;
    @ViewById
    VideoView videoViewTraining;

    CallbackManager mCalbackManager;
    ShareDialog mShareDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCalbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Click(R.id.btnPause)
    void btnPauseClicked(){
        //TODO Przejscie do nastepnego cwiczenia


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_enter,R.anim.slide_exit);
    }

    @Click(R.id.btnNext)
    void btnNextClicked(){
        //TODO Pauza
        if(exCounter < trainingArray.length){
        if(seriesCounter <3) {
            ProgressBarDialog progressBarDialog = new ProgressBarDialog(TrainingActivity.this, 4,null);
            progressBarDialog.setCancelable(false);
            progressBarDialog.show();
            textSeries.setText(String.valueOf(++seriesCounter));
        } else{
            ProgressBarDialog progressBarDialog = new ProgressBarDialog(TrainingActivity.this, 10, null);
            progressBarDialog.setCancelable(false);
            progressBarDialog.show();
            readFromRealm(trainingArray[exCounter]);
            textSeries.setText("1");
            exCounter = exCounter+1;
            seriesCounter = 1;
        }}else{
            if (ShareDialog.canShow(ShareLinkContent.class)) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentTitle("Uff, kolejny ciężki trening")
                        .setContentDescription(
                                "Właśnie udało mi się skończyć kolejny zestaw ćwiczeń, a Ty co dziś robiłeś?")
                        .build();
                mShareDialog = new ShareDialog(this);
                mShareDialog.show(linkContent);
            }
        }

    }

    @AfterViews
    void init(){
        //Realm
        setSupportActionBar(app_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        trainingArray = Training.set1;
        readFromRealm(trainingArray[0]);
        textSeries.setText(String.valueOf(seriesCounter));
        FacebookSdk.sdkInitialize(getApplicationContext());
        mCalbackManager = CallbackManager.Factory.create();

    }

    void readFromRealm(int id){
        Realm mRealm = Realm.getInstance(getApplicationContext());
        Exercise mExercise = mRealm.where(Exercise.class).equalTo("id",id).findFirst();
        if(mExercise==null){
            Toast.makeText(this,"No to jest null",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"No to nie był null",Toast.LENGTH_SHORT).show();
            textExName.setText(mExercise.getName());
            Uri uri = Uri.parse(baseUri+"togifa");
            videoViewTraining.setVideoURI(uri);
            videoViewTraining.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
            videoViewTraining.start();
            textRepet.setText(String.valueOf(mExercise.getSeries()));
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    void makeDialogBoxTimer(){

    }


}
