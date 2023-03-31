package com.example.onlinestoreapp.data.mapper

import com.example.onlinestoreapp.data.model.FlashSaleApiModel
import com.example.onlinestoreapp.data.model.FlashSaleProductsApiModel
import com.example.onlinestoreapp.domain.mapper.ResponseResultMapper
import com.example.onlinestoreapp.domain.model.FlashSale
import com.example.onlinestoreapp.domain.model.FlashSaleProducts

class FlashSaleProductsApiModelMapper :
    ResponseResultMapper<FlashSaleProductsApiModel, FlashSaleProducts>() {
    override fun mapData(from: FlashSaleProductsApiModel): FlashSaleProducts {
        val flashSaleProducts = mutableListOf<FlashSale>()
        from.flashSales.forEach {
            flashSaleProducts.add(getFlashSaleProducts(it))
        }
        return FlashSaleProducts(flashSaleProducts)
    }

    private fun getFlashSaleProducts(from: FlashSaleApiModel) = FlashSale(
        category = from.category,
        discount = from.discount,
        image_url = from.image_url,
        name = from.name,
        price = from.price
    )
}