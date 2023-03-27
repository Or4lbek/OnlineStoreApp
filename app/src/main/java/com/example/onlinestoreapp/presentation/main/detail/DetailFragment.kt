package com.example.onlinestoreapp.presentation.main.detail

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.example.onlinestoreapp.databinding.FragmentDetailBinding
import com.example.onlinestoreapp.utils.base_classes.BaseBindingFragment

class DetailFragment : BaseBindingFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    override fun initViews(savedInstanceState: Bundle?) {
        binding.hey.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}