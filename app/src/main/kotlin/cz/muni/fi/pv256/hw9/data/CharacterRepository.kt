package cz.muni.fi.pv256.hw9.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import cz.muni.fi.pv256.hw9.api.ApiService
import cz.muni.fi.pv256.hw9.db.MortyDatabase

class CharacterRepository(context: Context) {
    companion object {
        private val TAG = CharacterRepository::class.java.simpleName
    }

    private val characterDao = MortyDatabase.getInstance(context).characterDao()

    fun getCharacter(id: Int) = liveData {
        val character = ApiService.apiService.getCharacter(id)
        characterDao.insertAll(character)
        emitSource(
            characterDao.getById(id)
        )
        // Underlying DetailActivity doesn't work with Result, so there's no need to wrap
        // returned LiveData into another object
        // therefore, emitSource() should be called just once
    }

    fun getCharacters(page: Int) = liveData {
        // start emitting results from DB
        // there's high probability, it contains the data we need (just might not be fresh)
        val disposable = emitSource(
            characterDao.getAll().map {
                Result.loading(it) // Data are wrapped in Result, to signal their state
            }
        )
        try {
            // get fresh data from Internet
            val characters = ApiService.apiService.getCharacters(page).characters
            // dispose of the old data, if not propagated yet
            disposable.dispose()
            // persist new result
            characterDao.insertAll(*characters.toTypedArray())
            emitSource(
                // start returning fresh state from DB
                characterDao.getAll().map {
                    Result.success(it)
                }
            )
        } catch (e: Exception) {
            Log.e(TAG, "Getting data from the Internet failed", e)
            emitSource(
                characterDao.getAll().map {
                    // getting fresh data failed, start returning cached copy
                    Result.failure(e, it)
                }
            )
        }
    }
}
