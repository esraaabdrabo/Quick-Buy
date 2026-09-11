package com.route.quickbuy.features.products.states.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.route.domain.entities.ProductDetailsEntity
import com.route.domain.usecases.products.GetProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel()
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetails: GetProductDetailsUseCase
) : ViewModel() {
    val state = mutableStateOf(ViewModelState<ProductDetailsEntity>())

    suspend fun getProductDetails(id: String) {
        state.value = LoadingState()
        try {
            val details = getProductDetails.invoke(id)
            state.value = DataState(details)
        } catch (e: Exception) {
            state.value = ErrorState(e.message ?: "Unknown Error")
        }
    }
}