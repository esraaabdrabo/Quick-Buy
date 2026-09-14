package com.route.quickbuy.features.products.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

private const val SEARCH_DEBOUNCE_MS = 400L

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProducts: GetProductsUseCase,
) : ViewModel() {

    val state = mutableStateOf<ViewModelState<List<ProductEntity>>>(LoadingState())

    private val searchQueryFlow = MutableStateFlow("")

    var searchValue by mutableStateOf("")
        private set

    var debouncedQuery by mutableStateOf("")
        private set

    var isDebouncing by mutableStateOf(false)
        private set

    val isSearching: Boolean
        get() = debouncedQuery.isNotEmpty()

    init {
        viewModelScope.launch {
            searchQueryFlow
                .debounce(SEARCH_DEBOUNCE_MS)
                .collect { debounced ->
                    debouncedQuery = debounced
                    isDebouncing = false
                }
        }
    }

    fun onSearchQueryChanged(query: String) {
        searchValue = query
        val trimmed = query.trim()
        if (trimmed.isEmpty()) {
            isDebouncing = false
            debouncedQuery = ""
            searchQueryFlow.value = ""
            return
        }
        isDebouncing = true
        searchQueryFlow.value = trimmed
    }

    fun filterProducts(loadedProducts: List<ProductEntity>): List<ProductEntity> {
        return loadedProducts.filter { it.name.contains(debouncedQuery, ignoreCase = true) }
    }

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
