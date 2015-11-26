package com.armadillo.team.sunkagame.Drawing;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.armadillo.team.sunkagame.R;

import java.util.HashMap;

public class Sounds {
    private static SoundPool soundPool;
    private static  HashMap<Integer, Integer> soundPoolMap;
    private Context c;
    private static AudioManager mgr;

    public Sounds(Context c) {
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 100);
        mgr = (AudioManager) c.getSystemService(Context.AUDIO_SERVICE);
        soundPoolMap = new HashMap<Integer, Integer>();

        soundPoolMap.put(0, soundPool.load(c, R.raw.push, 1));
        soundPoolMap.put(1, soundPool.load(c, R.raw.blop, 1));
        soundPoolMap.put(2, soundPool.load(c, R.raw.stapler, 1));
    }
    public static void playSound(int sound) {

        int streamVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);

        soundPool.play(soundPoolMap.get(sound), streamVolume, streamVolume, 1, 0, 1f);

    }



}
