package vn.miraway.tutone.ui.tone

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import vn.miraway.tutone.databinding.ItemToneBinding
import vn.miraway.tutone.model.Tone

class ToneAdapter(
        private val context: Context?
) : BaseAdapter() {
    init {
        Log.d("ToneAdapter", "init")
    }
    companion object {
        var media: MediaPlayer? = null
    }
    var tones: List<Tone> = emptyList()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ItemToneBinding
        if (convertView == null) {
            binding = ItemToneBinding.inflate(LayoutInflater.from(context), parent, false)
            binding.root.tag = binding
        } else {
            binding = convertView.tag as ItemToneBinding
        }
        val tone = tones.get(position)
        binding?.btnPlay.setOnClickListener {
            media = MediaPlayer.create(this.context, Uri.parse(tone.url))
            media?.start()
        }
        binding?.btnPause.setOnClickListener {
            if (media != null) {
                if (media!!.isPlaying) {
                    media?.pause()
                }
            }
        }
        binding?.btnReplay.setOnClickListener {
            if (media == null) {
                media = MediaPlayer.create(this.context, Uri.parse(tone.url))
            }
            media?.reset()
        }
        binding?.tone = getItem(position) as Tone
        return binding.root
    }

    override fun getItem(position: Int): Any = tones.get(position)

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int = tones.size
}