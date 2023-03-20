package com.example.onlinestoreapp.presentation.splash

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.FragmentSplashBinding
import com.example.onlinestoreapp.utils.base_classes.BaseBindingFragment

class SplashFragment : BaseBindingFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    override fun initViews(savedInstanceState: Bundle?) {

        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.blue)

        view?.postDelayed({
            findNavController().navigate(
                R.id.action_splashFragment_to_loginFragment
            )
        }, 2000)
    }
}