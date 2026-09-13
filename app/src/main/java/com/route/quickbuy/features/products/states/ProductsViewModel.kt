package com.route.quickbuy.features.products.states

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.route.domain.core.AppResult
import com.route.domain.entities.PaginationResponse
import com.route.domain.entities.ProductEntity
import com.route.domain.usecases.products.GetProductsUseCase
import com.route.quickbuy.features.products.ProductsPagingSource
import com.route.quickbuy.features.products.states.details.DataState
import com.route.quickbuy.features.products.states.details.ErrorState
import com.route.quickbuy.features.products.states.details.LoadingState
import com.route.quickbuy.features.products.states.details.ViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
   private val getProducts: GetProductsUseCase,
) : ViewModel()  {

    val state = mutableStateOf<ViewModelState<List<ProductEntity>>>(LoadingState())

    suspend fun getProducts(offset: Int): PaginationResponse<List<ProductEntity>>? {
        return when (val result = getProducts.invoke(page = offset)) {
            is AppResult.Success -> {
                state.value = DataState(result.data.data)
                result.data
            }

            is AppResult.Failure -> {
                state.value = ErrorState(result.error.message)
                null
            }
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