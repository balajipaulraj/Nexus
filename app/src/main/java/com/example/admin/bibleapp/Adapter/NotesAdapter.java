package com.example.admin.bibleapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.Model.NotesModel;
import com.example.admin.bibleapp.Activity.Notes;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private List<NotesModel> NotesModel;
    private Context con;
    float fontSize;
    private int itemLayout;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView addNotes;
        Button btnEdit;
        Button btnSave;
        TextView date;
        TextView desc;
        EditText edtNotes;
        LinearLayout lay_sub,lay_detail,lay_title;
        TextView title;
        TextView tveditNotes;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            this.date = (TextView) itemView.findViewById(R.id.tv_note_time);
            this.title = (TextView) itemView.findViewById(R.id.tv_title);
            this.edtNotes = (EditText) itemView.findViewById(R.id.edt_edit_note);
            this.lay_sub = (LinearLayout) itemView.findViewById(R.id.lay_edit);
            this.lay_detail = (LinearLayout) itemView.findViewById(R.id.lay_detail);
            this.lay_title = (LinearLayout) itemView.findViewById(R.id.lay_title);
            this.btnEdit = (Button) itemView.findViewById(R.id.btn_select);
            this.btnSave = (Button) itemView.findViewById(R.id.btn_save);
            this.btnSave.setVisibility(View.GONE);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_notes, parent, false));
    }

    public NotesAdapter(List<NotesModel> NotesModel, int itemLayout, Context con) {
        this.NotesModel = NotesModel;
        this.itemLayout = itemLayout;
        this.con = con;
    }

    public void onBindViewHolder(final ViewHolder holder, int position) {
        NotesModel model = (NotesModel) this.NotesModel.get(position);
        holder.date.setText(model.getDate());
        String db_date = model.getDate();
        String flot_size = this.con.getSharedPreferences("MyPrefs", 0).getString("fontsize", null);
        if (flot_size == null) {
            this.fontSize = 17.0f;
        } else {
            this.fontSize = Float.parseFloat(flot_size);
        }
        holder.title.setText(model.getTitle());
        holder.title.setTextSize(this.fontSize);
        holder.edtNotes.setText(model.getNotes());
        holder.edtNotes.setTextSize(this.fontSize);
        if (new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()).equals(db_date)) {
            holder.btnEdit.setVisibility(View.GONE);
        } else {
            holder.btnEdit.setVisibility(View.VISIBLE);
        }
        holder.lay_title.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.lay_detail.getVisibility()==View.VISIBLE)
                {
                    holder.lay_detail.setVisibility(View.GONE);
                }
                else
                {
                    holder.lay_detail.setVisibility(View.VISIBLE);
                }
            }
        });
        holder.btnEdit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                holder.btnEdit.setVisibility(View.GONE);
                holder.btnSave.setVisibility(View.VISIBLE);
                holder.edtNotes.setEnabled(true);
                holder.edtNotes.setFocusable(true);
                holder.edtNotes.setBackgroundResource(R.color.white);
            }
        });
        holder.btnSave.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                holder.btnEdit.setVisibility(View.VISIBLE);
                holder.btnSave.setVisibility(View.GONE);
                ((Notes) NotesAdapter.this.con).updatenotes(holder.date.getText().toString(), holder.title.getText().toString(), holder.edtNotes.getText().toString());
                holder.edtNotes.setBackgroundResource(R.color.notesbg);
                holder.edtNotes.setEnabled(false);
                Toast.makeText(NotesAdapter.this.con, "Updated Successfully", Toast.LENGTH_LONG).show();
            }
        });
    }

    public NotesModel getItem(int position) {
        return (NotesModel) this.NotesModel.get(position);
    }

    public int getItemCount() {
        return this.NotesModel.size();
    }
}
