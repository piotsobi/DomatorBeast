package com.demon.domatorbeast.modules;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demon.domatorbeast.R;

import org.androidannotations.annotations.EFragment;

/**
 * Created by Piotr on 2016-01-08.
 */
@EFragment(R.layout.fragment_ex_desc)
public class ExerciseDescriptionFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup mViewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_ex_desc,container,false);

        return mViewGroup;
    }



}
