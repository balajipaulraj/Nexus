package com.example.admin.bibleapp;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.bibleapp.Adapter.DescriptionAdapter;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.Model.DescriptionModel;
import com.example.admin.bibleapp.Model.TitleModel;
import com.example.admin.bibleapp.util.DbBitmapUtility;

import java.util.List;

public class NewFragment extends Fragment {
    TextView tvTitle, tvDescription;
    RecyclerView rvDescription;
    ImageView ivPrayerImage, ivDescAdd;
    DescriptionAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    int i = 0;
    int titleid;
    DBhelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_new, container, false);
       titleid =getArguments().getInt("category_id");

        tvTitle = (TextView) rootView.findViewById(R.id.title);
        rvDescription = (RecyclerView) rootView.findViewById(R.id.rv_description);
        ivPrayerImage = (ImageView) rootView.findViewById(R.id.iv_prayer_image);
        ivDescAdd = (ImageView) rootView.findViewById(R.id.iv_add_image);

        dbHelper = new DBhelper(getActivity());
        final List<DescriptionModel> descriptiondata = dbHelper.getDescription(titleid);
        final List<TitleModel> data = dbHelper.getTitle(titleid);

        mLayoutManager = new LinearLayoutManager(getActivity());
        rvDescription.setLayoutManager(this.mLayoutManager);
        mAdapter = new DescriptionAdapter(getActivity(), descriptiondata,this);
        rvDescription.setAdapter(this.mAdapter);

        tvTitle.setText(data.get(0).getTitle());
        Log.e("Image Data from db", data.get(0).getImage()+"");
        if(data.get(0).getImage()!=null) {
            Bitmap im = DbBitmapUtility.getImage(data.get(0).getImage());



                ivPrayerImage.setImageBitmap(im);

        }
        else
        {
            if(tvTitle.getText().toString().equals("The Lords Prayer"))
            {
                ivPrayerImage.setImageDrawable(getResources().getDrawable(R.drawable.cross));
            }
        }
        if(tvTitle.getText().toString().equals("The Lords Prayer"))
        {
            ivDescAdd.setVisibility(View.GONE);
        }

        ivDescAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog1 = new Dialog(getActivity());
                dialog1.setContentView(R.layout.popup_add_description);
                final EditText edtSubject = (EditText) dialog1.findViewById(R.id.edt_description);
                TextView title=(TextView)dialog1.findViewById(R.id.tv_prayer_title);
                title.setText(data.get(0).getTitle());
               // final int title_id=dbHelper.getPrayerId(data.get(i).getTitle());
                ((TextView) dialog1.findViewById(R.id.btn_add)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        String descr = edtSubject.getText().toString();
                        if (descr.equals("")) {
                            Toast.makeText(getContext(), " Description cannot be Empty", Toast.LENGTH_LONG).show();
                        } else {
                            dbHelper.insertDescr(titleid,descr);
                            dialog1.dismiss();
                        }
                    }
                });
                dialog1.show();
            }
        });
        return rootView;
    }
    public void editDesc(int id, String desc)
    {
       dbHelper = new DBhelper(getActivity());
        dbHelper.checkDescription(id,desc);
    }

    public String gettitle()
    {
        return tvTitle.getText().toString();
    }

    public long insert_prayer(String sub,String desc,String date,String time)
    {
        long id = -1;
        dbHelper = new DBhelper(getActivity());
       id =  dbHelper.insertthankfulprayer(sub, desc, date, time);
        return id;
    }
    public void update_desc(String desc)
    {
        dbHelper.updatedesc(titleid,desc);
    }
}
