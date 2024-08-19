package com.example.binlist.domain

import com.example.binlist.data.db.CardEntity
import com.example.binlist.domain.models.Card
import kotlinx.coroutines.flow.Flow

interface DbRepository {

    fun getCardList(): Flow<List<CardEntity>>

    suspend fun addNewCard(card: Card, number: Int)

}