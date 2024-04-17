package com.example.wallpaperapp.di

import com.example.wallpaperapp.data.GeneralErrorHandlerImpl
import com.example.wallpaperapp.data.source.remote.WallpaperDataSource
import com.example.wallpaperapp.data.source.remote.WallpaperDataSourceImpl
import com.example.wallpaperapp.data.source.repositoryImpl.WallpaperRepositoryImpl
import com.example.wallpaperapp.domain.WallpapersRepository
import com.example.wallpaperapp.domain.error.ErrorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class WallpaperBindingModule {

    @Binds
    abstract fun provideWallpaperDataSource(wallpaperDataSourceImpl: WallpaperDataSourceImpl): WallpaperDataSource

    @Binds
    abstract fun provideWallpaperRepository(wallpaperRepositoryImpl: WallpaperRepositoryImpl): WallpapersRepository

    @Binds
    abstract fun bindsErrorHandler(errorHandler: GeneralErrorHandlerImpl): ErrorHandler
}