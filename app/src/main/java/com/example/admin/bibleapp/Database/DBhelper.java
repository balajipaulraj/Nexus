package com.example.admin.bibleapp.Database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.admin.bibleapp.Model.ContentModel;
import com.example.admin.bibleapp.Model.DescriptionModel;
import com.example.admin.bibleapp.Model.FirstModel;
import com.example.admin.bibleapp.Model.IncompleteModel;
import com.example.admin.bibleapp.Model.NewBookModel;
import com.example.admin.bibleapp.Model.NotesModel;
import com.example.admin.bibleapp.Model.Prayer;
import com.example.admin.bibleapp.Model.SearchModel;
import com.example.admin.bibleapp.Model.TitleModel;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper {
    private static final String AllData = "All_Data";
    private static final String BIBLE_NAME = "bible_name";
    private static final String BOOK_ID = "Book_id";
    private static final String BOOK_NUMBER = "Book";
    private static final String CHAPTER_NUMBER = "Chapter";
    private static final String CONTENT_DESC = "description";
    private static final String COPIED_CONTENT = "Content";
    private static final String DATABASE_NAME = "bible.db";
    public static final String TABLE_PRAYER = "Prayer";
    public static final String TABLE_PRAYER_DESCRIPTION = "Prayer_description";
    public static final String IMAGE = "image";
    public static final String TITLE = "title";
    public static final String TITLE_ID = "title_id";
    public static final String DESCRIPTION = "description";
    private static final int DATABASE_VERSION = 1;
    //    private static final String DATE = "date";
//    private static final String FOURTH_READ = "fourth_read";
//    private static final String Fourth = "fourth_testament";
    private static final String INITIALDATE = "initial_date";
    private static final String KEY_ID = "key_id";
    private static final String MON_BOOK_ID = "Mon_Book_id";
    //    private static final String NEW = "new_testament";
//    private static final String NEW_READ = "new_read";
    private static final String NO_OF_CHAP = "Chap_numbers";
    private static final String NO_OF_VERSE = "Verse_numbers";
    //    private static final String OLD = "old_testament";
//    private static final String OLD_READ = "old_read";
//    private static final String PSALM = "PSALM";
//    private static final String PSALM_READ = "PSALM_read";
    private static final String TABLE_ALL_DATE1 = "All_Date_Index_1";
    private static final String TABLE_CHAP_NUMBERS = "t_chap_no";
    private static final String TABLE_CONTENT = "t_user_notes";
    private static final String TABLE_INITIAL = "table_initialdate";
    private static final String TAG = "DatabaseHelper";
    private static final String TOTAL_READ_STATUS = "total_completed";
    private static final String VERSE_CONTENT = "verse";
    private static final String VERSE_NUMBER = "Versecount";
    private static final String table_INITIALDATE = "table_initialdate";
    private SQLiteDatabase mData;
    private Context mcon;
    private String pathToSaveDBFile;
    public static String TABLE_NAME = null;
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
    private static final String BNUMBER = "b_number";
    private static final String BNAME = "b_name";
    private static final String CNUMBER = "c_number";
    private static final String VERSE = "verse";
    private static final String VNUMBER = "v_number";
    private static final String BLANG = "b_lang";
    private static final String VOD = "Verse_of_the_Day";
    private static final String FLASHVERSE = "flash_verse";
    private static final String VERSE_ID = "verse_id";
    private static final String TIME = "time";

    public DBhelper(Context context, String filePath) {
        super(context, DATABASE_NAME, null, 1);
        this.mcon = context;
        this.pathToSaveDBFile = new StringBuffer(filePath).append("/").append(DATABASE_NAME).toString();
    }

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mcon = context;
    }

    public void prepareDatabase() throws IOException {
        if (!checkDataBase()) {
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    private boolean checkDataBase() {
        boolean checkDB = false;
        try {
            checkDB = new File(this.pathToSaveDBFile).exists();
        } catch (SQLiteException e) {
            Log.d(TAG, e.getMessage());
        }
        return checkDB;
    }

    private void copyDataBase() throws IOException {
        OutputStream os = new FileOutputStream(this.pathToSaveDBFile);
        InputStream is = this.mcon.getAssets().open(DATABASE_NAME);
        byte[] buffer = new byte[1024];
        while (true) {
            int length = is.read(buffer);
            if (length > 0) {
                os.write(buffer, 0, length);
            } else {
                is.close();
                os.flush();
                os.close();
                return;
            }
        }
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Prayer (SNo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,category TEXT,title TEXT,image BLOB);");
        db.execSQL("CREATE TABLE Category (SNo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,category TEXT,title TEXT);");
        db.execSQL("CREATE TABLE Prayer_description (SNo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,title_id INTEGER,description TEXT,time TEXT,answered TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS ReadHistorynew1(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DATE + " TEXT," + BOOK_ID + " TEXT," + CHAPTER_NUMBER + " TEXT," + VERSE_ID + " TEXT," + TIME + " TEXT );");
        db.execSQL("CREATE TABLE IF NOT EXISTS FlashCard(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DATE + " TEXT," + FLASHVERSE + " TEXT," + VERSE_ID + " TEXT" + ");");
        db.execSQL("CREATE TABLE " + VOD + " (SNo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,verse TEXT,title TEXT,power TEXT,date TEXT);");
        db.execSQL("CREATE TABLE ThankfulFor (SNo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,subject TEXT,description TEXT,date TEXT,time TEXT);");
        db.execSQL("CREATE TABLE NaviMenu (SNo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,menuId int,langId int,menuname TEXT,pageId int);");
        db.execSQL("CREATE TABLE FooterMenu (SNo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,menuId int,langId int,menuname TEXT,pageId int);");
        db.execSQL("CREATE TABLE SyncTable (SNo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,ID int,name TEXT,lastUpdateDate TEXT);");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public long insertthankfulprayer(String nsubject, String ndescription, String ndate, String ntime) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("subject", nsubject);
        cv.put("description", ndescription);
        cv.put("date", ndate);
        cv.put("time", ntime);
        long id = db.insert("ThankfulFor", null, cv);
        return id;
    }


    public void insertMenuItem(int menuid, int langid, String menuname, int pageid) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("menuId", menuid);
        cv.put("langId", langid);
        cv.put("menuname", menuname);
        cv.put("pageId", pageid);
        db.insert("NaviMenu", null, cv);
    }
 public void insertFooterItem(int menuid, int langid, String menuname, int pageid) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("menuId", menuid);
        cv.put("langId", langid);
        cv.put("menuname", menuname);
        cv.put("pageId", pageid);
        db.insert("FooterMenu", null, cv);
    }

    public ArrayList<String> getThankfulPrayers() {
        ArrayList<String> ThankfulPrayers = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor2 = db.rawQuery("select * from ThankfulFor ORDER BY Sno DESC", null);
        String tpdate = "";
        String tpsubject;
        String tpdesc;
        String time;
        if (cursor2.moveToFirst()) {
            do {
                tpdate = (cursor2.getString(cursor2.getColumnIndex("date")));
                tpsubject = (cursor2.getString(cursor2.getColumnIndex("subject")));
                tpdesc = (cursor2.getString(cursor2.getColumnIndex("description")));
                time = (cursor2.getString(cursor2.getColumnIndex("time")));
                String t_p = tpsubject + ",," + tpdesc + ",," + tpdate + ",," + time + "";
                ThankfulPrayers.add(t_p);

            } while (cursor2.moveToNext());
        }
        db.close();
        return ThankfulPrayers;
    }


    public int getMenuId(int menuid) {
        SQLiteDatabase db = getReadableDatabase();
        String countQuery = "SELECT  * FROM NaviMenu where menuid='" + menuid + "'";
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return 0;
        } else {
            cursor.moveToFirst();
            db.close();
            return cursor.getCount();

        }
    }


    public ArrayList<String> getMenu() {
        ArrayList<String> menu = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor2 = db.rawQuery("select * from NaviMenu ORDER BY SNo ASC", null);
        String tpdate = "";
        if (cursor2.moveToFirst()) {
            do {
                tpdate = (cursor2.getString(cursor2.getColumnIndex("menuname")));
                menu.add(tpdate);

            } while (cursor2.moveToNext());
        }
        db.close();
        return menu;
    }

    public ArrayList<String> getFooterMenu() {
        ArrayList<String> menu = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor2 = db.rawQuery("select * from FooterMenu ORDER BY SNo ASC", null);
        String tpdate = "";
        if (cursor2.moveToFirst()) {
            do {
                tpdate = (cursor2.getString(cursor2.getColumnIndex("menuname")));
                menu.add(tpdate);

            } while (cursor2.moveToNext());
        }
        db.close();
        return menu;
    }

    public void insertPrayer(String title, String category) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, title);
        cv.put("category", category);
        //  cv.put(IMAGE, image);
        long id = db.insert(TABLE_PRAYER, null, cv);
        Log.e("id", String.valueOf(id));
    }

    public void insertCategory(String title, String category) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, title);
        cv.put("category", category);
        //  cv.put(IMAGE, image);
        long id = db.insert("Category", null, cv);
    }

    public long insertvod(String title, String verse, String power, String date) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        long id = -1;

        cv.put("title", title);
        cv.put("verse", verse);
        cv.put("power", power);
        cv.put("date", date);
        //  cv.put(IMAGE, image);
        id = db.insert(VOD, null, cv);


        return id;
    }

    public void voddelete() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from " + VOD);

    }

    public int getPrayerId(String title) {
        SQLiteDatabase db = getReadableDatabase();
        String countQuery = "SELECT  * FROM " + TABLE_PRAYER + " where TITLE='" + title + "'";
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return 0;
        } else {
            cursor.moveToFirst();
            int book = cursor.getInt(cursor.getColumnIndex("SNo"));
            cursor.close();
            db.close();
            return book;
        }
    }

    public ArrayList<Integer> getTitleId(String category) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Integer> id = new ArrayList();
        String countQuery;
        if (category.equals("All")) {
            countQuery = "SELECT  * FROM Prayer ";
        } else {
            countQuery = "SELECT  * FROM " + TABLE_PRAYER + " where category='" + category + "'";
        }
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            id.add(0);
            return id;
        } else {
            if (cursor.moveToFirst()) {
                do {
                    int book = cursor.getInt(cursor.getColumnIndex("SNo"));
                    id.add(book);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return id;
        }
    }

    public ArrayList<String> getvodtitle() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> id = new ArrayList();
        String countQuery;
        countQuery = "SELECT  * FROM " + VOD + "";
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            id.add("");
            return id;
        } else {
            if (cursor.moveToFirst()) {
                do {
                    String date = cursor.getString(cursor.getColumnIndex("title"));
                    id.add(date);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return id;
        }
    }

    public ArrayList<String> getvoddate() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> id = new ArrayList();
        String countQuery;
        countQuery = "SELECT  * FROM " + VOD + "";
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            id.add("");
            return id;
        } else {
            if (cursor.moveToFirst()) {
                do {
                    String date = cursor.getString(cursor.getColumnIndex("date"));
                    id.add(date);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return id;
        }
    }

    public ArrayList<String> getvod(String date) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> id = new ArrayList();
        String countQuery;
        countQuery = "SELECT  * FROM " + VOD + " where date='" + date + "'";
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            id.add("");
            return id;
        } else {
            if (cursor.moveToFirst()) {
                do {
                    String title1 = cursor.getString(cursor.getColumnIndex("title"));
                    String verse = cursor.getString(cursor.getColumnIndex("verse"));
                    String power = cursor.getString(cursor.getColumnIndex("power"));
                    id.add(title1 + ",," + verse + ",," + power);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return id;
        }
    }

    public ArrayList<String> getallvod() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> id = new ArrayList();
        String countQuery;
        countQuery = "SELECT  * FROM " + VOD + " ORDER BY Sno DESC";
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            id.add("");
            return id;
        } else {
            if (cursor.moveToFirst()) {
                do {
                    String title1 = cursor.getString(cursor.getColumnIndex("title"));
                    String verse = cursor.getString(cursor.getColumnIndex("verse"));
                    String date = cursor.getString(cursor.getColumnIndex("date"));
                    id.add(date + ",," + title1 + ",," + verse);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return id;
        }
    }

    public ArrayList<String> getCategory() {
        ArrayList<String> modelList = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor2;
        cursor2 = db.rawQuery("select * from Category", null);
        if (cursor2.getCount() < 1) {
            String model;
            model = "";
            modelList.add(model);
            return modelList;
        } else {
            if (cursor2.moveToFirst()) {
                do {
                    String model;
                    model = (cursor2.getString(cursor2.getColumnIndex("category")));
                    modelList.add(model);
                } while (cursor2.moveToNext());
            }
            db.close();
            return modelList;
        }
    }


    public ArrayList<String> getPrayerCategory() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> category = new ArrayList();
        String countQuery = "SELECT  category FROM  Category ";
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            category.add("");
            return category;
        } else {
            cursor.moveToFirst();
            String cat = cursor.getString(cursor.getColumnIndex("category"));
            category.add(cat);
            cursor.close();
            db.close();
            return category;
        }
    }

    public void insertDescrwithimage(int title_id, byte[] img) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        Log.e("Image byte inserting", img + "");
        cv.put(IMAGE, img);
        db.update(TABLE_PRAYER, cv, "SNo=" + title_id, null);
    }

    public void insertDescr(int title_id, String des) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE_ID, title_id);
        cv.put(DESCRIPTION, des);
        cv.put("answered", "false");
        db.insert(TABLE_PRAYER_DESCRIPTION, null, cv);
    }

    public void replaceDescr(int id, String des) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DESCRIPTION, des);
        db.update(TABLE_PRAYER_DESCRIPTION, cv, "SNo=" + id, null);
    }

    public int getRowCount() {
        SQLiteDatabase db = getReadableDatabase();
        String countQuery = "SELECT  * FROM " + TABLE_PRAYER;
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public List<Prayer> getPrayer(String category) {
        ArrayList<Prayer> modelList = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor2;
        if (category.equals("All")) {
            cursor2 = db.rawQuery("select * from Prayer ", null);

        } else {
            cursor2 = db.rawQuery("select * from Prayer where category='" + category + "'", null);
        }
        if (cursor2.moveToFirst()) {
            do {
                Prayer model = new Prayer();
                String title = cursor2.getString(cursor2.getColumnIndex("title"));
                model.setTitle(title);
                modelList.add(model);
            } while (cursor2.moveToNext());
        }
        db.close();
        return modelList;
    }


    public List<DescriptionModel> getDescription(int title_id) {
        ArrayList<DescriptionModel> modelList = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor2 = db.rawQuery("select * from Prayer_description where title_id='" + title_id + "' and answered = 'false'", null);
        if (cursor2.moveToFirst()) {
            do {
                DescriptionModel model = new DescriptionModel();
                model.setSid(cursor2.getInt(cursor2.getColumnIndex("SNo")));
                model.setDescription(cursor2.getString(cursor2.getColumnIndex("description")));
                String m = cursor2.getString(cursor2.getColumnIndex("answered"));
                modelList.add(model);
            } while (cursor2.moveToNext());
        }
        db.close();
        return modelList;
    }

    public void updatedesc(int title_id, String desc) {

        SQLiteDatabase db = getWritableDatabase();
        //String sql = "UPDATE "+TABLE_PRAYER_DESCRIPTION +" SET  answered = 'true' WHERE title_id = '"+title_id+"'and description='"+desc+"'";
        ContentValues cv = new ContentValues();
        cv.put("answered", "true");
        db.update(TABLE_PRAYER_DESCRIPTION, cv, "title_id= ? and description =?", new String[]{String.valueOf(title_id), desc});
        //  db.rawQuery(sql,null);
        db.close();

    }

    public List<TitleModel> getTitle(int title_id) {
        ArrayList<TitleModel> modelList = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor2 = db.rawQuery("select * from Prayer where SNo='" + title_id + "'", null);
        if (cursor2.moveToFirst()) {
            do {
                TitleModel model = new TitleModel();
                model.setTitle(cursor2.getString(cursor2.getColumnIndex("title")));
                Log.e("Data in db", cursor2.getBlob(cursor2.getColumnIndex("image")) + "");
                model.setImage(cursor2.getBlob(cursor2.getColumnIndex("image")));
                modelList.add(model);
            } while (cursor2.moveToNext());
        }
        db.close();
        return modelList;
    }


    public boolean checkDescription(int SNO, String Descr) {
        Cursor cursor = null;
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM " + TABLE_PRAYER_DESCRIPTION + " WHERE SNo='" + SNO + "'";
        cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            replaceDescr(SNO, Descr);
            return true;
        } else {
            insertDescr(SNO, Descr);
            return false;
        }
    }

    public List<NewBookModel> getOldBook() {
        List<NewBookModel> modelList = new ArrayList();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 1);
        Cursor cursor = db.rawQuery("select * from t_chap_no where key_id<=39", null);
        if (cursor.moveToFirst()) {
            do {
                NewBookModel model = new NewBookModel();
                model.setBook(cursor.getString(cursor.getColumnIndex(BOOK_ID)));
                int count = cursor.getCount();
                modelList.add(model);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return modelList;
    }

    public List<SearchModel> getSearch(String t_name, String word) {
        List<SearchModel> modelList = new ArrayList();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 1);
        Cursor cursor = db.rawQuery("select * from " + t_name + " where " + VERSE_CONTENT + " like '%" + word + "%'", null);
        if (cursor.moveToFirst()) {
            do {
                SearchModel model = new SearchModel();
                model.setBookid(cursor.getString(cursor.getColumnIndex(BOOK_NUMBER)));
                model.setChapterid(cursor.getString(cursor.getColumnIndex(CHAPTER_NUMBER)));
                model.setVerseid(cursor.getString(cursor.getColumnIndex(VERSE_NUMBER)));
                model.setVerse(cursor.getString(cursor.getColumnIndex(VERSE_CONTENT)));
                modelList.add(model);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return modelList;
    }

    public List<FirstModel> getFirstModel(String t_name, String bookid, String chapid) {
        List<FirstModel> modelList = new ArrayList();
        String content = "";
        String query = "select verse from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' and " + CHAPTER_NUMBER + "='" + chapid + "' COLLATE NOCASE";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                FirstModel model = new FirstModel();
                model.setVerse(cursor.getString(cursor.getColumnIndex(VERSE_CONTENT)));
                modelList.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return modelList;
    }

    public List<SearchModel> Copytodb(String t_name, String word) {
        List<SearchModel> modelList = new ArrayList();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 1);
        Cursor cursor = db.rawQuery("select * from " + t_name + " where " + VERSE_CONTENT + " like '%" + word + "%'", null);
        if (cursor.moveToFirst()) {
            do {
                SearchModel model = new SearchModel();
                model.setBookid(cursor.getString(cursor.getColumnIndex(BOOK_NUMBER)));
                model.setChapterid(cursor.getString(cursor.getColumnIndex(CHAPTER_NUMBER)));
                model.setVerseid(cursor.getString(cursor.getColumnIndex(VERSE_NUMBER)));
                model.setVerse(cursor.getString(cursor.getColumnIndex(VERSE_CONTENT)));
                modelList.add(model);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return modelList;
    }


    public List<NewBookModel> getNewBook() {
        List<NewBookModel> modelList = new ArrayList();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 1);
        Cursor cursor = db.rawQuery("select * from t_chap_no where key_id>39", null);
        if (cursor.moveToFirst()) {
            do {
                NewBookModel model = new NewBookModel();
                model.setBook(cursor.getString(cursor.getColumnIndex(BOOK_ID)));
                int count = cursor.getCount();
                modelList.add(model);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return modelList;
    }

    public String getId(String i) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 1);
        Cursor cursor = db.rawQuery("select  key_id from t_chap_no where Book_id='" + i + "'", null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "Not Exist";
        }
        cursor.moveToFirst();
        String book = cursor.getString(cursor.getColumnIndex(KEY_ID));
        cursor.close();
        db.close();
        return book;
    }

    public void open() {
        SQLiteDatabase db = getWritableDatabase();
    }

    public void addNotes(String date, String title, String notes) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        ContentValues cv = new ContentValues();
        cv.put(DATE, date);
        cv.put(COPIED_CONTENT, title);
        cv.put(CONTENT_DESC, notes);
        db.insert(TABLE_CONTENT, null, cv);
    }

    public void addinitialdate(String bible, String initialdate) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        ContentValues cv = new ContentValues();
        cv.put(INITIALDATE, initialdate);
        cv.put(BIBLE_NAME, bible);
        db.insert("table_initialdate", null, cv);
    }

    public String getInitialdate(String plan) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery("select * from table_initialdate where bible_name='" + plan + "'", null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "Not Exist";
        }
        cursor.moveToFirst();
        String date = cursor.getString(cursor.getColumnIndex(INITIALDATE));
        cursor.close();
        db.close();
        return date;
    }

    public boolean getdate(String date) {
        Cursor cursor = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0).rawQuery("select * from t_user_notes where date ='" + date + "' COLLATE NOCASE", null);
        if (cursor.getCount() <= 0) {
            return false;
        }
        cursor.moveToFirst();
        return true;
    }

    public ArrayList<String> getversion() {
        ArrayList<String> bname = new ArrayList<String>();
        String query = "select * from t_bible_version_key";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 1);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            bname.add("");
            return bname;
        } else {
            cursor.moveToFirst();
            do {
                String n = cursor.getString(cursor.getColumnIndex("abbrevation"));
                String m = cursor.getString(cursor.getColumnIndex("Table_name"));
                bname.add(n + ",," + m);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
            return bname;
        }
    }

    public boolean getbookpresent(String book) {
        Cursor cursor = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0).rawQuery("select * from table_initialdate where bible_name ='" + book + "' COLLATE NOCASE", null);
        if (cursor.getCount() <= 0) {
            return false;
        }
        cursor.moveToFirst();
        return true;
    }

    public void updatenote(String date, String title, String notes) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        ContentValues cv = new ContentValues();
        cv.put(DATE, date);
        cv.put(COPIED_CONTENT, title);
        cv.put(CONTENT_DESC, notes);
        db.update(TABLE_CONTENT, cv, "date='" + date + "'", null);
    }

    public void insertdata(String table, String date, String i1, String i2, String i3, String i4) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        ContentValues cv = new ContentValues();
        cv.put(OLD, i1);
        cv.put(PSALM, i2);
        cv.put(NEW, i3);
        cv.put(Fourth, i4);
        db.update(table, cv, "date='" + date + "'", null);
        Log.e("data values", "old_testament,PSALM,new_testament");
    }

    public void insertdatabyid(String table, String id, String i1, String i2, String i3, String i4) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        ContentValues cv = new ContentValues();
        cv.put(OLD, i1);
        cv.put(PSALM, i2);
        cv.put(NEW, i3);
        cv.put(Fourth, i4);
        db.update(table, cv, "date='" + id + "'", null);
        Log.e("data values", "old_testament,PSALM,new_testament");
    }

    public List<NotesModel> getNotes() {
        List<NotesModel> modelList = new ArrayList();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 1);
        Cursor cursor = db.rawQuery("select * from t_user_notes ORDER BY id DESC", null);
        if (cursor.moveToFirst()) {
            do {
                NotesModel model = new NotesModel();
                model.setDate(cursor.getString(cursor.getColumnIndex(DATE)));
                model.setNotes(cursor.getString(cursor.getColumnIndex(CONTENT_DESC)));
                model.setTitle(cursor.getString(cursor.getColumnIndex(COPIED_CONTENT)));
                modelList.add(model);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return modelList;
    }

    public String getInitialdateId(String table, String id) {
        String query = "select id from " + table + " where " + DATE + " ='" + id + "' COLLATE NOCASE";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 1);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            return null;
        }
        cursor.moveToFirst();
        String bookId = cursor.getString(0);
        cursor.close();
        db.close();
        return bookId;
    }

    public List<IncompleteModel> getReadings(String table, String date, String date2) {
        List<IncompleteModel> modelList = new ArrayList();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 1);
        Cursor cursor = db.rawQuery("select * from " + table + " where id" + ">='" + date + "' and id" + "<='" + date2 + "'", null);
        String str_old = "";
        String str_psalm = "";
        String str_new = "";
        String str_fourth = "";
        String str_date = "";
        if (cursor.moveToFirst()) {
            do {
                int psalmread;
                int newread;
                int fourthread;
                IncompleteModel model = new IncompleteModel();
                str_date = cursor.getString(cursor.getColumnIndex(DATE));
                str_old = cursor.getString(cursor.getColumnIndex(OLD));
                str_psalm = cursor.getString(cursor.getColumnIndex(PSALM));
                str_new = cursor.getString(cursor.getColumnIndex(NEW));
                str_fourth = cursor.getString(cursor.getColumnIndex(Fourth));
                int oldread = cursor.getInt(cursor.getColumnIndex(OLD_READ));
                model.setDate(str_date);
                if (oldread == 0) {
                    model.setPortion1(cursor.getString(cursor.getColumnIndex(OLD)));
                    model.setPor1(cursor.getInt(cursor.getColumnIndex(OLD_READ)));
                }
                if (str_psalm.equals("")) {
                    psalmread = 1;
                } else {
                    psalmread = cursor.getInt(cursor.getColumnIndex(PSALM_READ));
                }
                if (psalmread == 0) {
                    model.setPortion2(cursor.getString(cursor.getColumnIndex(PSALM)));
                    model.setPor2(cursor.getInt(cursor.getColumnIndex(PSALM_READ)));
                }
                if (str_new.equals("")) {
                    newread = 1;
                } else {
                    newread = cursor.getInt(cursor.getColumnIndex(NEW_READ));
                }
                if (newread == 0) {
                    model.setPortion3(cursor.getString(cursor.getColumnIndex(NEW)));
                    model.setPor3(cursor.getInt(cursor.getColumnIndex(NEW_READ)));
                }
                if (str_fourth.equals("")) {
                    fourthread = 1;
                } else {
                    fourthread = cursor.getInt(cursor.getColumnIndex(FOURTH_READ));
                }
                if (fourthread == 0) {
                    model.setPortion4(cursor.getString(cursor.getColumnIndex(Fourth)));
                    model.setPor4(cursor.getInt(cursor.getColumnIndex(FOURTH_READ)));
                }
                if (str_psalm.equals("") && str_new.equals("") && str_fourth.equals("") && oldread == 0) {
                    model.setDate(cursor.getString(cursor.getColumnIndex(DATE)));
                    modelList.add(model);
                } else if (str_new.equals("") && str_fourth.equals("") && (oldread == 0 || psalmread == 0)) {
                    model.setDate(cursor.getString(cursor.getColumnIndex(DATE)));
                    modelList.add(model);
                } else if (str_fourth.equals("") && (oldread == 0 || psalmread == 0 || newread == 0)) {
                    model.setDate(cursor.getString(cursor.getColumnIndex(DATE)));
                    modelList.add(model);
                } else if (!(str_old == "" || str_psalm == "" || str_new == "" || str_fourth == "" || (oldread == 1 && psalmread == 1 && newread == 1 && fourthread == 1))) {
                    model.setDate(cursor.getString(cursor.getColumnIndex(DATE)));
                    modelList.add(model);
                }
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return modelList;
    }

    public ArrayList<String> getplan(String plan_name) {
        ArrayList<String> plan = new ArrayList<>();
        String query = "select * from " + plan_name;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 1);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            plan.add("");
            return plan;
        } else {
            cursor.moveToFirst();
            do {
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String oldread = cursor.getString(cursor.getColumnIndex(OLD_READ));
                String newread = cursor.getString(cursor.getColumnIndex(NEW_READ));
                String psalmread = cursor.getString(cursor.getColumnIndex(PSALM_READ));
                String fourthread = cursor.getString(cursor.getColumnIndex(FOURTH_READ));
                String planread = id + ",," + date + ",," + oldread + ",," + psalmread + ",," + newread + ",," + fourthread;
                plan.add(planread);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
            return plan;
        }

    }

    public String getMonBookId(String id) {
        String query = "select key_id from t_chap_no where Mon_Book_id ='" + id + "' COLLATE NOCASE";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 1);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            return null;
        }
        cursor.moveToFirst();
        String bookId = cursor.getString(0);
        cursor.close();
        db.close();
        return bookId;
    }

    public String getContentbydate(String table, String date, String col) {
        String query = "select " + col + " from " + table + " where " + DATE + " ='" + date + "' COLLATE NOCASE";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 1);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            return null;
        }
        cursor.moveToFirst();
        String content = cursor.getString(0);
        cursor.close();
        db.close();
        return content;
    }

    public Integer getContentbyread(String table, String date, String col) {
        String query = "select " + col + " from " + table + " where " + DATE + " ='" + date + "' COLLATE NOCASE";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 1);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            return null;
        }
        cursor.moveToFirst();
        int content = cursor.getInt(0);
        cursor.close();
        db.close();
        return Integer.valueOf(content);
    }

    public Cursor getDateContent2(String table, String id) {
        String query = "select * from " + table + " where id ='" + id + "'";
        Log.e("Query in date", query);
        Cursor cursor = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 1).rawQuery(query, null);
        if (cursor.getCount() < 0) {
            return null;
        }
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor getDateContent1(String table, String date) {
        Cursor cursor = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 1).rawQuery("select * from " + table + " where " + DATE + "='" + date + "'", null);
        if (cursor.getCount() < 0) {
            return null;
        }
        cursor.moveToFirst();
        return cursor;
    }

    public int getNoofChap(String t_name, String BOOK) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery("select  max(CAST(Chapter AS Int)) from " + t_name + " where " + BOOK_NUMBER + "='" + BOOK + "'", null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        int chap = cursor.getInt(0);
        cursor.close();
        db.close();
        return chap;
    }

    public int getLargerNoofChap(String t_name, String BOOK) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery("select  max(CAST(Chapter AS Int)) from " + t_name + " where " + BOOK_NUMBER + "='" + BOOK + "'", null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        int chap = cursor.getInt(0);
        cursor.close();
        db.close();
        return chap;
    }

    public int getNoofVerse(String t_name, String BOOK, String chapid) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery("select  max(CAST(Versecount AS Int)) from " + t_name + " where " + BOOK_NUMBER + "='" + BOOK + "'and " + CHAPTER_NUMBER + "='" + chapid + "'", null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        int verse = cursor.getInt(0);
        cursor.close();
        db.close();
        return verse;
    }

    public int getLargerNoofVerse(String t_name, String BOOK, String chapid) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery("select  max(CAST(Versecount AS Int)) from " + t_name + " where " + BOOK_NUMBER + "='" + BOOK + "'and " + CHAPTER_NUMBER + "='" + chapid + "'", null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        int verse = cursor.getInt(0);
        cursor.close();
        db.close();
        return verse;
    }

    public void close() {
        if (this.mData != null) {
            this.mData.close();
        }
        super.close();
    }

    public Cursor getContentbyId(String t_name, int id) {
        Cursor cursor = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0).rawQuery("select * from " + t_name + " where id<='" + id + "'", null);
        if (cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToFirst();
        return cursor;
    }

    public int getContentId(String t_name, String bookid, String chapid, String verseid) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery("select id from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' and " + CHAPTER_NUMBER + "='" + chapid + "' and " + VERSE_NUMBER + "='" + verseid + "'", null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        int id = cursor.getInt(0);
        cursor.close();
        db.close();
        return id;
    }

    public Cursor getContentfromNotes() {
        Cursor cursor = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0).rawQuery("select * from t_user_notes", null);
        if (cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToFirst();
        return cursor;
    }

    public List<ContentModel> getWhole(String t_name) {
        List<ContentModel> modelList = new ArrayList();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 1);
        Cursor cursor = db.rawQuery("select * from " + t_name, null);
        if (cursor.moveToFirst()) {
            do {
                ContentModel model = new ContentModel();
                model.setVerse(cursor.getString(cursor.getColumnIndex(VERSE_CONTENT)));
                modelList.add(model);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return modelList;
    }

    public Cursor getContent(String t_name, String bookid, String chapid, String verseid) {
        Cursor cursor = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0).rawQuery("select * from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' and " + CHAPTER_NUMBER + "='" + chapid + "' and " + VERSE_NUMBER + "='" + verseid + "'", null);
        if (cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor getBookContent(String t_name, String bookid) {
        Cursor cursor = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0).rawQuery("select * from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "'", null);
        if (cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor getChapterContent(String t_name, String bookid, String chapid) {
        Cursor cursor = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0).rawQuery("select * from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' and " + CHAPTER_NUMBER + "='" + chapid + "'", null);
        if (cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor getVerseContent(String t_name, String bookid, String chapid, String verseid) {
        Cursor cursor = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0).rawQuery("select * from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' and " + CHAPTER_NUMBER + "='" + chapid + "' and " + VERSE_NUMBER + ">='" + verseid + "'", null);
        if (cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToFirst();
        return cursor;
    }

    public ArrayList<String> getContentbyrange(String t_name, int Sid, int Eid) {
        ArrayList<String> content = new ArrayList();
        int i = 1;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery("select * from " + t_name + " where id>='" + Sid + "' and id<='" + Eid + "'", null);
        if (cursor.getCount() <= 0) {
            content.add("NOT EXIST");
            return content;
        }
        cursor.moveToFirst();
        do {
            String verses, versecount, Chapter;
            versecount = cursor.getString(cursor.getColumnIndex(VERSE_NUMBER));
            if (versecount.equals("01") || i == 1) {
                Chapter = "Chapter " + cursor.getString(cursor.getColumnIndex(CHAPTER_NUMBER));
                verses = cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
                content.add(Chapter);
                content.add(verses);
                i = i + 1;
            } else {
                verses = cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
                content.add(verses);
            }
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return content;
    }
// public String getContentbyrange(String t_name, int Sid, int Eid) {
//        String content = "";
//        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
//        Cursor cursor = db.rawQuery("select verse from " + t_name + " where id>='" + Sid + "' and id<='" + Eid + "'", null);
//        if (cursor.getCount() <= 0) {
//            return "NOT EXIST";
//        }
//        cursor.moveToFirst();
//        do {
//            content = content + cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
//        } while (cursor.moveToNext());
//        cursor.close();
//        db.close();
//        return content;
//    }

    //    public String getMonBook(String t_name, String bookid) {
//        String content = "";
//        String query = "select verse from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' COLLATE NOCASE";
//        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
//        Cursor cursor = db.rawQuery(query, null);
//        if (cursor.getCount() <= 0) {
//            return "NOT EXIST";
//        }
//        cursor.moveToFirst();
//        do {
//            content = content + cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
//        } while (cursor.moveToNext());
//        cursor.close();
//        db.close();
//        return content;
//    }
    public ArrayList<String> getMonBook(String t_name, String bookid) {
        ArrayList<String> content = new ArrayList();
        String query = "select Versecount,verse from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' COLLATE NOCASE";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            content.add("NOT EXIST");
            return content;
        }
        cursor.moveToFirst();
        int i = 1;
        do {
            String verse, versecount, Chapter;
            versecount = cursor.getString(cursor.getColumnIndex(VERSE_NUMBER));
            if (versecount.equals("01")) {
                Chapter = "Chapter " + String.valueOf(i);
                i++;
                content.add(Chapter);
                verse = cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
            } else {
                verse = cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
            }
            content.add(verse);
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return content;
    }

    public String getBookName(String bookid) {
        String content = "";
        String query = "select Mon_Full_Book from t_chap_no where key_id='" + bookid + "' COLLATE NOCASE";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        do {
            content = content + cursor.getString(cursor.getColumnIndex("Mon_Full_Book"));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return content;
    }

    public String getBookid(String bookname) {
        String content = "";
        String query = "select key_id from t_chap_no where Mon_Full_Book='" + bookname + "' COLLATE NOCASE";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        do {
            content = content + cursor.getString(cursor.getColumnIndex("key_id"));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return content;
    }

    //    public String getMonBookChap(String t_name, String bookid, String chapid) {
//        String content = "";
//        String query = "select verse from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' and " + CHAPTER_NUMBER + "='" + chapid + "' COLLATE NOCASE";
//        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
//        Cursor cursor = db.rawQuery(query, null);
//        if (cursor.getCount() <= 0) {
//            return "NOT EXIST";
//        }
//        cursor.moveToFirst();
//        do {
//            content = content + cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
//        } while (cursor.moveToNext());
//        cursor.close();
//        db.close();
//        return content;
//    }
    public ArrayList<String> getMonBookChap(String t_name, String bookid, String chapid) {
        ArrayList<String> content = new ArrayList();
        String query = "select * from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' and " + CHAPTER_NUMBER + "='" + chapid + "' COLLATE NOCASE";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            content.add("NOT EXIST");
            return content;
        }
        cursor.moveToFirst();
        do {
            String verse, versecount, Chapter;
            versecount = cursor.getString(cursor.getColumnIndex(VERSE_NUMBER));
            if (versecount.equals("01")) {
                Chapter = "Chapter " + cursor.getString(cursor.getColumnIndex(CHAPTER_NUMBER));
                verse = cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
                content.add(Chapter);
                content.add(verse);
            } else {
                verse = cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
                content.add(verse);
            }
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return content;
    }

    //    public String getMonBookChapstartend(String t_name, String bookid, String chapstart, String chapend) {
//        chapend = String.valueOf(Integer.parseInt(chapend) + 1);
//        if (chapend.equals("1") || chapend.equals("2") || chapend.equals("3") || chapend.equals("4") || chapend.equals("5") || chapend.equals("6") || chapend.equals("7") || chapend.equals("8") || chapend.equals("9")) {
//            chapend = "0" + chapend;
//        }
//        String content = "";
//        String query = "select verse from " + t_name + " where " + CHAPTER_NUMBER + ">='" + chapstart + "' and " + CHAPTER_NUMBER + "<'" + chapend + "' and " + BOOK_NUMBER + "='" + bookid + "' COLLATE NOCASE";
//        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
//        Cursor cursor = db.rawQuery(query, null);
//        if (cursor.getCount() <= 0) {
//            return "NOT EXIST";
//        }
//        cursor.moveToFirst();
//        do {
//            content = content + cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
//        } while (cursor.moveToNext());
//        cursor.close();
//        db.close();
//        return content;
//    }
    public ArrayList<String> getMonBookChapstartend(String t_name, String bookid, String chapstart, String chapend) {
        chapend = String.valueOf(Integer.parseInt(chapend) + 1);
        if (chapend.equals("1") || chapend.equals("2") || chapend.equals("3") || chapend.equals("4") || chapend.equals("5") || chapend.equals("6") || chapend.equals("7") || chapend.equals("8") || chapend.equals("9")) {
            chapend = "0" + chapend;
        }
        ArrayList<String> content = new ArrayList();
        String query = "select * from " + t_name + " where " + CHAPTER_NUMBER + ">='" + chapstart + "' and " + CHAPTER_NUMBER + "<'" + chapend + "' and " + BOOK_NUMBER + "='" + bookid + "' COLLATE NOCASE";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            content.add("NOT EXIST");
            return content;
        }
        cursor.moveToFirst();
        do {
            String verse, versecount, Chapter;
            versecount = cursor.getString(cursor.getColumnIndex(VERSE_NUMBER));
            if (versecount.equals("01")) {
                Chapter = "Chapter " + cursor.getString(cursor.getColumnIndex(CHAPTER_NUMBER));
                verse = cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
                content.add(Chapter);
                content.add(verse);
            } else {
                verse = cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
                content.add(verse);
            }
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return content;
    }

    //    public String getMonBookChapverse(String t_name, String bookid, String chap, String verse) {
//        String content = "";
//        String query = "select verse from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' and " + CHAPTER_NUMBER + "='" + chap + "' and " + VERSE_NUMBER + ">='" + verse + "' COLLATE NOCASE";
//        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
//        Cursor cursor = db.rawQuery(query, null);
//        if (cursor.getCount() <= 0) {
//            return "NOT EXIST";
//        }
//        cursor.moveToFirst();
//        do {
//            content = content + cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
//        } while (cursor.moveToNext());
//        cursor.close();
//        db.close();
//        return content;
//    }
    public ArrayList<String> getMonBookChapverse(String t_name, String bookid, String chap, String verse) {
        ArrayList<String> content = new ArrayList();
        String query = "select * from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' and " + CHAPTER_NUMBER + "='" + chap + "' and " + VERSE_NUMBER + ">='" + verse + "' COLLATE NOCASE";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            content.add("NOT EXIST");
            return content;
        }
        cursor.moveToFirst();
        do {
            String verses, versecount, Chapter;
            versecount = cursor.getString(cursor.getColumnIndex(VERSE_NUMBER));
            if (versecount.equals("01")) {
                Chapter = "Chapter " + cursor.getString(cursor.getColumnIndex(CHAPTER_NUMBER));
                verses = cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
                content.add(Chapter);
                content.add(verses);
            } else {
                verses = cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
                content.add(verses);
            }
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return content;
    }

    public int getMonChapVerseStartId(String t_name, String bookid, String chapstart, String versestart) {
        String content = "";
        String query = "select id from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' and " + CHAPTER_NUMBER + "='" + chapstart + "' and " + VERSE_NUMBER + "='" + versestart + "' COLLATE NOCASE";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            return 0;
        }
        cursor.moveToFirst();
        int id = cursor.getInt(0);
        cursor.close();
        db.close();
        return id;
    }

    public int getMonChapStartId(String t_name, String bookid, String chapstart) {
        String content = "";
        String query = "select id from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' and " + CHAPTER_NUMBER + "='" + chapstart + "' COLLATE NOCASE";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            return 0;
        }
        cursor.moveToFirst();
        int id = cursor.getInt(0);
        cursor.close();
        db.close();
        return id;
    }

    public int getMonChapVerseEndId(String t_name, String bookid, String chapstart, String versestart) {
        String content = "";
        String query = "select id from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' and " + CHAPTER_NUMBER + "='" + chapstart + "' and " + VERSE_NUMBER + "='" + versestart + "' ORDER BY id DESC";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        int id = cursor.getInt(0);
        cursor.close();
        db.close();
        return id;
    }


    public ArrayList<String> getMonBookChapVer(String t_name, String bookid, String chapid) {
        ArrayList<String> content = new ArrayList();
        String[] vsplit;
        String query = "select verse from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' and " + CHAPTER_NUMBER + "='" + chapid + "' COLLATE NOCASE";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            return null;
        }
        cursor.moveToFirst();
        do {
            String verse;
            verse = cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
            vsplit = verse.split(".", 4);
            String v3 = vsplit[3];
            content.add(v3);
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return content;
    }

    public String getallMonBookChapVer(String t_name, String bookid, String chapid, String verseid) {
        String content;
        String[] vsplit;
        String query = "select verse from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' and " + CHAPTER_NUMBER + "='" + chapid + "' and " + VERSE_NUMBER + "='" + verseid + "' COLLATE NOCASE";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            content = "";
            return content;
        }
        cursor.moveToFirst();
        do {
            String verse;
            verse = cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
            vsplit = verse.split(".", 4);
            String v3 = vsplit[3];
            content = v3;
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return content;
    }

    public ArrayList<Integer> getMonVerid(String t_name, String bookid, String chapid) {
        ArrayList<Integer> vid = new ArrayList();
        String[] vsplit;
        String query = "select Versecount from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' and " + CHAPTER_NUMBER + "='" + chapid + "' COLLATE NOCASE";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            return null;
        }
        Log.e("cursor value", cursor.getColumnIndex(VERSE_NUMBER) + "");
        cursor.moveToFirst();
        do {
            String id = cursor.getString(cursor.getColumnIndex(VERSE_NUMBER));
//            vsplit=verse.split(".",3);
//            String v1=vsplit[0];
//            String v2=vsplit[1];
//            String v3=vsplit[2];
            vid.add(Integer.parseInt(id));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return vid;
    }

    public int getMonChapEndId(String t_name, String bookid, String chapstart) {
        String content = "";
        String query = "select id from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' and " + CHAPTER_NUMBER + "='" + chapstart + "' ORDER BY id DESC";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        int id = cursor.getInt(0);
        cursor.close();
        db.close();
        return id;
    }

    public long insertreadhistory(String book, String chap, String verse, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(BOOK_ID, book);
        contentValues.put(CHAPTER_NUMBER, chap);
        contentValues.put(VERSE_ID, verse);
        contentValues.put(TIME, time);
        Long i = db.insert("ReadHistorynew1", null, contentValues);
        return i;

    }

    public List<String> getflashverse() {
        ArrayList<String> flashList = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor2 = db.rawQuery("select " + FLASHVERSE + " from FlashCard", null);
        if (cursor2 != null) {
            if (cursor2.moveToFirst()) {
                do {
                    String model = new String();
                    model = (cursor2.getString(cursor2.getColumnIndex(FLASHVERSE)));
                    flashList.add(model);
                } while (cursor2.moveToNext());
            }
        }
        db.close();
        return flashList;
    }

    public long insertflashcard(String verse, String date, String verseid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, date);
        contentValues.put(FLASHVERSE, verse);
        contentValues.put(VERSE_ID, verseid);
        Long i = db.insert("FlashCard", null, contentValues);
        return i;

    }

    public String[] getflashcard(SQLiteDatabase db, int i) {
        int j = 0;
        String[] flash = new String[1000];
        // SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 1);
        db = this.getReadableDatabase();
        db.query
                (
                        "FlashCard",
                        new String[]{COLUMN_ID, DATE, FLASHVERSE, VERSE_ID},
                        COLUMN_ID + "=" + i,
                        null, null, null, null, null
                );
        String query = "Select " + DATE + "," + VERSE_ID + "," + FLASHVERSE + " from Flashcard where Sno=" + i + "";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            return null;
        } else {
            cursor.moveToFirst();
            do {
                j = j + 1;
                flash[j] = cursor.getString(0);
                flash[j + 1] = cursor.getString(1);
                flash[j + 2] = cursor.getString(2);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
            return flash;

        }
    }

    public ArrayList<String> getReadHistory() {
        ArrayList<String> readhistory = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor2 = db.rawQuery("select * from ReadHistorynew1 ORDER BY Sno DESC", null);
        String rhdate = "";
        String rhdate2;
        String rhbname;
        String rhchapno;
        String rhverseid;
        String time;
        if (cursor2.moveToFirst()) {
            do {
                rhdate2 = rhdate;
                rhdate = (cursor2.getString(cursor2.getColumnIndex(DATE)));
                rhbname = (cursor2.getString(cursor2.getColumnIndex(BOOK_ID)));
                rhchapno = (cursor2.getString(cursor2.getColumnIndex(CHAPTER_NUMBER)));
                rhverseid = (cursor2.getString(cursor2.getColumnIndex(VERSE_ID)));
                time = (cursor2.getString(cursor2.getColumnIndex(TIME)));
                String read = "" + rhbname + " " + rhchapno + ":" + rhverseid + "";
                readhistory.add(read);

            } while (cursor2.moveToNext());
        }
        db.close();
        return readhistory;
    }

    public ArrayList<String> getReadHistoryDate() {
        ArrayList<String> readhistory = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor2 = db.rawQuery("select date from ReadHistorynew1 ORDER BY Sno DESC", null);
        String rhdate = "";
        if (cursor2.moveToFirst()) {
            do {

                rhdate = (cursor2.getString(cursor2.getColumnIndex(DATE)));
                readhistory.add(rhdate);
            } while (cursor2.moveToNext());
        }
        db.close();
        return readhistory;
    }

    public ArrayList<String> getReadHistoryTime() {
        ArrayList<String> readhistory = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor2 = db.rawQuery("select time from ReadHistorynew1 ORDER BY Sno DESC", null);
        String rhdate = "";
        if (cursor2.moveToFirst()) {
            do {

                rhdate = (cursor2.getString(cursor2.getColumnIndex(TIME)));
                readhistory.add(rhdate);
            } while (cursor2.moveToNext());
        }
        db.close();
        return readhistory;
    }

    public int getTotalComplete1(String table) {
        String query = "select count(total_completed) from " + table + " where " + TOTAL_READ_STATUS + "='4'";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        db.close();
        return count;
    }

    public int getTotalComplete2(String table) {
        String query = "select count(total_completed) from " + table + " where " + TOTAL_READ_STATUS + "='4'";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        db.close();
        return count;
    }

    public int getTotalCount1(String table) {
        String query = "select count(id) from " + table;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        db.close();
        return count;
    }

    public int getTotalCount2(String table) {
        String query = "select count(id) from " + table;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        db.close();
        return count;
    }

    public ArrayList<String> getdateforMakeUp1() {
        ArrayList<String> value = new ArrayList();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery("select date from All_Date_Index_1 where old_testament='REFLECTION | MAKE-UP'", null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        do {
            value.add(cursor.getString(cursor.getColumnIndex(DATE)));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return value;
    }

    public ArrayList<String> getdateforMakeUp2(String table) {
        String query = "select date from " + table + " where " + OLD + "='REFLECTION | MAKE-UP'";
        ArrayList<String> value = new ArrayList();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        do {
            value.add(cursor.getString(cursor.getColumnIndex(DATE)));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return value;
    }

    public ArrayList<String> getTotalOne1() {
        ArrayList<String> value = new ArrayList();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery("select date from All_Date_Index_1 where total_completed='1'", null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        do {
            value.add(cursor.getString(cursor.getColumnIndex(DATE)));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return value;
    }

    public ArrayList<String> getTotalOne2(String table) {
        String query = "select date from " + table + " where " + TOTAL_READ_STATUS + "='1'";
        ArrayList<String> value = new ArrayList();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        do {
            value.add(cursor.getString(cursor.getColumnIndex(DATE)));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return value;
    }

    public ArrayList<String> getTotaltwo1() {
        ArrayList<String> value = new ArrayList();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery("select date from All_Date_Index_1 where total_completed='2'", null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        do {
            value.add(cursor.getString(cursor.getColumnIndex(DATE)));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return value;
    }

    public ArrayList<String> getTotaltwo2(String table) {
        String query = "select date from " + table + " where " + TOTAL_READ_STATUS + "='2'";
        ArrayList<String> value = new ArrayList();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        do {
            value.add(cursor.getString(cursor.getColumnIndex(DATE)));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return value;
    }

    public ArrayList<String> getTotalthree2(String table) {
        String query = "select date from " + table + " where " + TOTAL_READ_STATUS + "='3'";
        ArrayList<String> value = new ArrayList();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        do {
            value.add(cursor.getString(cursor.getColumnIndex(DATE)));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return value;
    }

    public ArrayList<String> getTotalthree1() {
        ArrayList<String> value = new ArrayList();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery("select date from All_Date_Index_1 where total_completed='3'", null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        do {
            value.add(cursor.getString(cursor.getColumnIndex(DATE)));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return value;
    }

    public ArrayList<String> getMonChapVerse(String t_name, String bookid, String chapid, String versestart, String verseend) {
        ArrayList<String> content = new ArrayList();
        int i = 1;
        String query = "select * from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' and " + CHAPTER_NUMBER + "='" + chapid + "' and " + VERSE_NUMBER + ">='" + versestart + "' and " + VERSE_NUMBER + "<='" + verseend + "' COLLATE NOCASE";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            content.add("NOT EXIST");
            return content;
        }
        cursor.moveToFirst();
        do {
            String verse, versecount, Chapter;
            versecount = cursor.getString(cursor.getColumnIndex(VERSE_NUMBER));
            if (versecount.equals("01") || i == 1) {
                Chapter = "Chapter " + cursor.getString(cursor.getColumnIndex(CHAPTER_NUMBER));
                verse = cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
                content.add(Chapter);
                content.add(verse);
                i = i + 1;
            } else {
                verse = cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
                content.add(verse);
            }
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return content;
    }
//public String getMonChapVerse(String t_name, String bookid, String chapid, String versestart, String verseend) {
//        String content = "";
//        String query = "select verse from " + t_name + " where " + BOOK_NUMBER + "='" + bookid + "' and " + CHAPTER_NUMBER + "='" + chapid + "' and " + VERSE_NUMBER + ">='" + versestart + "' and " + VERSE_NUMBER + "<='" + verseend + "' COLLATE NOCASE";
//        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
//        Cursor cursor = db.rawQuery(query, null);
//        if (cursor.getCount() <= 0) {
//            return "NOT EXIST";
//        }
//        cursor.moveToFirst();
//        do {
//            content = content + cursor.getString(cursor.getColumnIndex(VERSE_CONTENT));
//        } while (cursor.moveToNext());
//        cursor.close();
//        db.close();
//        return content;
//    }

    public String resetdata1(String table) {
        String query = "UPDATE " + table + " SET " + PSALM_READ + "= 0," + NEW_READ + "=0," + OLD_READ + "=0," + FOURTH_READ + "=0," + TOTAL_READ_STATUS + "=0";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        cursor.close();
        db.close();
        return "EXIST";
    }

    public String resetdata2(String table) {
        String query = "UPDATE " + table + " SET " + PSALM_READ + "= 0," + NEW_READ + "=0," + OLD_READ + "=0," + FOURTH_READ + "=0," + TOTAL_READ_STATUS + "=0";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        cursor.close();
        db.close();
        return "EXIST";
    }

    public int addoldreaddate1(String date, String old, boolean read) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        ContentValues cv = new ContentValues();
        cv.put(DATE, date);
        cv.put(OLD_READ, Boolean.valueOf(read));
        cv.put(OLD, old);
        return db.update(TABLE_ALL_DATE1, cv, "date='" + date + "' and " + OLD + "='" + old + "'", null);
    }

    public int addoldreaddate2(String table, String date, String old, boolean read) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        ContentValues cv = new ContentValues();
        cv.put(DATE, date);
        cv.put(OLD_READ, Boolean.valueOf(read));
        cv.put(OLD, old);
        return db.update(table, cv, "date='" + date + "' and " + OLD + "='" + old + "'", null);
    }

    public int addoldreaddate3(String table, String datediff, String old, boolean read) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        ContentValues cv = new ContentValues();
        cv.put(OLD_READ, Boolean.valueOf(read));
        cv.put(OLD, old);
        return db.update(table, cv, "id='" + datediff + "' and " + OLD + "='" + old + "'", null);
    }

    public void addpsalmreaddate1(String date, String old, boolean read) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        ContentValues cv = new ContentValues();
        cv.put(DATE, date);
        cv.put(PSALM_READ, Boolean.valueOf(read));
        cv.put(PSALM, old);
        db.update(TABLE_ALL_DATE1, cv, "date='" + date + "' and " + PSALM + "='" + old + "'", null);
    }

    public void addpsalmreaddate2(String table, String date, String old, boolean read) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        ContentValues cv = new ContentValues();
        cv.put(DATE, date);
        cv.put(PSALM_READ, Boolean.valueOf(read));
        cv.put(PSALM, old);
        db.update(table, cv, "date='" + date + "' and " + PSALM + "='" + old + "'", null);
    }

    public void addpsalmreaddate3(String table, String date, String old, boolean read) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        ContentValues cv = new ContentValues();
        cv.put(PSALM_READ, Boolean.valueOf(read));
        cv.put(PSALM, old);
        db.update(table, cv, "id='" + date + "' and " + PSALM + "='" + old + "'", null);
    }

    public void addnewreaddate1(String date, String old, boolean read) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        ContentValues cv = new ContentValues();
        cv.put(DATE, date);
        cv.put(NEW_READ, Boolean.valueOf(read));
        cv.put(NEW, old);
        db.update(TABLE_ALL_DATE1, cv, "date='" + date + "' and " + NEW + "='" + old + "'", null);
    }

    public void addnewreaddate2(String table, String date, String old, boolean read) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        ContentValues cv = new ContentValues();
        cv.put(DATE, date);
        cv.put(NEW_READ, Boolean.valueOf(read));
        cv.put(NEW, old);
        db.update(table, cv, "date='" + date + "' and " + NEW + "='" + old + "'", null);
    }

    public void addnewreaddate3(String table, String date, String old, boolean read) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        ContentValues cv = new ContentValues();
        cv.put(NEW_READ, Boolean.valueOf(read));
        cv.put(NEW, old);
        db.update(table, cv, "id='" + date + "' and " + NEW + "='" + old + "'", null);
    }

    public void addfourthreaddate2(String table, String date, String old, boolean read) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        ContentValues cv = new ContentValues();
        cv.put(DATE, date);
        cv.put(FOURTH_READ, Boolean.valueOf(read));
        cv.put(Fourth, old);
        db.update(table, cv, "date='" + date + "' and " + Fourth + "='" + old + "'", null);
    }

    public void addfourthreaddate3(String table, String date, String old, boolean read) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        ContentValues cv = new ContentValues();
        cv.put(FOURTH_READ, Boolean.valueOf(read));
        cv.put(Fourth, old);
        db.update(table, cv, "id='" + date + "' and " + Fourth + "='" + old + "'", null);
    }

    public void updatetotal1(int total, String date) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        ContentValues cv = new ContentValues();
        cv.put(DATE, date);
        cv.put(TOTAL_READ_STATUS, Integer.valueOf(total));
        db.update(TABLE_ALL_DATE1, cv, "date='" + date + "'", null);
    }

    public void updatetotal2(String table, int total, String date) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        ContentValues cv = new ContentValues();
        cv.put(DATE, date);
        cv.put(TOTAL_READ_STATUS, Integer.valueOf(total));
        db.update(table, cv, "date='" + date + "'", null);
    }

    public void updatetotal3(String table, int total, String datediff) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(this.pathToSaveDBFile, null, 0);
        ContentValues cv = new ContentValues();
        cv.put(TOTAL_READ_STATUS, Integer.valueOf(total));
        db.update(table, cv, "id='" + datediff + "'", null);
    }

    public void createPlanTable(SQLiteDatabase db, String user) {

        db.execSQL("DROP TABLE IF EXISTS " + user);
        db = getWritableDatabase();
        String CREATE_TABLE = "CREATE TABLE " + user + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DATE + " TEXT,"
                + OLD_READ + " TEXT," + OLD + " TEXT," + NEW + " TEXT,"
                + NEW_READ + " TEXT," + PSALM + " TEXT," + PSALM_READ + " TEXT," + Fourth + " TEXT," + FOURTH_READ + " TEXT," +
                TOTALCOMPLETED + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
        TABLE_NAME = user;
    }

    public void createBibleTable(SQLiteDatabase db, String user) {

        db.execSQL("DROP TABLE IF EXISTS " + user);
        db = getWritableDatabase();
        String CREATE_TABLE = "CREATE TABLE " + user + "("
                + BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + BLANG + " TEXT," + BOOK_NUMBER + " TEXT,"
                + BNAME + " TEXT," + CHAPTER_NUMBER + " TEXT," + VERSE_NUMBER + " TEXT,"
                + VERSE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
        TABLE_NAME = user;
    }

    public long insertplan(String cid, String Name, String Price2, String Price3, String Price4, String Price5, String Price6,
                           String Price7, String Price8, String Price9) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, cid);
        contentValues.put(DATE, Name);
        contentValues.put(OLD, Price2);
        contentValues.put(OLD_READ, Price3);
        contentValues.put(NEW, Price4);
        contentValues.put(NEW_READ, Price5);
        contentValues.put(PSALM, Price6);
        contentValues.put(PSALM_READ, Price7);
        contentValues.put(Fourth, Price8);
        contentValues.put(FOURTH_READ, Price9);
        contentValues.put(TOTALCOMPLETED, Price2);
        long id = db.insert(TABLE_NAME, null, contentValues);

        // insert row
        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public long insertBible(String blang, String bnumber, String bookname, String cnumber, String vnumber, String verse) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them

        contentValues.put(BLANG, blang);
        contentValues.put(BOOK_NUMBER, bnumber);
        contentValues.put(BNAME, bookname);
        contentValues.put(CHAPTER_NUMBER, cnumber);
        contentValues.put(VERSE, verse);
        contentValues.put(VERSE_NUMBER, vnumber);

        long id = db.insert(TABLE_NAME, null, contentValues);
        // insert row
        // close db connection
        db.close();
        // return newly inserted row id
        return id;
    }

    public boolean getSyncData(int id, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean status = true;
        try {
            Cursor cursor2 = db.rawQuery("select * from SyncTable where ID='" + id + "'", null);
            cursor2.moveToFirst();
            if (cursor2.getCount() > 0) {
                String tabletime = cursor2.getString(cursor2.getColumnIndex("lastUpdateDate"));
                if (time.equals(tabletime)) {
                    status = true;
                } else {
                    status = false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return status;
    }

    public boolean getSyncId(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean status = false;
        try {
            Cursor cursor2 = db.rawQuery("select * from SyncTable where ID='" + id + "'", null);
            cursor2.moveToFirst();
            if (cursor2.getCount() > 0) {
                status = true;
            } else {
                status = false;
            }
        } catch (Exception e) {
            return false;
        }
        return status;
    }


    public void insertSyncdata(int id, String name, String date) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ID", id);
        cv.put("name", name);
        cv.put("lastUpdateDate", date);
        db.insert("SyncTable", null, cv);
    }


    public void updateSyncdata(int id, String name, String date) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ID", id);
        cv.put("name", name);
        cv.put("lastUpdateDate", date);
        db.insert("SyncTable", null, cv);
        db.update("SyncTable", cv, "id='" + id + "'", null);
    }
}
