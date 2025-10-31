package com.route.quickbuy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.route.quickbuy.ui.theme.QuickBuyTheme
import com.route.quickbuy.ui.theme.onPrimary
import com.route.quickbuy.ui.theme.primary
import com.route.quickbuy.ui.theme.white

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            QuickBuyTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .padding(0.dp)
                                .border(
                                    width = 1.dp,
                                    color = primary,
                                    shape = RoundedCornerShape(
                                        200.dp
                                    )
                                )
                                .clip(
                                    RoundedCornerShape(
                                        topEnd =
                                            15.dp, topStart = 15.dp
                                    ),
                                )
                                .background(primary),

                            ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly,
                            ) {


                                BottomBarItem(
                                    isSelected = true,
                                    iconId = R.drawable.home
                                )


                                BottomBarItem(
                                    isSelected = false,
                                    iconId = R.drawable.category
                                )



                                BottomBarItem(
                                    isSelected = false,
                                    iconId = R.drawable.heart
                                )



                                BottomBarItem(
                                    isSelected = false,
                                    iconId = R.drawable.profile


                                )

                            }
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


@Composable
fun BottomBarItem(
    isSelected: Boolean, iconId: Int,
    onClick: () -> Unit = {}
) {


    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                color = if (isSelected) onPrimary else primary,
                shape = CircleShape,
            )
            .padding(8.dp)
            .clickable(
                onClick = onClick
            ),
        contentAlignment = Alignment.Center

    )
    {
        Image(
            modifier = Modifier
                .size(40.dp)
                .border(
                    width = 1.dp,
                    color = white,
                    shape = RoundedCornerShape(
                        200.dp
                    )

                ),
            painter = painterResource(iconId),
            contentDescription = "",
            colorFilter =
                if (isSelected) ColorFilter.tint(primary)
                else
                    ColorFilter.tint(onPrimary)
        )
    }


}


