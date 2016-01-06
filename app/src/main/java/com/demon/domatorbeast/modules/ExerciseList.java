package com.demon.domatorbeast.modules;

import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.demon.domatorbeast.R;
import com.demon.domatorbeast.useful.ExArrayAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Piotr on 2016-01-06.
 */
@EActivity(R.layout.exercise_list)
public class ExerciseList extends Activity{

    static final String[] NAMES = new String[]{"ABBA","KLATA","PLECY","BARKI"};

    @ViewById
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View.inflate(this,findViewById(R.id.app_bar));
        //setContentView(R.layout.exercise_list);
        //ListView mListView = (ListView) findViewById(R.id.list_view);


    }
    @AfterViews
    void init(){
        listView.setAdapter(new ExArrayAdapter(this, NAMES));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView mTextView = (TextView) view.findViewById(R.id.textNoShowed);
                mTextView.setVisibility(View.VISIBLE);
            }
        });
    }


    /*@Override
    public void onClick(View v) {
        TextView mTextView = (TextView) v.findViewById(R.id.textNoShowed);
        mTextView.setVisibility(View.VISIBLE);
    }*/
}
