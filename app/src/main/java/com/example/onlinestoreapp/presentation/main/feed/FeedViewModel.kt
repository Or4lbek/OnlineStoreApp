package com.example.onlinestoreapp.presentation.main.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestoreapp.domain.network.Response
import com.example.onlinestoreapp.domain.presentation.AdvancedViewState
import com.example.onlinestoreapp.domain.repository.AuthorizationRepository
import com.example.onlinestoreapp.domain.repository.OnlineStoreRepository
import com.example.onlinestoreapp.domain.use_case.GetAuthTokenUseCase
import com.example.onlinestoreapp.presentation.main.feed.latest.LatestProducts
import kotlinx.coroutines.launch

class FeedViewModel(
    private val repository: AuthorizationRepository,
    private val getAuthTokenUseCase: GetAuthTokenUseCase,
    private val onlineStoreRepository: OnlineStoreRepository
) : ViewModel() {

    private val _viewState = MutableLiveData<AdvancedViewState<FeedViewState>>()
    var viewState: LiveData<AdvancedViewState<FeedViewState>> = _viewState

    fun onFeedEvent(event: FeedEvent) {
        when (event) {
            is FeedEvent.OnUserTypes -> {

            }
            is FeedEvent.OnScreenOpen -> {
                fetchUser()
                fetchLatestProducts()
            }
        }
    }

    private fun fetchUser() {
        _viewState.value = AdvancedViewState.Loading
        viewModelScope.launch {
            val user = repository.getUserByTokenId(getAuthTokenUseCase())
            _viewState.value = AdvancedViewState.Data(FeedViewState.OnUserFetched(user))
        }
    }

    private fun fetchLatestProducts() {
        viewModelScope.launch {
            when (val latestProductsResponse = onlineStoreRepository.getLatestProducts()) {
                is Response.Error -> {
                    onError(latestProductsResponse.error)
                }
                is Response.NetworkError -> onNetworkError()
                is Response.Success -> {
                    fetchFlashSaleProducts(latestProductsResponse.data)
                }
            }
        }
    }

    private fun fetchFlashSaleProducts(latestProducts: LatestProducts) {
        viewModelScope.launch {
            when (val flashSaleProductsResponse = onlineStoreRepository.getFlashSaleProducts()) {
                is Response.Error -> {
                    onError(flashSaleProductsResponse.error)
                }
                is Response.NetworkError -> onNetworkError()
                is Response.Success -> {
                    _viewState.value = AdvancedViewState.Data(
                        FeedViewState.AllDataFetched(
                            latestProducts,
                            flashSaleProductsResponse.data
                        )
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