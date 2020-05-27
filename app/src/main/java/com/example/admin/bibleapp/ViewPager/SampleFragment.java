package com.example.admin.bibleapp.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.example.admin.bibleapp.Adapter.FirstPageListview;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.Activity.Notes;
import com.example.admin.bibleapp.Activity.SearchList;
import com.example.admin.bibleapp.util.UiUtil;
import com.example.admin.bibleapp.webviewmarker.TextSelectionSupport;
import com.example.admin.bibleapp.webviewmarker.TextSelectionSupport.SelectionListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SampleFragment extends Fragment {
    String bible;
    Bundle bundle;
    String date = " ";
    DBhelper db;
    Animation fabOpen;
    float fontSize;
    private int mScrollY;
    private String mSelectedText = "";
    private TextSelectionSupport mTextSelectionSupport;
    String oldread = "";
    LinearLayout option;
    Dialog pop_search_word;
    boolean read = false;
    String title = " ";
    TextView tvcopy;
    TextView tvnotes;
    TextView tvsearch;
    TextView tvFlash;
    ArrayList<String> value = new ArrayList( );
    ArrayList<Integer> vid = new ArrayList( );
    WebSettings webSettings;
    WebView wvContent;
    ListView lv_content;
    String[] lv_sample;
    ArrayList<Integer> pos = new ArrayList<>( );
    Integer[] lv_verseid;
    int onclick_flag = 0;
    FirstPageListview adapter1;
    int count = 0;
    ArrayList<String> onclick_value = new ArrayList( );
    View row;


    class C02581 extends WebViewClient {

        class C02571 implements Runnable {
            C02571() {
            }

            public void run() {
                int contentHeight = SampleFragment.this.wvContent.getContentHeight( );
                int viewHeight = SampleFragment.this.wvContent.getHeight( );
            }
        }

        C02581() {
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            new Handler( ).postDelayed(new C02571( ), 500);
        }
    }

    class C02603 implements OnClickListener {
        C02603() {
        }

        public void onClick(View v) {

            String fverse = "";
            for (int s = 0; s < onclick_value.size( ); s++) {
                fverse = fverse + onclick_value.get(s);
            }
            UiUtil.copyToClipboard(getActivity( ), fverse);
            Toast.makeText(getActivity( ), "Copied", Toast.LENGTH_LONG).show( );
            //lv_content.setBackgroundColor(getResources( ).getColor(R.color.white));
        }
    }

    class C02614 implements OnClickListener {
        C02614() {
        }

        public void onClick(View v) {

            String fverse = "";
            for (int s = 0; s < onclick_value.size( ); s++) {
                fverse = fverse + onclick_value.get(s);
            }
            search(fverse);
//            lv_content.setBackgroundColor();
            pos.clear( );
            onclick_value.clear( );
            onclick_flag = 0;
            count = 0;
            option.setVisibility(View.GONE);
        }
    }

    class C02636 implements OnClickListener {
        C02636() {
        }

        public void onClick(View v) {
            pop_search_word.dismiss( );
        }
    }

    class C02669 implements OnClickListener {
        C02669() {
        }

        public void onClick(View v) {
            pop_search_word.dismiss( );
        }
    }

    class C02625 implements OnClickListener {
        C02625() {
        }

        public void onClick(View v) {

            startActivity(new Intent(getActivity( ), Notes.class));
            pos.clear( );
            onclick_value.clear( );
            onclick_flag = 0;
            count = 0;
        }
    }


    class C02624 implements OnClickListener {
        private DBhelper db;

        C02624() {
        }

        public void onClick(View v) {

            String fverse = "";
            this.db = new DBhelper(getActivity( ));
            db.getWritableDatabase( );
            String v_title = title;
            String date = new SimpleDateFormat("dd-MM-yy").format(Calendar.getInstance( ).getTime( ));

            for (int s = 0; s < onclick_value.size( ); s++) {
                fverse = fverse + onclick_value.get(s).toString( );
            }
            long i = db.insertflashcard(fverse, date, v_title);
            if (i != -1) {
                Toast.makeText(getActivity( ), "Added to Memory verse", Toast.LENGTH_LONG).show( );
                pos.clear( );
                onclick_value.clear( );
                onclick_flag = 0;
                count = 0;
                option.setVisibility(View.GONE);
            }

        }
    }


    public class JavaScriptInterface {

        class C02671 implements Runnable {
            C02671() {
            }

            public void run() {
                Toast.makeText(SampleFragment.this.getActivity( ), "Reset Completed", Toast.LENGTH_LONG).show( );
            }
        }

        @JavascriptInterface
        public void didScrollToBottom() {
            new Handler( ).post(new C02671( ));
        }
    }

    class C05032 implements SelectionListener {

        class C02591 implements Runnable {
            C02591() {
            }

            public void run() {
                SampleFragment.this.option.setVisibility(View.VISIBLE);
                SampleFragment.this.option.setAnimation(SampleFragment.this.fabOpen);
            }
        }

        C05032() {
        }

        public void startSelection() {
        }

        public void selectionChanged(String text) {
            SampleFragment.this.mSelectedText = text;
            SampleFragment.this.getActivity( ).runOnUiThread(new C02591( ));
        }

        public void endSelection() {
            View v = new View(SampleFragment.this.getActivity( ));
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_one, container, false);
//        this.wvContent = (WebView) rootView.findViewById(R.id.wv_content);
//        this.wvContent.setHorizontalScrollBarEnabled(false);
        lv_content = (ListView) rootView.findViewById(R.id.lv_content);
        if (VERSION.SDK_INT >= 19) {
//            this.wvContent.setLayerType(2, null);
        } else {
            //       this.wvContent.setLayerType(1, null);
        }
        this.bundle = getArguments( );
        this.db = new DBhelper(getActivity( ), getActivity( ).getFilesDir( ).getAbsolutePath( ));
        SharedPreferences sharedPreferences = getActivity( ).getSharedPreferences("MyPrefs", 0);
        this.date = sharedPreferences.getString("clickdate", null);
        this.bible = sharedPreferences.getString("bible", null);
        String flot_size = sharedPreferences.getString("fontsize", null);
        if (flot_size == null) {
            this.fontSize = 17.0f;
        } else {
            this.fontSize = Float.parseFloat(flot_size);
        }
        this.option = (LinearLayout) rootView.findViewById(R.id.lay_option);
        this.tvcopy = (TextView) rootView.findViewById(R.id.tv_copy);
        this.tvsearch = (TextView) rootView.findViewById(R.id.tv_compare);
        this.tvnotes = (TextView) rootView.findViewById(R.id.tv_mynote);
        this.tvFlash = (TextView) rootView.findViewById(R.id.tv_flashcard);
        this.option.setVisibility(View.GONE);
//        this.fabOpen = AnimationUtils.loadAnimation(getActivity(), C0246R.anim.fab_open);
//        this.wvContent.addJavascriptInterface(new JavaScriptInterface(), "AndroidFunction");
//        this.webSettings = this.wvContent.getSettings();
//        this.wvContent.setWebViewClient(new C02581());
        if (this.bundle != null) {
            this.value = this.bundle.getStringArrayList("frag1");
            this.title = this.bundle.getString("title1", null);
            this.oldread = this.bundle.getString("oldread", null);
        }
        if (this.value.isEmpty( ) == true) {
            lv_sample = new String[1];
            lv_verseid = new Integer[1];
            lv_sample[0] = "No Portion to Display";
            lv_verseid[0] = 0;
            adapter1 = new FirstPageListview(getActivity( ), lv_sample, lv_verseid, fontSize, pos);
            lv_content.setAdapter(adapter1);
//            this.wvContent.loadDataWithBaseURL("", "No portion to display", "text/html", "utf-8", null);

        } else if (value.size( ) == 1) {
            lv_sample = new String[1];
            lv_verseid = new Integer[1];
            lv_sample[0] = "NOT EXIST";
            lv_verseid[0] = 0;
            adapter1 = new FirstPageListview(getActivity( ), lv_sample, lv_verseid, fontSize, pos);
            lv_content.setAdapter(adapter1);
        }
//            vid = this.dbhelper.getMonVerid(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
//            lvsample = FirstPage.this.dbhelper.getMonBookChapVer(FirstPage.this.bible, FirstPage.this.lastSeenBook, FirstPage.this.lastSeenChap);
//            lv_verseid = new Integer[vid.size( )];
//            for (int i = 0; i < lvsample.size( ); i++) {
//                lv_verseid[i] = vid.get(i);
//            }
        else {
            lv_sample = new String[value.size( )];
            for (int i = 0; i < value.size( ); i++) {
                String[] c =value.get(i).split(" ",1);
                if(c[0].equals("Chapter"))
                {
                    lv_sample[i]=value.get(i);
                }
                else {
                    String[] verse = value.get(i).split(".", 4);
                    lv_sample[i] = verse[3];
                }
            }
            lv_verseid = new Integer[value.size( )];
            for (int i = 0; i < value.size( ); i++) {
                String vid;
                String[] c =value.get(i).split(" ",2);
                if(c[0].equals("Chapter"))
                {
                   vid="0";
                }
                else
                {
                    vid = value.get(i).charAt(0) + "" + value.get(i).charAt(1);
                }
                Log.e("verse_id", vid);
                lv_verseid[i] = Integer.parseInt(vid);
            }
            adapter1 = new FirstPageListview(getActivity( ), lv_sample, lv_verseid, fontSize, pos);

            lv_content.setAdapter(adapter1);
        }
//        this.mTextSelectionSupport = TextSelectionSupport.support(getActivity(), this.wvContent);
//        this.mTextSelectionSupport.setSelectionListener(new C05032());
        this.tvcopy.setOnClickListener(new C02603( ));
        this.tvsearch.setOnClickListener(new C02614( ));
        this.tvnotes.setOnClickListener(new C02625( ));
        tvFlash.setOnClickListener(new C02624( ));

        lv_content.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener( ) {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long arg3) {
                if (deselect(position) == true) {
                    adapter1.notifyDataSetChanged( );
                    lv_content.getChildAt(position - lv_content.getFirstVisiblePosition( )).setBackgroundColor(getResources( ).getColor(R.color.white));
                    Log.e("pos", position - lv_content.getFirstVisiblePosition( ) + "");
                    adapter1.notifyDataSetChanged( );
                    count = count - 1;
                    if (count == 0) {
                        option.setVisibility(View.GONE);
                        onclick_flag = 0;

                    }
                    //lv_content.getChildAt(position+lv_content.getLastVisiblePosition()).setBackgroundColor(getResources().getColor(R.color.white));


                } else {
                    String sverse = "" + (position + 1) + "." + adapter.getItemAtPosition(position).toString( );
                    onclick_value.add(sverse);
                    row = view;
                    onclick_flag = 1;
                    pos.add(position);
                    count = count + 1;
                    option.setVisibility(View.VISIBLE);
                    lv_content.getChildAt(position - lv_content.getFirstVisiblePosition( )).setBackgroundColor(getResources( ).getColor(R.color.selectionblue));
                    adapter1.notifyDataSetChanged( );

                }
                return true;
            }
        });

        lv_content.setOnItemClickListener(new AdapterView.OnItemClickListener( ) {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long l) {
                // view=lv_content.getChildAt(position-lv_content.getFirstVisiblePosition());
                //      wv_layout.setVisibility(View.GONE);
                if (onclick_flag == 1 && deselect(position) == false) {
                    String sverse = "" + (position + 1) + "." + adapter.getItemAtPosition(position).toString( );
                    onclick_value.add(sverse);
                    pos.add(position);
                    Log.e("Position", position + "");
                    count = count + 1;
                    option.setVisibility(View.VISIBLE);
                    int j = lv_content.getDisplay( ).getDisplayId( );
                    Log.e("content position", lv_content.getChildAt(position) + "");
////
//                     FirstPageListview adapter1 = new FirstPageListview(FirstPage.this, lv_sample, lv_verseid, fontSize,pos);
                    lv_content.getChildAt(position - lv_content.getFirstVisiblePosition( )).setBackgroundColor(getResources( ).getColor(R.color.selectionblue));
                    adapter1.notifyDataSetChanged( );
//                    lv_content.setAdapter(adapter1);
//                    lv_content.setSelection(view.getId());
//                    lv_content.getPositionForView(view);
                } else if (count == 0) {
                    Toast.makeText(getActivity( ), "Long press to select verse", Toast.LENGTH_LONG).show( );
                } else {
                    Log.e("pos", position - lv_content.getFirstVisiblePosition( ) + "");
                    lv_content.getChildAt(position - lv_content.getFirstVisiblePosition( )).setBackgroundColor(getResources( ).getColor(R.color.white));
                    adapter1.notifyDataSetChanged( );

                    //view.setBackgroundColor(getResources( ).getColor(R.color.white));

                    count = count - 1;
                    if (count == 0) {
                        option.setVisibility(View.GONE);
                        onclick_flag = 0;

                    }
                }

            }
        });
        adapter1.notifyDataSetChanged( );
        return rootView;

    }

    private boolean deselect(int position) {
        boolean b = false;

        for (int i = 0; i < count; i++) {
            if (pos.get(i) == position) {
                pos.remove(i);
                onclick_value.remove(i);
                adapter1.notifyDataSetChanged( );
                b = true;
                return b;
            }
        }
        return b;
    }

    private void search(String mSelectedText) {
        if (this.pop_search_word == null) {
            this.pop_search_word = new Dialog(getActivity( ));
            this.pop_search_word.requestWindowFeature(1);
            this.pop_search_word.setTitle("Search Option");
            this.pop_search_word.setCanceledOnTouchOutside(true);
            this.pop_search_word.setCancelable(true);
            this.pop_search_word.setContentView(R.layout.popup_search);
            final EditText edtSearchWord = (EditText) this.pop_search_word.findViewById(R.id.edt_search_word);
            Button btnSumbit = (Button) this.pop_search_word.findViewById(R.id.btn_submit);
            Button btnCancel = (Button) this.pop_search_word.findViewById(R.id.btn_cancel);
            // ImageView ivclose = (ImageView) this.pop_search_word.findViewById(R.id.iv_close);
            edtSearchWord.setText(mSelectedText);
//            ivclose.setOnClickListener(new SampleFragment.C02636( ));
            btnSumbit.setOnClickListener(new OnClickListener( ) {
                public void onClick(View v) {
                    String searchedWord = edtSearchWord.getText( ).toString( );
                    if (searchedWord == " ") {
                        edtSearchWord.setError("Enter a word to search");
                        return;
                    }
                    Intent i = new Intent(getActivity( ), SearchList.class);
                    i.putExtra("searchword", searchedWord);
                    startActivity(i);
                }
            });
            edtSearchWord.setOnEditorActionListener(new TextView.OnEditorActionListener( ) {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (event.getKeyCode( ) == 66) {
                        String searchedWord = edtSearchWord.getText( ).toString( );
                        if (searchedWord == " ") {
                            edtSearchWord.setError("Enter a word to search");
                        } else {
                            Intent i = new Intent(getActivity( ), SearchList.class);
                            i.putExtra("searchword", searchedWord);
                            startActivity(i);
                        }
                    }
                    return true;
                }
            });
            btnCancel.setOnClickListener(new SampleFragment.C02669( ));
            pop_search_word.show( );
        } else if (this.pop_search_word.isShowing( )) {
            this.pop_search_word.dismiss( );
            this.pop_search_word = null;
            search("");
        } else {
            this.pop_search_word = null;
            search("");
        }
    }


    public void onResume() {
        super.onResume( );
        onclick_flag = 0;
        count = 0;
        onclick_value.clear( );
        pos.clear( );
        if (count == 0 && pos.size( ) == 0) {
            option.setVisibility(View.GONE);
        }
        SharedPreferences sharedPreferences = getActivity( ).getSharedPreferences("MyPrefs", 0);
        String flot_size = sharedPreferences.getString("fontsize", null);
        if (flot_size == null) {
            this.fontSize = 17.0f;
        } else {
            this.fontSize = Float.parseFloat(flot_size);
        }
        this.bible = sharedPreferences.getString("bible", null);
//        this.webSettings.setDefaultFontSize((int) this.fontSize);
        if (this.bundle != null) {
            this.value = this.bundle.getStringArrayList("frag1");
            this.title = this.bundle.getString("title1", null);
            this.oldread = this.bundle.getString("oldread", null);
        }
        ViewCompat.setNestedScrollingEnabled(lv_content, true);
        lv_content.setSelection(0);
        if (this.value.isEmpty( ) == true) {
            lv_sample = new String[1];
            lv_verseid = new Integer[1];
            lv_sample[0] = "No Portion to Display";
            lv_verseid[0] = 0;
            adapter1 = new FirstPageListview(getActivity( ), lv_sample, lv_verseid, fontSize, pos);
            lv_content.setAdapter(adapter1);
            //  this.wvContent.loadDataWithBaseURL("", "No portion to display", "text/html", "utf-8", null);
            return;
        } else if (value.size( ) == 1) {
            lv_sample = new String[1];
            lv_verseid = new Integer[1];
            lv_sample[0] = "NOT EXIST";
            lv_verseid[0] = 0;
            adapter1 = new FirstPageListview(getActivity( ), lv_sample, lv_verseid, fontSize, pos);
            lv_content.setAdapter(adapter1);
        }
//        this.value = "<!DOCTYPE HTML>\n<html>\n<head>\n<meta charset=\"utf-8\"/>\n<title>content</title>\n<link rel=\"stylesheet\" href=\"css/sample.css\"/>\n<script src='jquery-1.8.3.js'></script>\n<script src='jpntext.js'></script>\n<script src='rangy-core.js'></script>\n<script src='rangy-serializer.js'></script>\n<script src='android.selection.js'></script>\n</head>\n<body><div style='line-height:150%;text-align: justify;'>" + this.value + "</div></body>\n" + "</html>";
//        this.wvContent.loadDataWithBaseURL("file:///android_asset/", this.value, "text/html", "utf-8", null);
        else {

            lv_sample = new String[value.size( )];
            for (int i = 0; i < value.size( ); i++) {
                String[] c =value.get(i).split(" ",2);
                if(c[0].equals("Chapter"))
                {
                  lv_sample[i]=value.get(i);
                }
                else {
                    String[] verse = value.get(i).split(".", 4);
                    lv_sample[i] = verse[3];
                }
            }
            pos = new ArrayList( );
            lv_verseid = new Integer[value.size( )];
            for (int i = 0; i < value.size( ); i++) {
                String vid;
                String[] c =value.get(i).split(" ",2);
                if(c[0].equals("Chapter"))
                {
                    vid="0";
                }
                else
                {
                    vid = value.get(i).charAt(0) + "" + value.get(i).charAt(1);
                }
                Log.e("verse_id", vid);
                lv_verseid[i] = Integer.parseInt(vid);
            }
            adapter1 = new FirstPageListview(getActivity( ), lv_sample, lv_verseid, fontSize, pos);

            lv_content.setAdapter(adapter1);
        }
    }
}