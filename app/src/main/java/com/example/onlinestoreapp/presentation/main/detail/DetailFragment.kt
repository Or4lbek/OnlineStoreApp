package com.example.onlinestoreapp.presentation.main.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.onlinestoreapp.databinding.FragmentDetailBinding
import com.example.onlinestoreapp.domain.model.DetailProduct
import com.example.onlinestoreapp.domain.presentation.AdvancedViewState
import com.example.onlinestoreapp.presentation.main.detail.colors.ColorListAdapter
import com.example.onlinestoreapp.presentation.main.detail.image_slider.ImageSliderAdapter
import com.example.onlinestoreapp.utils.base_classes.BaseBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

class DetailFragment : BaseBindingFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel: DetailViewModel by viewModel()
    override fun initViews(savedInstanceState: Bundle?) {
        binding.apply {
            backArrowIv.setOnClickListener {
                findNavController().navigateUp()
            }
            productSliderVp2.adapter = ImageSliderAdapter()
            productSliderVp2.offscreenPageLimit = 3
            productSliderVp2.clipChildren = false
            productSliderVp2.clipToPadding = false
            productSliderVp2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setUpTransformer(productSliderVp2)

            detailColorsRv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            detailColorsRv.adapter = ColorListAdapter()
        }
        initObservers()
    }

    private fun setUpTransformer(viewPager2: ViewPager2) {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        viewPager2.setPageTransformer(transformer)
    }

    private fun initObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            handleViewStateChanges(it)
        }
    }

    private fun handleViewStateChanges(viewState: AdvancedViewState<DetailViewState>) {
        when (viewState) {
            is AdvancedViewState.Data -> {
                hideDialog()
                handleDetailViewState(viewState.data)
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

    private fun handleDetailViewState(viewState: DetailViewState) {
        when (viewState) {
            is DetailViewState.AllDataFetched -> {
                updateViews(viewState.detailProduct)
            }
            is DetailViewState.OnBasketChanged -> {

            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateViews(detailProduct: DetailProduct) = with(binding) {
        (productSliderVp2.adapter as ImageSliderAdapter).submitList(detailProduct.image_urls)
        (detailColorsRv.adapter as ColorListAdapter).submitList(detailProduct.colors)
        detailNameTv.text = detailProduct.name
        detailDescriptionTv.text = detailProduct.description
        detailReviewsTv.text = "(${detailProduct.number_of_reviews} reviews)"
        detailRatingTv.text = detailProduct.rating.toString()
        detailPriceTv.text = "$ ${detailProduct.price}.00"
    }
}