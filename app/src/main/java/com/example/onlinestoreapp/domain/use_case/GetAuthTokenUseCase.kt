package com.example.onlinestoreapp.domain.use_case

import com.example.onlinestoreapp.domain.repository.AuthTokenRepository

class GetAuthTokenUseCase(private val authRepository: AuthTokenRepository) {

    operator fun invoke(): Int {
        return authRepository.getAuthToken()
    }
}