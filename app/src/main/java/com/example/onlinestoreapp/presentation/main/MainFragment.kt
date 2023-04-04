package com.example.onlinestoreapp.presentation.main

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.FragmentMainBinding


class MainFragment :
    com.example.onlinestoreapp.core.base_classes.BaseBindingFragment<FragmentMainBinding>(
        FragmentMainBinding::inflate
    ) {

    override fun initViews(savedInstanceState: Bundle?) {
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.white)
        binding.mainBottomNavigationView
        val navController =
            ((childFragmentManager.findFragmentById(R.id.mainContainerView)) as NavHostFragment).navController
        NavigationUI.setupWithNavController(binding.mainBottomNavigationView, navController)
    }
}