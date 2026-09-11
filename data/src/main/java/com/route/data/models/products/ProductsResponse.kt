package com.route.data.models.products

data class ProductsResponse(
    val results: Int,
    val data: List<ProductModel>,
    val metadata: ResponseMetaData
)

data class ResponseMetaData(
    // page 0 is the same as 1 in backend metadata
    val currentPage: Int,
    val numberOfPages: Int,
    val limit: Int,
    val nextPage: Int?,
    val prevPage: Int?,
)