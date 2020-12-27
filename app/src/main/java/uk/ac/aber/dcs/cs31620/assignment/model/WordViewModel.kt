package uk.ac.aber.dcs.cs31620.assignment.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import uk.ac.aber.dcs.cs31620.assignment.datasource.LanguageRepository

class WordViewModel(application: Application) :  AndroidViewModel(application)  {
    private val repository: LanguageRepository = LanguageRepository(application)
    private var wordsList: LiveData<List<Word>> = repository.getWords()



    fun getWords(): LiveData<List<Word>> {
        return wordsList
    }

    override fun onCleared() {
        super.onCleared()
    }
}
