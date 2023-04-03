package com.example.onlinestoreapp.presentation.main.feed

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.FragmentFeedBinding
import com.example.onlinestoreapp.domain.model.HintWords
import com.example.onlinestoreapp.domain.model.User
import com.example.onlinestoreapp.domain.presentation.ViewState
import com.example.onlinestoreapp.presentation.main.feed.category.CategoryListAdapter
import com.example.onlinestoreapp.presentation.main.feed.flash_sale.FlashSaleProductListAdapter
import com.example.onlinestoreapp.presentation.main.feed.latest.LatestProductListAdapter
import com.example.onlinestoreapp.utils.base_classes.BaseBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedFragment : BaseBindingFragment<FragmentFeedBinding>(FragmentFeedBinding::inflate) {

    private val viewModel: FeedViewModel by viewModel()
    override fun initViews(savedInstanceState: Bundle?) {

        binding.apply {
            categoryRv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            categoryRv.adapter = CategoryListAdapter()

            latestRv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            latestRv.adapter = LatestProductListAdapter()

            flashSaleRv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            flashSaleRv.adapter = FlashSaleProductListAdapter {
                findNavController().navigate(R.id.action_feedFragment_to_detailFragment)
            }
        }

        viewModel.onFeedEvent(FeedEvent.OnScreenOpen)
        // Actually I would like to send response every second and get filtering words
        // but api of this test work for only get response
        initObservers()
    }

    private fun initObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            handleViewStateChanges(it)
        }
    }

    private fun handleViewStateChanges(viewState: ViewState<FeedViewState>) {
        when (viewState) {
            is ViewState.Data -> {
                handleFeedViewState(viewState.data)
            }
            is ViewState.Error -> {
                hideDialog()
                Toast.makeText(context, resources.getString(viewState.error), Toast.LENGTH_SHORT)
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

    private fun handleFeedViewState(data: FeedViewState) {
        when (data) {
            is FeedViewState.AllDataFetched -> {
                hideDialog()
                (binding.latestRv.adapter as LatestProductListAdapter).submitList(data.latestProducts.latest)
                (binding.flashSaleRv.adapter as FlashSaleProductListAdapter).submitList(data.flashSaleProducts.flashSales)
                (binding.categoryRv.adapter as CategoryListAdapter).submitList(data.categories.categories)
            }
            is FeedViewState.OnUserFetched -> {
                updateUserImage(data.user)
            }
            is FeedViewState.OnHintWordsFetched -> {
                updateHintWords(data.hintWords)
            }
        }
    }

    private fun updateHintWords(hintWords: HintWords) {
        val arrAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, hintWords.words)
        binding.searchItemsEt.setAdapter(arrAdapter)
    }

    private fun updateUserImage(user: User?) {
        Glide.with(requireContext()).load(user?.image).placeholder(R.drawable.placeholder_avatar)
            .into(binding.userImageIv)
    }
}