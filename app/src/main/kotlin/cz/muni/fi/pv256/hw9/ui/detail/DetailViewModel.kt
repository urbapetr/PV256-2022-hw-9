package cz.muni.fi.pv256.hw9.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import cz.muni.fi.pv256.hw9.data.CharacterRepository

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CharacterRepository(application)

    private val id: MutableLiveData<Int> = MutableLiveData()
    val user = id.switchMap { id ->
        repository.getCharacter(id)
    }

    fun setCharacterId(characterId: Int) {
        id.value = characterId
    }
}
