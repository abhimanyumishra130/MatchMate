package com.matchmate.app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.matchmate.app.data.local.entity.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {

    @Query("SELECT * FROM person")
    fun fetchData(): List<PersonEntity>

    @Query("SELECT * FROM person")
    fun fetchPersonsEntities(): Flow<List<PersonEntity>>

    @Insert(onConflict = REPLACE)
    fun insertAllPersons(persons: List<PersonEntity>)

    @Query("DELETE FROM person")
    fun clearPersons()

    @Delete
    fun deletePerson(person: PersonEntity)

}