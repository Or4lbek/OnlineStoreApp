package com.example.onlinestoreapp.presentation.splash

import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.FragmentSplashBinding
import com.example.onlinestoreapp.domain.presentation.AdvancedViewState
import com.example.onlinestoreapp.utils.base_classes.BaseBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseBindingFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

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

    private fun handleViewStateChanges(viewState: AdvancedViewState<SplashViewState>) {
        when (viewState) {
            is AdvancedViewState.Data -> {
                handleSplashViewState(viewState.data)
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