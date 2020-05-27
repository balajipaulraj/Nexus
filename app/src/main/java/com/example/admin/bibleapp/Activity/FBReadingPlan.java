package com.example.admin.bibleapp.Activity;


/**
 * Created by Admin on 6/28/2018.
 */

public class FBReadingPlan {
    public String id;
    public String date;
    public String oldread;
    public String psalmread;
    public String newread;
    public String fourthread;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public FBReadingPlan() {
    }

    public FBReadingPlan(String id,String date, String oldread, String psalmread,String newread,String fourthread) {
        this.id = id;
        this.date = date;
        this.oldread = oldread;
        this.psalmread = psalmread;
        this.newread = newread;
        this.fourthread = fourthread;
    }

}