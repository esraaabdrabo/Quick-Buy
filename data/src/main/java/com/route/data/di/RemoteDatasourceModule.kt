package com.route.data.di

import com.route.data.dataSources.auth.AuthRemoteDataSource
import com.route.data.dataSources.auth.AuthRemoteDataSourceImpl
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
    abstract fun bindsProductsRemoteDataSource(
        impl: ProductsRemoteDataSourceImpl
    ): ProductsRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsAuthRemoteDataSource(
        impl: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource
}
