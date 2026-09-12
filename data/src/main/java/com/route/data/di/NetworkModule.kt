package com.route.data.di

import com.route.data.core.network.AccessTokenInterception
import com.route.data.services.AuthServices
import com.route.data.services.ProductsServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Qualifier
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Qualifier
@Retention
annotation class AuthClient

@Qualifier
@Retention
annotation class AuthenticatedClient

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton

    fun providesAuthRetrofit(
        @AuthClient okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://ecommerce.routemisr.com/api/v1/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build();
    }

    @Provides
    @Singleton
    fun providesAuthenticatedRetrofit(
        @AuthenticatedClient okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://ecommerce.routemisr.com/api/v1/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build();
    }

    @Provides
    @Singleton
    @AuthenticatedClient
    fun providesAuthenticatedOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        accessTokenInterception: AccessTokenInterception
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(accessTokenInterception)
            .build()
    }

    @Provides
    @Singleton
    @AuthClient
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(level = HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun providesAuthServices(
        @AuthClient retrofit: Retrofit
    ): AuthServices {
        return retrofit.create(
            AuthServices::class.java
        )
    }

    @Provides
    @Singleton
    fun providesProductsServices(
        @AuthenticatedClient retrofit: Retrofit
    ): ProductsServices {
        return retrofit.create(
            ProductsServices::class.java
        )
    }
}