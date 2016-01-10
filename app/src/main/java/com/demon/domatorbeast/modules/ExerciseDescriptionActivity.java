package com.demon.domatorbeast.modules;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.demon.domatorbeast.R;
import com.demon.domatorbeast.useful.ZoomOutPageTransformer;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Piotr on 2016-01-08.
 */
@EActivity(R.layout.activity_ex_desc)
public class ExerciseDescriptionActivity extends AppCompatActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;
    private static int position;
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    @ViewById
    Toolbar app_bar;
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_screen_slide);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        // Instantiate a ViewPager and a PagerAdapter.

    }

    @AfterViews
    void init(){
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        CirclePageIndicator titlePageIndicator = (CirclePageIndicator) findViewById(R.id.titles);
        titlePageIndicator.setViewPager(mPager);
        setSupportActionBar(app_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //setActionBar(app_bar);
        Intent mIntent = getIntent();
        position = mIntent.getIntExtra("position",0);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
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
    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ExerciseDescriptionFragment.newInstance(position + ExerciseDescriptionActivity.position*10);
        }


        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }


}
