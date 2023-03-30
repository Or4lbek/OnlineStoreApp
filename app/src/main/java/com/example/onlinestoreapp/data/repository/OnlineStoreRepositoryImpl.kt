package com.example.onlinestoreapp.data.repository

import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.data.api.StoreService
import com.example.onlinestoreapp.domain.model.*
import com.example.onlinestoreapp.domain.network.Response
import com.example.onlinestoreapp.domain.repository.OnlineStoreRepository
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

    override suspend fun getHintWords(): Response<HintWords> {
        return try {
            withContext(Dispatchers.IO) {
                Response.Success(service.getSearchingProducts())
            }
        } catch (e: Exception) {
            e.catchError()
        }
    }

    override suspend fun getDetailProduct(): Response<DetailProduct> {
        return try {
            withContext(Dispatchers.IO) {
                Response.Success(service.getDetailProduct())
            }
        } catch (e: Exception) {
            e.catchError()
        }
    }

    override suspend fun getCategories(): Categories {
        val categories = mutableListOf<Category>()
        categories.add(Category(name = "Phones", image = R.drawable.ic_phone))
        categories.add(Category(name = "Headphones", image = R.drawable.ic_headphones))
        categories.add(Category(name = "Games", image = R.drawable.ic_game))
        categories.add(Category(name = "Cars", image = R.drawable.ic_car))
        categories.add(Category(name = "Furniture", image = R.drawable.ic_bed))
        categories.add(Category(name = "Kids", image = R.drawable.ic_bot))
        categories.add(Category(name = "Phones", image = R.drawable.ic_phone))
        categories.add(Category(name = "Headphones", image = R.drawable.ic_headphones))
        return Categories(categories)
    }
}