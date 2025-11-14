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

//TODO: remove this before merging
val productList = listOf(
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
    ),
    ProductEntity(
        id = "d55fe547-d230-4bd4-b3ed-7469c3016575",
        name = "Galvan, Patrick and Hudson",
        originalPrice = 286.66,
        discountedPrice = 335.25,
        imageUrl = "https://placekitten.com/928/975",
        rating = null,
        reviewCount = 16,
        isWishlisted = false
    ),
    ProductEntity(
        id = "a7b0bb6b-915b-4a62-be6d-086647b760fc",
        name = "Tate, Beck and Gould",
        originalPrice = 444.12,
        discountedPrice = 304.93,
        imageUrl = "https://placeimg.com/810/818/any",
        rating = 1.9,
        reviewCount = 439,
        isWishlisted = true
    ),
    ProductEntity(
        id = "006bf58e-6837-4f93-b736-0e58f585593a",
        name = "Thomas PLC",
        originalPrice = 251.31,
        discountedPrice = 222.39,
        imageUrl = "https://www.lorempixel.com/887/969",
        rating = 3.4,
        reviewCount = 779,
        isWishlisted = false
    ),
    ProductEntity(
        id = "24d717ea-b8c3-4b06-b7a6-c70b76f1246d",
        name = "McLaughlin-Wolf",
        originalPrice = 139.95,
        discountedPrice = 135.67,
        imageUrl = "https://dummyimage.com/312x563",
        rating = 4.5,
        reviewCount = 250,
        isWishlisted = false
    ),
    ProductEntity(
        id = "96be62c0-9d65-4d93-85b9-9514fa76a116",
        name = "Miller, Inc.",
        originalPrice = 509.78,
        discountedPrice = 489.45,
        imageUrl = "https://placekitten.com/600/600",
        rating = 4.8,
        reviewCount = 1000,
        isWishlisted = true
    ),
    ProductEntity(
        id = "b6d772dd-75b1-4718-9742-c2bbf084f007",
        name = "Stewart-Murphy",
        originalPrice = 309.99,
        discountedPrice = 289.23,
        imageUrl = "https://placeimg.com/800/800/tech",
        rating = 3.8,
        reviewCount = 450,
        isWishlisted = false
    ),
    ProductEntity(
        id = "6e9f8b78-51ea-4f1e-b45b-cd8b17f81955",
        name = "Jones LLC",
        originalPrice = 160.75,
        discountedPrice = 145.50,
        imageUrl = "https://dummyimage.com/200x200",
        rating = 4.1,
        reviewCount = 150,
        isWishlisted = true
    ),
    ProductEntity(
        id = "87b510ea-8b91-47d2-b437-63564f5479db",
        name = "Barnes-Russell",
        originalPrice = 302.43,
        discountedPrice = 272.33,
        imageUrl = "https://placekitten.com/300/300",
        rating = null,
        reviewCount = null,
        isWishlisted = false
    ),
    ProductEntity(
        id = "d2ab46a6-eaeb-4d55-938e-b078d6e6b78b",
        name = "Miller-Williams",
        originalPrice = 112.33,
        discountedPrice = 103.99,
        imageUrl = "https://placeimg.com/720/720/animals",
        rating = 4.3,
        reviewCount = 210,
        isWishlisted = true
    ),
    ProductEntity(
        id = "72b622fa-d4b5-4c44-b02e-30f8f69ea52e",
        name = "Gonzalez and Sons",
        originalPrice = 431.12,
        discountedPrice = 398.50,
        imageUrl = "https://dummyimage.com/315x219",
        rating = 3.0,
        reviewCount = 360,
        isWishlisted = true
    ),
    ProductEntity(
        id = "fb7f1f4a-b62b-4024-b3e4-fc76cb1d2338",
        name = "Lee and Sons",
        originalPrice = 122.99,
        discountedPrice = 112.99,
        imageUrl = "https://placekitten.com/640/480",
        rating = 4.7,
        reviewCount = 785,
        isWishlisted = false
    ),
    ProductEntity(
        id = "9c2d218f-5cae-4c3f-9544-c5c9276f47c1",
        name = "Phillips-Garcia",
        originalPrice = 189.99,
        discountedPrice = 174.50,
        imageUrl = "https://placeimg.com/800/400/people",
        rating = 4.0,
        reviewCount = 120,
        isWishlisted = false
    ),
    ProductEntity(
        id = "be5d9298-e9f7-4a50-bf09-cb6a8b964392",
        name = "Watson, Brown and Lee",
        originalPrice = 198.80,
        discountedPrice = 178.30,
        imageUrl = "https://placeimg.com/640/640/nature",
        rating = 2.9,
        reviewCount = 250,
        isWishlisted = true
    ),
    ProductEntity(
        id = "392c3f6f-2a72-4620-90cf-0030327ff0c3",
        name = "Jackson-Jones",
        originalPrice = 342.44,
        discountedPrice = 312.50,
        imageUrl = "https://dummyimage.com/240x300",
        rating = 3.5,
        reviewCount = 540,
        isWishlisted = false
    ),
    ProductEntity(
        id = "64e9bbf0-6b0a-478d-8599-9b4476fe3067",
        name = "Bates, Ltd.",
        originalPrice = 419.20,
        discountedPrice = 379.50,
        imageUrl = "https://placeimg.com/720/1280/business",
        rating = 5.0,
        reviewCount = 890,
        isWishlisted = true
    ),
    ProductEntity(
        id = "ced34b0b-f848-4794-86d1-0b7673cce4a7",
        name = "Harris, LLC",
        originalPrice = 121.99,
        discountedPrice = 111.30,
        imageUrl = "https://dummyimage.com/700x400",
        rating = 4.6,
        reviewCount = 730,
        isWishlisted = false
    ),
    ProductEntity(
        id = "0e4e4a88-5e7c-4753-b5ba-bd1690e803c0",
        name = "White Inc.",
        originalPrice = 290.14,
        discountedPrice = 249.75,
        imageUrl = "https://placekitten.com/1000/1000",
        rating = 4.2,
        reviewCount = 120,
        isWishlisted = true
    )
)
