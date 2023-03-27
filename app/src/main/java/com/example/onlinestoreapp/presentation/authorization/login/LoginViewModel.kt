package com.example.onlinestoreapp.presentation.authorization.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestoreapp.domain.presentation.AdvancedViewState
import com.example.onlinestoreapp.domain.repository.OnlineStoreRepository
import com.example.onlinestoreapp.domain.use_case.CreateAuthTokenUseCase
import com.example.onlinestoreapp.domain.use_case.ValidateEmailUseCase
import com.example.onlinestoreapp.domain.use_case.ValidatePasswordUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LoginViewModel(
    private val repository: OnlineStoreRepository,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val createAuthTokenUseCase: CreateAuthTokenUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<AdvancedViewState<LoginViewState>>()
    val viewState: LiveData<AdvancedViewState<LoginViewState>> = _viewState

    fun onLoginEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnLoginClicked -> {
                validateFields(event.email, event.password)
            }
        }
    }

    private fun validateFields(email: String, password: String) {
        val emailResult = validateEmailUseCase(email)
        val passwordResult = validatePasswordUseCase(password)

        val results = listOf(emailResult, passwordResult)
        val errorResult = results.firstOrNull { it.errorMessage != null }

        if (errorResult?.errorMessage != null) {
            _viewState.value = AdvancedViewState.Error(errorResult.errorMessage)
            return
        }
        loginUser(email, password)
    }


    private fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _viewState.value = AdvancedViewState.Loading
            delay(2000)
            val userId = repository.loginUser(
                email,
                password
            )
            _viewState.value = AdvancedViewState.Data(
                if (userId != null) {
                    createAuthToken(userId)
                    LoginViewState.OnUserFetched
                } else
                    LoginViewState.ShowUserDoNotExist
            )
        }
    }

    private fun createAuthToken(userId: Int) {
        createAuthTokenUseCase(userId)
    }
}