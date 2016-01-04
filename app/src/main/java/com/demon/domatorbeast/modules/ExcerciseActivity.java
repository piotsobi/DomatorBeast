package com.demon.domatorbeast.modules;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.demon.domatorbeast.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Piotr on 2015-11-30.
 */
@EActivity(R.layout.excercise)
public class ExcerciseActivity extends Activity{

    @ViewById
    ImageView mImageView;
    @ViewById
    TextView mExText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void init(){
        //TODO Inicjalizacja z bazy obrazka/gifu i opisu; mongoDB

    }
}
