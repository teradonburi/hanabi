package com.teradonburi.hanabi.game.sound;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.RawRes;
import android.support.v7.app.AppCompatActivity;

import com.teradonburi.hanabi.inject.ForApplication;
import com.teradonburi.hanabi.inject.lifecycle.Lifecycle;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by daiki on 2017/09/07.
 */

@Lifecycle
public class BackgroundMusic {

    private MediaPlayer mediaPlayer;
    private AppCompatActivity activity;

    @Inject
    public BackgroundMusic(AppCompatActivity activity){
        this.activity = activity;
    }


    public boolean load(String filePath){
        boolean fileCheck = false;

        // インタンスを生成
        mediaPlayer = new MediaPlayer();

        try {
            // assetsから mp3 ファイルを読み込み
            AssetFileDescriptor afdescripter = activity.getAssets().openFd(filePath);
            // MediaPlayerに読み込んだ音楽ファイルを指定
            mediaPlayer.setDataSource(afdescripter.getFileDescriptor(),
                    afdescripter.getStartOffset(),
                    afdescripter.getLength());
            // 音量調整を端末のボタンに任せる
            activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
            fileCheck = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileCheck;
    }

    public boolean load(@RawRes int resourceId){
        mediaPlayer = MediaPlayer.create(activity, resourceId);
        return true;
    }


    public void play(final boolean loop) {

        restart();

        // 終了を検知するリスナー
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(loop){
                    play(loop);
                }
            }
        });
    }

    public void play(MediaPlayer.OnCompletionListener listener) {

        restart();

        // 終了を検知するリスナー
        mediaPlayer.setOnCompletionListener(listener);
    }

    private void restart(){

        if (mediaPlayer == null) {
            return;
        }

        // 繰り返し再生する場合
        mediaPlayer.stop();
        mediaPlayer.reset();

        // 再生する
        mediaPlayer.start();
    }

    public void release() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
