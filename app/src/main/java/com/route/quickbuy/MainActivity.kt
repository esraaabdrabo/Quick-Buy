package com.route.quickbuy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.route.domain.entities.productList
import com.route.quickbuy.features.HomeBaseScreen
import com.route.quickbuy.features.SplashScreen
import com.route.quickbuy.features.products.screens.ProductDetailScreen
import com.route.quickbuy.ui.theme.QuickBuyTheme
import dagger.hilt.android.AndroidEntryPoint

val navController = compositionLocalOf<NavHostController> {
    error("No NavHostController")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuickBuyTheme {
                CompositionLocalProvider(navController provides rememberNavController()) {


                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        NavHost(
                            navController = navController.current,
                            modifier = Modifier.padding(innerPadding),
                            startDestination = SplashDestination
                        ) {
                            composable<SplashDestination>() {
                                SplashScreen(navController = navController.current)
                            }
                            composable<BaseHomeDestination>() {
                                HomeBaseScreen()
                            }
                            composable<ProductDetailDestination> {
                                //should i really pass a value here?
                                ProductDetailScreen(product = productList.first())
                            }
                        }
                    }
                }
            }
        }
    }
}

