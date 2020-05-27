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

import com.example.admin.bibleapp.R;


public class MyListView extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] maintitle;
    private final String[] date;
    private final String[] time;
    int flag=0;

    public MyListView(Activity context, String[] maintitle, String[] date, String[] time) {
        super(context, R.layout.fplist, maintitle);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.maintitle=maintitle;
        this.date=date;
        this.time=time;

    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater( );
        View rowView = inflater.inflate(R.layout.mylist, null, true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView dateText = (TextView) rowView.findViewById(R.id.listdate);
        TextView timeText=(TextView)rowView.findViewById(R.id.rhtime);

        if(position!=0) {
            if (!date[position].equals(date[position - 1])) {
                dateText.setText(date[position]);
                titleText.setText(maintitle[position]);
                timeText.setText(time[position]);
            } else {
                timeText.setText(time[position]);
                titleText.setText(maintitle[position]);
                dateText.setVisibility(View.GONE);
            }
        }
        else
        {
            timeText.setText(time[position]);
            dateText.setText(date[position]);
            titleText.setText(maintitle[position]);
        }
        return rowView;

    };
}

