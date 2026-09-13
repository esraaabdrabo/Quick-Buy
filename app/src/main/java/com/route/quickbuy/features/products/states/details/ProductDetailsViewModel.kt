package com.route.quickbuy.features.products.states.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.route.domain.core.AppResult
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
        when (val result = getProductDetails.invoke(id)) {
            is AppResult.Success -> state.value = DataState(result.data)
            is AppResult.Failure -> state.value = ErrorState(result.error.message)
        }
    }
}