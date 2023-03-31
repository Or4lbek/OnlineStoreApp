package com.example.onlinestoreapp.data.mapper

import com.example.onlinestoreapp.data.model.CategoriesApiModel
import com.example.onlinestoreapp.data.model.CategoryApiModel
import com.example.onlinestoreapp.domain.mapper.Mapper
import com.example.onlinestoreapp.domain.model.Categories
import com.example.onlinestoreapp.domain.model.Category

class CategoriesApiModelMapper : Mapper<CategoriesApiModel, Categories>() {
    override fun map(from: CategoriesApiModel): Categories {
        val categories = mutableListOf<Category>()
        from.categories.forEach {
            categories.add(getCategory(it))
        }
        return Categories(categories)
    }

    private fun getCategory(from: CategoryApiModel) = Category(
        name = from.name,
        image = from.image
    )
}