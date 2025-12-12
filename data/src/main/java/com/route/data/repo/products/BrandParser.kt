package com.route.data.repo.products

import com.route.data.models.products.BrandModel
import com.route.domain.entities.BrandEntity

class BrandParser {
    companion object {
        fun toEntity(data: BrandModel?): BrandEntity {
            return BrandEntity(
                data?.image,
                data?.name,
                data?.id,
                data?.slug
            )
        }
    }
}