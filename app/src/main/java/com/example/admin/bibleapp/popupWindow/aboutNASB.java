package com.example.admin.bibleapp.popupWindow;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.bibleapp.R;

public class aboutNASB extends Activity {
    Typeface RobotoSlabLight;
    TextView abtnasb;
    TextView abtnasb1;
    ImageView ivClose;
    TextView web1;
    TextView web2;

    class C03051 implements OnClickListener {
        C03051() {
        }

        public void onClick(View v) {
            aboutNASB.this.finish();
        }
    }

    class C03062 implements OnClickListener {
        C03062() {
        }

        public void onClick(View v) {
            aboutNASB.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.lockman.org")));
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_about_nasb);
        this.abtnasb = (TextView) findViewById(R.id.aboutnsabcontent1);
        this.abtnasb1 = (TextView) findViewById(R.id.aboutnsabcontent2);
        this.web1 = (TextView) findViewById(R.id.lockwebsite1);
        this.web2 = (TextView) findViewById(R.id.lockwebsite2);
       // this.ivClose = (ImageView) findViewById(R.id.iv_close);
        this.RobotoSlabLight = Typeface.createFromAsset(getAssets(), "fonts/RobotoSlabLight.ttf");
        this.abtnasb.setTypeface(this.RobotoSlabLight);
        this.abtnasb1.setTypeface(this.RobotoSlabLight);
        this.web1.setTypeface(this.RobotoSlabLight);
        this.abtnasb.setText("\"Scripture taken from the NEW AMERICAN STANDARD BIBLE(R), Copyright (C) 1960, 1962, 1963, 1968, 1971, 1972, 1973, 1975, 1977, 1995 by The Lockman Foundation. Used by permission.\"\n\nWhen quotations from the NASB(R) text are used in not-for-sale media, such as church bulletins, orders of service, posters, transparencies or similar media, the abbreviation (NASB) may be used at the end of the quotation.\n\nThis permission to quote is limited to material which is wholly manufactured in compliance with the provisions of the copyright laws of the United States of America.  The Lockman Foundation may terminate this permission at any time.\n\nQuotations and/or reprints in excess of the above limitations, or other permission requests, must be directed to and approved in writing by The Lockman Foundation, PO Box 2279, La Habra, CA 90632-2279 (714)879-3055.\n");
       // this.ivClose.setOnClickListener(new C03051());
        this.web1.setText(Html.fromHtml("<u>http://www.lockman.org\n</u>"));
        this.web1.setOnClickListener(new C03062());
//BlurBehind.getInstance().withAlpha(80).withFilterColor(Color.parseColor("#40000000")).setBackground(this);
    }
}
