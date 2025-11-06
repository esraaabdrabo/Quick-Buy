package com.route.quickbuy.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.route.quickbuy.BaseHomeDestination
import com.route.quickbuy.R
import com.route.quickbuy.SplashDestination
import com.route.quickbuy.ui.theme.QuickBuyTheme
import com.route.quickbuy.ui.theme.primary
import com.route.quickbuy.ui.theme.white
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.popBackStack(SplashDestination, true)
        navController.navigate(BaseHomeDestination)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        backgroundGradient(
            colors = listOf(
                white.copy(alpha = .4f),
                primary.copy(alpha = .5f)
            ),
            Modifier
                .weight(1f),
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_white_logo),
                contentDescription = "Route Logo",
                alignment = Alignment.Center,
                modifier = Modifier.scale(3f)
            )
        }
        backgroundGradient(
            colors = listOf(
                primary.copy(alpha = .5f),
                white.copy(alpha = .4f),
            ),
            Modifier
                .weight(1f),
        )


    }
}


@Composable
fun backgroundGradient(colors: List<Color>, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors

                )
            )
            .blur(150.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun SplashBodyPreview() {
    QuickBuyTheme {
        SplashScreen(rememberNavController())
    }
}
