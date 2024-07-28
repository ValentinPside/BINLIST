package com.example.binlist.domain

import com.example.binlist.domain.models.Card

interface Repository {

    suspend fun getCard(cardNumber: String): Card

}