package com.example.onlinestoreapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: Int = UNDEFINED_ID,
    val email: String,
    var name: String,
    var password: String,
    var image: String? = null
) : Parcelable {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
