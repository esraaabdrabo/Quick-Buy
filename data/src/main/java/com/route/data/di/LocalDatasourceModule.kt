package com.route.data.di

import com.route.data.dataSources.auth.AuthLocalDataSource
import com.route.data.dataSources.auth.AuthLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDatasourceModule {
    @Binds
    @Singleton
    abstract fun bindsAuthLocalDataSource(
        impl: AuthLocalDataSourceImpl
    ): AuthLocalDataSource
}
