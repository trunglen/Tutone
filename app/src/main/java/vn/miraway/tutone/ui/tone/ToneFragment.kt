package vn.miraway.tutone.ui.tone


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_tone.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import vn.miraway.tutone.R
import vn.miraway.tutone.databinding.FragmentToneBinding
import vn.miraway.tutone.injection.component.DaggerAppComponent
import vn.miraway.tutone.model.Tone
import vn.miraway.tutone.modules.RestModule
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentToneBinding>(inflater, R.layout.fragment_tone, container, false)

        toneApi.getTones()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate{this.stopLoading()}
                .map { res->res.data }
                .subscribe(
                        {res->Log.d("response_body",res.toString())},
                        {err->Log.d("response_err",err.message)}
                )
        val toneAdapter = ToneAdapter(this.context)
        var tones = ArrayList<Tone>()
        tones.add(Tone("ds", "dasdasd", "dasd", "dasdsa"))
        tones.add(Tone("ds", "dasdasd", "dasd", "dasdsa"))
        tones.add(Tone("ds", "dasdasd", "dasd", "dasdsa"))
        tones.add(Tone("ds", "dasdasd", "dasd", "dasdsa"))
        toneAdapter.tones = tones
        binding.tones = toneAdapter
//        return inflater.inflate(R.layout.fragment_tone, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}
