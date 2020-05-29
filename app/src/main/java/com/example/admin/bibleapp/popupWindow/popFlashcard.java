package com.example.admin.bibleapp.popupWindow;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.Activity.Fragment_flash_card;
import com.example.admin.bibleapp.R;

import java.util.List;

/**
 * Created by Admin on 5/25/2018.
 */

public class popFlashcard extends FragmentActivity {
    private ViewPager mPager;
    DBhelper db;
    SQLiteDatabase dbh;
    ImageButton rightnav,leftnav;
    int i,flag=0;
    TextView tvFlashEmpty;
    private PagerAdapter mPagerAdapter;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_flash_card);
        db=new DBhelper(getApplicationContext(),getFilesDir().getAbsolutePath());
        dbh=db.getReadableDatabase();
        rightnav=(ImageButton)findViewById(R.id.fright_nav);
        leftnav=(ImageButton)findViewById(R.id.fleft_nav);
        back =(Button)findViewById(R.id.tv_back);
        mPager = (ViewPager) findViewById(R.id.viewPager);
        tvFlashEmpty = (TextView) findViewById(R.id.tv_flash_empty);
        List<String> lists = db.getflashverse();
        if(lists.size()>0) {
            mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
            mPager.setAdapter(mPagerAdapter);
            mPager.setCurrentItem(0);
            mPager.setVisibility(View.VISIBLE);
            rightnav.setVisibility(View.VISIBLE);
            leftnav.setVisibility(View.VISIBLE);
            tvFlashEmpty.setVisibility(View.GONE);
        }
        else
        {
            mPager.setVisibility(View.GONE);
            rightnav.setVisibility(View.INVISIBLE);
            leftnav.setVisibility(View.INVISIBLE);
            tvFlashEmpty.setVisibility(View.VISIBLE);
        }
        //BlurBehind.getInstance().withAlpha(80).withFilterColor(Color.parseColor("#40000000")).setBackground(this);
        back.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        leftnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = mPager.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    mPager.setCurrentItem(tab);
                } else if (tab == 0) {
                    mPager.setCurrentItem(tab);

                }
            }
        });

        // Images right navigatin
        rightnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = mPager.getCurrentItem();
                tab++;
                mPager.setCurrentItem(tab);
            }
        });
    }
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        List<String> lists = db.getflashverse();
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            Fragment_flash_card fragment;
            fragment = new Fragment_flash_card();
            Bundle args = new Bundle();
            position=position+1;
            args.putInt("category_id", position);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return this.lists.size( );
        }
    }

}