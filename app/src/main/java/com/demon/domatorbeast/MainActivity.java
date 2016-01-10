package com.demon.domatorbeast;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.widget.Toast;

import com.demon.domatorbeast.data.Exercise;
import com.demon.domatorbeast.logic.MongoReader;
import com.demon.domatorbeast.modules.ExerciseList_;
import com.demon.domatorbeast.modules.TrainingActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;
import io.realm.RealmConfiguration;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    Intent mIntent;
    @ViewById
    Toolbar app_bar;
    int counter = 1;

    SharedPreferences mPref;

    @Click(R.id.btnTr)
    void btnTrClicked(){
        mIntent = new Intent(this, TrainingActivity_.class);
        startActivity(mIntent);
    }

    @Click(R.id.btnEx)
    void btnExClicked(){
        mIntent = new Intent(this, ExerciseList_.class);
        startActivity(mIntent);
        Realm mRealm = Realm.getDefaultInstance();
        Exercise mExercise = mRealm.where(Exercise.class).equalTo("id",2).findFirst();
        Toast.makeText(this,"KURWA",Toast.LENGTH_SHORT).show();
        mRealm.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mPref.getBoolean("firstrun",true)){
            //do the stuff do testów zakomentowane
            //new MongoReader(getApplicationContext()).execute();
            mPref.edit().putBoolean("firstrun",false).apply();
        }
    }

    @Click(R.id.btnPorady)
    void btnPoradyClicked(){
        //TODO Porady
        new MongoReader(getApplicationContext()).execute();
    }

    @Click(R.id.btnExit)
    void btnExitClicked(){

        Toast.makeText(this,"BACK PRESS PLS", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        mPref = getSharedPreferences("com.demon.domatorbeast",MODE_PRIVATE);
    }

    @AfterViews
    void init(){
        setSupportActionBar(app_bar);
        //getSupportActionBar().setIcon(R.drawable.ic_launcher);
        RealmConfiguration mRealmConfig = new RealmConfiguration.Builder(getApplicationContext()).build();
        Realm.setDefaultConfiguration(mRealmConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_enter,R.anim.slide_exit);
    }

    public String toByteArrayString() throws IOException {

        AssetManager mAsset = getAssets();
        String[] files = mAsset.list("");
        InputStream input;
        input = mAsset.open("togifa.gif");
        int size = input.available();
        byte[] buffer = new byte[size];
        input.read(buffer);
        input.close();
        String result = Base64.encodeToString(buffer,Base64.URL_SAFE);
        //Bitmap bm = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.togifa);
        //File mFile = new File()
        //ByteArrayOutputStream stream = new ByteArrayOutputStream();
        //bm.compress(Bitmap.CompressFormat.PNG,100,stream);
        //byte[] bArr = stream.toByteArray();
        //bm.recycle();
        //String result = Base64.encodeToString(bArr,Base64.URL_SAFE);
        // bm.toString();
        //Log.e("STRIB BYTE", result);

        return result;
    }
}
