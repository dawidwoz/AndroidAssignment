package uk.ac.aber.dcs.cs31620.assignment.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import uk.ac.aber.dcs.cs31620.assignment.datasource.DatabaseRepository

class LanguageViewModel(application: Application) :  AndroidViewModel(application) {
    private val repository: DatabaseRepository = DatabaseRepository(application)
    var languageList: LiveData<List<Language>> = repository.getLanguages()
        private set

    fun getLanguage(): LiveData<List<Language>> {
        return languageList
    }

    fun saveYourLanguage(value: String) {
        repository.insertLanguage(Language(Common.YourLanguage.value, value))
    }

    fun saveDesiredLanguage(value: String) {
        repository.insertLanguage(Language(Common.DesiredLanguage.value, value))
    }

    override fun onCleared() {
        super.onCleared()
    }
}
