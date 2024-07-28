package com.example.binlist.domain

interface Repository {

    suspend fun getCard(cardNumber: String): Card

}