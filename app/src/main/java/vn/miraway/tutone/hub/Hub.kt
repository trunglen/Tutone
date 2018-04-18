package vn.miraway.tutone.hub

import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.Subject

class Hub {
    companion object {
        var mediaAction = Subject.just(0)

    }
    fun handleBtn(position: Int) {
        mediaAction.doOnNext(Consumer {
            position
        })
    }

}