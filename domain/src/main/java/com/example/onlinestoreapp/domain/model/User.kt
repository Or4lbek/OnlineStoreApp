package com.example.onlinestoreapp.domain.model


data class User(
    var id: Int = UNDEFINED_ID,
    val email: String,
    var name: String,
    var password: String,
    var image: String? = null
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
