package com.example.onlinestoreapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserAuth(
    var id: Int = UNDEFINED_ID,
    val email: String,
    var name: String,
    var password: String
) : Parcelable {
    companion object {
        const val UNDEFINED_ID = 0
    }

    override fun toString(): String {
        return "UserAuth(id=$id, email='$email', name='$name', password='$password')"
    }
}
