package com.example.binlist.utils

import com.example.binlist.data.network.dto.BankDto
import com.example.binlist.data.network.dto.CardDto
import com.example.binlist.data.network.dto.CountryDto
import com.example.binlist.domain.models.Bank
import com.example.binlist.domain.models.Card
import com.example.binlist.domain.models.Country

fun CardDto.asCard() = Card(
    country = this.country.asCountry(),
    scheme = this.scheme,
    bank = this.bank.asBank()
)

fun CountryDto.asCountry() = Country(
    name = this.name,
    latitude = this.latitude,
    longitude = this.longitude
)

fun BankDto.asBank() = Bank(
    name = this.name,
    url = this.url,
    phone = this.phone,
    city = this.city
)