package com.example.onlinestoreapp.presentation.main.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestoreapp.domain.model.LatestProducts
import com.example.onlinestoreapp.domain.network.Response
import com.example.onlinestoreapp.domain.presentation.ViewState
import com.example.onlinestoreapp.domain.repository.AuthorizationRepository
import com.example.onlinestoreapp.domain.repository.OnlineStoreRepository
import com.example.onlinestoreapp.domain.use_case.GetAuthTokenUseCase
import kotlinx.coroutines.launch

class FeedViewModel(
    private val authorizationRepository: AuthorizationRepository,
    private val getAuthTokenUseCase: GetAuthTokenUseCase,
    private val onlineStoreRepository: OnlineStoreRepository
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState<FeedViewState>>()
    var viewState: LiveData<ViewState<FeedViewState>> = _viewState


    fun onFeedEvent(event: FeedEvent) {
        when (event) {
            is FeedEvent.OnScreenOpen -> {
                fetchUser()
                fetchLatestProducts()
                fetchHintWords()
            }
        }
    }

    private fun fetchHintWords() {
        viewModelScope.launch {
            when (val hintWordsResponse = onlineStoreRepository.getHintWords()) {
                is Response.Error -> {
                    onError(hintWordsResponse.error)
                }
                is Response.NetworkError -> onNetworkError()
                is Response.Success -> {
                    _viewState.value =
                        ViewState.Data(
                            FeedViewState.OnHintWordsFetched(
                                hintWords = hintWordsResponse.data
                            )
                        )
                }
            }
        }
    }

    private fun fetchUser() {
        _viewState.value = ViewState.Loading
        viewModelScope.launch {
            val user = authorizationRepository.getUserByTokenId(getAuthTokenUseCase())
            _viewState.value = ViewState.Data(FeedViewState.OnUserFetched(user))
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
                    _viewState.value = ViewState.Data(
                        FeedViewState.AllDataFetched(
                            latestProducts,
                            flashSaleProductsResponse.data,
                            onlineStoreRepository.getCategories()
                        )
                    )
                }
            }
        }
    }

    private fun onError(errorString: Int) =
        _viewState.postValue(ViewState.Error(errorString))

    private fun onNetworkError() =
        _viewState.postValue(ViewState.NetworkError)
}