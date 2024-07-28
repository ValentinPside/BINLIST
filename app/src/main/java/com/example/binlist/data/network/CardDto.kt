package com.example.binlist.data.network

data class CardDto(
    val country: CountryDto,
    val scheme: String,
    val bank: BankDto
)
