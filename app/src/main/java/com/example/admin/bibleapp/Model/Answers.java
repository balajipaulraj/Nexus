package com.example.admin.bibleapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Answers {

    @SerializedName("qnre_id")
    public String qnre_id;
    @SerializedName("answers")
    public List<Answer> answer = null;

    public class Answer {
        @SerializedName("res_id")
        public String res_id;
        @SerializedName("usr_id")
        public String usr_id;
        @SerializedName("qstn_id")
        public String qstn_id;
        @SerializedName("ans_id")
        public String ans_id;
        @SerializedName("ans_text")
        public String ans_text;

    }
}
