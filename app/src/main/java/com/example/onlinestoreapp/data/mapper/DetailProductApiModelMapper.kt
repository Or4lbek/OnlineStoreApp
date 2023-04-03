package com.example.onlinestoreapp.data.mapper

import com.example.onlinestoreapp.data.model.DetailProductApiModel
import com.example.onlinestoreapp.domain.mapper.ResponseResultMapper
import com.example.onlinestoreapp.domain.model.DetailProduct

class DetailProductApiModelMapper : ResponseResultMapper<DetailProductApiModel, DetailProduct>() {

    override fun mapData(from: DetailProductApiModel) = DetailProduct(
        colors = from.colors,
        description = from.description,
        imageUrls = from.imageUrls,
        name = from.name,
        numberOfReviews = from.numberOfReviews,
        price = from.price,
        rating = from.rating
    )
}
