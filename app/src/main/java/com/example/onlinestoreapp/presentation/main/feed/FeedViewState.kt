package com.example.onlinestoreapp.presentation.main.feed

import com.example.onlinestoreapp.domain.model.User
import com.example.onlinestoreapp.domain.model.Categories
import com.example.onlinestoreapp.domain.model.FlashSaleProducts
import com.example.onlinestoreapp.domain.model.LatestProducts
import com.example.onlinestoreapp.domain.model.HintWords


sealed class FeedViewState {
    data class AllDataFetched(
        val latestProducts: LatestProducts,
        val flashSaleProducts: FlashSaleProducts,
        val categories: Categories
    ) : FeedViewState()

    data class OnUserFetched(val user: User?) : FeedViewState()

    data class OnHintWordsFetched(val hintWords: HintWords) : FeedViewState()
}