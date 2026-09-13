package com.route.quickbuy

import kotlinx.serialization.Serializable

abstract class Destination

@Serializable
data object SplashDestination : Destination()

@Serializable
data object SignInDestination : Destination()


@Serializable
data object SignUpDestination : Destination()


@Serializable
data object BaseHomeDestination : Destination()

@Serializable
data object ProductsDestination : Destination()

@Serializable
data class ProductDetailDestination(val id: String? = null) : Destination()


