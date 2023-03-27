package com.example.onlinestoreapp.domain.repository

import com.example.onlinestoreapp.domain.network.Response
import com.example.onlinestoreapp.presentation.main.feed.flash_sale.FlashSaleProducts
import com.example.onlinestoreapp.presentation.main.feed.latest.LatestProducts

interface OnlineStoreRepository {

    suspend fun getLatestProducts(): Response<LatestProducts>
    suspend fun getFlashSaleProducts(): Response<FlashSaleProducts>

}