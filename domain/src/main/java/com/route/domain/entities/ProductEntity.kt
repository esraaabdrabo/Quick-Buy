package com.route.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class ProductEntity(
    val id: String,
    val name: String,
    val originalPrice: Double,
    val discountedPrice: Double,
    val imageUrl: String?,
    val rating: Double?,
    val reviewCount: Int?,
    val isWishlisted: Boolean = false,
)
