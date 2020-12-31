package uk.ac.aber.dcs.cs31620.assignment.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import uk.ac.aber.dcs.cs31620.assignment.MainActivity
import uk.ac.aber.dcs.cs31620.assignment.R
import uk.ac.aber.dcs.cs31620.assignment.databinding.FragmentAddBinding
import uk.ac.aber.dcs.cs31620.assignment.model.Common
import uk.ac.aber.dcs.cs31620.assignment.model.LanguageViewModel
import uk.ac.aber.dcs.cs31620.assignment.model.WordViewModel
import uk.ac.aber.dcs.cs31620.assignment.ui.common.WordsListAdapter

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var languageViewModel: LanguageViewModel
    private lateinit var wordsViewModel: WordViewModel
    private lateinit var addViewModel: AddViewModel
    private lateinit var wordsListAdapter: WordsListAdapter

    private lateinit var editYourWord: EditText
    private lateinit var editDesiredWord: EditText

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)
        languageViewModel = ViewModelProvider(this).get(LanguageViewModel::class.java)
        wordsViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        addViewModel = ViewModelProvider(this).get(AddViewModel::class.java)

        editYourWord = binding.addEditYourWord
        editDesiredWord = binding.addEditDesiredWord

        addLanguageInfo()

        checkIfThereWords()

        addWordsRecyclerView()

        addClickHandler()

        MainActivity.UiController.hideBottomNav()

        return binding.root
    }

    override fun onDestroy() {
        MainActivity.UiController.showBottomNav()
        super.onDestroy()
    }

    private fun validateForm(): Boolean {
        return editYourWord.text.isNotEmpty() && editDesiredWord.text.isNotEmpty()
    }

    private fun saveWord() {
        activity?.let { it1 -> MainActivity.hideSoftKeyboard(it1) }
        addViewModel.addWord(editYourWord.text.toString(), editDesiredWord.text.toString())
        wordsViewModel.addWord(editYourWord.text.toString(), editDesiredWord.text.toString())
        editYourWord.setText("")
        editDesiredWord.setText("")
    }

    private fun addLanguageInfo() {
        val yourWord = binding.addYourWord
        val desiredWord = binding.addDesiredWord

        languageViewModel.getLanguage().observe(viewLifecycleOwner) { languageList ->
            languageList.forEach {
                when (it.langauge) {
                    Common.YourLanguage.value -> yourWord.append(" " + it.value + ":")
                    Common.DesiredLanguage.value -> desiredWord.append(" " + it.value + ":")
                    else -> MainActivity.UiController.displayToast(requireContext(), R.string.general_error)
                }
            }
        }
    }

    private fun checkIfThereWords() {
        val addNewWord = binding.addNewWords
        addViewModel.getWords().observe(viewLifecycleOwner) { wordList ->
            wordsListAdapter.changeDataSet(wordList)
            if(wordList.size > 0) {
                addNewWord.visibility = View.VISIBLE
            }
        }
    }

    private fun addClickHandler() {
        val buttonNext = binding.addAddButton;
        buttonNext.setOnClickListener(View.OnClickListener {
            if (validateForm()) {
                saveWord()
            } else {
                MainActivity.displayToast(requireContext(),R.string.word_validation_error_start_form)
            }
        })
    }

    private fun addWordsRecyclerView() {
        val listWords = binding.addNewWordsList

        val gridLayoutManager = GridLayoutManager(context, 1)
        listWords.layoutManager = gridLayoutManager

        wordsListAdapter = WordsListAdapter(context)
        listWords.adapter = wordsListAdapter
    }
}