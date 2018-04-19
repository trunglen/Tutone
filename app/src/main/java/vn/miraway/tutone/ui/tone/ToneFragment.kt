package vn.miraway.tutone.ui.tone

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.fragment_tone.*
import kotlinx.android.synthetic.main.item_tone.*
import vn.miraway.tutone.R
import vn.miraway.tutone.TutoneApplication
import vn.miraway.tutone.databinding.FragmentToneBinding
import vn.miraway.tutone.hub.Hub
import vn.miraway.tutone.model.Tone
import vn.miraway.tutone.network.ToneApi
import vn.miraway.tutone.ui.dialog.DialogUtil
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
class ToneFragment : BaseFragment(), AbsListView.OnScrollListener {
    override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        if (view?.lastVisiblePosition == totalItemCount - 1 && totalItemCount != 0 && !isLoading) {
            this.page++
            Log.d("scroll_event", "load den cuoi roi $page $firstVisibleItem $visibleItemCount $totalItemCount")
//            this.loadDataToListView(this.page)
            isLoading = true
        }
    }

    override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
        Log.d("scroll_event", scrollState.toString())
    }

    @Inject
    lateinit var toneApi: ToneApi
    var page = 1
    var isLoading = false
    lateinit var binding: FragmentToneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Hub.mediaAction.subscribe { res ->
            Log.d("on_recieve", res.toString())
        }
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
        Log.d("category", category)
        toneAdapter.tones = realm.where(Tone::class.java).`in`("category", arrayOf(category)).findAll().toList()
        binding.tones = toneAdapter
        binding.lvTones.setOnItemClickListener { parent, _, position, _ ->
            val dialogUtil = DialogUtil(context, toneAdapter.tones.get(position),activity).showDialog()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lvTones.setOnScrollListener(this)
    }
}
