package com.example.onlinestoreapp.presentation.splash

import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.FragmentSplashBinding
import com.example.onlinestoreapp.domain.presentation.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment :
    com.example.onlinestoreapp.core.base_classes.BaseBindingFragment<FragmentSplashBinding>(
        FragmentSplashBinding::inflate
    ) {

    private val viewModel: SplashViewModel by viewModel()

    override fun initViews(savedInstanceState: Bundle?) {
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.blue)

        initObservers()
        view?.postDelayed({
            viewModel.onSplashEvent(
                SplashEvent.OnUserEnter
            )
        }, 2000)
    }

    private fun initObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            handleViewStateChanges(it)
        }
    }

    private fun handleViewStateChanges(viewState: ViewState<SplashViewState>) {
        when (viewState) {
            is ViewState.Data -> {
                handleSplashViewState(viewState.data)
            }
            is ViewState.Error -> {
                hideDialog()
                Toast.makeText(
                    context,
                    viewState.error.asString(requireContext()),
                    Toast.LENGTH_SHORT
                )
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

    private fun handleSplashViewState(data: SplashViewState) {
        when (data) {
            is SplashViewState.UserWasAuthorized -> {
                findNavController().navigate(
                    R.id.action_splashFragment_to_mainFragment
                )
            }
            SplashViewState.UserWasNotAuthorized -> {
                findNavController().navigate(
                    R.id.action_splashFragment_to_loginFragment
                )
            }
        }
    }
}