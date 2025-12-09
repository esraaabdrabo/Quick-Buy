package com.route.quickbuy.screens.products.states

import ProductsRepoImpl
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.route.data.dataSources.products.ProductsRemoteDataSourceImpl
import com.route.domain.usecases.products.GetProductsUseCase
import com.route.quickbuy.screens.products.ProductsPagingSource

class ProductsViewModel : ViewModel() {

    private val getProductsUseCase: GetProductsUseCase by lazy {
        GetProductsUseCase(ProductsRepoImpl(ProductsRemoteDataSourceImpl()))

    }
    private val pager = Pager(
        config = PagingConfig(
            pageSize = 30,
            enablePlaceholders = false,
            prefetchDistance = 3
        ),
        pagingSourceFactory = {
            ProductsPagingSource(
                getProductsUseCase
            )
        }
    )
    val productsFlow = pager.flow.cachedIn(viewModelScope)

}