package uk.ac.aber.dcs.cs31620.assignment.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import uk.ac.aber.dcs.cs31620.assignment.datasource.DatabaseRepository

class WordViewModel(application: Application) :  AndroidViewModel(application)  {
    private val repository: DatabaseRepository = DatabaseRepository(application)
    private var wordsList: LiveData<List<Word>> = repository.getWords()

    fun getWords(): LiveData<List<Word>> {
        return wordsList
    }

    fun addWord(original: String, translation: String) {
        repository.insertWord(Word(original = original, translation = translation))
    }

    fun updateWordById(id: Int, original: String, translation: String) {
        repository.updateWord(Word(id = id, original = original, translation = translation, failed = 0))
    }

    fun deleteWordById(id: Int) {
        repository.deleteWord(id);
    }

    fun deleteAllWord() {
        repository.deleteAllWords();
    }

    override fun onCleared() {
        super.onCleared()
    }
}
