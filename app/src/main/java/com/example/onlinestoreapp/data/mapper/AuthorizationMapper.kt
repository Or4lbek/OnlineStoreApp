package com.example.onlinestoreapp.data.mapper

import com.example.onlinestoreapp.data.model.UserDBModel
import com.example.onlinestoreapp.domain.model.User

class AuthorizationMapper {

    fun mapEntityToDBModel(user: User): UserDBModel = UserDBModel(
        id = user.id,
        name = user.name,
        email = user.email,
        password = user.password,
        image = user.image

    )

    fun mapDBModelToEntity(userDBModel: UserDBModel?): User? {
        if (userDBModel == null)
            return null
        return User(
            id = userDBModel.id,
            name = userDBModel.name,
            email = userDBModel.email,
            password = userDBModel.password,
            image = userDBModel.image
        )
    }
}