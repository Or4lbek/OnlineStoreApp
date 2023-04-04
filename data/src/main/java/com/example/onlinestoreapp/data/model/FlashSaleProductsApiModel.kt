package com.example.onlinestoreapp.data.model

import com.google.gson.annotations.SerializedName

data class FlashSaleProductsApiModel(
    @SerializedName("flash_sale")
    val flashSales: List<FlashSaleApiModel>
)