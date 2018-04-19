package vn.miraway.tutone.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder

/**
 * Created by Admin on 19/04/2018.
 */
class AudioService:Service(),MediaPlayer.OnCompletionListener {
    lateinit var mediaPlayer: MediaPlayer

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {

    }

    override fun onCompletion(p0: MediaPlayer?) {
        stopSelf()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer = MediaPlayer.create(this, Uri.parse(intent?.extras?.getString("url")));// raw/s.mp3
        mediaPlayer.setOnCompletionListener(this);
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        return START_STICKY;
    }

    override fun onDestroy() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }
}