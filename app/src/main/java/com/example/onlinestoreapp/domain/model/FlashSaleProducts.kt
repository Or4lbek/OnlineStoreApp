package com.example.onlinestoreapp.domain.model

import com.google.gson.annotations.SerializedName

data class FlashSaleProducts(
    @SerializedName("flash_sale")
    val flashSales: List<FlashSale>
) {
    override fun toString(): String {
        return "FlashSaleProducts(flash_sale=$flashSales)"
    }
}
//Api Module