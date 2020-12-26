package uk.ac.aber.dcs.cs31620.assignment.datasource

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.assignment.model.Language

class LanguageRepository(application: Application) {
    private val languagesDao = Injection.getDatabase(application).languagesDao()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insert(language: Language) {
        coroutineScope.launch(Dispatchers.IO) {
            languagesDao.insertLanguage(language)
        }
    }

    fun deleteAll() {
        coroutineScope.launch(Dispatchers.IO) {
            languagesDao.deleteAll()
        }
    }

    fun getLanguages(): LiveData<List<Language>> {
            return languagesDao.getLanguages()
    }

}
