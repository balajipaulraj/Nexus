package com.example.admin.bibleapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.bibleapp.Database.DBhelper;


import java.util.ArrayList;

/**
 * Created by Admin on 6/27/2018.
 */

public class Sync extends AppCompatActivity {
    Button sync;
    DBhelper dbhelper;
    SharedPreferences sharedPreferences;

    String table_plan;
    String[] id,date,oldread,pslamread,newread,fourthread;
    ArrayList<String> plan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);
        sync = (Button)findViewById(R.id.sync);
        this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
        table_plan = sharedPreferences.getString("tablePlan",null);
        this.dbhelper = new DBhelper(this, getFilesDir().getAbsolutePath());

        sync.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
              dbhelper.getReadableDatabase();
              plan=dbhelper.getplan(table_plan);
                id= new String[plan.size()];
                date= new String[plan.size()];
                oldread= new String[plan.size()];
                pslamread= new String[plan.size()];
                newread= new String[plan.size()];
                fourthread= new String[plan.size()];
                for(int i =0;i<plan.size();i++)
                {
                    String[] split =plan.get(i).split(",,",6);
                    id[i]= split[0];
                    date[i]= split[1];
                    oldread[i]= split[2];
                    pslamread[i]= split[3];
                    newread[i]= split[4];
                    fourthread[i]= split[5];

                    createUser(id[i], date[i], oldread[i],pslamread[i],newread[i],fourthread[i]);

                }
                Toast.makeText(getApplicationContext(),date[0]+" "+oldread[0],Toast.LENGTH_LONG).show();
                //get int_oldread,int_newread,int_psalmread,int_fourthread from DB
                //pass all the values to arraylist and store the values to firebase on click sync
            }
        });
    }
    private void createUser(String id, String date, String oldread, String psalmread,String newread,String fourthread) {
    }
}
