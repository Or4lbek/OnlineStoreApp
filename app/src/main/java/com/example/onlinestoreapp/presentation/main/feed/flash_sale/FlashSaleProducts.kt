package com.example.onlinestoreapp.presentation.main.feed.flash_sale

import com.google.gson.annotations.SerializedName

data class FlashSaleProducts(
    @SerializedName("flash_sale")
    val flashSales: List<FlashSale>
) {
    override fun toString(): String {
        return "FlashSaleProducts(flash_sale=$flashSales)"
    }
}