package com.example.onlinestoreapp.domain.model

data class Latest(
    val category: String,
    val image_url: String,
    val name: String,
    val price: Int
) {
    override fun toString(): String {
        return "Latest(category='$category', image_url='$image_url', name='$name', price=$price)"
    }
}