package cz.muni.fi.pv256.hw9.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import cz.muni.fi.pv256.hw9.data.CharacterRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CharacterRepository(application)

    private val page: MutableLiveData<Int> = MutableLiveData()
    val items = page.switchMap { page ->
        repository.getCharacters(page)
    }

    fun setPage(pageNo: Int) {
        page.value = pageNo
    }
}
