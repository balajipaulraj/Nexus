package com.example.admin.bibleapp.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.popupWindow.ListActivity;

/**
 * Created by Admin on 21-May-18.
 */

public class MyPrayer extends AppCompatActivity {
    ImageButton ibAddPrayer, ibPrayerList;
    DBhelper dbHelper;
    TextView tv_addprayer,tv_mylist;
    String category;
    EditText edtCategory;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprayer);
        Intent intent = getIntent();
        category = intent.getStringExtra("category");
        tv_addprayer = (TextView) findViewById(R.id.add_prayer);
        tv_mylist = (TextView)findViewById(R.id.my_lists);

        ibAddPrayer=(ImageButton) findViewById(R.id.ib_add_prayer);
        ibPrayerList=(ImageButton) findViewById(R.id.ib_prayer_list);
        dbHelper = new DBhelper(getApplicationContext());
        tv_addprayer.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                Dialog dialog1 = new Dialog(MyPrayer.this);
                dialog1.setContentView(R.layout.popup_add_prayer);
                if(category.equals("All"))
                {
                   edtCategory = (EditText) dialog1.findViewById(R.id.new_category_name_edit);
                   edtCategory.setHint("Enter existing category");
                    edtCategory.setVisibility(View.VISIBLE);
                }
                final EditText edtSubject = (EditText) dialog1.findViewById(R.id.new_subject_name_edit);

                ((TextView) dialog1.findViewById(R.id.new_subject_confirm_button)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        String subjectname = edtSubject.getText().toString();
                        if(category.equals("All"))
                        {
                            category = "";
                            category = edtCategory.getText().toString();
                        }
                        if (subjectname.equals("") || category.equals("") ) {
                            Toast.makeText(getApplicationContext(), " Subject or Category name cannot be Empty", Toast.LENGTH_LONG).show();
                        } else {
                            dbHelper.insertPrayer(subjectname,category);
                            int prayerid = dbHelper.getPrayerId(subjectname);
                            Intent i = new Intent(getApplicationContext(), AddSubjectDescription.class);
                            i.putExtra("prayertitle", subjectname);
                            i.putExtra("prayerid", prayerid);
                            startActivity(i);
                            finish();
                        }
                    }
                });
                dialog1.show();
            }
        });
        ibAddPrayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog1 = new Dialog(MyPrayer.this);
                dialog1.setContentView(R.layout.popup_add_prayer);
                final EditText edtSubject = (EditText) dialog1.findViewById(R.id.new_subject_name_edit);
                ((TextView) dialog1.findViewById(R.id.new_subject_confirm_button)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        String subjectname = edtSubject.getText().toString();
                        if (subjectname.equals("")) {
                            Toast.makeText(getApplicationContext(), " Subject name cannot be Empty", Toast.LENGTH_LONG).show();
                        } else {
                            dbHelper.insertPrayer(subjectname,category);
                            int prayerid = dbHelper.getPrayerId(subjectname);
                            Intent i = new Intent(getApplicationContext(), AddSubjectDescription.class);
                            i.putExtra("prayertitle", subjectname);
                            i.putExtra("prayerid", prayerid);
                            startActivity(i);
                            finish();
                        }
                    }
                });
                dialog1.show();
            }
        });
        tv_mylist.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra("category",category);
                intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        ibPrayerList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra("category",category);
                intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

    }
}
