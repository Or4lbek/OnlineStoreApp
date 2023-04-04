package com.example.onlinestoreapp.data.repository

import com.example.onlinestoreapp.core.R
import com.example.onlinestoreapp.core.UiText
import com.example.onlinestoreapp.data.mapper.*
import com.example.onlinestoreapp.data.model.CategoriesApiModel
import com.example.onlinestoreapp.data.model.CategoryApiModel
import com.example.onlinestoreapp.data.remote.StoreService
import com.example.onlinestoreapp.domain.model.*
import com.example.onlinestoreapp.domain.network.Response
import com.example.onlinestoreapp.domain.repository.OnlineStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class OnlineStoreRepositoryImpl(
    private val service: StoreService,
    private val latestProductsApiModelMapper: LatestProductsApiModelMapper,
    private val flashSaleProductsApiModelMapper: FlashSaleProductsApiModelMapper,
    private val hintWordsApiModelMapper: HintWordsApiModelMapper,
    private val detailProductApiModelMapper: DetailProductApiModelMapper,
    private val categoriesApiModelMapper: CategoriesApiModelMapper
) : OnlineStoreRepository {

    override suspend fun getLatestProducts(): Response<LatestProducts> {
        return try {
            withContext(Dispatchers.IO) {
                latestProductsApiModelMapper.map(
                    Response.Success(service.getLatestProducts())
                )
            }
        } catch (e: Exception) {
            e.catchError()
        }
    }

    override suspend fun getFlashSaleProducts(): Response<FlashSaleProducts> {
        return try {
            withContext(Dispatchers.IO) {
                flashSaleProductsApiModelMapper.map(Response.Success(service.getFlashSaleProducts()))
            }
        } catch (e: Exception) {
            e.catchError()
        }
    }

    override suspend fun getHintWords(): Response<HintWords> {
        return try {
            withContext(Dispatchers.IO) {
                hintWordsApiModelMapper.map(Response.Success(service.getSearchingProducts()))
            }
        } catch (e: Exception) {
            e.catchError()
        }
    }

    override suspend fun getDetailProduct(): Response<DetailProduct> {
        return try {
            withContext(Dispatchers.IO) {
                detailProductApiModelMapper.map(Response.Success(service.getDetailProduct()))
            }
        } catch (e: Exception) {
            e.catchError()
        }
    }

    override suspend fun getCategories(): Categories {
        val categories = mutableListOf<CategoryApiModel>()
        categories.add(
            CategoryApiModel(
                name = R.string.phones,
                image = R.drawable.ic_phone
            )
        )
        categories.add(
            CategoryApiModel(
                name = R.string.headphones,
                image = R.drawable.ic_headphones
            )
        )
        categories.add(CategoryApiModel(name = R.string.games, image = R.drawable.ic_game))
        categories.add(CategoryApiModel(name = R.string.cars, image = R.drawable.ic_car))
        categories.add(CategoryApiModel(name = R.string.furniture, image = R.drawable.ic_bed))
        categories.add(CategoryApiModel(name = R.string.kids, image = R.drawable.ic_bot))
        categories.add(CategoryApiModel(name = R.string.phones, image = R.drawable.ic_phone))
        categories.add(
            CategoryApiModel(
                name = R.string.headphones,
                image = R.drawable.ic_headphones
            )
        )
        return categoriesApiModelMapper.map(CategoriesApiModel(categories))
    }
}

fun <T> Throwable.catchError(): Response<T> {
    this.printStackTrace()
    return when (this) {
        is HttpException -> {
            Response.Error(UiText.StringResource(R.string.http_exception_message))
        }
        is IllegalStateException -> {
            Response.Error(UiText.StringResource(R.string.something_went_wrong_message))
        }
        else -> {
            Response.NetworkError
        }
    }
}