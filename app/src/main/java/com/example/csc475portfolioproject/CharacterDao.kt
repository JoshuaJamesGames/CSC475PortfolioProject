package com.example.csc475portfolioproject

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters")
    fun getAll(): List<Character>

    @Query("SELECT * FROM characters WHERE character_name LIKE :name LIMIT 1")
    fun findByName(name: String): Character

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg characters: Character)

    @Delete
    fun delete(character: Character)
}