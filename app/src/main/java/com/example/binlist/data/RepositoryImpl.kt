package com.example.binlist.data

import com.example.binlist.data.network.NetworkServiceAPI
import com.example.binlist.domain.Card
import com.example.binlist.domain.Repository
import com.example.binlist.utils.asCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: NetworkServiceAPI): Repository {

    override suspend fun getCard(cardNumber: String): Card {
        return withContext(Dispatchers.IO) {
            val cardRemote = api.getCardDto(cardNumber)
            cardRemote.asCard()
        }
    }

}