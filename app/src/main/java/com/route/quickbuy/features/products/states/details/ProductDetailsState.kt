package com.route.quickbuy.features.products.states.details

open class ViewModelState<T> {}
class LoadingState<T> : ViewModelState<T>()
data class ErrorState<T>(val error: String) : ViewModelState<T>()
data class DataState<T>(val data: T) :
    ViewModelState<T>()
