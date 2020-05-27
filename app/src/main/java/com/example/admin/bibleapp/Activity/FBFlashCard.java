package com.example.admin.bibleapp.Activity;

/**
 * Created by Admin on 6/28/2018.
 */
public class FBFlashCard {

    public String date;
    public String verse_id;
    public String verse;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public FBFlashCard() {
    }

    public FBFlashCard(String date, String title, String content) {
        this.date = date;
        this.verse_id = title;
        this.verse = content;

    }

}
