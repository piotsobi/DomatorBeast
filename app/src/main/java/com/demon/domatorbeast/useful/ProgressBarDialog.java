package com.demon.domatorbeast.useful;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.demon.domatorbeast.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.EView;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Piotr on 2016-01-05.
 */

public class ProgressBarDialog extends Dialog {

    public Context mContext;
    public long timerInMillis = 1000 * 5;
    //@ViewById
    TextView textDialogTimer;
    //@ViewById
    ProgressBar progresBarTimer;


    public ProgressBarDialog(Context context) {
        super(context);

        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialogbox_timer);
        progresBarTimer = (ProgressBar) findViewById(R.id.progresBarTimer);
        textDialogTimer = (TextView) findViewById(R.id.textDialogTimer);
        init();
    }

    //@AfterViews
    void init(){
        progresBarTimer.setMax((int)timerInMillis);
        CountDownTimer mCountDown = new CountDownTimer(timerInMillis,50) {
            @Override
            public void onTick(long millisUntilFinished) {
                //long seconds = millisUntilFinished/1000;

                //int setBar = timerInMillis - ((int)(seconds)/60*100);
                progresBarTimer.setProgress((int) (timerInMillis - millisUntilFinished));
            }

            @Override
            public void onFinish() {
                progresBarTimer.setProgress(progresBarTimer.getMax());
                dismiss();
            }
        }.start();
    }
}
