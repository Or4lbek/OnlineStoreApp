package com.example.onlinestoreapp.presentation.main.feed

sealed class FeedEvent {
    object OnScreenOpen : FeedEvent()
}