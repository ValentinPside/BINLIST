package com.example.binlist.utils

import com.example.binlist.data.db.CardEntity
import com.example.binlist.data.network.dto.BankDto
import com.example.binlist.data.network.dto.CardDto
import com.example.binlist.data.network.dto.CountryDto
import com.example.binlist.domain.models.Bank
import com.example.binlist.domain.models.Card
import com.example.binlist.domain.models.Country
import com.example.binlist.domain.models.RvCard


fun List<CardEntity>.asRvCardList() = this.map { it.asRvCard() }
fun CardEntity.asRvCard() = RvCard(
    scheme = this.scheme,
    number = this.id.toString(),
    country = this.countryName
)

fun Card.asCardEntity(number: Int) = CardEntity(
    id = number,
    scheme = this.scheme,
    countryName = this.country.name,
    latitude = this.country.latitude,
    longitude = this.country.longitude,
    bankName = bank.name,
    url = this.bank.url,
    phone = this.bank.phone,
    city = this.bank.city
)

fun CardDto.asCard() = this.country.asCountry()?.let {
    Card(
        country = it,
        scheme = if (!this.scheme.isNullOrEmpty()) this.scheme else "-",
        bank = this.bank.asBank()
    )
}

fun CountryDto.asCountry() = (if (this.latitude != 0) this.latitude else 0)?.let {
    (if (this.longitude != 0) this.longitude else 0)?.let { it1 ->
        Country(
            name = if (!this.name.isNullOrEmpty()) this.name else "-",
            latitude = it,
            longitude = it1,
        )
    }
}

fun BankDto.asBank() = Bank(
    name = if (!this.name.isNullOrEmpty()) this.name else "",
    url = if (!this.url.isNullOrEmpty()) this.url else "",
    phone = if (!this.phone.isNullOrEmpty()) this.phone else "",
    city = if (!this.city.isNullOrEmpty()) this.city else ""
)