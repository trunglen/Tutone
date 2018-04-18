package vn.miraway.tutone.ui.tone

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import vn.miraway.tutone.TutoneApplication
import vn.miraway.tutone.databinding.ItemToneBinding
import vn.miraway.tutone.hub.Hub
import vn.miraway.tutone.model.Tone

class ToneAdapter(
        private val context: Context?
) : BaseAdapter() {

    companion object {
        var media: MediaPlayer? = null
        var trial = println("ToneAdapter")
    }

    var selectedTone = 0
    var tones: List<Tone> = emptyList()
    var hub: Hub = Hub()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ItemToneBinding
        if (convertView == null) {
            binding = ItemToneBinding.inflate(LayoutInflater.from(context), parent, false)
            binding.root.tag = binding
        } else {
            binding = convertView.tag as ItemToneBinding
        }
        val tone = tones.get(position)
        binding?.btnPlay.setOnClickListener { v ->
            TutoneApplication.hub.handleBtn(position)
            media?.stop()
            media = MediaPlayer.create(this.context, Uri.parse(tone.url))
            media?.start()
            v.visibility = View.GONE
            binding?.btnPause.visibility = View.VISIBLE
        }

        binding?.btnPause.setOnClickListener { v ->
            media?.pause()
            v.visibility = View.GONE
            binding?.btnPlay.visibility = View.VISIBLE
        }
        binding?.btnReplay.setOnClickListener {
            media?.stop()
            media = MediaPlayer.create(this.context, Uri.parse(tone.url))
            media?.start()
        }
        binding?.tone = getItem(position) as Tone
        return binding.root
    }

    override fun getItem(position: Int): Any = tones.get(position)

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int = tones.size


}