package com.example.onlinestoreapp.utils

import android.content.Context
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.util.Patterns
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.domain.network.Response
import com.google.android.material.textfield.TextInputLayout
import retrofit2.HttpException

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

fun TextInputLayout.checkPasswordLength(context: Context) {
    this.editText?.doOnTextChanged { text, _, _, _ ->
        if (text == null) {
            return@doOnTextChanged
        }

        error = if (text.length < 8) context.getString(R.string.un_valid_password) else null
    }
}

fun TextInputLayout.checkIsEmailValid(context: Context) {
    this.editText?.doOnTextChanged { text, _, _, _ ->
        if (text == null) {
            return@doOnTextChanged
        }
        error = if (Patterns.EMAIL_ADDRESS.matcher(text)
                .matches()
        ) null else context.getString(R.string.email_is_not_valid)
    }
}

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

fun <T> Throwable.catchError(): Response<T> {
    this.printStackTrace()
    return when (this) {
        is HttpException -> {
            Response.Error(R.string.http_exception_message)
        }
        is IllegalStateException -> {
            Response.Error(R.string.something_went_wrong_message)
        }
        else -> {
            Response.NetworkError
        }
    }
}
