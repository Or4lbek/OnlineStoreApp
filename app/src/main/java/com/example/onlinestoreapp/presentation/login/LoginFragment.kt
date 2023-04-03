package com.example.onlinestoreapp.presentation.login

import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.FragmentLoginBinding
import com.example.onlinestoreapp.domain.presentation.ViewState
import com.example.onlinestoreapp.utils.base_classes.BaseBindingFragment
import com.example.onlinestoreapp.utils.checkIsEmailValid
import com.example.onlinestoreapp.utils.checkPasswordLength
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseBindingFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModel()

    override fun initViews(savedInstanceState: Bundle?) {
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.white)

        initObservers()

        binding.emailTil.checkIsEmailValid(requireContext())
        binding.passwordTil.checkPasswordLength(requireContext())
        binding.signInBtn.setOnClickListener { onClickSignIn() }
        binding.signUpTv.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    private fun initObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            handleViewStateChanges(it)
        }
    }

    private fun handleViewStateChanges(viewState: ViewState<LoginViewState>) {
        when (viewState) {
            is ViewState.Data -> {
                hideDialog()
                handleLoginViewState(viewState.data)
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

    private fun handleLoginViewState(data: LoginViewState) {
        when (data) {
            is LoginViewState.OnUserFetched -> {
                findNavController().navigate(
                    R.id.action_loginFragment_to_mainFragment
                )
            }
            LoginViewState.ShowUserDoNotExist -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.user_does_not_exist),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun onClickSignIn() {
        viewModel.onLoginEvent(
            LoginEvent.OnLoginClicked(
                email = binding.emailEt.text.toString(),
                password = binding.passwordEt.text.toString()
            )
        )
    }
}