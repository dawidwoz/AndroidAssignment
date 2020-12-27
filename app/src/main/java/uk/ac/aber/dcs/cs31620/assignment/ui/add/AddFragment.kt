package uk.ac.aber.dcs.cs31620.assignment.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import uk.ac.aber.dcs.cs31620.assignment.MainActivity
import uk.ac.aber.dcs.cs31620.assignment.R
import uk.ac.aber.dcs.cs31620.assignment.databinding.FragmentAddBinding
import uk.ac.aber.dcs.cs31620.assignment.model.LanguageViewModel
import uk.ac.aber.dcs.cs31620.assignment.model.WordViewModel
import uk.ac.aber.dcs.cs31620.assignment.ui.common.WordsListAdapter

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var languageViewModel: LanguageViewModel
    private lateinit var wordsViewModel: WordViewModel
    private lateinit var yourWord: TextView
    private lateinit var desiredWord: TextView
    private lateinit var wordsListAdapter: WordsListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)
        languageViewModel = ViewModelProvider(this).get(LanguageViewModel::class.java)
        wordsViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        yourWord = binding.addYourWord
        desiredWord = binding.addDesiredWord

        languageViewModel.getLanguage().observe(viewLifecycleOwner) { languageList ->
            languageList.forEach {
                when (it.langauge) {
                    "your_language" -> yourWord.append(" " + it.value + ":")
                    "desired_language" -> desiredWord.append(" " + it.value + ":")
                    else -> MainActivity.UiController.displayToast(requireContext(), R.string.general_error)
                }
            }
        }

        addWordsRecyclerView()

        val wordList = wordsViewModel.getWords()

        wordList.observe(viewLifecycleOwner) { words ->
            wordsListAdapter.changeDataSet(words.toMutableList())
        }

        MainActivity.UiController.hideBottomNav()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        MainActivity.UiController.showBottomNav()
    }

    private fun addWordsRecyclerView() {
        val listWords = binding.addNewWordsList

        val gridLayoutManager = GridLayoutManager(context, 1)
        listWords.layoutManager = gridLayoutManager

        wordsListAdapter = WordsListAdapter(context)
        listWords.adapter = wordsListAdapter
    }
}