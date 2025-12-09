package com.route.domain.usecases.products

import com.route.domain.entities.PaginationResponse
import com.route.domain.entities.ProductEntity
import com.route.domain.repos.products.ProductsRepo

class GetProductsUseCase(val repo: ProductsRepo) {
    suspend fun invoke(page: Int = 1): PaginationResponse<List<ProductEntity>> {
        return repo.getProducts(page)

    }
}