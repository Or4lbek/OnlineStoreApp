package com.example.onlinestoreapp.presentation.main.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.FragmentProfileBinding
import com.example.onlinestoreapp.domain.model.User
import com.example.onlinestoreapp.domain.presentation.AdvancedViewState
import com.example.onlinestoreapp.presentation.activity.MainActivity
import com.example.onlinestoreapp.utils.base_classes.BaseBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment :
    BaseBindingFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModel()

    override fun initViews(savedInstanceState: Bundle?) {
        viewModel.onProfileEvent(ProfileEvent.onScreenOpen)

        binding.uploadItemTv.setOnClickListener {
            onClickButtonUploadItem()
        }
        binding.logOutTv.setOnClickListener {
            viewModel.onProfileEvent(ProfileEvent.OnUserLogOut)
            onClickButtonLogOut()
        }
        initObservers()
    }

    private fun initObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            handleViewStateChanges(it)
        }
    }

    private fun handleViewStateChanges(viewState: AdvancedViewState<ProfileViewState>) {
        when (viewState) {
            is AdvancedViewState.Data -> {
                handleProfileViewState(viewState.data)
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

    private fun handleProfileViewState(data: ProfileViewState) {
        when (data) {
            is ProfileViewState.OnUserFetched -> {
                updateViews(data.user)
            }
            ProfileViewState.ShowUserImageSuccessfullyUpdated -> {
                Toast.makeText(
                    context,
                    getString(R.string.successfully_updated),
                    Toast.LENGTH_SHORT
                ).show()
            }
            ProfileViewState.ShowUserImageWasNotUpdated -> {
                Toast.makeText(context, getString(R.string.was_not_updated), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun updateViews(user: User?) {
        binding.userNameTv.text = user?.name
        Glide.with(requireContext()).load(user?.image).into(binding.userImageIv)
    }



    private fun onClickButtonLogOut() {
        startActivity(Intent(requireActivity(), MainActivity::class.java))
        requireActivity().finish()
    }

    private fun onClickButtonUploadItem() {
        getContent.launch("image/*")
    }

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            // Do something with the image URI, such as load it into an ImageView
            uri?.let {
                viewModel.onProfileEvent(ProfileEvent.OnUploadedItem(it.toString()))
                binding.userImageIv.setImageURI(it)
            }
        }
}