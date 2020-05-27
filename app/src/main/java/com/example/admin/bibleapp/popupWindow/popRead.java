package com.example.admin.bibleapp.popupWindow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.admin.bibleapp.R;

public class popRead extends Activity {
    ImageView ivclose;
    WebView tvAbout;

    class C03161 implements OnClickListener {
        C03161() {
        }

        public void onClick(View v) {
            popRead.this.finish();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_pop_read);
        this.tvAbout = (WebView) findViewById(R.id.tv_intro);
        this.ivclose = (ImageView) findViewById(R.id.iv_close);
        this.ivclose.setOnClickListener(new C03161());
        this.tvAbout.loadDataWithBaseURL("", "<div style='line-height:125%;text-align: justify;><font size='1px'><b>Schedule a daily time and place.</b> You must form a ritualized habit of reading the Bible. If you don’t intentionally schedule Bible reading into your life, the day’s activities will force it out of your life. \n<br><br><b><i>Pick a Bible.</i></b> Find a good translation. I use the English Standard Bible at Church at the Cross and recommend the ESV Study Bible as a great resource for your Bible reading. It is important not to get bogged down in Study Bible Notes in your daily reading, but those notes are very helpful in extended study and when reading through hard to understand passages. In addition, it is helpful to read the introduction to each book in a good Study Bible before reading the book. Doing so gives you context and helps you understand the historical situation in which the author is writing a particular people. Having this context will be key to your understanding of what you are reading. \n<br><br><b><i>Read the Bible prayerfully.</i></b> Pray before you read the Bible that God would give you focus and understanding. Pray while you are reading the Bible, responding to God’s voice in the Scripture with confession, thanksgiving, praise, and petition. Pray after you read, thanking God for his word and asking for his help in applying it. \n<br><br><b><i>Read aloud.</i></b> Sometimes reading out loud will help you stay focused and allow you to engage more of your senses as you read. \n<br><br><b><i>Look for Jesus and the gospel.</i></b> Jesus is the point of Scripture. As you read, look for him and for gospel patterns in the text that you might know Christ more. \n<br><br><b><i>Keep a Journal.</i></b> Consider keeping a journal where you write down insights, prayers, aspirations, or prayers in response to the section of Scripture you are reading. \n<br><br><b><i>Obey and share what you read.</i></b> The goal is not information, but transformation. Respond to God’s word in trust and obedience. Share with others how God is speaking to you in the Scripture and how it is shaping you. \n<br><br><b><i>Read it with a friend.</i></b> Have other people in your church follow this plan with you. Consider meeting weekly to share what God is saying to you and how you are seeking to apply. This will add accountability and encourage you as you hear how God is speaking to others. \n<br><br><b><i>Don’t give up.</i></b> We are all in different places when it comes to Bible reading. Some people are just beginning their journey and reading multiple chapters a day can be overwhelming and intimidating. Sometimes we find ourselves in seasons where the demands for our time make reading more challenging. If you miss a reading, don’t give up. The CATC plan includes four days a month to catch up and reflect on what you are reading. If you get far behind, don’t worry about catching up or making up the reading. Just start reading again the assigned reading for the day and move forward. \n</font></div><br><hr><br><i><center>“BLESSED IS THE MAN WHO WALKS NOT IN THE COUNSEL OF THE WICKED, NOR STANDS IN THE WAY OF SINNERS, NOR SITS IN THE SEAT OF SCOFFERS; BUT HIS DELIGHT IS IN THE LAW OF THE LORD, AND ON HIS LAW HE MEDITATES DAY AND NIGHT. HE IS LIKE A TREE PLANTED BY STREAMS OF WATER THAT YIELDS ITS FRUIT IN ITS SEASON, AND ITS LEAF DOES NOT WITHER. IN ALL THAT HE DOES, HE PROSPERS.” (PSALM 1:1–3, ESV)</center></i><br><hr>", "text/html", "utf-8", null);
//BlurBehind.getInstance().withAlpha(80).withFilterColor(Color.parseColor("#40000000")).setBackground(this);    }
    }
}
