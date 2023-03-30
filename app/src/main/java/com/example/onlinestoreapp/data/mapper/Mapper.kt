package com.example.onlinestoreapp.data.mapper

abstract class Mapper<FROM, TO> {

    abstract fun map(from: FROM): TO

}
