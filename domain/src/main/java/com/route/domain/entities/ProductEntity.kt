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

data class MetaData(
    val currentPage: Int?,
    val numberOfPages: Int?,
    val limit: Int?,
    val nextPage: Int?
)

data class PaginationResponse<T>(
    val metadata: MetaData,
    val data: T,
)

val
        productList = listOf(
    ProductEntity(
        id = "44c3cb8f-6688-400c-8923-0b0252b5de95",
        name = "Ross-Garza",
        originalPrice = 379.79,
        discountedPrice = 342.81,
        imageUrl = "https://dummyimage.com/135x719",
        rating = 4.2,
        reviewCount = 163,
        isWishlisted = false
    ),
    ProductEntity(
        id = "7b1c0917-8f3b-4d7a-b680-49b057ec5403",
        name = "Decker and Sons",
        originalPrice = 245.45,
        discountedPrice = 395.88,
        imageUrl = "https://placekitten.com/721/480",
        rating = 2.7,
        reviewCount = null,
        isWishlisted = true
    )
)