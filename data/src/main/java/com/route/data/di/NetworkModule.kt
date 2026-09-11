package com.route.data.di

import com.route.data.services.AuthServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
 fun   providesRetrofit(
     okHttpClient: OkHttpClient,
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
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor ( httpLoggingInterceptor ).build()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(level = HttpLoggingInterceptor.Level.BODY)
    }

@Provides
@Singleton
fun providesAuthServices(
    retrofit: Retrofit
): AuthServices{
    return retrofit.create(
        AuthServices::class.java
    )
}}