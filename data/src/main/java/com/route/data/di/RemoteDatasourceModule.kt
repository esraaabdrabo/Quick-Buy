package com.route.data.di

import com.route.data.dataSources.products.ProductsRemoteDataSource
import com.route.data.dataSources.products.ProductsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)


abstract class RemoteDatasourceModule {
    @Binds
    @Singleton
    abstract fun bindsRemoteDataSource(
        impl: ProductsRemoteDataSourceImpl
    ): ProductsRemoteDataSource
}