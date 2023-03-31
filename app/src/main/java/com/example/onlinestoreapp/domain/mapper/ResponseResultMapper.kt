package com.example.onlinestoreapp.domain.mapper

import com.example.onlinestoreapp.domain.network.Response

abstract class ResponseResultMapper<FROM, TO> {

    abstract fun mapData(from: FROM): TO

    fun map(from: Response<FROM>): Response<TO> = mapSimpleResult(from)

    private fun mapSimpleResult(from: Response<FROM>): Response<TO> = when (from) {
        is Response.Error -> Response.Error(from.error)
        is Response.NetworkError -> Response.NetworkError
        is Response.Success -> Response.Success(mapData(from.data))
    }
}
