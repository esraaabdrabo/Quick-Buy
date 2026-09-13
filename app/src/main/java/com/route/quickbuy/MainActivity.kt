package com.route.quickbuy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.route.data.core.session.SessionEvent
import com.route.data.core.session.SessionManager
import com.route.domain.usecases.auth.LogoutUseCase
import com.route.quickbuy.core.services.ConnectivityObserver
import com.route.quickbuy.features.HomeBaseScreen
import com.route.quickbuy.features.auth.screens.SignInScreen
import com.route.quickbuy.features.auth.screens.SignUpScreen
import com.route.quickbuy.features.products.screens.ProductDetailScreen
import com.route.quickbuy.features.products.screens.ProductsScreen
import com.route.quickbuy.features.splash.screens.SplashScreen
import com.route.quickbuy.ui.theme.QuickBuyTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


val navController = compositionLocalOf<NavHostController> {
    error("No NavHostController")
}

val LocalConnectivityObserver = compositionLocalOf<ConnectivityObserver> {
    error("No ConnectivityObserver")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var connectivityObserver: ConnectivityObserver

    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
    lateinit var logoutUseCase: LogoutUseCase


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuickBuyTheme {
                CompositionLocalProvider(LocalConnectivityObserver provides connectivityObserver) {
                    CompositionLocalProvider(navController provides rememberNavController()) {
                        val navController = navController.current
                        LaunchedEffect(Unit) {
                            sessionManager.sessionEvents.collect { event ->
                                when (event) {
                                    SessionEvent.Expired, SessionEvent.LoggedOut -> {
                                        logoutUseCase.invoke()
                                        navController.navigate(SignInDestination) {
                                            popUpTo(0) { inclusive = true }
                                        }
                                    }
                                }
                            }
                        }

                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            AppNavHost(innerPadding = innerPadding);
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AppNavHost(innerPadding: PaddingValues) {
    return NavHost(
        navController = navController.current,
        modifier = Modifier.padding(innerPadding),
        startDestination = SplashDestination
    ) {
        composable<SplashDestination>() {
            SplashScreen()
        }
        composable<BaseHomeDestination>() {
            HomeBaseScreen()
        }
        composable<SignInDestination>() {
            SignInScreen()
        }

        composable<SignUpDestination>() {
            SignUpScreen()
        }
        composable<ProductDetailDestination> { param ->
            ProductDetailScreen(
                id = param.arguments!!.getString("id") ?: ""
            )

        }
        composable<ProfileDestination>() {
            ProductsScreen()
        }
    }
}