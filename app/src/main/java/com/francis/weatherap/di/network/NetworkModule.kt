package com.francis.weatherap.di.network

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

private const val OkHttpTag = "OkHttp"
private const val ConnectTimeoutSeconds = 15L
private const val ReadTimeoutSeconds = 30L
private const val CacheSize = 10L * 1024L * 1024L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    @HeaderLoggingInterceptor
    fun provideHeaderLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor { message ->
            Log.d(OkHttpTag, message)
        }.apply { level = HttpLoggingInterceptor.Level.HEADERS }

    @Provides
    @Singleton
    fun provideHttpCache(@ApplicationContext context: Context): Cache =
        Cache(context.cacheDir, CacheSize)

    @Provides
    @Singleton
    fun providedOkHttpClientBuilder(
        cache: Cache,
        @HeaderLoggingInterceptor headerLoggingInterceptor: Interceptor,
    ): OkHttpClient.Builder = OkHttpClient
        .Builder()
        .cache(cache)
        .connectTimeout(ConnectTimeoutSeconds, TimeUnit.SECONDS)
        .readTimeout(ReadTimeoutSeconds, TimeUnit.SECONDS)
        .addInterceptor(headerLoggingInterceptor)
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class HeaderLoggingInterceptor
