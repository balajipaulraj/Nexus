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


public class ThankfulPrayersAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] subject;
    private final String[] desc;
    private final String[] date;
    private final String[] time;
    int flag=0;

    public ThankfulPrayersAdapter(Activity context, String[] subject,String[] desc, String[] date, String[] time) {
        super(context, R.layout.thankful_prayer_list, subject);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.subject=subject;
        this.desc=desc;
        this.date=date;
        this.time=time;

    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater( );
        View rowView = inflater.inflate(R.layout.thankful_prayer_list, null, true);

        TextView titleText = (TextView) rowView.findViewById(R.id.subject_name);
        TextView dateText = (TextView) rowView.findViewById(R.id.date);
        TextView timeText=(TextView)rowView.findViewById(R.id.tptime);
        TextView descText = (TextView)rowView.findViewById(R.id.Description);


            timeText.setText(time[position]);
            dateText.setText(date[position]);
            titleText.setText(subject[position]);
            descText.setText(desc[position]);

        return rowView;

    };
}

