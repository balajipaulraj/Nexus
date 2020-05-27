package com.example.admin.bibleapp.Adapter;

/**
 * Created by Admin on 5/28/2018.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CompareAdapter extends ArrayAdapter<String> {

    private final Activity context;
//    private final String[] verse_id;
    private final String[] version;
    private final String[] verse;
    int flag=0;
    DBhelper db;

    public CompareAdapter(Activity context,  String[] version, String[] verse) {
        super(context, R.layout.compare_list, verse);
        // TODO Auto-generated constructor stub

        this.context=context;
//        this.verse_id=verse_id;
        this.version=version;
        this.verse=verse;

    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater( );
        View rowView = inflater.inflate(R.layout.compare_list, null, true);

//        TextView tv_vodtitle = (TextView) rowView.findViewById(R.id.vod_title);
        final TextView tv_date = (TextView) rowView.findViewById(R.id.version);
        TextView tv_verse=(TextView)rowView.findViewById(R.id.verse);

//        tv_vodtitle.setText(verse_id[position]);
        if(position<6) {
            tv_date.setText(version[position]);
            tv_verse.setText(verse[position]);
        }
        return rowView;

    };
}

