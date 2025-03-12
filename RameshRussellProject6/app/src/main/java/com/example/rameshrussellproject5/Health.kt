package com.example.rameshrussellproject5

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "health")
data class Health (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name= "food") val food :String?,
    @ColumnInfo(name= "calories") val calories :String?
)