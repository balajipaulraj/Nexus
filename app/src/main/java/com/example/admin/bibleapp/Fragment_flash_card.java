

package com.example.admin.bibleapp;

        //import android.app.Fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.admin.bibleapp.Database.DBhelper;

/**
 * Created by Admin on 5/25/2018.
 */

public class Fragment_flash_card extends Fragment {
    TextView verse;
    TextView date;
    TextView verse_id;
    DBhelper db;
    SQLiteDatabase dbh;
    String flash[] = new String[1000];
    int i=1,flag=0;
    ImageButton close;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_flashcard, container, false);
        final int titleid = getArguments( ).getInt("category_id");
        verse=(TextView) rootView.findViewById( R.id.tv_fverse);
        date=(TextView)rootView.findViewById(R.id.tv_fdate);
        verse_id=(TextView)rootView.findViewById(R.id.tv_fverseid);
        close=(ImageButton)rootView.findViewById(R.id.close);
        db=new DBhelper(getActivity());
        dbh=db.getReadableDatabase();
        flash=db.getflashcard(dbh,titleid);
        flag=1;
        db.close();
        date.setText(flash[1]);
        verse_id.setText(flash[2]);
        verse.setText(flash[3]);
        createUser(flash[1], flash[2], flash[3]);
        close.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {

            }
        });
        close.setOnClickListener(new View.OnClickListener( ) {

            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        return rootView;

    }
    private void createUser(String date, String verseid, String verses) {
        // TODO
    }
}
