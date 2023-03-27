package com.example.onlinestoreapp.presentation.main.feed

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.onlinestoreapp.databinding.FragmentFeedBinding
import com.example.onlinestoreapp.domain.model.UserAuth
import com.example.onlinestoreapp.domain.presentation.AdvancedViewState
import com.example.onlinestoreapp.presentation.main.feed.flash_sale.FlashSaleProductListAdapter
import com.example.onlinestoreapp.presentation.main.feed.latest.LatestProductListAdapter
import com.example.onlinestoreapp.utils.base_classes.BaseBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedFragment : BaseBindingFragment<FragmentFeedBinding>(FragmentFeedBinding::inflate) {

    private val viewModel: FeedViewModel by viewModel()

    override fun initViews(savedInstanceState: Bundle?) {
        binding.latestRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.latestRv.adapter = LatestProductListAdapter()

        binding.flashSaleRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.flashSaleRv.adapter = FlashSaleProductListAdapter()

        viewModel.onFeedEvent(FeedEvent.OnScreenOpen)
        initObservers()
    }

    private fun initObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            handleViewStateChanges(it)
        }
    }

    private fun handleViewStateChanges(viewState: AdvancedViewState<FeedViewState>) {
        when (viewState) {
            is AdvancedViewState.Data -> {
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

    private fun handleLoginViewState(data: FeedViewState) {
        when (data) {
            is FeedViewState.AllDataFetched -> {
                hideDialog()
                (binding.latestRv.adapter as LatestProductListAdapter).submitList(data.latestProducts.latest)
                (binding.flashSaleRv.adapter as FlashSaleProductListAdapter).submitList(data.flashSaleProducts.flashSales)
            }
            is FeedViewState.OnUserFetched -> {
                updateUserImage(data.userAuth)
            }
        }
    }

    private fun updateUserImage(userAuth: UserAuth?) {
        Glide.with(requireContext()).load(userAuth?.image).into(binding.userImageIv)
    }
}