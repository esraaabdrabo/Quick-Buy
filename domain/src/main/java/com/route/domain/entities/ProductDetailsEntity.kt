package com.route.domain.entities

data class ProductDetailsEntity(

    val id: String? = null,

    val images: List<String?>? = null,

    val title: String? = null,

    val price: Int? = null,

    val sold: Double? = null,

    val description: String? = null,

    val quantity: Int? = null,

    val imageCover: String? = null,

    val ratingsQuantity: Int? = null,

    val ratingsAverage: Double? = null,

    val brand: BrandEntity? = null,
)

