package vn.miraway.tutone.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.miraway.tutone.common.BASE_URL
import vn.miraway.tutone.network.ToneApi
import javax.inject.Singleton

@Module
class RestModule {
    @Provides
    @Singleton
    fun provideRetrofit():Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
    }

    @Provides
    @Singleton
    fun provideToneApi(retrofit: Retrofit):ToneApi {
        return retrofit.create(ToneApi::class.java)
    }
}