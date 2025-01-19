package com.francis.weatherap.framework.di.city

import com.francis.weatherap.data.interactors.city.DeleteFavoriteCityInteractor
import com.francis.weatherap.data.interactors.city.GetCitiesInteractor
import com.francis.weatherap.data.interactors.city.GetCitiesPagingInteractor
import com.francis.weatherap.data.interactors.city.GetFavoriteCitiesInteractor
import com.francis.weatherap.data.interactors.city.GetFavoriteCityIdsInteractor
import com.francis.weatherap.data.repository.city.dif.CityRepository
import com.francis.weatherap.data.repository.city.impl.CityRepositoryImpl
import com.francis.weatherap.domain.city.DeleteFavoriteCityInteractorImpl
import com.francis.weatherap.domain.city.GetCitiesInteractorImpl
import com.francis.weatherap.domain.city.GetCitiesPagingInteractorImpl
import com.francis.weatherap.domain.city.GetFavoriteCitiesInteractorImpl
import com.francis.weatherap.domain.city.GetFavoriteCityIdsInteractorImpl
import com.francis.weatherap.framework.network.city.CityService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

private val MediaType = "application/json"
private const val HeaderKey = "x-rapidapi-key"
private const val HeaderHost = "x-rapidapi-host"
private const val HeaderContent = "Content-Type"

@Module
@InstallIn(ActivityRetainedComponent::class)
interface CityRepositoryModule {

    @Binds
    @ViewModelScoped
    fun bindCityRepository(impl: CityRepositoryImpl): CityRepository

    @Binds
    @ViewModelScoped
    fun bindGetCitiesInteractor(impl: GetCitiesInteractorImpl): GetCitiesInteractor

    @Binds
    @ViewModelScoped
    fun bindGetCitiesPagingInteractor(impl: GetCitiesPagingInteractorImpl): GetCitiesPagingInteractor

    companion object {
        @Provides
        @ActivityRetainedScoped
        @CityAuthorizationInterceptor
        fun provideCityAuthorizationInterceptor(): Interceptor = Interceptor { chain ->
            val original: Request = chain.request()
            val request: Request = original.newBuilder()
                .header(HeaderKey, "BuildConfig.RAPID_SERVICE_KEY")
                .header(HeaderHost, "BuildConfig.RAPID_SERVICE_CITY_HOST")
                .header(HeaderContent, MediaType)
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }

        @Provides
        @ActivityRetainedScoped
        @CityRetrofit
        fun provideCityRetrofit(
            converterFactory: GsonConverterFactory,
            okHttpClientBuilder: OkHttpClient.Builder,
            @CityAuthorizationInterceptor interceptor: Interceptor,
        ): Retrofit = Retrofit.Builder()
            .baseUrl("BuildConfig.CITY_SERVICE_BASE_URL")
            .addConverterFactory(converterFactory)
            .client(okHttpClientBuilder.addInterceptor(interceptor).build())
            .build()

        @Provides
        @ActivityRetainedScoped
        internal fun provideCityService(
            @CityRetrofit retrofit: Retrofit,
        ): CityService = retrofit.create(CityService::class.java)
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class CityAuthorizationInterceptor

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class CityRetrofit
