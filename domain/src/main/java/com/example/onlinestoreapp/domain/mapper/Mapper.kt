package com.example.onlinestoreapp.domain.mapper

abstract class Mapper<FROM, TO> {

    abstract fun map(from: FROM): TO

}
