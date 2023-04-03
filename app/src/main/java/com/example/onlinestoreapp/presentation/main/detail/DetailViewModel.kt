package com.example.onlinestoreapp.presentation.main.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestoreapp.domain.network.Response
import com.example.onlinestoreapp.domain.presentation.AdvancedViewState
import com.example.onlinestoreapp.domain.repository.OnlineStoreRepository
import kotlinx.coroutines.launch


class DetailViewModel(
    private val onlineStoreRepository: OnlineStoreRepository
) : ViewModel() {

    private val _viewState = MutableLiveData<AdvancedViewState<DetailViewState>>()
    val viewState: LiveData<AdvancedViewState<DetailViewState>> = _viewState

    init {
        fetchDetailProduct()
    }

    private fun fetchDetailProduct() {
        _viewState.value = AdvancedViewState.Loading
        viewModelScope.launch {
            when (val detailProduct = onlineStoreRepository.getDetailProduct()) {
                is Response.Error -> onError(detailProduct.error)
                Response.NetworkError -> onNetworkError()
                is Response.Success -> {
                    _viewState.value = AdvancedViewState.Data(
                        DetailViewState.AllDataFetched(detailProduct.data)
                    )
                }
            }
        }
    }

    private fun onError(errorString: String) =
        _viewState.postValue(AdvancedViewState.Error(errorString))

    private fun onNetworkError() =
        _viewState.postValue(AdvancedViewState.NetworkError)
}