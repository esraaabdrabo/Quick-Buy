package com.route.quickbuy.ui.components

import com.route.quickbuy.R

enum class BottomBarItems(val iconID: Int, val labelResId: Int) {
    Home(R.drawable.home, R.string.home),
    Category(R.drawable.category, R.string.categories),
    WishList(R.drawable.heart, R.string.wishlist),
    Profile(R.drawable.profile, R.string.profile)
}