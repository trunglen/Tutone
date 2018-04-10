package vn.miraway.tutone.ui.tone

import android.support.annotation.StringRes
import vn.miraway.tutone.base.BaseView
import vn.miraway.tutone.model.Tone

interface ToneView : BaseView {
    fun getTones(): List<Tone>
    fun updateTones(tones: List<Tone>)
    fun showLoading()
    fun hideLoading()
    fun showError(error: String)
    fun showError(@StringRes errResId: Int) {
        this.showError(getContext().getString(errResId))
    }
}