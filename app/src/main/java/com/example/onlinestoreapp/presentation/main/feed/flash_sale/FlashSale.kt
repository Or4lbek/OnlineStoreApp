package com.example.onlinestoreapp.presentation.main.feed.flash_sale

data class FlashSale(
    val category: String,
    val discount: Int,
    val image_url: String,
    val name: String,
    val price: Double
) {
    override fun toString(): String {
        return "FlashSale(category='$category', discount=$discount, image_url='$image_url', name='$name', price=$price)"
    }
}