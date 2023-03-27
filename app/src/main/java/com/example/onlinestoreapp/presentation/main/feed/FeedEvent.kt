package com.example.onlinestoreapp.presentation.main.feed

sealed class FeedEvent {
    data class OnUserTypes(val text: String) : FeedEvent()
    object OnScreenOpen : FeedEvent()
}