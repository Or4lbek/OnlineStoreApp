package com.example.onlinestoreapp.data.mapper

import com.example.onlinestoreapp.data.model.LatestApiModel
import com.example.onlinestoreapp.data.model.LatestProductsApiModel
import com.example.onlinestoreapp.domain.mapper.ResponseResultMapper
import com.example.onlinestoreapp.domain.model.Latest
import com.example.onlinestoreapp.domain.model.LatestProducts

class LatestProductsApiModelMapper :
    ResponseResultMapper<LatestProductsApiModel, LatestProducts>() {
    override fun mapData(from: LatestProductsApiModel): LatestProducts {
        val latest = mutableListOf<Latest>()
        from.latest.forEach {
            latest.add(getLatestProducts(it))
        }
        return LatestProducts(latest)
    }

    private fun getLatestProducts(from: LatestApiModel) = Latest(
        category = from.category,
        image_url = from.image_url,
        name = from.name,
        price = from.price
    )
}