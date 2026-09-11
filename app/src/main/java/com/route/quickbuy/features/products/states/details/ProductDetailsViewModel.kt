package com.route.quickbuy.features.products.states.details

import ProductsRepoImpl
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.route.data.dataSources.products.ProductsRemoteDataSourceImpl
import com.route.domain.entities.ProductDetailsEntity
import com.route.domain.usecases.products.GetProductDetailsUseCase

class ProductDetailsViewModel() : ViewModel() {
    private val _state = mutableStateOf(ViewModelState<ProductDetailsEntity>())
    val state: State<ViewModelState<ProductDetailsEntity>> = _state


    suspend fun getProductDetails(id: String) {
        val getProductDetailsUseCase =
            GetProductDetailsUseCase(ProductsRepoImpl(ProductsRemoteDataSourceImpl()))
        _state.value = LoadingState()

        try {
            val details = getProductDetailsUseCase.invoke(id)
            _state.value = DataState(details)
        } catch (e: Exception) {
            _state.value = ErrorState(e.message ?: "Unknown Error")
        }
    }
}