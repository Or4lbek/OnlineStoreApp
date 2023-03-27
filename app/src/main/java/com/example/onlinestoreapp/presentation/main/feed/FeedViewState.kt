package com.example.onlinestoreapp.presentation.main.feed

import com.example.onlinestoreapp.domain.model.UserAuth
import com.example.onlinestoreapp.presentation.main.feed.flash_sale.FlashSaleProducts
import com.example.onlinestoreapp.presentation.main.feed.latest.LatestProducts


sealed class FeedViewState {
    data class AllDataFetched(
        val latestProducts: LatestProducts,
        val flashSaleProducts: FlashSaleProducts
    ) : FeedViewState()

    data class OnUserFetched(val userAuth: UserAuth?) : FeedViewState()
}