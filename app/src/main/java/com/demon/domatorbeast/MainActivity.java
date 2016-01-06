package com.demon.domatorbeast;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.demon.domatorbeast.data.Exercise;
import com.demon.domatorbeast.logic.ExData;
import com.demon.domatorbeast.logic.MongoReader;
import com.demon.domatorbeast.modules.ExcerciseActivity_;
import com.demon.domatorbeast.modules.ExerciseList_;
import com.demon.domatorbeast.modules.TrainingActivity;
import com.demon.domatorbeast.modules.TrainingActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    Intent mIntent;
    @ViewById
    Toolbar app_bar;

    @Click(R.id.btnTr)
    void btnTrClicked(){
        mIntent = new Intent(this, TrainingActivity_.class);
        startActivity(mIntent);
    }

    @Click(R.id.btnEx)
    void btnExClicked(){
        mIntent = new Intent(this, ExerciseList_.class);
        startActivity(mIntent);
        Realm mRealm = Realm.getInstance(getApplicationContext());
        Exercise mExercise = mRealm.where(Exercise.class).equalTo("id",2).findFirst();
        Toast.makeText(this,"KURWA",Toast.LENGTH_SHORT).show();
        mRealm.close();
    }

    @Click(R.id.btnPorady)
    void btnPoradyClicked(){
        //TODO Porady
        new MongoReader(getApplicationContext()).execute();
    }

    @Click(R.id.btnExit)
    void btnExitClicked(){
        //new MongoConnector().execute("");
        ExData exData = new ExData();
        exData.opis = "SIEMA";
        exData.level = "CO TAM GIF";
        try {
            exData.steps = toByteArrayString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //exData.steps = "TRORLORRLRORLORLRORRORLRROOR";
        //MongoReader mSaver = new MongoReader();
        //mSaver.execute(exData);
        //MongoConnector mConnect = new MongoConnector();
        //mConnect.sendData();
        Toast.makeText(this,"BACK PRESS PLS", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @AfterViews
    void init(){
        setSupportActionBar(app_bar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
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
        Log.e("STRIB BYTE", result);

        return result;
    }
}
