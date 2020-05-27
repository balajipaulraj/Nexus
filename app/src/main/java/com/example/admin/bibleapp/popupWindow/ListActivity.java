package com.example.admin.bibleapp.popupWindow;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.bibleapp.Adapter.PrayerAdapter;
import com.example.admin.bibleapp.Activity.AddSubjectDescription;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.Model.Prayer;
import com.example.admin.bibleapp.R;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends Activity {
    RecyclerView rvPrayerList;
    private PrayerAdapter mAdapter;
    List<Prayer> data = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    TextView tvListEmpty;
    TextView listtitle;
    DBhelper dbHelper;
    ImageView add_prayer;
    EditText edtCategory;
    String category;
    ArrayList<String> PrayCategory;
    Spinner Exist_Category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        add_prayer = (ImageView)findViewById(R.id.add_prayer);
        category= intent.getStringExtra("category");
        dbHelper = new DBhelper(getApplicationContext());
        data = dbHelper.getPrayer(category);
        PrayCategory = intent.getStringArrayListExtra("category array");
        PrayCategory.remove(PrayCategory.size()-1);
        PrayCategory.remove(0);
        PrayCategory.remove(0);
        PrayCategory.remove(0);
        rvPrayerList = (RecyclerView) findViewById(R.id.rv_prayer_list);
        mLayoutManager = new LinearLayoutManager(this);
        listtitle= (TextView)findViewById(R.id.category);
        listtitle.setText(category);
        if(category.equals("The Lords Prayer"))
        {
            add_prayer.setVisibility(View.GONE);
        }
        tvListEmpty=(TextView) findViewById(R.id.tv_list_empty);
        rvPrayerList.setLayoutManager(this.mLayoutManager);
        if (data.size() == 0) {
            tvListEmpty.setVisibility(View.VISIBLE);
            rvPrayerList.setVisibility(View.GONE);
        } else {
            mAdapter = new PrayerAdapter(this, data,category);
            rvPrayerList.setAdapter(this.mAdapter);
            rvPrayerList.setVisibility(View.VISIBLE);
            tvListEmpty.setVisibility(View.GONE);
        }

        add_prayer.setOnClickListener(new View.OnClickListener( ) {

            @Override
            public void onClick(View view) {
                Dialog dialog1 = new Dialog(ListActivity.this);
                dialog1.setContentView(R.layout.popup_add_prayer);
                if(category.equals("All"))
                {
                    edtCategory = (EditText) dialog1.findViewById(R.id.new_category_name_edit);
                    Exist_Category = (Spinner)dialog1.findViewById(R.id.new_category_name_spinner);
                    Exist_Category.setVisibility(View.VISIBLE);
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                            (getApplicationContext(), android.R.layout.simple_spinner_item,PrayCategory); //selected item will look like a spinner set from XML
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Exist_Category.setAdapter(spinnerArrayAdapter);
                }
                final EditText edtSubject = (EditText) dialog1.findViewById(R.id.new_subject_name_edit);

                ((TextView) dialog1.findViewById(R.id.new_subject_confirm_button)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        String subjectname = edtSubject.getText().toString();
                        if(category.equals("All"))
                        {
                            category = "";
                            category = Exist_Category.getSelectedItem().toString();
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
    }
}
