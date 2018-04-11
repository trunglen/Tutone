package vn.miraway.tutone.network

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import vn.miraway.tutone.model.SuccessResponse
import vn.miraway.tutone.model.Tone

interface ToneApi {
    @GET("tone/list") fun getTones():Observable<SuccessResponse<List<Tone>>>
}