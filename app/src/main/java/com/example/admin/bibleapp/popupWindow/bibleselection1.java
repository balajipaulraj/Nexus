package com.example.admin.bibleapp.popupWindow;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.R;

import java.util.ArrayList;

public class bibleselection1 extends Activity {
    public static final String MyPREFERENCES = "MyPrefs";
    Typeface RobotoSlabLight;
    Typeface RobotoSlabThin;
    Button btnCancel;
    Button btnSumbit;
    TextView goBook;
    TextView goChap;
    TextView heading;
    ImageView ivclose;
    Typeface monBold;
    Typeface monLight;
    Typeface monRegular;
    TextView newTestament;
    TextView oldTestament;
    ProgressDialog pd;
    TextView pre;
    RadioButton rbASV;
    RadioButton rbBBE;
    RadioButton rbDBE;
    RadioButton rbKJV;
    RadioButton rbWBT;
    RadioButton rbWEB;
    RadioGroup rgSelectPlan;
    SharedPreferences sharedPreferences;
    Typeface tribbon;
    RadioButton wbYLT;
    ListView Bible;
    DBhelper db;

    class C03091 implements OnClickListener {
        C03091() {
        }

        public void onClick(View v) {
            bibleselection1.this.finish();
        }
    }

    class C03102 implements OnClickListener {
        C03102() {
        }

        public void onClick(View v) {
            switch (bibleselection1.this.rgSelectPlan.getCheckedRadioButtonId()) {
                case R.id.rb_asv:
  //                  bibleselection1.this.pd.show();
                    bibleselection1.this.sharedPreferences = bibleselection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen = bibleselection1.this.sharedPreferences.edit();
                    edit_gen.putString("bible", "t_asv");
                    edit_gen.commit();
                    bibleselection1.this.finish();
                    return;
                case R.id.rb_bbe:
//                    bibleselection1.this.pd.show();
                    bibleselection1.this.sharedPreferences = bibleselection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen1 = bibleselection1.this.sharedPreferences.edit();
                    edit_gen1.putString("bible", "t_bbe");
                    edit_gen1.commit();
                    bibleselection1.this.finish();
                    return;
                case R.id.rb_deb:
                    //bibleselection1.this.pd.show();
                    bibleselection1.this.sharedPreferences = bibleselection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen2 = bibleselection1.this.sharedPreferences.edit();
                    edit_gen2.putString("bible", "t_dby");
                    edit_gen2.commit();
                    bibleselection1.this.finish();
                    return;
                case R.id.rb_kjv:
    //                bibleselection1.this.pd.show();
                    bibleselection1.this.sharedPreferences = bibleselection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen3 = bibleselection1.this.sharedPreferences.edit();
                    edit_gen3.putString("bible", "t_kjv");
                    edit_gen3.commit();
                    bibleselection1.this.finish();
                    return;
                case R.id.rb_wb:
      //              bibleselection1.this.pd.show();
                    bibleselection1.this.sharedPreferences = bibleselection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen4 = bibleselection1.this.sharedPreferences.edit();
                    edit_gen4.putString("bible", "t_wbt");
                    edit_gen4.commit();
                    bibleselection1.this.finish();
                    return;
                case R.id.rb_web:
        //            bibleselection1.this.pd.show();
                    bibleselection1.this.sharedPreferences = bibleselection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen5 = bibleselection1.this.sharedPreferences.edit();
                    edit_gen5.putString("bible", "t_web");
                    edit_gen5.commit();
                    bibleselection1.this.finish();
                    return;
                case R.id.rb_ylt:
          //          bibleselection1.this.pd.show();
                    bibleselection1.this.sharedPreferences = bibleselection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen6 = bibleselection1.this.sharedPreferences.edit();
                    edit_gen6.putString("bible", "t_ylt");
                    edit_gen6.commit();
                    bibleselection1.this.finish();
                    return;
                case R.id.rb_nasb:
            //        bibleselection1.this.pd.show();
                    bibleselection1.this.sharedPreferences = bibleselection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen10 = bibleselection1.this.sharedPreferences.edit();
                    edit_gen10.putString("bible", "t_nasb");
                    edit_gen10.commit();
                    return;
                default:
                    return;
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_bibleselection1);
        // this.pd = new ProgressDialog(this, R.style.MyTheme);
//        this.pd.setCancelable(false);
        //      this.pd.setProgressStyle(16973854);
        this.heading = (TextView) findViewById(R.id.heading);
        this.rgSelectPlan = (RadioGroup) findViewById(R.id.rg_bible);
        this.rbASV = (RadioButton) findViewById(R.id.rb_asv);
        this.rbBBE = (RadioButton) findViewById(R.id.rb_bbe);
        this.rbDBE = (RadioButton) findViewById(R.id.rb_deb);
        this.rbKJV = (RadioButton) findViewById(R.id.rb_kjv);
        this.rbWBT = (RadioButton) findViewById(R.id.rb_wb);
        this.rbWEB = (RadioButton) findViewById(R.id.rb_web);
        this.wbYLT = (RadioButton) findViewById(R.id.rb_ylt);
//        ArrayList<String> bname = new ArrayList<String>();
//        db = new DBhelper(getApplicationContext(), getFilesDir().getAbsolutePath());
//        db.getReadableDatabase();
//        bname = db.getBibleName();
//        Log.e("Biblename", bname+"");
//        db.close();
//        Bible = (ListView) findViewById(R.id.bibleversion);
//        ArrayAdapter adapter = new ArrayAdapter<String>(bibleselection1.this, android.R.layout.simple_list_item_1, bname);
//        Bible.setAdapter(adapter);
        this.btnSumbit = (Button) findViewById(R.id.btn_select);
        this.ivclose = (ImageView) findViewById(R.id.iv_close);
        this.ivclose.setOnClickListener(new C03091());
           this.btnSumbit.setOnClickListener(new C03102());
//BlurBehind.getInstance().withAlpha(80).withFilterColor(Color.parseColor("#40000000")).setBackground(this);    }
    }
}
