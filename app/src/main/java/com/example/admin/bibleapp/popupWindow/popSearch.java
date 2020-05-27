package com.example.admin.bibleapp.popupWindow;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import androidx.annotation.RequiresApi;

import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Activity.SearchList;

public class popSearch extends Activity {
    Button btnCancel;
    Button btnSumbit;
    EditText edtSearchWord;
    ImageView ivclose;
    TextView searchHeading;
    RelativeLayout layout_MainMenu;

    class C03241 implements OnClickListener {
        C03241() {
        }

        public void onClick(View v) {
            popSearch.this.finish();
        }
    }

    class C03252 implements OnClickListener {
        C03252() {
        }

        public void onClick(View v) {
            String searchedWord = popSearch.this.edtSearchWord.getText().toString();
            if (searchedWord == " ") {
                popSearch.this.edtSearchWord.setError("Enter your Search word");
                return;
            }
            Intent i = new Intent(popSearch.this.getApplicationContext(), SearchList.class);
            i.putExtra("searchword", searchedWord);
            popSearch.this.startActivity(i);
        }
    }

    class C03263 implements OnEditorActionListener {
        C03263() {
        }

        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (event.getKeyCode() == 66) {
                String searchedWord = popSearch.this.edtSearchWord.getText().toString();
                if (searchedWord == " ") {
                    popSearch.this.edtSearchWord.setError("Enter your Search word");
                } else {
                    Intent i = new Intent(popSearch.this.getApplicationContext(), SearchList.class);
                    i.putExtra("searchword", searchedWord);
                    popSearch.this.startActivity(i);
                }
            }
            return true;
        }
    }

    class C03274 implements OnClickListener {
        C03274() {
        }

        public void onClick(View v) {
            popSearch.this.finish();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_pop_search);
        this.edtSearchWord = (EditText) findViewById(R.id.edt_search_word);
        this.btnSumbit = (Button) findViewById(R.id.btn_submit);
        this.btnCancel = (Button) findViewById(R.id.btn_cancel);
//        this.ivclose = (ImageView) findViewById(R.id.iv_close);
        layout_MainMenu = (RelativeLayout) findViewById( R.id.activity_pop_search);
//        layout_MainMenu.getForeground().setAlpha( 220); // dim
        this.searchHeading = (TextView) findViewById(R.id.search_heading);
//        this.ivclose.setOnClickListener(new C03241());
        this.btnSumbit.setOnClickListener(new C03252());
        this.edtSearchWord.setOnEditorActionListener(new C03263());
        this.btnCancel.setOnClickListener(new C03274());
//BlurBehind.getInstance().withAlpha(80).withFilterColor(Color.parseColor("#40000000")).setBackground(this);    }
    }
}
