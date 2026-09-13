package com.route.domain.repos.products

import com.route.domain.core.AppResult
import com.route.domain.entities.PaginationResponse
import com.route.domain.entities.ProductDetailsEntity
import com.route.domain.entities.ProductEntity

abstract
class ProductsRepo {
    // can not return the model here since it will cause circular dependency
    abstract suspend fun getProducts(page: Int = 1): AppResult<PaginationResponse<List<ProductEntity>>>
    abstract suspend fun getProduct(id: String): AppResult<ProductDetailsEntity>
}

