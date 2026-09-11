package com.route.quickbuy.features.products.states.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.route.domain.entities.ProductDetailsEntity
import com.route.domain.usecases.products.GetProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetails: GetProductDetailsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ViewModelState<ProductDetailsEntity>())
    val state: State<ViewModelState<ProductDetailsEntity>> = _state


    suspend fun getProductDetails(id: String) {
        _state.value = LoadingState()

        try {
            val details = getProductDetails.invoke(id)
            _state.value = DataState(details)
        } catch (e: Exception) {
            _state.value = ErrorState(e.message ?: "Unknown Error")
        }
    }
}