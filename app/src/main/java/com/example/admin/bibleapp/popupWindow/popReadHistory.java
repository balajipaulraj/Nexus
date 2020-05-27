package com.example.admin.bibleapp.popupWindow;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.bibleapp.Adapter.MyListView;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.R;


import java.util.ArrayList;

/**
 * Created by Admin on 5/28/2018.
 */

public class popReadHistory extends AppCompatActivity {
ListView readhistory;
TextView tvHistoryEmpty;
DBhelper dbhelper;
Button back;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_read_history);
        readhistory=(ListView) findViewById(R.id.readhistory);
        back = (Button)findViewById(R.id.rh_back);
        tvHistoryEmpty=(TextView) findViewById(R.id.tv_history_empty);
        ArrayList<String> rhdate = new ArrayList();
        ArrayList<String> rhtime = new ArrayList();
        ArrayList<String> rhcontent = new ArrayList();
        this.dbhelper = new DBhelper(this);
        dbhelper.getReadableDatabase();
        rhtime=dbhelper.getReadHistoryTime();

        rhcontent=dbhelper.getReadHistory();
        rhdate=dbhelper.getReadHistoryDate();
        dbhelper.close();
        String[] content=new String[rhdate.size()];
        String[] date = new String[rhdate.size()];
        String[] time=new String[rhdate.size()];
        if(rhdate.size()==0)
        {
            tvHistoryEmpty.setVisibility(View.VISIBLE);
            readhistory.setVisibility(View.GONE);
        }
        else
        {
            tvHistoryEmpty.setVisibility(View.GONE);
            readhistory.setVisibility(View.VISIBLE);
            for(int i=0;i<rhdate.size();i++)
            {
                time[i]=rhtime.get(i);
                content[i]=rhcontent.get(i);
                date[i]=rhdate.get(i);

            }
            MyListView adapter = new MyListView(this,content,date,time);
            readhistory.setAdapter(adapter);
        }
        back.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}