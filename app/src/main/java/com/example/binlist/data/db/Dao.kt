package com.example.binlist.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Upsert
    suspend fun upsertCardTable(cardEntity: CardEntity)

    @Query("SELECT * FROM cardTable")
    fun getAllFromCardTable(): Flow<List<CardEntity>>

    @Query("DELETE FROM cardTable")
    fun removeAll()
}