package com.route.quickbuy.features.products

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.route.domain.entities.PaginationResponse
import com.route.domain.entities.ProductEntity
import okio.IOException

class ProductsPagingSource(private val getProducts: suspend (offset: Int) -> PaginationResponse<List<ProductEntity>>?) :
    PagingSource<Int, ProductEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductEntity> {

        try {
            val offset = params.key ?: 0
            val response = getProducts(offset)
            if (response == null) {
                return LoadResult.Error(
                    throwable = Throwable(message = "Unknown Error")
                )
            }
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