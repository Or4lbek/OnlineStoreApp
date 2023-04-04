package com.example.onlinestoreapp.data.model

import com.google.gson.annotations.SerializedName

data class FlashSaleApiModel(
    val category: String,
    val discount: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    val name: String,
    val price: Double
)