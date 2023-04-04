package com.example.onlinestoreapp.data.model

import com.google.gson.annotations.SerializedName

data class DetailProductApiModel(
    val colors: List<String>,
    val description: String,
    @SerializedName("image_urls")
    val imageUrls: List<String>,
    val name: String,
    @SerializedName("number_of_reviews")
    val numberOfReviews: Int,
    val price: Int,
    val rating: Double
)