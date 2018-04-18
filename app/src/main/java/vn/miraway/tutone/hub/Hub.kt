package vn.miraway.tutone.hub

import android.util.Log
import io.reactivex.subjects.BehaviorSubject

class Hub {
    companion object {
        var mediaAction = BehaviorSubject.create<Int>()
        fun handleBtn(position: Int) {
            Log.d("on_send", position.toString())
            mediaAction.onNext(position)
        }
    }
}