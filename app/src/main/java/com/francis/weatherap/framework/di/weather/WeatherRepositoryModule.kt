package com.francis.weatherap.framework.di.weather

import com.francis.weatherap.data.interactors.weather.GetForecastInteractor
import com.francis.weatherap.data.interactors.weather.GetTodayWeatherInteractor
import com.francis.weatherap.data.repository.weather.dif.WeatherRepository
import com.francis.weatherap.data.repository.weather.impl.WeatherRepositoryImpl
import com.francis.weatherap.domain.weather.GetForecastInteractorImpl
import com.francis.weatherap.domain.weather.GetTodayWeatherInteractorImpl
import com.francis.weatherap.framework.network.weather.WeatherService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

private const val MediaType = "application/json"
private const val HeaderKey = "x-rapidapi-key"
private const val HeaderHost = "x-rapidapi-host"
private const val HeaderContent = "Content-Type"

@Module
@InstallIn(ActivityRetainedComponent::class)
interface WeatherRepositoryModule {

    @Binds
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    @Binds
    fun bindGetForecastInteractor(impl: GetForecastInteractorImpl): GetForecastInteractor

    @Binds
    fun bindGetTodayWeatherInteractor(impl: GetTodayWeatherInteractorImpl): GetTodayWeatherInteractor


    companion object {
        @Provides
        @ActivityRetainedScoped
        @WeatherAuthorizationInterceptor
        fun provideWeatherAuthorizationInterceptor(): Interceptor = Interceptor { chain ->
            val original: Request = chain.request()
            val request: Request = original.newBuilder()
                .header(HeaderKey, "321fef3568msh4d5ba15f96ce1fcp1cf902jsne4afa5be691e")
                .header(HeaderHost, "openweather43.p.rapidapi.com")
                .header(HeaderContent, MediaType)
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }

        @Provides
        @ActivityRetainedScoped
        @WeatherRetrofit
        fun provideWeatherRetrofit(
            converterFactory: GsonConverterFactory,
            okHttpClientBuilder: OkHttpClient.Builder,
            @WeatherAuthorizationInterceptor interceptor: Interceptor,
        ): Retrofit = Retrofit.Builder()
            .baseUrl("https://openweather43.p.rapidapi.com/")
            .addConverterFactory(converterFactory)
            .client(okHttpClientBuilder.addInterceptor(interceptor).build())
            .build()

        @Provides
        @ActivityRetainedScoped
        internal fun provideWeatherService(
            @WeatherRetrofit retrofit: Retrofit,
        ): WeatherService = retrofit.create(WeatherService::class.java)
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class WeatherAuthorizationInterceptor

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class WeatherRetrofit
