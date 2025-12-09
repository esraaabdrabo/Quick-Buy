package com.route.data.dataSources.products

import com.route.data.ApiManager
import com.route.data.models.products.ProductModel
import com.route.domain.entities.PaginationResponse

abstract class ProductsRemoteDataSource {
    abstract suspend fun getProducts(page: Int = 1): PaginationResponse<List<ProductModel>>
    abstract suspend fun getProduct(id: Int): ProductModel
}

class ProductsRemoteDataSourceImpl : ProductsRemoteDataSource() {
    val productsServices = ApiManager().getProductsServices()
    override suspend fun getProducts(page: Int): PaginationResponse<List<ProductModel>> {
        // this will automatically suspend and wait for the API response.
        val response = productsServices.getProducts(page)
        return response
    }

    override suspend fun getProduct(id: Int): ProductModel {
        val response = productsServices.getProduct(id)
        return response

    }


}