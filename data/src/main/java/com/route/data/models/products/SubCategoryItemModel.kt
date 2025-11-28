package com.route.data.models.products

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubCategoryItemModel(
    val name: String? = null,
    val id: String? = null,
    val category: String? = null,
    val slug: String? = null
) : Parcelable