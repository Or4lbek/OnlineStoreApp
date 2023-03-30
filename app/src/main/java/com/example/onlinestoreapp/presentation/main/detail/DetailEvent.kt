package com.example.onlinestoreapp.presentation.main.detail

sealed class DetailEvent {
    object OnQuantityAdded : DetailEvent()
    object OnQuantitySubtracted : DetailEvent()
}