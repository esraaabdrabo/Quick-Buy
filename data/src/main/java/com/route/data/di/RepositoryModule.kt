package com.route.data.di

import com.route.data.repo.auth.AuthRepoImpl
import com.route.data.repo.products.ProductsRepoImpl
import com.route.domain.repos.auth.AuthRepo
import com.route.domain.repos.products.ProductsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsProductsRepo(
        impl: ProductsRepoImpl
    ): ProductsRepo

    @Binds
    @Singleton
    abstract fun bindsAuthRepo(
        impl: AuthRepoImpl
    ): AuthRepo
}
