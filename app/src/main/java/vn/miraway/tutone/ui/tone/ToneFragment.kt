package vn.miraway.tutone.ui.tone

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vn.miraway.tutone.R
import vn.miraway.tutone.databinding.FragmentToneBinding
import vn.miraway.tutone.model.Tone
import vn.miraway.tutone.network.ToneApi
import vn.miraway.tutone.views.fragments.BaseFragment
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ToneFragment : BaseFragment() {

    @Inject
    lateinit var toneApi: ToneApi
    var page = 1
    var isLoading = false
    lateinit var binding: FragmentToneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentToneBinding>(inflater, R.layout.fragment_tone, container, false)
        this.loadDataToListView(this.page)
        return binding.root
    }

    fun loadDataToListView(page: Int) {
        val toneAdapter = ToneAdapter(this.context)
        val category = arguments?.get("category").toString()
        toneAdapter.tones = realm.where(Tone::class.java).`in`("category", arrayOf(category)).findAll().toList()
        binding.tones = toneAdapter
        binding.lvTones.setOnItemClickListener { parent, _, position, _ ->
            val tone = toneAdapter.tones.get(position)
            val intent = Intent(this.activity, ToneDetailActivity::class.java)
            intent.putExtra("name", tone.name)
            intent.putExtra("url", tone.url)
            startActivity(intent)
        }
//        toneApi.getTones()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .doOnTerminate { this.stopLoading() }
//                .map { res -> res.data }
//                .subscribe(
//                        { res ->
//                            toneAdapter.tones = res!!
//                            binding.tones = toneAdapter
//                        },
//                        { err -> Log.d("response_err", err.message) }
//                )
    }

}
