package com.example.onlinestoreapp.data.mapper

import com.example.onlinestoreapp.data.model.HintWordsApiModel
import com.example.onlinestoreapp.domain.mapper.ResponseResultMapper
import com.example.onlinestoreapp.domain.model.HintWords

class HintWordsApiModelMapper : ResponseResultMapper<HintWordsApiModel, HintWords>() {

    override fun mapData(from: HintWordsApiModel): HintWords {
        return HintWords(from.words)
    }
}

