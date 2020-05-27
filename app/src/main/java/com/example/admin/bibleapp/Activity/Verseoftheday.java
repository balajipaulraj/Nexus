package com.example.admin.bibleapp.Activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.bibleapp.Adapter.VodAdapter;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.util.ConnectivityReceiver;
import com.example.admin.bibleapp.util.UiUtil;

import java.util.ArrayList;

/**
 * Created by Admin on 6/18/2018.
 */

public class Verseoftheday extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener
{
    // Write a message to the database

 ListView lv_vod;
 TextView NA;
 String userId;
 DBhelper dBhelper;
 String[] date1;
 int flag=0;
    String[] vdate;
    ArrayList<String> date =  new ArrayList<>();
    String[] title;

    ArrayList<VerseofDay> read=new ArrayList<>();
    String j;

    String[] verse;

    ArrayList<String> allvod = new ArrayList();

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }

    class C02603 implements View.OnClickListener {
        C02603() {
        }

        public void onClick(View v) {


            UiUtil.copyToClipboard(getApplicationContext( ), j);
            Toast.makeText(getApplicationContext( ), "Copied", Toast.LENGTH_LONG).show( );
            //lv_content.setBackgroundColor(getResources( ).getColor(R.color.white));
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_vod);
        lv_vod = (ListView) findViewById(R.id.vod_list);
        NA = (TextView) findViewById(R.id.na);

        checkConnection( );
        dBhelper = new DBhelper(getApplicationContext( ));
        dBhelper.getReadableDatabase( );
        allvod = dBhelper.getallvod( );
        if (allvod.get(0).equals("")) {
            NA.setVisibility(View.VISIBLE);
            lv_vod.setVisibility(View.GONE);
        } else {
            NA.setVisibility(View.GONE);
            lv_vod.setVisibility(View.VISIBLE);
            String[] date = new String[allvod.size( )];
            String[] verse = new String[allvod.size( )];
            String[] title = new String[allvod.size( )];
            for (int i = 0; i < allvod.size( ); i++) {
                String[] split = allvod.get(i).split(",,", 3);
                date[i] = split[0];
                title[i] = split[1];
                verse[i] = split[2];

                createUser(date[i], title[i], verse[i]);
            }
            boolean isConnected = isNetworkConnected();
            if(isConnected==false)
            {
                VodAdapter adapter = new VodAdapter(Verseoftheday.this,date,title,verse);
                lv_vod.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"Enable Internet",Toast.LENGTH_LONG).show();
            }
        }


        }




    private void checkConnection() {


    }


    private void createUser(String date, String title, String verse) {

        // In real apps this userId should be fetched
        // by implementing firebase auth
        VerseofDay vod = new VerseofDay(date, title, verse);
    }


    public void setvod()
    {
        vdate = new String[read.size()];
        title = new String[read.size()];
        verse = new String[read.size()];
        for(int i=0;i<read.size();i++)
        {
            vdate[i] = read.get(i).date;
            title[i] = read.get(i).title;
            verse[i] = read.get(i).verse;
        }
        VodAdapter adapter = new VodAdapter(Verseoftheday.this,title,vdate,verse);
        lv_vod.setAdapter(adapter);
    }



public void onResume()
{
    super.onResume();


}
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}

