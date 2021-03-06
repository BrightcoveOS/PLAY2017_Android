package com.brightcove.a360videodemo;


import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.brightcove.player.media.DeliveryType;
import com.brightcove.player.model.Video;
import com.brightcove.player.view.BrightcoveExoPlayerVideoView;
import com.brightcove.player.view.BrightcovePlayer;


public class MainActivity extends BrightcovePlayer {

    private final Handler handler = new Handler();
    private View decorView;

    @Override
    @SuppressWarnings("ResourceType")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // When extending the BrightcovePlayer, we must assign the brightcoveVideoView before
        // entering the superclass. This allows for some stock video player lifecycle
        // management.  Establish the video object and use it's event emitter to get important
        // notifications and to control logging.
        setContentView(R.layout.activity_main);
        brightcoveVideoView = (BrightcoveExoPlayerVideoView) findViewById(R.id.brightcove_video_view);
        Video video = Video.createVideo(
                "asset:///sample360.mp4", DeliveryType.MP4, Video.ProjectionFormat.EQUIRECTANGULAR);
        brightcoveVideoView.add(video);

        decorView = getWindow().getDecorView();
    }

    private final Runnable hideSystemUiTask = new Runnable() {
        @Override
        public void run() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            handler.postDelayed(hideSystemUiTask, 500);
        }
    }
}
