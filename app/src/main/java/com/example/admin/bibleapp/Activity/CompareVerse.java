package com.example.admin.bibleapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.bibleapp.Adapter.CompareAdapter;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.R;

import java.util.ArrayList;

/**
 * Created by Admin on 6/19/2018.
 */

public class CompareVerse extends AppCompatActivity implements GestureDetector.OnGestureListener {
    ListView lv_compare;
    TextView tv_verseid;
    DBhelper dBhelper;
    RelativeLayout swipefull;
    private GestureDetector productGestureDetector;
    ArrayList<String> version = new ArrayList();
    ArrayList<String> all_verse = new ArrayList();
    String[] t_name;
    String[] v_name;
    String bookId;
    String chapno;
    ArrayList<Integer> vid = new ArrayList();
    ArrayList<String> s_vid = new ArrayList();
    private static final int SWIPE_THRESHOLD = 150;
    private static final int SWIPE_VELOCITY_THRESHOLD = 150;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_compare);
        lv_compare = (ListView)findViewById(R.id.compare_list);
        tv_verseid = (TextView)findViewById(R.id.tv_verseid);
        swipefull = (RelativeLayout)findViewById(R.id.swipefull);
        Intent intent = getIntent();
        bookId = intent.getStringExtra("book_id");
        chapno = intent.getStringExtra("chap_id");
        productGestureDetector = new GestureDetector(CompareVerse.this);

        vid = intent.getIntegerArrayListExtra("vid");
        s_vid = intent.getStringArrayListExtra("s_vid");
        dBhelper = new DBhelper(this, getFilesDir( ).getAbsolutePath( ));
        dBhelper.getReadableDatabase();
        String fullbookname = dBhelper.getBookName(bookId);
        tv_verseid.setText(fullbookname+" "+chapno);
        version = dBhelper.getversion();
        version.remove(4);
        t_name = new String[version.size()];
        v_name = new String[version.size()];
        for(int i=0;i<version.size();i++)
        {
            String[] split = version.get(i).split(",,",2);
            v_name[i] = split[0];
            t_name[i] = split[1];
        }

        for(int i=0;i<version.size();i++)
        {
          all_verse.add(dBhelper.getallMonBookChapVer(t_name[i],bookId,chapno,s_vid.get(0)));
        }
        String[] verse = new String[all_verse.size()];
        for(int i=0;i<all_verse.size();i++)
        {
            verse[i]= s_vid.get(0).toString() +"." +all_verse.get(i);
        }
        CompareAdapter adapter = new CompareAdapter(this,v_name,verse);
        lv_compare.setAdapter(adapter);



    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        super.dispatchTouchEvent(ev);
        return productGestureDetector.onTouchEvent(ev);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;
        try {
            float diffY = e2.getRawY() - e1.getRawY();
            float diffX = e2.getRawX() - e1.getRawX();
            if ((Math.abs(diffX) - Math.abs(diffY)) > SWIPE_THRESHOLD) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        swipeToLeft();
                    } else {
                        swipeToRight();
                    }
                }
            }
        } catch (Exception e) {

        }
        return result;
    }
public void swipeToRight()
{
    if(s_vid.get(0).equals(String.valueOf(vid.size())))
    {
        Toast.makeText(getApplicationContext(),"End of Chapter",Toast.LENGTH_LONG).show();
    }
    else {
        int n = Integer.parseInt(s_vid.get(0)) + 1;
        String s = String.valueOf(n);
        s_vid.clear( );
        if (n == 1 || n == 2 || n == 3 || n == 4 || n == 5 || n == 6 || n == 7 || n == 8 || n == 9 || n == 0) {
            s_vid.add("0" + (s));
        } else {
            s_vid.add("" + (s));
        }
        all_verse.clear( );
        for (int i = 0; i < version.size( ); i++) {
            all_verse.add(dBhelper.getallMonBookChapVer(t_name[i], bookId, chapno, s_vid.get(0)));
        }
        String[] verse = new String[all_verse.size( )];
        for (int i = 0; i < all_verse.size( ); i++) {
            verse[i] = s_vid.get(0).toString( ) + "." + all_verse.get(i);
        }
        CompareAdapter adapter = new CompareAdapter(this, v_name, verse);
        lv_compare.setAdapter(adapter);
    }
}
public void swipeToLeft()
    {
        String check = "01";
        if(check.equals(s_vid.get(0)))
        {
            Toast.makeText(getApplicationContext(),"Nothing to Display",Toast.LENGTH_LONG).show();
        }
    else {


        int n = Integer.parseInt(s_vid.get(0)) - 1;
        String s = String.valueOf(n);
        s_vid.clear( );
        if (n == 1 || n == 2 || n == 3 || n == 4 || n == 5 || n == 6 || n == 7 || n == 8 || n == 9 || n == 0) {
            s_vid.add("0" + (s));
        } else {
            s_vid.add("" + (s));
        }
        all_verse.clear( );
        for (int i = 0; i < version.size( ); i++) {
            all_verse.add(dBhelper.getallMonBookChapVer(t_name[i], bookId, chapno, s_vid.get(0)));
        }
        String[] verse = new String[all_verse.size( )];
        for (int i = 0; i < all_verse.size( ); i++) {
            verse[i] = s_vid.get(0).toString( ) + "." + all_verse.get(i);
        }
        CompareAdapter adapter = new CompareAdapter(this, v_name, verse);
        lv_compare.setAdapter(adapter);
    }
    }
}
