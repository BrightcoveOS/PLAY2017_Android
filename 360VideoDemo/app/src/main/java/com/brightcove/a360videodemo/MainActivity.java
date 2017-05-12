package com.brightcove.a360videodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.media.DeliveryType;
import com.brightcove.player.model.Video;
import com.brightcove.player.view.BrightcoveExoPlayerVideoView;

public class MainActivity extends AppCompatActivity {
    private BrightcoveExoPlayerVideoView videoPlayer;

    @Override
    @SuppressWarnings("ResourceType")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoPlayer = (BrightcoveExoPlayerVideoView) findViewById(R.id.video_player);
        videoPlayer.getEventEmitter().on(EventType.CHANGE_ORIENTATION, new EventListener() {
            @Override
            public void processEvent(Event event) {
                int orientation = event.getIntegerProperty(Event.REQUESTED_ORIENTATION);
                setRequestedOrientation(orientation);
            }
        });

        Video video = Video.createVideo(
                "asset:///sample360.mp4", DeliveryType.MP4, Video.ProjectionFormat.EQUIRECTANGULAR);
        videoPlayer.add(video);
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.getEventEmitter().emit(EventType.ACTIVITY_PAUSED);
    }



    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.getEventEmitter().emit(EventType.ACTIVITY_RESUMED);
    }
}
