package com.example.admin.bibleapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.bibleapp.Model.DescriptionModel;
import com.example.admin.bibleapp.NewFragment;
import com.example.admin.bibleapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Admin on 10-Apr-18.
 */

public class DescriptionAdapter extends RecyclerView.Adapter<DescriptionAdapter.ViewHolder> {

    Context context;
    NewFragment frag;
    List<DescriptionModel> data = new ArrayList<DescriptionModel>();

    public DescriptionAdapter(Context context, List<DescriptionModel> data, NewFragment frag) {
        this.context = context;
        this.data = data;
        this.frag=frag;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_description, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final DescriptionModel prayer = (DescriptionModel) this.data.get(position);
        holder.tv_single_description.setText(prayer.getDescription());
        if(frag.gettitle().equals("The Lords Prayer"))
        {
            holder.iv_edit.setVisibility(View.GONE);
            holder.iv_thank.setVisibility(View.GONE);
        }
        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   holder.tv_single_description.setEnabled(true);

                   holder.iv_edit.setVisibility(View.GONE);
                   holder.iv_save.setVisibility(View.VISIBLE);
            }
        });

        holder.iv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.iv_save.setVisibility(View.GONE);
                holder.iv_edit.setVisibility(View.VISIBLE);
                holder.tv_single_description.setEnabled(false);
                frag.editDesc(prayer.getSid(),holder.tv_single_description.getText().toString());
                Toast.makeText(context, "Updated Successfully", Toast.LENGTH_LONG).show();
            }
        });

        holder.iv_thank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desc=holder.tv_single_description.getText().toString();
                String subject = frag.gettitle();
                String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance( ).getTime( )).toString();
                String time = new SimpleDateFormat("h:mm a").format(Calendar.getInstance( ).getTime( )).toString();
                long id = frag.insert_prayer(subject,desc,date,time);
                frag.update_desc(desc);
                if(id!=-1)
                {
                    Toast.makeText(context, "Added to I'm Thankful for", Toast.LENGTH_LONG).show();
                    holder.iv_thank.setVisibility(View.GONE);
                    holder.iv_thank_done.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_single_description;
        ImageView iv_edit, iv_save , iv_thank , iv_thank_done;

        public ViewHolder(View view) {
            super(view);
            tv_single_description=(TextView) view.findViewById(R.id.tv_single_description);
            iv_edit=(ImageView) view.findViewById(R.id.iv_edit);
            iv_save=(ImageView) view.findViewById(R.id.iv_save);
            iv_thank = (ImageView)view.findViewById(R.id.iv_thank);
            iv_thank_done = (ImageView)view.findViewById(R.id.iv_thank_done);
        }
    }

}
