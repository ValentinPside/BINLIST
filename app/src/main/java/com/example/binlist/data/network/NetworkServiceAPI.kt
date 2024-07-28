package com.example.binlist.data.network

import com.example.binlist.data.network.dto.CardDto
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkServiceAPI {

    @GET("{cardNumber}")
    suspend fun getCardDto(@Path("cardNumber") cardNumber: String): CardDto

}