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
import com.example.admin.bibleapp.util.UiUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class VodAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] vod_title;
    private final String[] date;
    private final String[] verse;
    int flag=0;
    DBhelper db;

    public VodAdapter(Activity context, String[] vod_title, String[] date, String[] verse) {
        super(context, R.layout.vod_list, vod_title);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.vod_title=vod_title;
        this.date=date;
        this.verse=verse;

    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater( );
        View rowView = inflater.inflate(R.layout.vod_list, null, true);

        TextView tv_vodtitle = (TextView) rowView.findViewById(R.id.vod_title);
        final TextView tv_date = (TextView) rowView.findViewById(R.id.vod_date);
        TextView tv_verse=(TextView)rowView.findViewById(R.id.vod);
        TextView tv_flashcard = (TextView)rowView.findViewById(R.id.tv_flashcard);
        TextView tv_copy = (TextView)rowView.findViewById(R.id.tv_copy);

                tv_vodtitle.setText(vod_title[position]);
                tv_date.setText(date[position]);
                tv_verse.setText(verse[position]);


        tv_copy.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                String j =vod_title[position]+ "\n" + verse[position];
                UiUtil.copyToClipboard(getContext( ),j);
                Toast.makeText(getContext( ), "Copied", Toast.LENGTH_LONG).show( );
            }
        });

        tv_flashcard.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {

                db = new DBhelper(getContext( ));
                db.getWritableDatabase( );
                String v_title = vod_title[position];
                String date1 = date[position];
                long i = db.insertflashcard(verse[position], date1 , v_title);
                if (i != -1) {
                    Toast.makeText(getContext( ), "Added to Memory Verse", Toast.LENGTH_LONG).show( );

                }
            }
        });
        return rowView;

    };
}

