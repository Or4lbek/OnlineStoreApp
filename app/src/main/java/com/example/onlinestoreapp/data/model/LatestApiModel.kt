package com.example.onlinestoreapp.data.model

import com.google.gson.annotations.SerializedName

data class LatestApiModel(
    val category: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val name: String,
    val price: Int
)