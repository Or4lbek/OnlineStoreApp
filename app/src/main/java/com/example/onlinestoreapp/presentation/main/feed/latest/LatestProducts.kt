package com.example.onlinestoreapp.presentation.main.feed.latest

data class LatestProducts(
    val latest: List<Latest>
) {
    override fun toString(): String {
        return "LatestProducts(latest=$latest)"
    }
}