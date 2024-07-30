package com.example.binlist.data

import com.example.binlist.data.db.CardEntity
import com.example.binlist.data.db.MainDb
import com.example.binlist.domain.DbRepository
import com.example.binlist.domain.models.Card
import com.example.binlist.utils.asCardEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DbRepositoryImpl@Inject constructor(private val db: MainDb): DbRepository {

    override suspend fun getCardList(): Flow<List<CardEntity>> {
         return withContext(Dispatchers.IO){
            db.dao().getAllFromCardTable()
        }
    }

    override suspend fun addNewCard(card: Card, number: Int) {
        val cardEntity = card.asCardEntity(number)
        db.dao().upsertCardTable(cardEntity)
    }

}