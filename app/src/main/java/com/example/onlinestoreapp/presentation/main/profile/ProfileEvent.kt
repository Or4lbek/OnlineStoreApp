package com.example.onlinestoreapp.presentation.main.profile


sealed class ProfileEvent {
    data class OnUploadedItem(val imageUri: String) : ProfileEvent()
    object OnUserLogOut : ProfileEvent()
    object OnScreenOpen : ProfileEvent()
}