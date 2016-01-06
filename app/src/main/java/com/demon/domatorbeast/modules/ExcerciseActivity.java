package com.demon.domatorbeast.modules;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.demon.domatorbeast.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;

/**
 * Created by Piotr on 2015-11-30.
 */
@EActivity(R.layout.excercise)
public class ExcerciseActivity extends Activity implements MediaPlayer.OnCompletionListener, SurfaceHolder.Callback{

    private SurfaceHolder mSurfaceHolder;
    private MediaPlayer mMediaPlayer;
    @ViewById
    TextView mExText;

    @ViewById
    VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @AfterViews
    void init(){
        mMediaPlayer = new MediaPlayer();
        mSurfaceHolder = mVideoView.getHolder();
        mSurfaceHolder.addCallback(this);
        AssetManager mManager = getAssets();
        AssetFileDescriptor mDesc;
        try {
            mDesc = mManager.openFd("togifa.mp4");
            mMediaPlayer.setDataSource(mDesc.getFileDescriptor());
            mDesc.close();
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.start();

        //mVideoView.setVideoURI(Uri.parse("android.resource//"+getPackageName()+"/"+R.raw.togifa));
        Log.e("CONTENT", "android.resource//" + getPackageName() + "/" + R.raw.togifa);
        mVideoView.setOnCompletionListener(this);
        //mVideoView.start();
        //TODO Inicjalizacja z bazy obrazka/gifu i opisu; mongoDB

    }

    @Click(R.id.mVideoView)
    void videoTouch(){

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        //mVideoView
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(mMediaPlayer == null){
            mMediaPlayer = new MediaPlayer();
        }
        mMediaPlayer.setDisplay(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
