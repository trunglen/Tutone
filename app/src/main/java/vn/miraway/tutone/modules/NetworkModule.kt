package vn.miraway.tutone.modules

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import vn.miraway.tutone.common.BASE_URL
import vn.miraway.tutone.network.ToneApi

@Module
object NetworkModule {
    @Provides
    @JvmStatic
    @Reusable
    internal fun provideToneApi(retrofit: Retrofit): ToneApi {
        return retrofit.create(ToneApi::class.java)
    }

    @Provides
    @JvmStatic
    @Reusable
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }
}