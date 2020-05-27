package com.example.admin.bibleapp.Activity;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.Model.Prayer;
import com.example.admin.bibleapp.NewFragment;
import com.example.admin.bibleapp.R;

import java.util.ArrayList;
import java.util.List;

public class PrayerActivity extends FragmentActivity {
    private static int NUM_PAGES;
    private ViewPager mPager;
    DBhelper dbHelper;
    String category;
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer);
        dbHelper=new DBhelper(this);
        NUM_PAGES=dbHelper.getRowCount();
        Intent intent = getIntent();
        final int position = intent.getIntExtra("fragno",0);
       category=intent.getStringExtra("category");
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(position);
    }

    @Override
    public void onBackPressed() {
//        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
//        } else {
//            // Otherwise, select the previous step.
//            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
//        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        List<Prayer> lists=dbHelper.getPrayer(category);
        ArrayList<Integer> title_id=dbHelper.getTitleId(category);
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }


        public int getItemPosition(Object object) {

                int requestedCatId = ((Prayer) object).getId();
               // lists=dbHelper.getPrayer();
                for (int i = 1; i < this.lists.size(); i++) {
                    if (((Prayer) this.lists.get(i)).getId() == requestedCatId) {
                        return i + 1;
                    }
            }
            return -1;
        }

        public Fragment getItem(int position) {
//            if (position == 0) {
//                return new Fragment();
//            }

            Prayer item = (Prayer) this.lists.get(position);
            Fragment fragment = new NewFragment();
            Bundle args = new Bundle();
            position=title_id.get(position);
            args.putInt("category_id", position);
            fragment.setArguments(args);
            return fragment;
        }

        public int getCount() {
            return this.lists.size();
        }
    }


}
