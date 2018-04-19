package vn.miraway.tutone.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.media.MediaPlayer
import android.net.Uri
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.SeekBar
import vn.miraway.tutone.R
import vn.miraway.tutone.model.Tone
import android.content.Intent
import vn.miraway.tutone.services.AudioService


class DialogUtil(val context: Context?, val tone: Tone, val activity: Activity?) {

    lateinit var btnPlay: ImageButton
    lateinit var btnPause: ImageButton
    lateinit var btnReplay: ImageButton
    lateinit var seekBar: SeekBar
    lateinit var mediaPlayer: MediaPlayer
    private val handler = Handler()
    private val handlerSeek = object : Runnable {
        override fun run() {
            Log.d("handler_", (mediaPlayer.currentPosition / 1000).toString())
            seekBar.setProgress(mediaPlayer.currentPosition / 1000)
            handler.postDelayed(this, 1000)
        }
    }

    fun showDialog() {
        val dialog = Dialog(context)
//        dialog.setOnDismissListener{
//            if (mediaPlayer.isPlaying) {
//                mediaPlayer.stop()
//                handler.removeCallbacks(handlerSeek)
//            }
//        }
        dialog.setContentView(R.layout.layout_dialog)
        initDialog(dialog)
        val intent = Intent(dialog.context, AudioService::class.java)
        intent.putExtra("url",tone.url)
        btnPlay.setOnClickListener{
            activity?.startService(intent)
            activity?.finish()
        }
        btnPause.setOnClickListener{
            activity?.stopService(intent)
            activity?.finish()
        }
        initDialog(dialog)
//        mediaPlayer = MediaPlayer.create(context, Uri.parse(tone.url));
//        mediaPlayer.setOnCompletionListener {
//            handler.removeCallbacks(handlerSeek)
//        }
//        var startTime = mediaPlayer.getCurrentPosition();
//        val finalTime = mediaPlayer.getDuration();
//        seekBar.setMax(finalTime/1000);
//        seekBar.setProgress(startTime/1000)
//        btnPlay.setOnClickListener { v ->
//            mediaPlayer.start()
//            handler.postDelayed(handlerSeek, 1000)
//
//        }
//        dialog.findViewById<TextView>(R.id.tvName).text = tone.name
        activity?.runOnUiThread(
                object : Runnable {
                    override fun run() {
                        dialog.show()
                    }
                }
        )
    }


    fun initDialog(dialog: Dialog) {
        btnPlay = dialog.findViewById<ImageButton>(R.id.btnPlay)
        btnPause = dialog.findViewById<ImageButton>(R.id.btnPause)
        btnReplay = dialog.findViewById<ImageButton>(R.id.btnReplay)
        seekBar = dialog.findViewById<SeekBar>(R.id.seekBar)
    }


}

