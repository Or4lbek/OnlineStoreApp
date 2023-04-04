package com.example.onlinestoreapp.domain.repository

import com.example.onlinestoreapp.domain.model.Categories
import com.example.onlinestoreapp.domain.model.DetailProduct
import com.example.onlinestoreapp.domain.model.HintWords
import com.example.onlinestoreapp.domain.model.LatestProducts
import com.example.onlinestoreapp.domain.model.*
import com.example.onlinestoreapp.domain.network.Response

interface OnlineStoreRepository {

    suspend fun getLatestProducts(): Response<LatestProducts>
    suspend fun getFlashSaleProducts(): Response<FlashSaleProducts>
    suspend fun getHintWords(): Response<HintWords>

    suspend fun getDetailProduct(): Response<DetailProduct>

    suspend fun getCategories(): Categories
}