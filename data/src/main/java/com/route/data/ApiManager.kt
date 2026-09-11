package com.route.data

import com.route.data.services.AuthServices
import com.route.data.services.CartServices
import com.route.data.services.CategoriesServices
import com.route.data.services.OrdersServices
import com.route.data.services.ProductsServices
import com.route.data.services.ProfileServices
import com.route.data.services.WishListServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    // Use a thread-safe Singleton pattern with lazy initialization
    companion object {
        // Thread-safe and ensures only one instance is created
        @Volatile
        private var instance: ApiManager? = null

        fun getInstance(): ApiManager {
            return instance ?: synchronized(this) {
                instance ?: ApiManager().also { instance = it }
            }
        }
    }


    private var retroFit: Retrofit? = null

    private val baseUrl = "https://ecommerce.routemisr.com"
    private val client = OkHttpClient.Builder().addInterceptor(
        HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY
        )
    ).build()

    // Get Retrofit instance, lazy initialize it
    fun getRetroFitInstance(): Retrofit {
        return retroFit ?: synchronized(this) {
            retroFit ?: Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .also { retroFit = it }
        }
    }


    fun getAuthServices(): AuthServices {
        return getRetroFitInstance().create(AuthServices::class.java)
    }

    fun getProfileServices(): ProfileServices {
        return getRetroFitInstance().create(ProfileServices::class.java)
    }

    fun getOrdersServices(): OrdersServices {
        return getRetroFitInstance().create(OrdersServices::class.java)
    }

    fun getCartServices(): CartServices {
        return getRetroFitInstance().create(CartServices::class.java)
    }

    fun getProductsServices(): ProductsServices {
        return getRetroFitInstance().create(ProductsServices::class.java)
    }

    fun getCategoriesServices(): CategoriesServices {
        return getRetroFitInstance().create(CategoriesServices::class.java)
    }

    fun getWishListServices(): WishListServices {
        return getRetroFitInstance().create(WishListServices::class.java)
    }
}
