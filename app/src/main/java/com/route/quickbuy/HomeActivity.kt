package com.route.quickbuy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.route.quickbuy.ui.components.AppBottomBar
import com.route.quickbuy.ui.theme.QuickBuyTheme

enum class BottomBarItems(val iconID: Int) {
    Home(R.drawable.home),
    Category(R.drawable.category),
    WishList(R.drawable.heart),
    Profile(R.drawable.profile)
}


class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            QuickBuyTheme {
                var selectedItem: BottomBarItems by remember {
                    mutableStateOf(
                        BottomBarItems.Home
                    )
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        AppBottomBar(selectedItem = selectedItem) { item ->
                            selectedItem = item
                        }
                    }
                ) { innerPadding ->
                    Body(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Body(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


