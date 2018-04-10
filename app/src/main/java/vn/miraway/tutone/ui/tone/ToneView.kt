package vn.miraway.tutone.ui

import android.support.annotation.StringRes
import vn.miraway.tutone.base.BaseView
import vn.miraway.tutone.models.Tone

interface ToneView :BaseView{
    fun getTones():List<Tone>
    fun showLoading()
    fun hideLoading()
    fun showError(error:String)
    fun showError(@StringRes errResId:Int) {
        this.showError(getContext().getString(errResId))
    }
}