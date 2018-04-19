package vn.miraway.tutone.ui.tone

import android.Manifest
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_tone_detail.*
import vn.miraway.tutone.R
import vn.miraway.tutone.services.AudioService
import vn.miraway.tutone.utils.FileDownloader
import android.content.pm.PackageManager
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.ContentValues
import android.content.Context
import android.media.RingtoneManager
import android.support.v4.content.ContextCompat
import android.os.Build
import android.provider.MediaStore
import java.io.File


class ToneDetailActivity : AppCompatActivity(), MediaPlayer.OnCompletionListener {


    lateinit var mediaPlayer: MediaPlayer
    val handler: Handler = Handler()
    val handlerSeek = object : Runnable {
        override fun run() {
            seekBar.progress = mediaPlayer.currentPosition / 100
            handler.postDelayed(this, 100)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tone_detail)
        initView()
    }

    fun initView() {
//        val intentService = Intent(this,AudioService::class.java)
//        intentService.putExtra("name", intent.extras.get("name").toString())
//        intentService.putExtra("url", intent.extras.get("url").toString())
        tvName.text = intent.extras.get("name").toString()
        btnPlay.setOnClickListener {
            changeView(true)
            val thread = Thread(Runnable {
                mediaPlayer = MediaPlayer.create(this, Uri.parse(intent?.extras?.getString("url")));// raw/s.mp3
                mediaPlayer.setOnCompletionListener(this);
                seekBar.max = mediaPlayer.duration / 100
                handler.postDelayed(handlerSeek, 100)
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
            })
            thread.start()
        }
        btnPause.setOnClickListener {
            //            stopService(intentService)  The application may be doing too much work on its main thread
            changeView(false)
            mediaPlayer?.stop()
            handler.removeCallbacks(handlerSeek)
            seekBar.progress = 0
        }
        btnSetRingtone.setOnClickListener {
            FileDownloader(openFileOutput(intent.extras.get("name").toString(), Context.MODE_PRIVATE)).execute(intent.extras.get("url").toString(), intent.extras.get("name").toString())
            setTone()
        }
    }

    fun changeView(isStart: Boolean) {
        btnPause.visibility = if (isStart) View.VISIBLE else View.GONE
        btnPlay.visibility = if (isStart) View.GONE else View.VISIBLE
    }

    override fun onCompletion(mp: MediaPlayer?) {
        handler.removeCallbacks(handlerSeek)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (mediaPlayer?.isPlaying) {
            mediaPlayer?.stop()
        }
        handler.removeCallbacks(handlerSeek)
    }

    protected fun shouldAskPermissions(): Boolean {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            return false
        }
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return permission != PackageManager.PERMISSION_GRANTED
    }

    fun setTone() {
        val k = File(filesDir, "${intent.extras.get("name").toString()}.mp3") // path is a file playing

        val values = ContentValues()
        values.put(MediaStore.MediaColumns.DATA, k.getAbsolutePath())
        values.put(MediaStore.MediaColumns.TITLE, "My Song title") //You will have to populate
        values.put(MediaStore.MediaColumns.SIZE, 215454)
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3")
        values.put(MediaStore.Audio.Media.ARTIST, "Band Name") //You will have to populate this
        values.put(MediaStore.Audio.Media.DURATION, 230)
        values.put(MediaStore.Audio.Media.IS_RINGTONE, true)
        values.put(MediaStore.Audio.Media.IS_NOTIFICATION, false)
        values.put(MediaStore.Audio.Media.IS_ALARM, false)
        values.put(MediaStore.Audio.Media.IS_MUSIC, false)

//Insert it into the database
        val uri = MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath())
        val newUri = contentResolver.insert(uri, values)
        contentResolver
        RingtoneManager.setActualDefaultRingtoneUri(
                this,
                RingtoneManager.TYPE_RINGTONE,
                newUri
        )
    }
}
