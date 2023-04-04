package com.example.onlinestoreapp.domain.use_case

import com.example.onlinestoreapp.domain.repository.AuthTokenRepository

class  CreateAuthTokenUseCase(private val authRepository: AuthTokenRepository) {

    operator fun invoke(token: Int) {
        return authRepository.storeAuthToken(token)
    }
}