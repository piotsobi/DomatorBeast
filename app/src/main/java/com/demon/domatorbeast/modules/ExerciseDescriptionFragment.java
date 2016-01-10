package com.demon.domatorbeast.modules;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.demon.domatorbeast.R;
import com.demon.domatorbeast.data.Exercise;

import org.androidannotations.annotations.EFragment;
import org.w3c.dom.Text;

import java.net.URL;

import io.realm.Realm;

/**
 * Created by Piotr on 2016-01-08.
 */
@EFragment(R.layout.fragment_ex_desc)
public class ExerciseDescriptionFragment extends Fragment {


    private String baseUri = "android.resource://com.demon.domatorbeast/raw/";
    private int page;
    //private Context mContext;

    public static ExerciseDescriptionFragment newInstance(int page){
        ExerciseDescriptionFragment mFragment = new ExerciseDescriptionFragment();
        Bundle mBundle = new Bundle();
        mBundle.putInt("page",page);

        mFragment.setArguments(mBundle);

        return mFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page",0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup mViewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_ex_desc,container,false);
        Exercise mExercise = readFromRealm(page);
        TextView mTextView = (TextView) mViewGroup.findViewById(R.id.textViewDesc);
        TextView mTextName = (TextView) mViewGroup.findViewById(R.id.textName);
        VideoView mVideView = (VideoView) mViewGroup.findViewById(R.id.videoView);
        Uri URI = Uri.parse(baseUri+"togifa");
        mVideView.setVideoURI(URI);
        mVideView.start();
        mVideView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.setLooping(true);
                mp.start();
            }
        });

        mTextView.setText(mExercise.getDescription());
        mTextName.setText(mExercise.getName());
        return mViewGroup;
    }

    private Exercise readFromRealm(int id){
        Realm mRealm = Realm.getInstance(getActivity());
        Exercise mExercise = mRealm.where(Exercise.class).equalTo("id",id+11).findFirst();

        return mExercise;
    }



}
