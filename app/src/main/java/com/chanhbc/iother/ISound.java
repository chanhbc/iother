package com.chanhbc.iother;

import android.content.Context;
import android.media.MediaPlayer;

public class ISound {
    private MediaPlayer mediaPlayer;
    private Context context;
    private static ISound instance;

    public ISound(Context context) {
        this.context = context;
    }


    public static ISound getISound(Context context) {
        if (instance == null) {
            instance = new ISound(context);
        }
        return instance;
    }

    public void playSoundRaw(int idResource, boolean isLoop) {
        this.stop();
        mediaPlayer = MediaPlayer.create(context, idResource);
        mediaPlayer.setLooping(isLoop);
        mediaPlayer.start();
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
