package com.example.binlist.data.network.dto

data class CardDto(
    val country: CountryDto,
    val scheme: String,
    val bank: BankDto
)
