package com.route.quickbuy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.route.quickbuy.screens.HomeBaseScreen
import com.route.quickbuy.ui.theme.QuickBuyTheme


class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            QuickBuyTheme {
                HomeBaseScreen()
            }
        }
    }
}




