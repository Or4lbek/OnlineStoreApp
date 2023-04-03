package com.example.onlinestoreapp.data.remote

import com.example.onlinestoreapp.data.model.DetailProductApiModel
import com.example.onlinestoreapp.data.model.FlashSaleProductsApiModel
import com.example.onlinestoreapp.data.model.HintWordsApiModel
import com.example.onlinestoreapp.data.model.LatestProductsApiModel
import retrofit2.http.GET

interface StoreService {
    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatestProducts(): LatestProductsApiModel

    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getFlashSaleProducts(): FlashSaleProductsApiModel

    @GET("4c9cd822-9479-4509-803d-63197e5a9e19")
    suspend fun getSearchingProducts(): HintWordsApiModel

    @GET("f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun getDetailProduct(): DetailProductApiModel
}