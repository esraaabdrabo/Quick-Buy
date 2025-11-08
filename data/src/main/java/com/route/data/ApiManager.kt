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

class ApiManager {
    private lateinit var retroFit: Retrofit;
    private val baseUrl = "https://ecommerce.routemisr.com"
    private val client = OkHttpClient.Builder().addInterceptor(
        HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY
        )
    ).build()

    private val loggingInterceptor = HttpLoggingInterceptor()

    fun getInstance(): Retrofit {
        if (!::retroFit.isInitialized) {
            retroFit = Retrofit.Builder().baseUrl(baseUrl).client(
                client
            ).build();
        }
        return retroFit;
    }


    fun getAuthServices(): AuthServices {
        return getInstance().create(AuthServices::class.java)
    }

    fun getProfileServices(): ProfileServices {
        return getInstance().create(ProfileServices::class.java)
    }

    fun getOrdersServices(): OrdersServices {
        return getInstance().create(OrdersServices::class.java)
    }

    fun getCartServices(): CartServices {
        return getInstance().create(CartServices::class.java)
    }

    fun getProductsServices(): ProductsServices {
        return getInstance().create(ProductsServices::class.java)
    }

    fun getCategoriesServices(): CategoriesServices {
        return getInstance().create(CategoriesServices::class.java)
    }

    fun getWishListServices(): WishListServices {
        return getInstance().create(WishListServices::class.java)
    }

}
