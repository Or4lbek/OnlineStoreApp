package com.example.onlinestoreapp.utils

import android.content.Context
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import androidx.core.widget.doOnTextChanged
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.domain.network.Response
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.*
import retrofit2.HttpException

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

fun TextInputLayout.checkPasswordLength(context: Context) {
    this.editText?.doOnTextChanged { text, _, _, _ ->
        if (text == null) {
            return@doOnTextChanged
        }

        error = if (text.length < 8) context.getString(R.string.need_more_8) else null
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
            Response.Error("HttpException")
        }
        is IllegalStateException -> {
            Response.Error("Something went wrong")
        }
        else -> {
            Response.NetworkError
        }
    }
}

fun AutoCompleteTextView.afterTextChangedDebounce(input: (String) -> Unit) {
    val delayMillis = 500L
    var lastInput = ""
    var debounceJob: Job? = null
    val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            if (editable != null) {
                val newtInput = editable.toString()
                debounceJob?.cancel()
                if (lastInput != newtInput) {
                    lastInput = newtInput
                    debounceJob = uiScope.launch {
                        delay(delayMillis)
                        if (lastInput == newtInput) {
                            input(newtInput)
                        }
                    }
                }
            }
        }

        override fun beforeTextChanged(cs: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(cs: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}