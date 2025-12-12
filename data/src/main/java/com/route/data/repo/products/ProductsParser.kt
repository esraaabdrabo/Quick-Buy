package com.route.data.repo.products

import com.route.data.models.products.ProductModel
import com.route.domain.entities.PaginationResponse
import com.route.domain.entities.ProductEntity

class ProductsParser {
    companion object {
        fun toEntity(response: PaginationResponse<List<ProductModel>>): PaginationResponse<List<ProductEntity>> {
            return PaginationResponse<List<ProductEntity>>(
                metadata = response.metadata,
                data = productsToEntity(response.data)

            )
        }

        fun productsToEntity(data: List<ProductModel>): List<ProductEntity> {
            val products: MutableList<ProductEntity> = mutableListOf()
            data.forEach { product ->
                if (product.id == null) throw Exception("product id is null")

                if (product.price == null) throw Exception("product price is null")

                val entity = ProductEntity(
                    id = product.id,
                    name = product.title ?: "Product",
                    originalPrice = product.price.toDouble(),
                    discountedPrice = product.price.toDouble(),
                    imageUrl = product.imageCover,
                    rating = (product.ratingsAverage ?: 0).toDouble(),
                    reviewCount = product.ratingsQuantity ?: 0,
                    isWishlisted = false // need to know
                )
                products.add(entity)
            }
            return products
        }

    }
}