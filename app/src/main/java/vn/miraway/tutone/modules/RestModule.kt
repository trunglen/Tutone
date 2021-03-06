package vn.miraway.tutone.modules

import android.util.Log
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import vn.miraway.tutone.common.BASE_URL
import vn.miraway.tutone.network.ToneApi
import javax.inject.Singleton
import android.preference.PreferenceManager
import android.app.Application
import android.content.SharedPreferences


@Module
class RestModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        Log.d("inject", "dsas")
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

    @Provides
    fun provideToneApi(retrofit: Retrofit): ToneApi {
        Log.d("inject", "dsas")
        return retrofit.create(ToneApi::class.java)
    }

}