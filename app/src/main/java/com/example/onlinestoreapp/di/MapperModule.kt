package com.example.onlinestoreapp.di

import com.example.onlinestoreapp.data.mapper.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mapperModule = module {
    singleOf(::HintWordsApiModelMapper)
    singleOf(::FlashSaleProductsApiModelMapper)
    singleOf(::LatestProductsApiModelMapper)
    singleOf(::CategoriesApiModelMapper)
    singleOf(::DetailProductApiModelMapper)
}