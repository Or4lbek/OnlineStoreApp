package com.example.onlinestoreapp.presentation.main.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestoreapp.core.UiText
import com.example.onlinestoreapp.domain.network.Response
import com.example.onlinestoreapp.domain.presentation.ViewState
import com.example.onlinestoreapp.domain.repository.OnlineStoreRepository
import kotlinx.coroutines.launch


class DetailViewModel(
    private val onlineStoreRepository: OnlineStoreRepository
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState<DetailViewState>>()
    val viewState: LiveData<ViewState<DetailViewState>> = _viewState

    init {
        fetchDetailProduct()
    }

    private fun fetchDetailProduct() {
        _viewState.value = ViewState.Loading
        viewModelScope.launch {
            when (val detailProduct = onlineStoreRepository.getDetailProduct()) {
                is Response.Error -> onError(detailProduct.error)
                Response.NetworkError -> onNetworkError()
                is Response.Success -> {
                    _viewState.value = ViewState.Data(
                        DetailViewState.AllDataFetched(detailProduct.data)
                    )
                }
            }
        }
    }

    private fun onError(errorString: UiText) =
        _viewState.postValue(ViewState.Error(errorString))

    private fun onNetworkError() =
        _viewState.postValue(ViewState.NetworkError)
}