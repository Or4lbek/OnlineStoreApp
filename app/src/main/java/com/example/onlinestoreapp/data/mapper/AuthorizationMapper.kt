package com.example.onlinestoreapp.data.mapper

import com.example.onlinestoreapp.data.model.UserAuthDBModel
import com.example.onlinestoreapp.domain.model.UserAuth

class AuthorizationMapper {

    fun mapEntityToDBModel(userAuth: UserAuth): UserAuthDBModel = UserAuthDBModel(
        id = userAuth.id,
        name = userAuth.name,
        email = userAuth.email,
        password = userAuth.password,
        image = userAuth.image

    )

    fun mapDBModelToEntity(userAuthDBModel: UserAuthDBModel?): UserAuth? {
        if (userAuthDBModel == null)
            return null
        return UserAuth(
            id = userAuthDBModel.id,
            name = userAuthDBModel.name,
            email = userAuthDBModel.email,
            password = userAuthDBModel.password,
            image = userAuthDBModel.image
        )
    }
}