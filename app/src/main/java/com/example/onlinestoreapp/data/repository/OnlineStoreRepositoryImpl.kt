package com.example.onlinestoreapp.data.repository

import com.example.onlinestoreapp.data.api.StoreService
import com.example.onlinestoreapp.domain.network.Response
import com.example.onlinestoreapp.domain.repository.OnlineStoreRepository
import com.example.onlinestoreapp.presentation.main.feed.flash_sale.FlashSaleProducts
import com.example.onlinestoreapp.presentation.main.feed.latest.LatestProducts
import com.example.onlinestoreapp.utils.catchError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OnlineStoreRepositoryImpl(private val service: StoreService) : OnlineStoreRepository {

    override suspend fun getLatestProducts(): Response<LatestProducts> {
        return try {
            withContext(Dispatchers.IO) {
                Response.Success(service.getLatestProducts())
            }
        } catch (e: Exception) {
            e.catchError()
        }
    }

    override suspend fun getFlashSaleProducts(): Response<FlashSaleProducts> {
        return try {
            withContext(Dispatchers.IO) {
                Response.Success(service.getFlashSaleProducts())
            }
        } catch (e: Exception) {
            e.catchError()
        }
    }
}