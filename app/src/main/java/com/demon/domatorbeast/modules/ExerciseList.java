package com.demon.domatorbeast.modules;

import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.demon.domatorbeast.R;
import com.demon.domatorbeast.useful.ExArrayAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Piotr on 2016-01-06.
 */
@EActivity(R.layout.exercise_list)
public class ExerciseList extends AppCompatActivity {

    static final String[] NAMES = new String[]{"ABBA","KLATA","PLECY","BARKI","tested","testing"};

    @ViewById
    ListView listView;
    @ViewById
    Toolbar app_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View.inflate(this,findViewById(R.id.app_bar));
        //setContentView(R.layout.exercise_list);
        //ListView mListView = (ListView) findViewById(R.id.list_view);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

    }
    @AfterViews
    void init(){
        listView.setAdapter(new ExArrayAdapter(this, NAMES));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent = new Intent(getApplicationContext(), ExerciseDescriptionActivity_.class);
                startActivity(mIntent);
                Toast.makeText(ExerciseList.this, NAMES[position], Toast.LENGTH_SHORT).show();
            }
        });
        setSupportActionBar(app_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_enter,R.anim.slide_exit);
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
    /*@Override
    public void onClick(View v) {
        TextView mTextView = (TextView) v.findViewById(R.id.textNoShowed);
        mTextView.setVisibility(View.VISIBLE);
    }*/
}
