package vn.miraway.tutone.ui.tone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import vn.miraway.tutone.databinding.ItemToneBinding
import vn.miraway.tutone.model.Tone

class ToneAdapter(
        private val context:Context?
):BaseAdapter() {
    var tones:List<Tone> = emptyList()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding :ItemToneBinding
        if (convertView == null) {
            binding = ItemToneBinding.inflate(LayoutInflater.from(context),parent, false)
            binding.root.tag = binding
        } else {
            binding = convertView.tag as ItemToneBinding
        }
        binding?.tone = getItem(position) as Tone
        return binding.root
    }

    override fun getItem(position: Int): Any = tones.get(position)

    override fun getItemId(position: Int): Long =0

    override fun getCount(): Int = tones.size
}