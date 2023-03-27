package com.example.onlinestoreapp.presentation.main.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.FragmentProfileBinding
import com.example.onlinestoreapp.domain.model.UserAuth
import com.example.onlinestoreapp.domain.presentation.AdvancedViewState
import com.example.onlinestoreapp.presentation.activity.MainActivity
import com.example.onlinestoreapp.utils.Constants.PICK_IMAGE_REQUEST
import com.example.onlinestoreapp.utils.base_classes.BaseBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment :
    BaseBindingFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModel()

    override fun initViews(savedInstanceState: Bundle?) {
        initObservers()

        viewModel.onProfileEvent(ProfileEvent.OnUserOpen)

        binding.uploadItemTv.setOnClickListener {
            onClickButtonUploadItem()
        }
        binding.logOutTv.setOnClickListener {
            viewModel.onProfileEvent(ProfileEvent.OnUserLogOut)
            onClickButtonLogOut()
        }
    }

    private fun initObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            handleViewStateChanges(it)
        }
    }

    private fun handleViewStateChanges(viewState: AdvancedViewState<ProfileViewState>) {
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

    private fun handleSplashViewState(data: ProfileViewState) {
        when (data) {
            is ProfileViewState.OnUserFetched -> {
                updateViews(data.userAuth)
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

    private fun updateViews(userAuth: UserAuth?) {
        binding.userNameTv.text = userAuth?.name
        println(userAuth?.image)
        Glide.with(requireContext()).load(userAuth?.image).into(binding.userImageIv)
    }

    private fun onClickButtonLogOut() {
        startActivity(Intent(requireActivity(), MainActivity::class.java))
        requireActivity().finish()
    }

    private fun onClickButtonUploadItem() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)//result.launch(intent) helper class
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            val imageUri = data.data

            // Do something with the image URI, such as load it into an ImageView
            viewModel.onProfileEvent(ProfileEvent.OnUploadedItem(imageUri.toString()))

            binding.userImageIv.setImageURI(imageUri)
        }
    }
}