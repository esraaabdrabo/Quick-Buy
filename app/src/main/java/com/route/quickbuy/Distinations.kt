package com.route.quickbuy

import kotlinx.serialization.Serializable

@Serializable
data object SplashDestination

@Serializable
data object LoginDestination

@Serializable
data object RegisterDestination

@Serializable
data object BaseHomeDestination

@Serializable
data object ProductsDestination

@Serializable
data class ProductDetailDestination(val id: String? = null)


