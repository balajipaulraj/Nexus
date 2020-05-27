package com.example.admin.bibleapp.popupWindow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.bibleapp.R;

public class pop_activity_about extends Activity {
    ImageView ivclose;
    WebView tvAbout;
    TextView tvdaily;

    class C03281 implements OnClickListener {
        C03281() {
        }

        public void onClick(View v) {
            pop_activity_about.this.finish();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.pop_about);
        this.tvAbout = (WebView) findViewById(R.id.tv_intro);
        this.tvAbout.setHorizontalScrollBarEnabled(false);
        this.ivclose = (ImageView) findViewById(R.id.iv_close);
        this.ivclose.setOnClickListener(new C03281());
//BlurBehind.getInstance().withAlpha(80).withFilterColor(Color.parseColor("#40000000")).setBackground(this);        this.tvAbout.loadDataWithBaseURL("file:///android_asset/", "<div style='line-height:125%;text-align: justify;><font size='1px'> <b><br>WHY READ THE BIBLE?</b><br><br>The Bible is God's Word to us. It reveals God to\nus, shows us the way to be saved, brings us into\na greater experience of friendship with God,\nand is a primary means of grace in our spiritual\nformation. This plan is created to assist us in the\nregular reading ofthe Bible that we might grow\nin our relationship with God and with one another as it changes us.<br><br> \n<b>UNIQUE FEATURES </b><br><br>Reading on a Two—Year Cycle. There is no pressure to read the entire Bible in one year.<br><br>Use of Repetition to Strengthen Comprehension. In two years, you will read the New Testament siX times, the Old Testament once, and the Psalms twice. \n<br><br>Sensitivity to the Church Calendar. The Church calendar reflects the storyline of the gospel as does the selection and order of the books in the reading plan.\n<br><br>Inclusion of a Daily Psalm. Each day a Psalm or a portion of a Psalm is included to serve as an aid for morning or evening prayer. \n<br><br>Days of Reflection and Make-up. Every seventh day is a day to catch up and reflect on what you have been reading.\n<br><br><b>PRACTICAL INSTRUCTIONS</b>\nSchedule a dain time and place. You must form a ritualized habit of reading the Bible.\n<br><br>Pick a Bible. We recommend the English Standard Version Study Bible.\n<br><br>Read the Bible prayerfuIIy. Pray for God's help to\nunderstand, enjoy, and apply what you read.\nLook for Jesus. Look for Jesus and for gospel\npatterns in the teXt that you might enjoy Christ\nmore.\n<br><br>Apply what you read. Respond to God's word in\ntrust and obedience.\n<br><br>Read it with a friend. Join others in using the\nplan and share with them what God is showing you.\n<br><br>\nDon't give up. If you get behind, start again at\nthe assigned reading for the day and move\nforward.\n\n</font></div>" + "<br><br>" + " <div style='line-height:125%;text-align: justify;><font size='1px'><b><center>BOOK ABBREVIATIONS</center></b>\n\n<br>GENESIS (GN), EXODUS (EX), LEVITICUS (LV),\nNUMBERS (NU), DEUTERONOMY (DT), JOSHUA\n(JSH), JUDGES (JG), RUTH (RU), 1 SAMUEL (1 SA),\n2 SAMUEL(2 SA), I KINGS (1 KI) , 2 KINGS (2 KI), 1\nCHRONICLES (1 CH), 2 CHRONICLES (2 CH),\nEZRA (EZR), NEHEMIAH (NE), ESTHER (ES), NOB\n(JB), PSALMS (PS), PROVERBS (PR), ECCLESIAS\nTES (EC), SONG OF SOLOMON (SOS), ISAIAH (IS),\nJEREMIAH (JE), LAMENTATIONS (LA), EZEKIEL\n(EZE), DANIEL (DN), I-OSEA (I-O), JOEL, AMOS\n(AM), OBADIAH (OB), JONAH (JON), MICAH\n(MIC), NAHUM (NA), HABAKKUK(HAB), ZEPHANI\nAH (ZP), HAGGAI (HG , ZECHAQIAH (ZC), MALA\nCHI (ML), MATTHEW (MT), MARK (MK), LUKE (-K),\nJOHN (JN), ACTS (AC), ROMANS (RO), 1 CORINTHIANS (1 CO),\n 2 CORINTHIANS (2 CO), GALATIANS (GA),\n EPHESIANS (EPH), PHILIPPIANS(PHP), \nCOLOSSIANS (COL), 1 TIESSALONIANS (1TH),\n 2 THESSALONIANS (2 TH), 1 TIMOTHY (1 TI),\n2 TIMOTHY (2 TI), TITUS, PHILEMON (PHM),\nHEBREWS (HEB), JAMES (JM), I PETER (1 PE), 2\nPETER (2 PE), 1 JOHN (I JN), 2 JOHN (2 JN), 3\nJOHN (3 JN), JUDE(JUDE), REVELATION (RE)</font></div>", "text/html", "utf-8", null);
//BlurBehind.getInstance().withAlpha(80).withFilterColor(Color.parseColor("#40000000")).setBackground(this);    }
    }
}
