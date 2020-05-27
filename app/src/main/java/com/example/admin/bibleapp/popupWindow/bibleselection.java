package com.example.admin.bibleapp.popupWindow;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.admin.bibleapp.R;

public class bibleselection extends Activity {
    public static final String MyPREFERENCES = "MyPrefs";
    Typeface RobotoSlabLight;
    Button btnCancel;
    Button btnSumbit;
    TextView goBook;
    TextView goChap;
    TextView heading;
    ImageView ivclose;
    TextView newTestament;
    TextView oldTestament;
    //ProgressDialog pd;
    TextView pre;
    RadioButton rbASV;
    RadioButton rbBBE;
    RadioButton rbDBE;
    RadioButton rbKJV;
    RadioButton rbWBT;
    RadioButton rbWEB;
    RadioGroup rgSelectPlan;
    SharedPreferences sharedPreferences;
    RadioButton wbYLT;

    class C03071 implements OnClickListener {
        C03071(){
    }
        public void onClick(View v) {
            bibleselection.this.finish();
        }
    }

    class C03082 implements OnClickListener {
        C03082() {
        }

        public void onClick(View v) {
            Intent intent;
            switch (bibleselection.this.rgSelectPlan.getCheckedRadioButtonId()) {
                case R.id.rb_asv:
                   // bibleselection.this.pd.show();
                    Intent i = new Intent(bibleselection.this.getApplication(), PlanSelection.class);
                    bibleselection.this.sharedPreferences = bibleselection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen = bibleselection.this.sharedPreferences.edit();
                    edit_gen.putString("bible", "t_asv");
                    edit_gen.commit();
                    bibleselection.this.startActivity(i);
                    bibleselection.this.finish();
                    return;
                case R.id.rb_bbe:
                  //  bibleselection.this.pd.show();
                    Intent i1 = new Intent(bibleselection.this.getApplication(), PlanSelection.class);
                    bibleselection.this.sharedPreferences = bibleselection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen1 = bibleselection.this.sharedPreferences.edit();
                    edit_gen1.putString("bible", "t_bbe");
                    edit_gen1.commit();
                    bibleselection.this.startActivity(i1);
                    bibleselection.this.finish();
                    return;
                case R.id.rb_deb:
                 //   bibleselection.this.pd.show();
                    Intent i2 = new Intent(bibleselection.this.getApplication(), PlanSelection.class);
                    bibleselection.this.sharedPreferences = bibleselection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen2 = bibleselection.this.sharedPreferences.edit();
                    edit_gen2.putString("bible", "t_dby");
                    edit_gen2.commit();
                    bibleselection.this.startActivity(i2);
                    bibleselection.this.finish();
                    return;
                case R.id.rb_kjv:
                  //  bibleselection.this.pd.show();
                    Intent i3 = new Intent(bibleselection.this.getApplication(), PlanSelection.class);
                    bibleselection.this.sharedPreferences = bibleselection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen3 = bibleselection.this.sharedPreferences.edit();
                    edit_gen3.putString("bible", "t_kjv");
                    edit_gen3.commit();
                    bibleselection.this.startActivity(i3);
                    bibleselection.this.finish();
                    return;
                case R.id.rb_wb:
                  //  bibleselection.this.pd.show();
                    intent = new Intent(bibleselection.this.getApplication(), PlanSelection.class);
                    bibleselection.this.sharedPreferences = bibleselection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen4 = bibleselection.this.sharedPreferences.edit();
                    edit_gen4.putString("bible", "t_wbt");
                    edit_gen4.commit();
                    bibleselection.this.startActivity(intent);
                    bibleselection.this.finish();
                    return;
                case R.id.rb_web:
                 //   bibleselection.this.pd.show();
                    intent = new Intent(bibleselection.this.getApplication(), PlanSelection.class);
                    bibleselection.this.sharedPreferences = bibleselection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen5 = bibleselection.this.sharedPreferences.edit();
                    edit_gen5.putString("bible", "t_web");
                    edit_gen5.commit();
                    bibleselection.this.startActivity(intent);
                    bibleselection.this.finish();
                    return;
                case R.id.rb_ylt:
                 //   bibleselection.this.pd.show();
                    intent = new Intent(bibleselection.this.getApplication(), PlanSelection.class);
                    bibleselection.this.sharedPreferences = bibleselection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen6 = bibleselection.this.sharedPreferences.edit();
                    edit_gen6.putString("bible", "t_ylt");
                    edit_gen6.commit();
                    bibleselection.this.startActivity(intent);
                    bibleselection.this.finish();
                    return;
                case R.id.rb_nasb:
                 //   bibleselection.this.pd.show();
                    Intent i10 = new Intent(bibleselection.this.getApplication(), PlanSelection.class);
                    bibleselection.this.sharedPreferences = bibleselection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen10 = bibleselection.this.sharedPreferences.edit();
                    edit_gen10.putString("bible", "t_nasb");
                    edit_gen10.commit();
                    bibleselection.this.startActivity(i10);
                    bibleselection.this.finish();
                    return;
                default:
                    return;
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bibleselection);
//        this.pd = new ProgressDialog(this, R.style.AppTheme);
//        this.pd.setCancelable(false);
//        this.pd.setProgressStyle(16973854);
        this.RobotoSlabLight = Typeface.createFromAsset(getAssets(), "fonts/RobotoSlabLight.ttf");
        this.heading = (TextView) findViewById(R.id.heading);
        //this.heading.setTypeface(this.RobotoSlabLight);
        this.rgSelectPlan = (RadioGroup) findViewById(R.id.rg_bible);
        this.rbASV = (RadioButton) findViewById(R.id.rb_asv);
        this.rbBBE = (RadioButton) findViewById(R.id.rb_bbe);
        this.rbDBE = (RadioButton) findViewById(R.id.rb_deb);
        this.rbKJV = (RadioButton) findViewById(R.id.rb_kjv);
        this.rbWBT = (RadioButton) findViewById(R.id.rb_wb);
        this.rbWEB = (RadioButton) findViewById(R.id.rb_web);
        this.wbYLT = (RadioButton) findViewById(R.id.rb_ylt);
        this.btnSumbit = (Button) findViewById(R.id.btn_select);
        this.ivclose = (ImageView) findViewById(R.id.iv_close);
        this.ivclose.setOnClickListener(new C03071());
        this.btnSumbit.setOnClickListener(new C03082());
//BlurBehind.getInstance().withAlpha(80).withFilterColor(Color.parseColor("#40000000")).setBackground(this);    }
    }
}
