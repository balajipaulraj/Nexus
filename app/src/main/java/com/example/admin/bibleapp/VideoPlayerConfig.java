package com.example.admin.bibleapp;

public class VideoPlayerConfig {

    //Minimum Video you want to buffer while Playing
    public static final int MIN_BUFFER_DURATION = 3000;
    //Max Video you want to buffer during PlayBack
    public static final int MAX_BUFFER_DURATION = 5000;
    //Min Video you want to buffer before start Playing it
    public static final int MIN_PLAYBACK_START_BUFFER = 1500;
    //Min video You want to buffer when user resumes video
    public static final int MIN_PLAYBACK_RESUME_BUFFER = 5000;

    public static final String DEFAULT_VIDEO_URL2 =
            "http://blueappsoftware.in/layout_design_android_blog.mp4";

    public static final String DEFAULT_Audio_URL2 =
            "https://file-examples.com/wp-content/uploads/2017/11/file_example_MP3_700KB.mp3";


}
