package cz.muni.fi.pv256.hw9.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cz.muni.fi.pv256.hw9.data.Character

@Database(entities = [Character::class], version = 1)
abstract class MortyDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object {
        fun getInstance(context: Context): MortyDatabase {
            return Room.databaseBuilder(
                context,
                MortyDatabase::class.java,
                "Morty database"
            ).build()
        }
    }
}
