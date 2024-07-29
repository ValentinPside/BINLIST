package com.example.binlist.data.network.dto

data class CardDto(
    val scheme: String?,
    val country: CountryDto,
    val bank: BankDto
)
