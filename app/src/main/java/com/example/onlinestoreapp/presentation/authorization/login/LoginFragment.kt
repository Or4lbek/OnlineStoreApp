package com.example.onlinestoreapp.presentation.authorization.login

import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.FragmentLoginBinding
import com.example.onlinestoreapp.domain.presentation.AdvancedViewState
import com.example.onlinestoreapp.utils.Constants.USER
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

    private fun handleViewStateChanges(viewState: AdvancedViewState<LoginViewState>) {
        when (viewState) {
            is AdvancedViewState.Data -> {
                hideDialog()
                handleLoginViewState(viewState.data)
            }
            is AdvancedViewState.Error -> {
                hideDialog()
                Toast.makeText(context, viewState.error, Toast.LENGTH_SHORT).show()
            }
            AdvancedViewState.Loading -> {
                showDialog()
            }
            AdvancedViewState.NetworkError -> {
                hideDialog()
            }
        }
    }

    private fun handleLoginViewState(data: LoginViewState) {
        when (data) {
            is LoginViewState.OnUserFetched -> {
                val user = data.user
                findNavController().navigate(
                    R.id.action_loginFragment_to_mainFragment,
                    bundleOf(USER to user)
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