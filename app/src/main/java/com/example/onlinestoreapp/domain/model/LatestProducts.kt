package com.example.onlinestoreapp.domain.model

data class LatestProducts(
    val latest: List<Latest>
) {
    override fun toString(): String {
        return "LatestProducts(latest=$latest)"
    }
}