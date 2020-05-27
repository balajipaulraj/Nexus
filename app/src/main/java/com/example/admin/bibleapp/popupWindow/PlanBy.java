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
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Activity.CalPage;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PlanBy extends Activity {
    public static final String MyPREFERENCES = "MyPrefs";
    Typeface RobotoSlabLight;
    String bible;
    Button btnSumbit;
    String getformattedDate;
    TextView heading;
    ImageView ivclose;
    ProgressDialog pd;
    RadioGroup rgSelectPlan;
    SharedPreferences sharedPreferences;
    String table_plan;

    class C02991 implements OnClickListener {
        C02991() {
        }

        public void onClick(View v) {
            PlanBy.this.finish();
        }
    }

    class C03002 implements OnClickListener {
        C03002() {
        }

        public void onClick(View v) {
            switch (PlanBy.this.rgSelectPlan.getCheckedRadioButtonId()) {
                case R.id.rb_bydate:
                    //PlanBy.this.pd.show();
                    Intent i = new Intent(PlanBy.this.getApplication(), CalPage.class);
                    PlanBy.this.sharedPreferences = PlanBy.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen = PlanBy.this.sharedPreferences.edit();
                    edit_gen.putString("planby", "bydate");
                    edit_gen.putString("initialdate", PlanBy.this.getformattedDate);
                    edit_gen.commit();
                    PlanBy.this.startActivity(i);
                    PlanBy.this.finish();
                    return;
                case R.id.rb_byday:
                    //PlanBy.this.pd.show();
                    Intent i1 = new Intent(PlanBy.this.getApplication(), CalPage.class);
                    PlanBy.this.sharedPreferences = PlanBy.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen1 = PlanBy.this.sharedPreferences.edit();
                    edit_gen1.putString("planby", "byday");
                    edit_gen1.putString("initialdate", PlanBy.this.getformattedDate);
                    edit_gen1.commit();
                    PlanBy.this.startActivity(i1);
                    PlanBy.this.finish();
                    return;
                default:
                    return;
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_plan_by);
        //this.pd = new ProgressDialog(this, R.style.MyTheme);
//        this.pd.setCancelable(false);
//        this.pd.setProgressStyle(16973854);
        this.heading = (TextView) findViewById(R.id.heading);
        this.rgSelectPlan = (RadioGroup) findViewById(R.id.rg_bible);
        this.btnSumbit = (Button) findViewById(R.id.btn_select);
        this.ivclose = (ImageView) findViewById(R.id.iv_close);
        this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
        this.bible = this.sharedPreferences.getString("bible", null);
        this.table_plan = this.sharedPreferences.getString("tableplan", null);
        this.ivclose.setOnClickListener(new C02991());
        this.getformattedDate = new SimpleDateFormat("MMM dd").format(Calendar.getInstance().getTime());
        this.btnSumbit.setOnClickListener(new C03002());
    }
}
