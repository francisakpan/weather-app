package com.francis.weatherap.core.coroutine

import com.francis.weatherap.di.dispatchers.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier

@InstallIn(SingletonComponent::class)
@Module
object AppScopeModule {
    @Provides
    @ApplicationScope
    fun providesCoroutineScope(
        dispatcherProvider: DispatcherProvider,
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcherProvider.default)
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope
