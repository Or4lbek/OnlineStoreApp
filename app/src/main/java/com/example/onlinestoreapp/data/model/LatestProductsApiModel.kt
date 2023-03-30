package com.example.onlinestoreapp.data.model

data class LatestProductsApiModel(
    val latest: List<LatestApiModel>
) {
    override fun toString(): String {
        return "LatestProducts(latest=$latest)"
    }
}