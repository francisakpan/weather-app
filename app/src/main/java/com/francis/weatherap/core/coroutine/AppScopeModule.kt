package com.francis.weatherap.core.coroutine

import com.francis.weatherap.core.connectivity.def.ConnectivityMonitor
import com.francis.weatherap.core.connectivity.impl.ConnectivityMonitorImpl
import com.francis.weatherap.core.location.def.LocationProvider
import com.francis.weatherap.core.location.impl.LocationProviderImpl
import com.francis.weatherap.core.time.def.TimeFormatter
import com.francis.weatherap.core.time.def.TimeProvider
import com.francis.weatherap.core.time.def.ZoneIdProvider
import com.francis.weatherap.core.time.impl.TimeFormatterImpl
import com.francis.weatherap.core.time.impl.TimeProviderImpl
import com.francis.weatherap.core.time.impl.ZoneIdProviderImpl
import com.francis.weatherap.di.dispatchers.DispatcherProvider
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface AppScopeModule {

    @Binds
    fun bindsConnectivityMonitor(impl: ConnectivityMonitorImpl): ConnectivityMonitor

    @Binds
    fun bindsLocationProvider(impl: LocationProviderImpl): LocationProvider

    @Binds
    fun bindsTimeFormatter(impl: TimeFormatterImpl): TimeFormatter

    @Binds
    fun bindsTimeProvider(impl: TimeProviderImpl): TimeProvider

    @Binds
    fun bindsZoneIdProvider(impl: ZoneIdProviderImpl): ZoneIdProvider

    companion object {
        @Provides
        @Singleton
        fun providesGson(): Gson {
            return Gson().newBuilder()
                .setLenient()
                .create()
        }

        @Provides
        @ApplicationScope
        fun providesCoroutineScope(
            dispatcherProvider: DispatcherProvider,
        ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcherProvider.default)
    }
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope
