package com.brightcove.sample.play2017.playercustomization;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.brightcove.player.edge.Catalog;
import com.brightcove.player.edge.VideoListener;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.mediacontroller.BrightcoveMediaController;
import com.brightcove.player.model.Video;
import com.brightcove.player.view.BaseVideoView;
import com.brightcove.player.view.BrightcoveExoPlayerVideoView;
import com.brightcove.player.view.BrightcovePlayer;

public class CustomPlayer extends BrightcovePlayer {

    //This TTF font is included in the Brightcove SDK.
    public static final String FONT_AWESOME = "fontawesome-webfont.ttf";

    @Override protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.player_main);
        brightcoveVideoView = (BrightcoveExoPlayerVideoView) findViewById(R.id.brightcove_video_view);
        //initMediaController(brightcoveVideoView);
        super.onCreate(savedInstanceState);

        EventEmitter eventEmitter = brightcoveVideoView.getEventEmitter();
        Catalog catalog = new Catalog(eventEmitter, getString(R.string.account), getString(R.string.policy));
        catalog.findVideoByID(getString(R.string.videoId), new VideoListener() {

            // Add the video found to the queue with add().
            // Start playback of the video with start().
            @Override
            public void onVideo(Video video) {
                brightcoveVideoView.add(video);
                brightcoveVideoView.start();
            }
        });
    }

/*    public void initMediaController(final BaseVideoView brightcoveVideoView) {
        if (BrightcoveMediaController.checkTvMode(this)) {
            BrightcoveMediaController brightcoveMediaController = new BrightcoveMediaController(brightcoveVideoView, R.layout.my_tv_media_controller);
            // Use this method to verify if we're running in Android TV
            brightcoveVideoView.setMediaController(brightcoveMediaController);
        }
//        initButtons(brightcoveVideoView);

    }*/

/*    private void initButtons(final BaseVideoView brightcoveVideoView) {
        Button thumbsUp = (Button) brightcoveVideoView.findViewById(R.id.thumbs_up);
        if (thumbsUp != null) {
            // By setting this type face, we can use the symbols(icons) present in the font awesome file.
            Typeface font = Typeface.createFromAsset(this.getAssets(), FONT_AWESOME);
            thumbsUp.setTypeface(font);
            thumbsUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CustomPlayer.this, "LIKED", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }*/

}
