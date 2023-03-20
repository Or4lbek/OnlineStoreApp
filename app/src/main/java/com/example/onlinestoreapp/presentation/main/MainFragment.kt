package com.example.onlinestoreapp.presentation.main

import android.os.Bundle
import com.example.onlinestoreapp.databinding.FragmentMainBinding
import com.example.onlinestoreapp.domain.model.UserAuth
import com.example.onlinestoreapp.utils.base_classes.BaseBindingFragment
import com.example.onlinestoreapp.utils.parcelable


class MainFragment : BaseBindingFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    override fun initViews(savedInstanceState: Bundle?) {
        val user = requireArguments().parcelable<UserAuth>("user")
        binding.userTv.text = user?.email
    }
}