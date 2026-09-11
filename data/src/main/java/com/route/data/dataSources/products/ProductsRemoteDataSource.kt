package com.route.data.dataSources.products

import com.route.data.models.productDetails.ProductDetailsModel
import com.route.data.models.products.ProductModel
import com.route.data.services.ProductsServices
import com.route.domain.entities.PaginationResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
abstract class ProductsRemoteDataSource {
    abstract suspend fun getProducts(page: Int = 1): PaginationResponse<List<ProductModel>>
    abstract suspend fun getProduct(id: String): ProductDetailsModel
}

class ProductsRemoteDataSourceImpl @Inject constructor(
    private val productsServices: ProductsServices
) : ProductsRemoteDataSource() {

    override suspend fun getProducts(page: Int): PaginationResponse<List<ProductModel>> {
        // this will automatically suspend and wait for the API response.
        val response = productsServices.getProducts(page)
        return response
    }

    override suspend fun getProduct(id: String): ProductDetailsModel {
        val response = productsServices.getProduct(id)
        return response.data

    }


}