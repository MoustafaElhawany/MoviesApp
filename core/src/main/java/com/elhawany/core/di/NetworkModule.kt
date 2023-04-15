package com.elhawany.core.di

import com.elhawany.core.utils.app.AppConfigurations.BASE_URL
import com.elhawany.core.utils.app.AppConfigurations.NETWORK_CONNECT_TIMEOUT_DURATION_IN_SECONDS
import com.elhawany.core.utils.app.AppConfigurations.NETWORK_READ_TIMEOUT_DURATION_IN_SECONDS
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(NETWORK_READ_TIMEOUT_DURATION_IN_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(NETWORK_CONNECT_TIMEOUT_DURATION_IN_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}