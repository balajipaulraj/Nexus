package com.example.admin.bibleapp.Adapter;

/**
 * Created by Admin on 5/28/2018.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.admin.bibleapp.R;

import java.util.ArrayList;


public class FirstPageListview extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] verses;
    private final Integer[] verseid;
    private final float fontsize;
    private final ArrayList<Integer> positionb;
    int flag=0;
    int i;
    int c=1;
    int j=0;

    public FirstPageListview(Activity context, String[] verse, Integer[] id, float fsize, ArrayList<Integer> pos) {
        super(context, R.layout.fplist, verse);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.verses=verse;
        this.verseid=id;
        fontsize=fsize;
        positionb=pos;
        i=positionb.size();


    }

    @SuppressLint("ResourceAsColor")
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater( );
        View rowView = inflater.inflate(R.layout.fplist, null, true);

        LinearLayout content = (LinearLayout) rowView.findViewById(R.id.lv_content_lay);
        TextView verse_id = (TextView) rowView.findViewById(R.id.fpvid);
        TextView verse = (TextView) rowView.findViewById(R.id.fptitle);
        for(j=0;j<positionb.size();j++) {
            if (position == positionb.get(j))
                content.setBackgroundColor(ContextCompat.getColor(context, R.color.selectionblue));
        }
if(verseid[position].equals(0))
{
 verse_id.setText(" ");
}
else
{
    verse_id.setText(String.valueOf(verseid[position]));
}

        String check[] = verses[position].split(" ",2);
        if(check[0].equals("Chapter"))
        {
            if(c==1)
            {
                verse.setText(verses[position]);
                c=0;
            } else
            {
                verse.setText("\n" + verses[position]);
            }
            verse.setTextColor(R.color.black);
            verse.setTextSize(fontsize+5);
            verse.setGravity(Gravity.CENTER);
        }
        else {
            verse.setText(verses[position]);
            verse.setTextSize(fontsize);
        }
            verse.setLineSpacing(2,1);

        return rowView;


    }
}

