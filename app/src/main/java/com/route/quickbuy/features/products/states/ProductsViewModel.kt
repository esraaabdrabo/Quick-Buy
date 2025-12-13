package com.route.quickbuy.features.products.states

import ProductsRepoImpl
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.route.data.dataSources.products.ProductsRemoteDataSourceImpl
import com.route.domain.entities.PaginationResponse
import com.route.domain.entities.ProductEntity
import com.route.domain.usecases.products.GetProductsUseCase
import com.route.quickbuy.features.products.ProductsPagingSource
import com.route.quickbuy.features.products.states.details.DataState
import com.route.quickbuy.features.products.states.details.ErrorState
import com.route.quickbuy.features.products.states.details.LoadingState
import com.route.quickbuy.features.products.states.details.ViewModelState

class ProductsViewModel : ViewModel() {

    val state = mutableStateOf<ViewModelState<List<ProductEntity>>>(LoadingState())

    suspend fun getProducts(offset: Int): PaginationResponse<List<ProductEntity>>? {
        val getProducts =
            GetProductsUseCase(ProductsRepoImpl(ProductsRemoteDataSourceImpl()))

        try {
           
            val response: PaginationResponse<List<ProductEntity>> =
                getProducts.invoke(page = offset)
            state.value = DataState(response.data)
            return response
        } catch (e: Exception) {
            state.value = ErrorState(e.message ?: "Unknown Error")
            return null
        }

    }

    private val pagingSource = ProductsPagingSource { offset ->
        getProducts(offset)
    }
    private val pager = Pager(
        config = PagingConfig(
            pageSize = 30,
            enablePlaceholders = false,
            prefetchDistance = 3
        ),
        pagingSourceFactory = { pagingSource }
    )
    val productsFlow = pager.flow.cachedIn(viewModelScope)

}