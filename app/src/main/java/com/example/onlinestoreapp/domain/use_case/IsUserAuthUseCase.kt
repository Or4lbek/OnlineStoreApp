package com.example.onlinestoreapp.domain.use_case

import com.example.onlinestoreapp.domain.repository.AuthTokenRepository

class IsUserAuthUseCase(private val authRepository: AuthTokenRepository) {

    operator fun invoke(): Boolean {
        return authRepository.isUserAuth()
    }
}