package vn.miraway.tutone.services.http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HttpClient {
    companion object {
        private var retrofit: Retrofit? = null
        private val BASE_URL = ""
        public fun getInstance(): Retrofit? {
            if (retrofit == null) {
                return retrofit2.Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }
    }
}