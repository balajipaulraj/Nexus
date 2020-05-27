package com.example.admin.bibleapp.Activity;


/**
 * Created by Admin on 6/28/2018.
 */

public class FBReadingHistory {

    public String date;
        public String title;
        public String content;

        // Default constructor required for calls to
        // DataSnapshot.getValue(User.class)
        public FBReadingHistory() {
        }

        public FBReadingHistory(String date, String title, String content) {
            this.date = date;
            this.title = title;
            this.content = content;

        }

}