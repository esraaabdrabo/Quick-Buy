package com.route.quickbuy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.route.quickbuy.ui.theme.QuickBuyTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuickBuyTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {

                        IconButton(onClick = { handleBottomAppBarItemClick(getString(R.string.home)) }) {
                            Icon(Icons.Filled.Home, contentDescription = getString(R.string.home))
                        }
                        IconButton(onClick = { handleBottomAppBarItemClick(getString(R.string.categories)) }) {
                            Icon(Icons.Filled.Home, contentDescription =getString(R.string.categories))
                        }
                        IconButton(onClick = { handleBottomAppBarItemClick(getString(R.string.wishlist)) }) {
                            Icon(Icons.Filled.Home, contentDescription = getString(R.string.wishlist))
                        }
                        IconButton(onClick = { handleBottomAppBarItemClick(getString(R.string.profile)) }) {
                            Icon(Icons.Filled.Home, contentDescription = getString(R.string.profile))
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

fun handleBottomAppBarItemClick(item: String): Unit {
    // do something here
}


@Composable
fun Body(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    QuickBuyTheme {
        Body("Android")
    }
}