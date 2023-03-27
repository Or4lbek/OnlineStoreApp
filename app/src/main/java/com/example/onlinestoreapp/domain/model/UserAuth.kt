package com.example.onlinestoreapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserAuth(
    var id: Int = UNDEFINED_ID,
    val email: String,
    var name: String,
    var password: String,
    var image: String = DEFAULT_IMAGE
) : Parcelable {
    companion object {
        const val UNDEFINED_ID = 0
        const val DEFAULT_IMAGE =
            "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50?s=200"
    }

    override fun toString(): String {
        return "UserAuth(id=$id, email='$email', name='$name', password='$password')"
    }
}
