package com.example.admin.bibleapp.popupWindow;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.bibleapp.Activity.MonthlyCal;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Database.DBhelper;

public class popReset extends Activity {
    public static final String MyPREFERENCES = "MyPrefs";
    Typeface RobotoSlabLight;
    DBhelper dbhelper;
    TextView goBook;
    TextView goChap;
    TextView heading;
    TextView newTestament;
    TextView oldTestament;
    String planId;
    TextView pre;
    SharedPreferences sharedPreferences;
    String table_plan;
    Button tvNo;
    Button tvYes;

    class C03191 implements OnClickListener {
        C03191() {
        }

        public void onClick(View v) {
            popReset.this.finish();
        }
    }

    class C03202 implements OnClickListener {
        C03202() {
        }

        public void onClick(View v) {
            Intent i = new Intent(popReset.this,popResetConfirm.class);
            startActivity(i);
//            popReset.this.dbhelper.resetdata2(popReset.this.table_plan);
//            Toast.makeText(popReset.this.getApplicationContext(), "Reset Completed", Toast.LENGTH_LONG).show();
            popReset.this.finish();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_pop_reset);
        MonthlyCal mon = new MonthlyCal();
        this.dbhelper = new DBhelper(this, getFilesDir().getAbsolutePath());
        this.tvYes = (Button) findViewById(R.id.tv_yes);
        this.tvNo = (Button) findViewById(R.id.tv_no);
        this.heading = (TextView) findViewById(R.id.resetheading);
        this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
        this.table_plan = this.sharedPreferences.getString("tablePlan", null);
        this.planId = this.sharedPreferences.getString("plan", null);
        this.tvNo.setOnClickListener(new C03191());
        this.tvYes.setOnClickListener(new C03202());
//BlurBehind.getInstance().withAlpha(80).withFilterColor(Color.parseColor("#40000000")).setBackground(this);    }
    }
}
