package com.example.binlist.utils

import com.example.binlist.data.network.BankDto
import com.example.binlist.data.network.CardDto
import com.example.binlist.data.network.CountryDto
import com.example.binlist.domain.Bank
import com.example.binlist.domain.Card
import com.example.binlist.domain.Country

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