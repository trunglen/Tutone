package vn.miraway.tutone.network

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import vn.miraway.tutone.model.SuccessResponse
import vn.miraway.tutone.model.Tone

interface ToneApi {
    @GET("tone/list")
    fun getTones(@Query("page") page: Int=0, @Query("skip") skip: Int=0): Observable<SuccessResponse<List<Tone>>>
}