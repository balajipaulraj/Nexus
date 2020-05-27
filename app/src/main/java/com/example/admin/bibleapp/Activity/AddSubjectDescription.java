package com.example.admin.bibleapp.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.R;
import com.example.admin.bibleapp.util.DbBitmapUtility;
import com.example.admin.bibleapp.util.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddSubjectDescription extends Activity {
    TextView tvTitle;
    EditText edtDescription, edtImageBrowsePath;
    Button btnBrowse, btnAdd;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    String path="";
    Bitmap bm = null;
    byte[] imagebyte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DBhelper dbHelper = new DBhelper(this);
        setContentView(R.layout.activity_new_subject);
        Intent intent = getIntent();
        final String title = intent.getStringExtra("prayertitle");
        final int id = intent.getIntExtra("prayerid",0);
        tvTitle = (TextView) findViewById(R.id.tv_prayer_title);
        edtDescription = (EditText) findViewById(R.id.edt_description);
        edtImageBrowsePath = (EditText) findViewById(R.id.edt_img_browse_path);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnBrowse = (Button) findViewById(R.id.btn_browse);

        tvTitle.setText(title);

        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbBitmapUtility bitmapImage = new DbBitmapUtility();
                String desc = edtDescription.getText().toString();
                String imgpath = edtImageBrowsePath.getText().toString();
             // = bitmapImage.getBytes(bm);
                if (!(desc.equals(""))) {

                    if(!imgpath.equals(""))
                    {
                        getValues();
                        dbHelper.insertDescrwithimage(id, imagebyte);
                    }
                    dbHelper.insertDescr(id,desc);
                    Toast.makeText(getApplicationContext(), "Added Sccessfully" , Toast.LENGTH_LONG).show();
                    finish();
                }
                else
                {
                    if(desc.equals(""))
                    {
                        edtDescription.setError("Cannot be Empty");
                    }

                    Toast.makeText(getApplicationContext()," Fields are empty", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private byte[] profileImage(Bitmap b){

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 0, bos);
         return bos.toByteArray();

    }

    private void getValues(){
        imagebyte = profileImage(bm);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(AddSubjectDescription.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap photo = null;
        Uri tempUri = null;
        if(data!=null) {
            Log.e("Data values", data.getData()+","+data+","+data.getExtras().get("data"));
         photo = (Bitmap) data.getExtras().get("data");
           tempUri = getImageUri(getApplicationContext(), photo);
            File finalFile = new File(getRealPathFromURI(tempUri));
            path=finalFile.toString();
//            path = data.getData().getPath();
            Log.e("Data path in capture", path);
        }
        Uri choosenImage = tempUri;
        bm=decodeUri(choosenImage, 700);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.e("Data path in button", path);
        edtImageBrowsePath.setText(path);
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
            Uri tempUri = null;
        if (data != null) {
             tempUri = data.getData();
            File finalFile = new File(getRealPathFromURI(tempUri));
            path=finalFile.toString();
//                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
//                 path=data.getData().getPath();
            Log.e("Data path in gallery", path);
        }
        Uri choosenImage = tempUri;
        bm=decodeUri(choosenImage, 700);

        Log.e("Data path in button", path);
        edtImageBrowsePath.setText(path);
    }


    protected Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {

        try {

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

            // The new size we want to scale to
            // final int REQUIRED_SIZE =  size;

            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
