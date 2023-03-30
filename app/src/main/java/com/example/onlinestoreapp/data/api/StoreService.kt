package com.example.onlinestoreapp.data.api

import com.example.onlinestoreapp.domain.model.DetailProduct
import com.example.onlinestoreapp.domain.model.FlashSaleProducts
import com.example.onlinestoreapp.domain.model.LatestProducts
import com.example.onlinestoreapp.domain.model.HintWords
import retrofit2.http.GET

interface StoreService {
    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatestProducts(): LatestProducts

    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getFlashSaleProducts(): FlashSaleProducts

    @GET("4c9cd822-9479-4509-803d-63197e5a9e19")
    suspend fun getSearchingProducts(): HintWords

    @GET("f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun getDetailProduct(): DetailProduct
}