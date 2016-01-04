package com.demon.domatorbeast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.demon.domatorbeast.modules.ExcerciseActivity_;
import com.demon.domatorbeast.modules.TrainingActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    Intent mIntent;
    @ViewById
    Toolbar app_bar;

    @Click(R.id.btnTr)
    void btnTrClicked(){
        mIntent = new Intent(this, ExcerciseActivity_.class);
        startActivity(mIntent);
    }

    @Click(R.id.btnEx)
    void btnExClicked(){
        mIntent = new Intent(this, TrainingActivity_.class);
        startActivity(mIntent);
    }

    @Click(R.id.btnPorady)
    void btnPoradyClicked(){
        //TODO Porady
    }

    @Click(R.id.btnExit)
    void btnExitClicked(){
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
}
