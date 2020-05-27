package com.example.admin.bibleapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin.bibleapp.R;

import java.io.IOException;

public class AudioViewer extends AppCompatActivity {

    Button buttonStop;
    String AudioURL = "";
    MediaPlayer mediaplayer;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_viewer);
        extras = getIntent().getExtras();
        if (this.extras != null) {
            AudioURL = extras.getString("url");
        }
        buttonStop = (Button) findViewById(R.id.btn_play);
        mediaplayer = new MediaPlayer();
        mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaplayer.isPlaying()) {
                    buttonStop.setText("Play");
                    mediaplayer.stop();
                } else {
                    buttonStop.setText("Stop");
                    try {
                        mediaplayer.setDataSource(AudioURL);
                        mediaplayer.prepare();
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (SecurityException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mediaplayer.start();

                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaplayer.stop();
    }
}
