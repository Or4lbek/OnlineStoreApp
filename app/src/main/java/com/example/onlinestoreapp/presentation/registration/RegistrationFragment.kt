package com.example.onlinestoreapp.presentation.registration

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.FragmentRegistrationBinding
import com.example.onlinestoreapp.domain.model.User
import com.example.onlinestoreapp.domain.presentation.ViewState
import com.example.onlinestoreapp.utils.base_classes.BaseBindingFragment
import com.example.onlinestoreapp.utils.checkIsEmailValid
import com.example.onlinestoreapp.utils.checkPasswordLength
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegistrationFragment :
    BaseBindingFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {

    private val viewModel: RegistrationViewModel by viewModel()

    override fun initViews(savedInstanceState: Bundle?) {
        initObservers()
        binding.emailTil.checkIsEmailValid(requireContext())
        binding.passwordTil.checkPasswordLength(requireContext())
        binding.signUpBtn.setOnClickListener {
            onClickButtonSignUp()
        }
        binding.signInTv.setOnClickListener {
            onClickSignIn()
        }
    }

    private fun initObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            handleViewStateChanges(it)
        }
    }

    private fun handleViewStateChanges(viewState: ViewState<RegistrationViewState>) {
        when (viewState) {
            is ViewState.Data -> {
                hideDialog()
                handleRegistrationViewState(viewState.data)
            }
            is ViewState.Error -> {
                hideDialog()
                Toast.makeText(context, resources.getString(viewState.error), Toast.LENGTH_SHORT)
                    .show()
            }
            ViewState.Loading -> {
                showDialog()
            }
            ViewState.NetworkError -> {
                hideDialog()
            }
        }
    }

    private fun handleRegistrationViewState(data: RegistrationViewState) {
        when (data) {
            is RegistrationViewState.ShowUserSuccessfullyCreated -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.successfully_created_account),
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(
                    R.id.action_registrationFragment_to_loginFragment
                )
            }
            RegistrationViewState.ShowUserWasNotCreated -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.email_already_used),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun onClickButtonSignUp() {
        viewModel.onRegistrationEvent(
            RegistrationEvent.OnRegistrationClicked(
                user = User(
                    email = binding.emailEt.text.toString(),
                    name = binding.firstNameEt.text.toString(),
                    password = binding.passwordEt.text.toString()
                )
            )
        )
    }

    private fun onClickSignIn() {
        findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
    }
}
