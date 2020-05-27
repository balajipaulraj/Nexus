package com.example.admin.bibleapp.Activity;


/**
 * Created by Ravi Tamada on 07/10/16.
 * www.androidhive.info
 */

public class VerseofDay {

    public String date;
    public String title;
    public String verse;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public VerseofDay() {
    }

    public VerseofDay(String date, String title,String verse) {
        this.date = date;
        this.title = title;
        this.verse = verse;

    }
}
