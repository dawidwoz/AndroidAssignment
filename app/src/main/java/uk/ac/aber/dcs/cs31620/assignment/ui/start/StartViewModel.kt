package uk.ac.aber.dcs.cs31620.assignment.ui.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is start Fragment"
    }
    val text: LiveData<String> = _text
}