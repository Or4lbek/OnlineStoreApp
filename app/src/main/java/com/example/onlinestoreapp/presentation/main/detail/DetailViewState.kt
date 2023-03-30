package com.example.onlinestoreapp.presentation.main.detail

import com.example.onlinestoreapp.domain.model.DetailProduct

sealed class DetailViewState {

    data class AllDataFetched(
        val detailProduct: DetailProduct
    ) : DetailViewState()

    data class OnBasketChanged(
        val basket: Int
    ) : DetailViewState()
}