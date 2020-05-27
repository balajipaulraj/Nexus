package com.example.admin.bibleapp.popupWindow;

/**
 * Created by Admin on 5/15/2018.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.R;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class popLoadBible extends Activity {
    private static final int ACTIVITY_CHOOSE_FILE = 1;
    private static final int PICKFILE_RESULT_CODE = 1;
    public static String T_name = "New";
    public static final String TAG = "Main Activity";
    public static final int REQUEST_CODE = 6384; //ON ACTIVITY RESULT REQUEST
    String path;
    Button submit;
    Button cancel, browse;
    SQLiteDatabase db;
    EditText planname;
    EditText planpath;
    DBhelper dbh;
    Uri datum;
    public static final String COLUMN_ID = "SNo";
    private static final String Fourth = "fourth_testament";
    private static final String FOURTH_READ = "fourth_read";
    private static final String NEW = "new_testament";
    private static final String NEW_READ = "new_read";
    private static final String OLD = "old_testament";
    private static final String OLD_READ = "old_read";
    private static final String PSALM = "PSALM";
    private static final String PSALM_READ = "PSALM_read";
    private static final String DATE = "date";
    private static final String TOTALCOMPLETED = "total_completed";
//    private Context context=getApplicationContext();

    public String getcsvPath(Uri uri) {
        String[] projection = {MediaStore.Files.FileColumns.DATA};
        Cursor cursor = getContentResolver( ).query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);
        cursor.moveToFirst( );
        return cursor.getString(column_index);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {

                datum = data.getData( );
                String fname=getFileName(datum);
                //   if (datum.getLastPathSegment().endsWith("csv")) {
                path = datum.getPath();
                planpath.setText(path);
                // } else {
                //   CommonMethods.ShowMessageBox(CraneTrackActivity.this, "Invalid file type");
                // Toast.makeText(MainActivity.this, "Invalid file type", Toast.LENGTH_LONG).show( );
                //}
            }
        }
    }
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
    public void upload() {
        Long id = null;
        try {
            String yourFilePath = path;
            FileReader file2 = new FileReader(yourFilePath);
            BufferedReader buffer = new BufferedReader(file2);
            String line = "";

            while ((line = buffer.readLine( )) != null) {


                String[] str = line.split(",", 6);
                String cid = str[0].toString( );
                String Name = str[1].toString( );
                String Price2 = str[2].toString( );
                String Price3 = str[3].toString( );

                String Price4 = str[4].toString( );
                String Price5 = str[5].toString( );
//                String Price6 = str[6].toString( );
//                String Price7 = str[7].toString( );
//                String Price8 = str[8].toString( );
//                String Price9 = str[9].toString( );

                dbh = new DBhelper(this);
                id= dbh.insertBible(cid, Name,Price2, Price3, Price4,Price5);

            }
            if(id!=-1)
                Toast.makeText(popLoadBible.this, "Inserted", Toast.LENGTH_LONG).show( );
        } catch (FileNotFoundException e) {
            e.printStackTrace( );
            Toast.makeText(popLoadBible.this, "File Not Found", Toast.LENGTH_LONG).show( );
        } catch (IOException e)

        {
            Dialog d = new Dialog(this);
            d.setTitle(e.getMessage( ).toString( ) + "first");
            d.show( );
        }
    }


    public void openFolder() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        try {

            startActivityForResult(intent, 1);

        } catch (ActivityNotFoundException e) {

            planpath.setText("No app found for importing the file.");

        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_load_bible);
        submit = (Button) findViewById(R.id.btn_submit);
        cancel = (Button) findViewById(R.id.btn_cancel);
        browse = (Button) findViewById(R.id.btn_browse);
        planname = (EditText) findViewById(R.id.plan_name);
        planpath = (EditText) findViewById(R.id.plan_path);
//BlurBehind.getInstance().withAlpha(80).withFilterColor(Color.parseColor("#40000000")).setBackground(this);
 submit.setOnClickListener(new View.OnClickListener( ) {

            @Override
            public void onClick(View view) {
                T_name = planname.getText( ).toString( );
                DBhelper dbh = new DBhelper(getApplicationContext( ));
                db=dbh.getWritableDatabase();
                //dbh.createPlanTable(db,T_name);
                dbh.createBibleTable(db,T_name);
                upload( );
                dbh.close( );
                Toast.makeText(popLoadBible.this, T_name, Toast.LENGTH_LONG).show( );
                finish();
            }

        });

        cancel.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                finish( );
            }
        });
        browse.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                openFolder( );


            }
        });


    }

}


