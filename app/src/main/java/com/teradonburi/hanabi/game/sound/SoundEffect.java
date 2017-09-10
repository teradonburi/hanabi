package com.teradonburi.hanabi.game.sound;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.support.annotation.RawRes;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/09/07.
 */

public class SoundEffect {

    private final AppCompatActivity activity;

    private SoundPool soundPool;
    private Map<Integer,Integer> soundIds = new HashMap<>();

    @Inject
    public SoundEffect(AppCompatActivity activity){
        this.activity = activity;
    }

    public void load(SoundPool.OnLoadCompleteListener completeListener,@RawRes int... resourceIds){

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                // USAGE_MEDIA
                // USAGE_GAME
                .setUsage(AudioAttributes.USAGE_GAME)
                // CONTENT_TYPE_MUSIC
                // CONTENT_TYPE_SPEECH, etc.
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();

        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                // ストリーム数に応じて
                .setMaxStreams(resourceIds.length)
                .build();

        for(int resourceId:resourceIds){
            int soundId = soundPool.load(activity, resourceId, 1);
            soundIds.put(resourceId,soundId);
        }

        // load が終わったか確認する場合
        soundPool.setOnLoadCompleteListener(completeListener);
    }

    public void play(@RawRes int resourceId){
        // .wav の再生
        // play(ロードしたID, 左音量, 右音量, 優先度, ループ,再生速度)
        play(soundIds.get(resourceId),1.0f,1.0f,1.0f);
    }

    public void play(@RawRes int resourceId,float volumeLeft,float volumeRight,float speed){
        // .wav の再生
        // play(ロードしたID, 左音量, 右音量, 優先度, ループ,再生速度)
        soundPool.play(soundIds.get(resourceId),volumeLeft,volumeRight,0,0,speed);
    }

    public void release(){

        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }

    }

}
