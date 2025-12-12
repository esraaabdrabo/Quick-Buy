package com.route.data.repo.products

import com.route.data.models.productDetails.ProductDetailsModel
import com.route.domain.entities.ProductDetailsEntity

class ProductDetailsParser {
    companion object {
        fun toEntity(product: ProductDetailsModel): ProductDetailsEntity {
            return ProductDetailsEntity(
                product.id,
                product.images,
                product.title,
                product.price,
                product.sold,
                product.description,
                product.quantity,
                product.imageCover,
                product.ratingsQuantity,
                product.ratingsAverage,
                BrandParser.toEntity(product.brand)
            )
        }
    }
}