package com.example.onlinestoreapp.domain.model

import com.google.gson.annotations.SerializedName

data class HintWords(
    @SerializedName("words")
    val words: List<String>
)