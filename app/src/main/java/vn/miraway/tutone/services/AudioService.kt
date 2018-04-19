package vn.miraway.tutone.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.AsyncTask
import android.os.IBinder
import android.util.Log

/**
 * Created by Admin on 19/04/2018.
 */
class AudioService : Service(), MediaPlayer.OnCompletionListener {
    val tag = "[audio_srvice]"
    lateinit var mediaPlayer: MediaPlayer

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        Log.d(tag,"onCreate")
    }

    override fun onCompletion(p0: MediaPlayer?) {
        stopSelf()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(tag,"onStartCommand")
        val context = this;
        val thread = Thread(Runnable {
            mediaPlayer = MediaPlayer.create(context, Uri.parse(intent?.extras?.getString("url")));// raw/s.mp3
            mediaPlayer.setOnCompletionListener(context);
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        })
        thread.start()
        return START_STICKY;
    }

    override fun onDestroy() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }
}