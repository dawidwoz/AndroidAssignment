package uk.ac.aber.dcs.cs31620.assignment.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import uk.ac.aber.dcs.cs31620.assignment.model.Word

class AddViewModel(application: Application) :  AndroidViewModel(application) {
    var wordsList: MutableLiveData<MutableList<Word>> = MutableLiveData<MutableList<Word>>()
        private set

    init {
        wordsList.value = mutableListOf<Word>()
    }

    fun getWords(): MutableLiveData<MutableList<Word>> {
        return wordsList
    }

    fun addWord(original: String, translation: String) {
        wordsList.value?.add(Word(original = original, translation = translation))
        /*
        * The line below is a cheeky way to refresh a view data model.
        * It comes from: https://stackoverflow.com/questions/47941537/notify-observer-when-item-is-added-to-list-of-livedata/49022687#49022687
        * */
        wordsList.value = wordsList.value
    }

    override fun onCleared() {
        super.onCleared()
    }
}
