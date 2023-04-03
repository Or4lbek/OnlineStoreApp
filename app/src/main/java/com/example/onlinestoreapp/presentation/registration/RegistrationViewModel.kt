package com.example.onlinestoreapp.presentation.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestoreapp.domain.model.User
import com.example.onlinestoreapp.domain.presentation.ViewState
import com.example.onlinestoreapp.domain.repository.AuthorizationRepository
import com.example.onlinestoreapp.domain.use_case.ValidateEmailUseCase
import com.example.onlinestoreapp.domain.use_case.ValidateNameUseCase
import com.example.onlinestoreapp.domain.use_case.ValidatePasswordUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val repository: AuthorizationRepository,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateNameUseCase: ValidateNameUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState<RegistrationViewState>>()
    var viewState: LiveData<ViewState<RegistrationViewState>> = _viewState

    fun onRegistrationEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.OnRegistrationClicked -> {
                validateFields(event.user)
            }
        }
    }

    private fun validateFields(user: User) {
        val emailResult = validateEmailUseCase(email = user.email)
        val passwordResult = validatePasswordUseCase(password = user.password)
        val nameResult = validateNameUseCase(user.name)

        val results = listOf(emailResult, nameResult, passwordResult)

        val errorResult = results.firstOrNull { it.errorMessage != null }

        if (errorResult?.errorMessage != null) {
            _viewState.value = ViewState.Error(errorResult.errorMessage)
            return
        }
        createUser(user)
    }

    private fun createUser(user: User) {
        viewModelScope.launch {
            _viewState.value = ViewState.Loading
            delay(3000)
            if (checkUser(user.email)) {
                _viewState.value = ViewState.Data(
                    RegistrationViewState.ShowUserWasNotCreated
                )
                return@launch
            }
            repository.createUser(user)
            _viewState.value = ViewState.Data(
                RegistrationViewState.ShowUserSuccessfullyCreated
            )
        }
    }

    private suspend fun checkUser(email: String): Boolean = repository.checkUser(email = email)
}