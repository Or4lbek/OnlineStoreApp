package com.example.onlinestoreapp.data.model

import com.google.gson.annotations.SerializedName

data class HintWordsApiModel(
    @SerializedName("words")
    val words: List<String>
)