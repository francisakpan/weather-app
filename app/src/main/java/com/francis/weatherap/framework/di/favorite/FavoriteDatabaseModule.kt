package com.francis.weatherap.framework.di.favorite

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.francis.weatherap.data.interactors.city.DeleteFavoriteCityInteractor
import com.francis.weatherap.data.interactors.city.GetFavoriteCitiesInteractor
import com.francis.weatherap.data.interactors.city.GetFavoriteCityIdsInteractor
import com.francis.weatherap.data.repository.favorite.FavoriteRepository
import com.francis.weatherap.data.repository.favorite.FavoriteRepositoryImpl
import com.francis.weatherap.domain.city.DeleteFavoriteCityInteractorImpl
import com.francis.weatherap.domain.city.GetFavoriteCitiesInteractorImpl
import com.francis.weatherap.domain.city.GetFavoriteCityIdsInteractorImpl
import com.francis.weatherap.framework.database.dao.FavoriteCitiesDao
import com.francis.weatherap.framework.database.FavoriteCitiesDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface FavoriteDatabaseModule {

    @Binds
    @ViewModelScoped
    fun bindFavoriteRepository(impl: FavoriteRepositoryImpl): FavoriteRepository

    @Binds
    @ViewModelScoped
    fun bindDeleteFavoriteCityInteractor(impl: DeleteFavoriteCityInteractorImpl): DeleteFavoriteCityInteractor

    @Binds
    @ViewModelScoped
    fun bindGetFavoriteCitiesInteractor(impl: GetFavoriteCitiesInteractorImpl): GetFavoriteCitiesInteractor

    @Binds
    @ViewModelScoped
    fun bindGetFavoriteCityIdsInteractor(impl: GetFavoriteCityIdsInteractorImpl): GetFavoriteCityIdsInteractor


    companion object {
        @Provides
        @Singleton
        fun provideFavoriteDao(database: FavoriteCitiesDatabase): FavoriteCitiesDao =
            database.favoriteCitiesDao()

        @Provides
        @Singleton
        internal fun provideCitiesDatabase(
            @ApplicationContext context: Context,
        ): FavoriteCitiesDatabase =
            databaseBuilder(
                context,
                FavoriteCitiesDatabase::class.java,
                "favorite_cities.db",
            ).build()
    }
}
