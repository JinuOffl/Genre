package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;
import androidx.media3.ui.TimeBar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class PlayerActivity extends AppCompatActivity {
    PlayerView playerView;
    ExoPlayer player;

    String movieUrl;
    //String videoUrl="https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        playerView=findViewById(R.id.playerView);
        movieUrl=getIntent().getStringExtra("video");

        
    }

    @Override
    protected void onStart() {
        super.onStart();
        player= new ExoPlayer.Builder(this).build();
        playerView.setPlayer(player);
        MediaItem mediaItem=MediaItem.fromUri(movieUrl);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
    }

    protected void onStop() {
        super.onStop();
        player.release();
    }
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), HomeFragment.class));
        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }
}