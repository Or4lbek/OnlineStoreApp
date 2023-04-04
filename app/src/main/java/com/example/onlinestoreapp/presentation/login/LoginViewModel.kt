package com.example.onlinestoreapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestoreapp.core.UiText
import com.example.onlinestoreapp.domain.presentation.ViewState
import com.example.onlinestoreapp.domain.repository.AuthorizationRepository
import com.example.onlinestoreapp.domain.use_case.CreateAuthTokenUseCase
import com.example.onlinestoreapp.domain.use_case.ValidateEmailUseCase
import com.example.onlinestoreapp.domain.use_case.ValidatePasswordUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LoginViewModel(
    private val repository: AuthorizationRepository,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val createAuthTokenUseCase: CreateAuthTokenUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState<LoginViewState>>()
    val viewState: LiveData<ViewState<LoginViewState>> = _viewState

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
            val uiText = errorResult.errorMessage
            _viewState.value = ViewState.Error(uiText as UiText)
            return
        }
        loginUser(email, password)
    }


    private fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _viewState.value = ViewState.Loading
            delay(2000)
            val userId = repository.loginUser(
                email,
                password
            )
            _viewState.value = ViewState.Data(
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