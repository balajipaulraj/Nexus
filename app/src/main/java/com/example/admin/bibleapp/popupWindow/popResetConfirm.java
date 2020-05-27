package com.example.admin.bibleapp.popupWindow;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Database.DBhelper;

public class popResetConfirm extends Activity {
    public static final String MyPREFERENCES = "MyPrefs";
    DBhelper dbhelper;
    TextView goBook;
    TextView goChap;
    ImageView ivClose;
    TextView newTestament;
    TextView oldTestament;
    String planId;
    TextView pre;
    SharedPreferences sharedPreferences;
    String table_plan;
    Button tvNo;
    Button tvYes;

    class C03211 implements OnClickListener {
        C03211() {
        }

        public void onClick(View v) {
            popResetConfirm.this.finish();
        }
    }

    class C03222 implements OnClickListener {
        C03222() {
        }

        public void onClick(View v) {
            popResetConfirm.this.finish();
        }
    }

    class C03233 implements OnClickListener {
        C03233() {
        }

        public void onClick(View v) {

            popResetConfirm.this.dbhelper.resetdata2(popResetConfirm.this.table_plan);
            Toast.makeText(popResetConfirm.this.getApplicationContext(), "Reset Completed", 1).show();
            popResetConfirm.this.finish();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_pop_reset_confirm);
        this.dbhelper = new DBhelper(this, getFilesDir().getAbsolutePath());
        this.tvYes = (Button) findViewById(R.id.tv_yes);
        this.tvNo = (Button) findViewById(R.id.tv_no);
       // this.ivClose = (ImageView) findViewById(R.id.iv_close);
        this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
        this.planId = this.sharedPreferences.getString("plan", null);
        this.table_plan = this.sharedPreferences.getString("tablePlan", null);
       // this.ivClose.setOnClickListener(new C03211());
        this.tvNo.setOnClickListener(new C03222());
        this.tvYes.setOnClickListener(new C03233());
//BlurBehind.getInstance().withAlpha(80).withFilterColor(Color.parseColor("#40000000")).setBackground(this);    }
    }
}
