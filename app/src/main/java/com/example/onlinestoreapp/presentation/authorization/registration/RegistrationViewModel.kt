package com.example.onlinestoreapp.presentation.authorization.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestoreapp.domain.model.UserAuth
import com.example.onlinestoreapp.domain.presentation.AdvancedViewState
import com.example.onlinestoreapp.domain.repository.OnlineStoreRepository
import com.example.onlinestoreapp.domain.use_case.ValidateEmailUseCase
import com.example.onlinestoreapp.domain.use_case.ValidateNameUseCase
import com.example.onlinestoreapp.domain.use_case.ValidatePasswordUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val repository: OnlineStoreRepository,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateNameUseCase: ValidateNameUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<AdvancedViewState<RegistrationViewState>>()
    var viewState: LiveData<AdvancedViewState<RegistrationViewState>> = _viewState

    fun onRegistrationEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.OnRegistrationClicked -> {
                validateFields(event.userAuth)
            }
        }
    }

    private fun validateFields(userAuth: UserAuth) {
        val emailResult = validateEmailUseCase(email = userAuth.email)
        val passwordResult = validatePasswordUseCase(password = userAuth.password)
        val nameResult = validateNameUseCase(userAuth.name)

        val results = listOf(emailResult, nameResult, passwordResult)

        val errorResult = results.firstOrNull { it.errorMessage != null }

        if (errorResult?.errorMessage != null) {
            _viewState.value = AdvancedViewState.Error(errorResult.errorMessage)
            return
        }
        createUser(userAuth)
    }

    private fun createUser(userAuth: UserAuth) {
        viewModelScope.launch {
            _viewState.value = AdvancedViewState.Loading
            delay(3000)
            if (checkUser(userAuth.email)) {
                _viewState.value = AdvancedViewState.Data(
                    RegistrationViewState.ShowUserWasNotCreated
                )
                return@launch
            }
            repository.createUser(userAuth)
            _viewState.value = AdvancedViewState.Data(
                RegistrationViewState.ShowUserSuccessfullyCreated
            )
        }
    }

    private suspend fun checkUser(email: String): Boolean = repository.checkUser(email = email)
}