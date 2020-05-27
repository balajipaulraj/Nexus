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
import android.widget.Toast;

import com.example.admin.bibleapp.R;

public class PlanSelection1 extends Activity {
    public static final String MyPREFERENCES = "MyPrefs";
    Typeface RobotoSlabLight;
    String bible;
    Button btnSumbit;
    TextView heading;
    ImageView ivclose;
    ProgressDialog pd;
    RadioGroup rgSelectPlan;
    SharedPreferences sharedPreferences;
    String table_plan;

    class C03031 implements OnClickListener {
        C03031() {
        }

        public void onClick(View v) {
            PlanSelection1.this.finish();
        }
    }

    class C03042 implements OnClickListener {
        C03042() {
        }

        public void onClick(View v) {
            Intent intent;
            switch (PlanSelection1.this.rgSelectPlan.getCheckedRadioButtonId()) {
                case R.id.rb_nasb:
  //                  PlanSelection1.this.pd.show();
                    intent = new Intent(PlanSelection1.this.getApplication(), PlanBy.class);
                    PlanSelection1.this.sharedPreferences = PlanSelection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen10 = PlanSelection1.this.sharedPreferences.edit();
                    edit_gen10.putString("tablePlan", "t_nasb");
                    edit_gen10.commit();
                    PlanSelection1.this.startActivity(intent);
                    PlanSelection1.this.finish();
                    return;
                case R.id.rb_30day:
//                    PlanSelection1.this.pd.show();
                    intent = new Intent(PlanSelection1.this.getApplication(), PlanBy.class);
                    PlanSelection1.this.sharedPreferences = PlanSelection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen = PlanSelection1.this.sharedPreferences.edit();
                    edit_gen.putString("tablePlan", "t_30dayPsalm");
                    edit_gen.commit();
                    PlanSelection1.this.startActivity(intent);
                    PlanSelection1.this.finish();
                    return;
                case R.id.rb_90day_kids:
    //                PlanSelection1.this.pd.show();
                    intent = new Intent(PlanSelection1.this.getApplication(), PlanBy.class);
                    PlanSelection1.this.sharedPreferences = PlanSelection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen1 = PlanSelection1.this.sharedPreferences.edit();
                    edit_gen1.putString("tablePlan", "t_90dayKids");
                    edit_gen1.commit();
                    PlanSelection1.this.startActivity(intent);
                    PlanSelection1.this.finish();
                    return;
                case R.id.rb_90day_overview:
      //              PlanSelection1.this.pd.show();
                    intent = new Intent(PlanSelection1.this.getApplication(), PlanBy.class);
                    PlanSelection1.this.sharedPreferences = PlanSelection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen3 = PlanSelection1.this.sharedPreferences.edit();
                    edit_gen3.putString("tablePlan", "t_90dayOverview");
                    edit_gen3.commit();
                    PlanSelection1.this.startActivity(intent);
                    PlanSelection1.this.finish();
                    return;
                case R.id.rb_canbibe:
      //              PlanSelection1.this.pd.show();
                    intent = new Intent(PlanSelection1.this.getApplication(), PlanBy.class);
                    PlanSelection1.this.sharedPreferences = PlanSelection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen5 = PlanSelection1.this.sharedPreferences.edit();
                    edit_gen5.putString("tablePlan", "t_canonical");
                    edit_gen5.commit();
                    PlanSelection1.this.startActivity(intent);
                    PlanSelection1.this.finish();
                    return;
                case R.id.rb_hisbible:
      //              PlanSelection1.this.pd.show();
                    intent = new Intent(PlanSelection1.this.getApplication(), PlanBy.class);
                    PlanSelection1.this.sharedPreferences = PlanSelection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen8 = PlanSelection1.this.sharedPreferences.edit();
                    edit_gen8.putString("tablePlan", "t_historical");
                    edit_gen8.commit();
                    PlanSelection1.this.startActivity(intent);
                    PlanSelection1.this.finish();
                    return;
                case R.id.rb_blendbible:
      //              PlanSelection1.this.pd.show();
                    intent = new Intent(PlanSelection1.this.getApplication(), PlanBy.class);
                    PlanSelection1.this.sharedPreferences = PlanSelection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen4 = PlanSelection1.this.sharedPreferences.edit();
                    edit_gen4.putString("tablePlan", "t_blenbible");
                    edit_gen4.commit();
                    PlanSelection1.this.startActivity(intent);
                    PlanSelection1.this.finish();
                    return;
                case R.id.rb_chrobible:
      //              PlanSelection1.this.pd.show();
                    intent = new Intent(PlanSelection1.this.getApplication(), PlanBy.class);
                    PlanSelection1.this.sharedPreferences = PlanSelection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen6 = PlanSelection1.this.sharedPreferences.edit();
                    edit_gen6.putString("tablePlan", "t_chronological");
                    edit_gen6.commit();
                    PlanSelection1.this.startActivity(intent);
                    PlanSelection1.this.finish();
                    return;
                case R.id.rb_gideons:
     //               PlanSelection1.this.pd.show();
                    intent = new Intent(PlanSelection1.this.getApplication(), PlanBy.class);
                    PlanSelection1.this.sharedPreferences = PlanSelection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen7 = PlanSelection1.this.sharedPreferences.edit();
                    edit_gen7.putString("tablePlan", "t_gideons");
                    edit_gen7.commit();
                    PlanSelection1.this.startActivity(intent);
                    PlanSelection1.this.finish();
                    return;
                case R.id.rb_mchyene:
     //               PlanSelection1.this.pd.show();
                    intent = new Intent(PlanSelection1.this.getApplication(), PlanBy.class);
                    PlanSelection1.this.sharedPreferences = PlanSelection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen9 = PlanSelection1.this.sharedPreferences.edit();
                    edit_gen9.putString("tablePlan", "t_mchyenebible");
                    edit_gen9.commit();
                    PlanSelection1.this.startActivity(intent);
                    PlanSelection1.this.finish();
                    return;
                case R.id.rb_michael:
     //               PlanSelection1.this.pd.show();
                    intent = new Intent(PlanSelection1.this.getApplication(), PlanBy.class);
                    PlanSelection1.this.sharedPreferences = PlanSelection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen14 = PlanSelection1.this.sharedPreferences.edit();
                    edit_gen14.putString("tablePlan", "t_michaelgenre");
                    edit_gen14.commit();
                    PlanSelection1.this.startActivity(intent);
                    PlanSelection1.this.finish();
                    return;
                case R.id.rb_newtestyear:
     //               PlanSelection1.this.pd.show();
                    intent = new Intent(PlanSelection1.this.getApplication(), PlanBy.class);
                    PlanSelection1.this.sharedPreferences = PlanSelection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen11 = PlanSelection1.this.sharedPreferences.edit();
                    edit_gen11.putString("tablePlan", "t_newtestinayear");
                    edit_gen11.commit();
                    PlanSelection1.this.startActivity(intent);
                    PlanSelection1.this.finish();
                    return;
                case R.id.rb_newtestwithpsalm:
     //               PlanSelection1.this.pd.show();
                    intent = new Intent(PlanSelection1.this.getApplication(), PlanBy.class);
                    PlanSelection1.this.sharedPreferences = PlanSelection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen12 = PlanSelection1.this.sharedPreferences.edit();
                    edit_gen12.putString("tablePlan", "t_newtestwithps");
                    edit_gen12.commit();
                    PlanSelection1.this.startActivity(intent);
                    PlanSelection1.this.finish();
                    return;
                case R.id.rb_oneyear:
                    Toast.makeText(PlanSelection1.this.getApplicationContext(), "Plan not available", 1).show();
                    return;
                case R.id.rb_twoyear:
     //               PlanSelection1.this.pd.show();
                    intent = new Intent(PlanSelection1.this.getApplication(), PlanBy.class);
                    PlanSelection1.this.sharedPreferences = PlanSelection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen13 = PlanSelection1.this.sharedPreferences.edit();
                    edit_gen13.putString("tablePlan", "t_twoyear");
                    edit_gen13.commit();
                    PlanSelection1.this.startActivity(intent);
                    PlanSelection1.this.finish();
                    return;
                case R.id.rb_threeyear:
     //               PlanSelection1.this.pd.show();
                    intent = new Intent(PlanSelection1.this.getApplication(), PlanBy.class);
                    PlanSelection1.this.sharedPreferences = PlanSelection1.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen15 = PlanSelection1.this.sharedPreferences.edit();
                    edit_gen15.putString("tablePlan", "t_threeyear");
                    edit_gen15.commit();
                    PlanSelection1.this.startActivity(intent);
                    PlanSelection1.this.finish();
                    return;
                default:
                    return;
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_plan_selection1);
        //this.pd = new ProgressDialog(this, R.style.MyTheme);
//        this.pd.setCancelable(false);
        //    this.pd.setProgressStyle(16973854);
        this.heading = (TextView) findViewById(R.id.heading);
        this.rgSelectPlan = (RadioGroup) findViewById(R.id.rg_plan);
        this.btnSumbit = (Button) findViewById(R.id.btn_select);
        this.ivclose = (ImageView) findViewById(R.id.iv_close);
        this.ivclose.setOnClickListener(new C03031());
        this.btnSumbit.setOnClickListener(new C03042());
//BlurBehind.getInstance().withAlpha(80).withFilterColor(Color.parseColor("#40000000")).setBackground(this);    }
    }
}
