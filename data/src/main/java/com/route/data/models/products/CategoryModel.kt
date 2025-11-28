package com.route.data.models.products

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CategoryModel(
    val image: String? = null,
    val name: String? = null,
    val id: String? = null,
    val slug: String? = null
) : Parcelable