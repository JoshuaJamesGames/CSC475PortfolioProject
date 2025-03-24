package com.example.csc475portfolioproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Character(
    @ColumnInfo(name = "character_name") val characterName: String,
    @ColumnInfo(name = "character_class") val characterClass: String,
    @ColumnInfo(name = "character_level") val characterLevel: Int,
    @PrimaryKey(autoGenerate = true) val uid: Long = 0
)