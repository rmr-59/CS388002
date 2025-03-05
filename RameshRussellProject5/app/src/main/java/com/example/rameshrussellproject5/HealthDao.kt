package com.example.rameshrussellproject5

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthDao{
    @Query("SELECT * FROM health")
    fun getAll(): Flow<List<Health>>

    @Insert
    fun insert(health: Health)

    @Insert
    fun addAll(health: List<Health>)

    @Query("DELETE FROM health")
    fun deleteAll()
}