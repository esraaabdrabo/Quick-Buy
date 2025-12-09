package com.route.data.services

import com.route.data.models.products.ProductModel
import com.route.domain.entities.PaginationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsServices {
    @GET("/api/v1/products")
    abstract suspend fun getProducts(@Query("page") page: Int): PaginationResponse<List<ProductModel>>

    @GET("/api/v1/products/{id}")
    abstract suspend fun getProduct(@Query("id") id: Int): ProductModel

}