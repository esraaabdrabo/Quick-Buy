package com.route.quickbuy

import kotlinx.serialization.Serializable

@Serializable
data object SplashDestination

@Serializable
data object SignInDestination


@Serializable
data object SignUpDestination

@Serializable
data object RegisterDestination

@Serializable
data object BaseHomeDestination

@Serializable
data object ProductsDestination

@Serializable
data class ProductDetailDestination(val id: String? = null)


