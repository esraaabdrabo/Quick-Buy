package com.route.data.models.productDetails

import android.os.Parcelable
import com.route.data.models.products.BrandModel
import com.route.data.models.products.CategoryModel
import com.route.data.models.products.SubCategoryItemModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDetailsModel(

    val sold: Double? = null,

    val images: List<String?>? = null,

    val quantity: Int? = null,

    val imageCover: String? = null,

    val description: String? = null,

    val title: String? = null,

    val ratingsQuantity: Int? = null,

    val ratingsAverage: Double? = null,

    val createdAt: String? = null,

    val price: Int? = null,

    val id: String? = null,

    val subcategory: List<SubCategoryItemModel?>? = null,

    val category: CategoryModel? = null,

    val brand: BrandModel? = null,


    ) : Parcelable


