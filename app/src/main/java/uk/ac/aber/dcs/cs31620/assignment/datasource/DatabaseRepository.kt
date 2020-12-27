package uk.ac.aber.dcs.cs31620.assignment.datasource

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.assignment.model.Language
import uk.ac.aber.dcs.cs31620.assignment.model.Word

class DatabaseRepository(application: Application) {
    private val languagesDao = Injection.getDatabase(application).languagesDao()
    private val wordsDao = Injection.getDatabase(application).wordDao()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertLanguage(language: Language) {
        coroutineScope.launch(Dispatchers.IO) {
            languagesDao.insertLanguage(language)
        }
    }

    fun deleteAllLanguages() {
        coroutineScope.launch(Dispatchers.IO) {
            languagesDao.deleteAll()
        }
    }

    fun getLanguages(): LiveData<List<Language>> {
            return languagesDao.getLanguages()
    }

    fun insertWord(word: Word) {
        coroutineScope.launch(Dispatchers.IO) {
            wordsDao.insertWord(word)
        }
    }

    fun deleteAllWords() {
        coroutineScope.launch(Dispatchers.IO) {
            wordsDao.deleteAll()
        }
    }

    fun getWords(): LiveData<List<Word>> {
        return wordsDao.getWords()
    }

}
