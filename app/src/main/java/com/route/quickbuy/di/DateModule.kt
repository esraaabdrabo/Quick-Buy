package com.route.quickbuy.di

import com.route.data.core.network.Services.DateServices
import com.route.quickbuy.core.services.DateServicesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DateModule {
    @Binds
    abstract fun bindDateServices(impl: DateServicesImpl): DateServices
}