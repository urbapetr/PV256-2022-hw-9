package cz.muni.fi.pv256.hw9.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import cz.muni.fi.pv256.hw9.data.Character

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character")
    fun getAll(): LiveData<List<Character>>

    @Query("SELECT * FROM character WHERE id LIKE :id")
    fun getById(id: Int): LiveData<Character>

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(vararg character: Character)

    @Delete
    suspend fun delete(character: Character)
}
