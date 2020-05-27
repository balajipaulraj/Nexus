package com.example.admin.bibleapp.popupWindow;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.bibleapp.R;

public class popFontSize extends Activity {
    public static final String MyPREFERENCES = "MyPrefs";
    float font_size;
    ImageView ivadd;
    ImageView ivclose;
    ImageView ivminus;
    SharedPreferences sharedPreferences;
    TextView tvAbout;
    TextView tvok;
    TextView tvreset;

    class C03111 implements OnClickListener {
        C03111() {
        }

        public void onClick(View v) {
            popFontSize.this.finish();
        }
    }

    class C03122 implements OnClickListener {
        C03122() {
        }

        public void onClick(View v) {
            popFontSize.this.sharedPreferences = popFontSize.this.getSharedPreferences("MyPrefs", 0);
            Editor edit_gen = popFontSize.this.sharedPreferences.edit();
            edit_gen.putString("fontsize", popFontSize.this.font_size + "");
            edit_gen.commit();
            popFontSize.this.finish();
        }
    }

    class C03133 implements OnClickListener {
        C03133() {
        }

        public void onClick(View v) {
            popFontSize.this.tvok.setVisibility(0);
            popFontSize.this.font_size = 17.0f;
            popFontSize.this.tvAbout.setTextSize(popFontSize.this.font_size);
        }
    }

    class C03144 implements OnClickListener {
        C03144() {
        }

        public void onClick(View v) {
            popFontSize.this.tvok.setVisibility(View.VISIBLE);
            if (((double) popFontSize.this.font_size) < 24.0d) {
                popFontSize.this.font_size += 1.0f;
                Log.e("font", popFontSize.this.font_size + "");
                popFontSize.this.tvAbout.setTextSize(popFontSize.this.font_size);
            }
        }
    }

    class C03155 implements OnClickListener {
        C03155() {
        }

        public void onClick(View v) {
            popFontSize.this.tvok.setVisibility(View.VISIBLE);
            if (popFontSize.this.font_size > 10.0f) {
                popFontSize.this.font_size -= 1.0f;
                popFontSize.this.tvAbout.setTextSize(popFontSize.this.font_size);
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_pop_font_size);
        this.tvAbout = (TextView) findViewById(R.id.tv_font_size);
        this.sharedPreferences = getSharedPreferences("MyPrefs", 0);
        String str_flot_size = this.sharedPreferences.getString("fontsize", null);
        if (str_flot_size == null) {
            this.font_size = 17.0f;
        } else {
            this.font_size = Float.parseFloat(str_flot_size);
        }
        this.tvAbout.setTextSize(this.font_size);
        Log.e("font", this.font_size + "");
        this.ivclose = (ImageView) findViewById(R.id.iv_close);
       this.ivclose.setOnClickListener(new C03111());
        this.ivadd = (ImageView) findViewById(R.id.iv_add);
        this.ivminus = (ImageView) findViewById(R.id.iv_minus);
        this.tvreset = (TextView) findViewById(R.id.tv_reset);
        this.tvok = (TextView) findViewById(R.id.tv_ok);
        this.tvok.setOnClickListener(new C03122());
        this.tvreset.setOnClickListener(new C03133());
        Log.e("Button size", this.tvok.getTextSize() + "");
        this.ivadd.setOnClickListener(new C03144());
        this.ivminus.setOnClickListener(new C03155());
//BlurBehind.getInstance().withAlpha(80).withFilterColor(Color.parseColor("#40000000")).setBackground(this);    }
    }
}
