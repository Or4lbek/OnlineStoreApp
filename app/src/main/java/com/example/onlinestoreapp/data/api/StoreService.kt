package com.example.onlinestoreapp.data.api

import com.example.onlinestoreapp.presentation.main.feed.flash_sale.FlashSaleProducts
import com.example.onlinestoreapp.presentation.main.feed.latest.LatestProducts
import retrofit2.http.GET

interface StoreService {
    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatestProducts(): LatestProducts

    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getFlashSaleProducts(): FlashSaleProducts
}