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

    private var totalAmount: Int = 0;
    private var price: Int = 0

    init {
        fetchDetailProduct()
    }

    fun onDetailEvent(event: DetailEvent) {
        when (event) {
            DetailEvent.OnQuantityAdded -> {
                totalAmount += price
                _viewState.value =
                    AdvancedViewState.Data(DetailViewState.OnBasketChanged(totalAmount))
            }
            DetailEvent.OnQuantitySubtracted -> {
                totalAmount -= price
                if (totalAmount < 0) {
                    totalAmount = 0
                }
                _viewState.value =
                    AdvancedViewState.Data(DetailViewState.OnBasketChanged(totalAmount))
            }
        }
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
                    price = detailProduct.data.price
                }
            }
        }
    }

    private fun onError(errorString: String) =
        _viewState.postValue(AdvancedViewState.Error(errorString))

    private fun onNetworkError() =
        _viewState.postValue(AdvancedViewState.NetworkError)
}