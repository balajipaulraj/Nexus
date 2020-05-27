package com.example.admin.bibleapp.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.popupWindow.PlanSelection1;
import com.example.admin.bibleapp.popupWindow.bibleselection1;
import com.example.admin.bibleapp.popupWindow.popFontSize;
import com.example.admin.bibleapp.popupWindow.popResetConfirm;

import java.util.ArrayList;

/**
 * Created by Admin on 6/13/2018.
 */

public class Settings extends AppCompatActivity {

    ListView lv_settings;
    ArrayList<String> menu =  new ArrayList();
    Dialog pop_reset;
    float font_size;
    Dialog pop_font;
    LinearLayout set_back;
    SharedPreferences sharedPreferences;
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        lv_settings = (ListView)findViewById(R.id.settings);
        set_back = (LinearLayout)findViewById(R.id.settings_lay);
        menu.add("Reset Reading");
        menu.add("Font Size");
//        menu.add("Load new Bible");
//        menu.add("Load new Plan");
        menu.add("Change plan");
        menu.add("Change Bible");
        menu.add("Notification");

        for(int i=0;i<menu.size();i++)
        {

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu);
        lv_settings.setAdapter(adapter);
        
        lv_settings.setOnItemClickListener(new AdapterView.OnItemClickListener( ) {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int id, long l) {
                if (id==0) {
//                    Intent intent = new Intent(Settings.this, popReset.class);
//                    intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
//                    startActivity(intent);
                    reset_pop();
                } else if (id == 1) {
                    Intent intent = new Intent(Settings.this, popFontSize.class);
                    startActivity(intent);
//                } else if (id == 2) {
//                    Intent intent = new Intent(Settings.this, popLoadBible.class);
//                    intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
//                    startActivity(intent);
//
//                } else if (id == 3) {
//                    //     loaddata( );
//                    Intent intent = new Intent(Settings.this, popLoadplan.class);
//                    intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
//                    startActivity(intent);
                } else if (id ==2) {
                    Intent intent = new Intent(Settings.this, PlanSelection1.class);
                    intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);


                } else if (id == 3) {
                    Intent intent = new Intent(Settings.this, bibleselection1.class);
                    intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);

                } else if (id == 4) {
                    Toast.makeText(getApplicationContext(), "Notification is Disabled", 1).show();

                }
            }
        });
    }
    private void reset_pop() {
        if (this.pop_reset == null) {
            this.pop_reset = new Dialog(Settings.this);
            this.pop_reset.requestWindowFeature(1);
            this.pop_reset.setTitle("Search Option");
            this.pop_reset.setCanceledOnTouchOutside(true);
            this.pop_reset.setCancelable(true);
            this.pop_reset.setContentView(R.layout.activity_pop_reset);
            Button btnSumbit = (Button) this.pop_reset.findViewById(R.id.tv_yes);
            Button btnCancel = (Button) this.pop_reset.findViewById(R.id.tv_no);
            pop_reset.show( );
            // ImageView ivclose = (ImageView) this.pop_reset.findViewById(R.id.iv_close);

//            ivclose.setOnClickListener(new SampleFragment.C02636( ));
            btnSumbit.setOnClickListener(new View.OnClickListener( ) {
                public void onClick(View v) {
                    pop_reset.dismiss();
                    Intent i = new Intent(Settings.this,popResetConfirm.class);
                    startActivity(i);
                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View view) {
                    pop_reset.dismiss( );
                }
            });

        } else if (this.pop_reset.isShowing( )) {
            this.pop_reset.dismiss( );
            this.pop_reset = null;
            reset_pop();
        } else {
            this.pop_reset = null;
            reset_pop();
        }
    }

}
