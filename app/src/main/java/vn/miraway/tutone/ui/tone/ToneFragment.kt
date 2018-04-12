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
import kotlinx.android.synthetic.main.fragment_tone.*
import vn.miraway.tutone.R
import vn.miraway.tutone.databinding.FragmentToneBinding
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
class ToneFragment : BaseFragment(), AbsListView.OnScrollListener {
    override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        if (view?.lastVisiblePosition == totalItemCount - 1 && totalItemCount != 0) {
            this.page++
            Log.d("scroll_event", "load den cuoi roi $page $firstVisibleItem $visibleItemCount $totalItemCount")
//            this.loadDataToListView(this.page)
        }
    }

    override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
        Log.d("scroll_event", scrollState.toString())

    }

    @Inject
    lateinit var toneApi: ToneApi
    var page = 1
    lateinit var binding: FragmentToneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentToneBinding>(inflater, R.layout.fragment_tone, container, false)
        this.loadDataToListView(this.page)
//        return inflater.inflate(R.layout.fragment_tone, container, false)
        return binding.root
    }

    fun loadDataToListView(page: Int) {
        val toneAdapter = ToneAdapter(this.context)
        toneApi.getTones(page, vn.miraway.tutone.common.RECORD_PER_PAGE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { this.stopLoading() }
                .map { res -> res.data }
                .subscribe(
                        { res ->
                            toneAdapter.tones = res!!
                            binding.tones = toneAdapter
                        },
                        { err -> Log.d("response_err", err.message) }
                )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lvTones.setOnScrollListener(this)

    }
}
