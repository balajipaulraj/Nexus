package com.example.admin.bibleapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.bibleapp.Adapter.ThankfulPrayersAdapter;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.R;

import java.util.ArrayList;

/**
 * Created by Admin on 6/22/2018.
 */

public class ThankfulPrayers extends AppCompatActivity {
ListView lv_thankful;
ArrayList<String> t_prayers = new ArrayList();
TextView empty;
DBhelper dBhelper;
    String[] subject;
    String[] desc;
    String[] tpdate;
    String[] time;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.thanful_prayers);
        lv_thankful = (ListView) findViewById(R.id.thankfulprayers);
        empty = (TextView) findViewById(R.id.tv_list_empty);
        dBhelper = new DBhelper(getApplicationContext( ));
        dBhelper.getReadableDatabase( );
        t_prayers = dBhelper.getThankfulPrayers( );
        if (t_prayers.isEmpty( ) == true) {
            empty.setVisibility(View.VISIBLE);
            lv_thankful.setVisibility(View.GONE);
        } else {
            subject = new String[t_prayers.size( )];
            desc = new String[t_prayers.size( )];
            tpdate = new String[t_prayers.size( )];
            time = new String[t_prayers.size( )];
            for (int i = 0; i < t_prayers.size( ); i++) {
                String[] split = t_prayers.get(i).split(",,", 4);
                subject[i] = split[0];
                desc[i] = split[1];
                tpdate[i] = split[2];
                time[i] = split[3];
            }
            ThankfulPrayersAdapter adapter = new ThankfulPrayersAdapter(ThankfulPrayers.this, subject, desc, tpdate, time);
            lv_thankful.setAdapter(adapter);
        }
    }
    }