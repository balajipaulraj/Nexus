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

public class PlanSelection extends Activity {
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

    class C03011 implements OnClickListener {
        C03011() {
        }

        public void onClick(View v) {
            PlanSelection.this.finish();
        }
    }

    class C03022 implements OnClickListener {
        C03022() {
        }

        public void onClick(View v) {
            Intent intent;
            switch (PlanSelection.this.rgSelectPlan.getCheckedRadioButtonId()) {
                case R.id.rb_nasb:
                    //PlanSelection.this.pd.show();
                    intent = new Intent(PlanSelection.this.getApplication(), PlanBy.class);
                    PlanSelection.this.sharedPreferences = PlanSelection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen10 = PlanSelection.this.sharedPreferences.edit();
                    edit_gen10.putString("tablePlan", "t_nasb");
                    edit_gen10.commit();
                    PlanSelection.this.startActivity(intent);
                    PlanSelection.this.finish();
                    return;
                case R.id.rb_30day:
                    //PlanSelection.this.pd.show();
                    intent = new Intent(PlanSelection.this.getApplication(), PlanBy.class);
                    PlanSelection.this.sharedPreferences = PlanSelection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen = PlanSelection.this.sharedPreferences.edit();
                    edit_gen.putString("tablePlan", "t_30dayPsalm");
                    edit_gen.commit();
                    PlanSelection.this.startActivity(intent);
                    PlanSelection.this.finish();
                    return;
                case R.id.rb_90day_kids:
                    //PlanSelection.this.pd.show();
                    intent = new Intent(PlanSelection.this.getApplication(), PlanBy.class);
                    PlanSelection.this.sharedPreferences = PlanSelection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen1 = PlanSelection.this.sharedPreferences.edit();
                    edit_gen1.putString("tablePlan", "t_90dayKids");
                    edit_gen1.commit();
                    PlanSelection.this.startActivity(intent);
                    PlanSelection.this.finish();
                    return;
                case R.id.rb_90day_overview:
                    //PlanSelection.this.pd.show();
                    intent = new Intent(PlanSelection.this.getApplication(), PlanBy.class);
                    PlanSelection.this.sharedPreferences = PlanSelection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen3 = PlanSelection.this.sharedPreferences.edit();
                    edit_gen3.putString("tablePlan", "t_90dayOverview");
                    edit_gen3.commit();
                    PlanSelection.this.startActivity(intent);
                    PlanSelection.this.finish();
                    return;
                case R.id.rb_canbibe:
                    //PlanSelection.this.pd.show();
                    intent = new Intent(PlanSelection.this.getApplication(), PlanBy.class);
                    PlanSelection.this.sharedPreferences = PlanSelection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen5 = PlanSelection.this.sharedPreferences.edit();
                    edit_gen5.putString("tablePlan", "t_canonical");
                    edit_gen5.commit();
                    PlanSelection.this.startActivity(intent);
                    PlanSelection.this.finish();
                    return;
                case R.id.rb_hisbible:
                    //PlanSelection.this.pd.show();
                    intent = new Intent(PlanSelection.this.getApplication(), PlanBy.class);
                    PlanSelection.this.sharedPreferences = PlanSelection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen8 = PlanSelection.this.sharedPreferences.edit();
                    edit_gen8.putString("tablePlan", "t_historical");
                    edit_gen8.commit();
                    PlanSelection.this.startActivity(intent);
                    PlanSelection.this.finish();
                    return;
                case R.id.rb_blendbible:
                    //PlanSelection.this.pd.show();
                    intent = new Intent(PlanSelection.this.getApplication(), PlanBy.class);
                    PlanSelection.this.sharedPreferences = PlanSelection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen4 = PlanSelection.this.sharedPreferences.edit();
                    edit_gen4.putString("tablePlan", "t_blenbible");
                    edit_gen4.commit();
                    PlanSelection.this.startActivity(intent);
                    PlanSelection.this.finish();
                    return;
                case R.id.rb_chrobible:
                    //PlanSelection.this.pd.show();
                    intent = new Intent(PlanSelection.this.getApplication(), PlanBy.class);
                    PlanSelection.this.sharedPreferences = PlanSelection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen6 = PlanSelection.this.sharedPreferences.edit();
                    edit_gen6.putString("tablePlan", "t_chronological");
                    edit_gen6.commit();
                    PlanSelection.this.startActivity(intent);
                    PlanSelection.this.finish();
                    return;
                case R.id.rb_gideons:
                    //PlanSelection.this.pd.show();
                    intent = new Intent(PlanSelection.this.getApplication(), PlanBy.class);
                    PlanSelection.this.sharedPreferences = PlanSelection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen7 = PlanSelection.this.sharedPreferences.edit();
                    edit_gen7.putString("tablePlan", "t_gideons");
                    edit_gen7.commit();
                    PlanSelection.this.startActivity(intent);
                    PlanSelection.this.finish();
                    return;
                case R.id.rb_mchyene:
                    //PlanSelection.this.pd.show();
                    intent = new Intent(PlanSelection.this.getApplication(), PlanBy.class);
                    PlanSelection.this.sharedPreferences = PlanSelection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen9 = PlanSelection.this.sharedPreferences.edit();
                    edit_gen9.putString("tablePlan", "t_mchyenebible");
                    edit_gen9.commit();
                    PlanSelection.this.startActivity(intent);
                    PlanSelection.this.finish();
                    return;
                case R.id.rb_michael:
                    //PlanSelection.this.pd.show();
                    intent = new Intent(PlanSelection.this.getApplication(), PlanBy.class);
                    PlanSelection.this.sharedPreferences = PlanSelection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen14 = PlanSelection.this.sharedPreferences.edit();
                    edit_gen14.putString("tablePlan", "t_michaelgenre");
                    edit_gen14.commit();
                    PlanSelection.this.startActivity(intent);
                    PlanSelection.this.finish();
                    return;
                case R.id.rb_newtestyear:
                    //PlanSelection.this.pd.show();
                    intent = new Intent(PlanSelection.this.getApplication(), PlanBy.class);
                    PlanSelection.this.sharedPreferences = PlanSelection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen11 = PlanSelection.this.sharedPreferences.edit();
                    edit_gen11.putString("tablePlan", "t_newtestinayear");
                    edit_gen11.commit();
                    PlanSelection.this.startActivity(intent);
                    PlanSelection.this.finish();
                    return;
                case R.id.rb_newtestwithpsalm:
                    //PlanSelection.this.pd.show();
                    intent = new Intent(PlanSelection.this.getApplication(), PlanBy.class);
                    PlanSelection.this.sharedPreferences = PlanSelection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen12 = PlanSelection.this.sharedPreferences.edit();
                    edit_gen12.putString("tablePlan", "t_newtestwithps");
                    edit_gen12.commit();
                    PlanSelection.this.startActivity(intent);
                    PlanSelection.this.finish();
                    return;
                case R.id.rb_oneyear:
                    Toast.makeText(PlanSelection.this.getApplicationContext(), "Plan not available", 1).show();
                    return;
                case R.id.rb_twoyear:
                    ////PlanSelection.this.pd.show();
                    intent = new Intent(PlanSelection.this.getApplication(), PlanBy.class);
                    PlanSelection.this.sharedPreferences = PlanSelection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen13 = PlanSelection.this.sharedPreferences.edit();
                    edit_gen13.putString("tablePlan", "t_twoyear");
                    edit_gen13.commit();
                    PlanSelection.this.startActivity(intent);
                    PlanSelection.this.finish();
                    return;
                case R.id.rb_threeyear:
                    ////PlanSelection.this.pd.show();
                    intent = new Intent(PlanSelection.this.getApplication(), PlanBy.class);
                    PlanSelection.this.sharedPreferences = PlanSelection.this.getSharedPreferences("MyPrefs", 0);
                    Editor edit_gen15 = PlanSelection.this.sharedPreferences.edit();
                    edit_gen15.putString("tablePlan", "t_threeyear");
                    edit_gen15.commit();
                    PlanSelection.this.startActivity(intent);
                    PlanSelection.this.finish();
                    return;
                default:
                    return;
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_plan_selection);
        // this.pd = new ProgressDialog(this, R.style.MyTheme);
//        this.pd.setCancelable(false);
//        this.pd.setProgressStyle(16973854);
        this.RobotoSlabLight = Typeface.createFromAsset(getAssets(), "fonts/RobotoSlabLight.ttf");
        this.heading = (TextView) findViewById(R.id.heading);
        this.heading.setTypeface(this.RobotoSlabLight);
        this.rgSelectPlan = (RadioGroup) findViewById(R.id.rg_plan);
        this.btnSumbit = (Button) findViewById(R.id.btn_select);
        this.ivclose = (ImageView) findViewById(R.id.iv_close);
        this.ivclose.setOnClickListener(new C03011());
        this.btnSumbit.setOnClickListener(new C03022());
//BlurBehind.getInstance().withAlpha(80).withFilterColor(Color.parseColor("#40000000")).setBackground(this);
    }
}
