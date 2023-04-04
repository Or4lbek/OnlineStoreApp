package com.example.onlinestoreapp.domain.use_case

import com.example.onlinestoreapp.domain.repository.AuthTokenRepository

class RemoveAuthTokenUseCase(private val authRepository: AuthTokenRepository) {

    operator fun invoke() {
        authRepository.clearAuthToken()
    }
}