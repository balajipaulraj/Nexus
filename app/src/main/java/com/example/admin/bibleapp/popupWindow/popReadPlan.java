package com.example.admin.bibleapp.popupWindow;

import android.app.Activity;
import android.app.ProgressDialog;
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
import com.example.admin.bibleapp.Activity.CalPage;

public class popReadPlan extends Activity {
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
    RadioButton rbPlan1;
    RadioButton rbPlan2;
    RadioGroup rgSelectPlan;
    SharedPreferences sharedPreferences;
    Typeface tribbon;

    class C03171 implements OnClickListener {
        C03171() {
        }

        public void onClick(View v) {
            popReadPlan.this.finish();
        }
    }

    class C03182 implements OnClickListener {
        C03182() {
        }

        public void onClick(View v) {
            if (popReadPlan.this.rbPlan1.isChecked()) {
                popReadPlan.this.pd.show();
                Intent i = new Intent(popReadPlan.this.getApplication(), CalPage.class);
                popReadPlan.this.sharedPreferences = popReadPlan.this.getSharedPreferences("MyPrefs", 0);
                Editor edit_gen = popReadPlan.this.sharedPreferences.edit();
                edit_gen.putString("plan", "planI");
                edit_gen.commit();
                popReadPlan.this.startActivity(i);
                popReadPlan.this.finish();
                return;
            }
            popReadPlan.this.pd.show();
            Intent i = new Intent(popReadPlan.this.getApplicationContext(), CalPage.class);
            sharedPreferences =getSharedPreferences("MyPrefs", 0);
            Editor edit_gen = popReadPlan.this.sharedPreferences.edit();
            edit_gen.putString("plan", "planII");
            edit_gen.commit();
            popReadPlan.this.startActivity(i);
            popReadPlan.this.finish();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_pop_read_plan);
        //this.pd = new ProgressDialog(this, R.style.MyTheme);
        this.pd.setCancelable(false);
        this.pd.setProgressStyle(16973854);
        this.RobotoSlabLight = Typeface.createFromAsset(getAssets(), "fonts/RobotoSlabLight.ttf");
        this.heading = (TextView) findViewById(R.id.heading);
        this.heading.setTypeface(this.RobotoSlabLight);
        this.rgSelectPlan = (RadioGroup) findViewById(R.id.rg_year);
        this.rbPlan1 = (RadioButton) findViewById(R.id.rb_part1);
        this.rbPlan2 = (RadioButton) findViewById(R.id.rb_part2);
        this.btnSumbit = (Button) findViewById(R.id.btn_select);
        this.ivclose = (ImageView) findViewById(R.id.iv_close);
        this.ivclose.setOnClickListener(new C03171());
        this.btnSumbit.setOnClickListener(new C03182());
//BlurBehind.getInstance().withAlpha(80).withFilterColor(Color.parseColor("#40000000")).setBackground(this);    }
    }
}
