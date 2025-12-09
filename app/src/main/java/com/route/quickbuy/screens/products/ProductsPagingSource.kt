package com.route.quickbuy.screens.products

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.route.domain.entities.PaginationResponse
import com.route.domain.entities.ProductEntity
import com.route.domain.usecases.products.GetProductsUseCase
import okio.IOException

class ProductsPagingSource(private val getProducts: GetProductsUseCase) :
    PagingSource<Int, ProductEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductEntity> {

        try {
            Log.e("TAG", "load: ${params.key}")
            val response: PaginationResponse<List<ProductEntity>> =
                getProducts.invoke(page = params.key ?: 1)

            return LoadResult.Page(
                data = response.data,
                prevKey = null,
                nextKey = response.metadata.nextPage
            )
        } catch (e: IOException) {
            return LoadResult.Error(
                throwable = Throwable(message = e.message)
            )
        } catch (e: Exception) {
            return LoadResult.Error(
                throwable = Throwable(message = e.message)
            )
        }

    }

    override fun getRefreshKey(state: PagingState<Int, ProductEntity>): Int? {
        TODO("Not yet implemented")
    }
}