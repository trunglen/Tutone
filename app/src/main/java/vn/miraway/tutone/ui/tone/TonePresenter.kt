package vn.miraway.tutone.ui.tone

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import vn.miraway.tutone.R
import vn.miraway.tutone.base.BasePresenter
import vn.miraway.tutone.model.Tone
import vn.miraway.tutone.network.ToneApi
import javax.inject.Inject

class TonePresenter(toneView: ToneView) : BasePresenter<ToneView>(toneView) {
    override fun onViewDestroyed() {
        subscription?.dispose()
    }

    private var subscription: Disposable? = null
    @Inject
    lateinit var toneApi: ToneApi
    var realm = Realm.getDefaultInstance()
    fun loadTone() {
        view.showLoading()
//        subscription = toneApi.getTones()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .doOnTerminate { view.hideLoading() }
//                .subscribe(
//                        { toneList -> view.updateTones(toneList) },
//                        { view.showError(R.string.title_notifications) }
//                )

    }
}