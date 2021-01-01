package uk.ac.aber.dcs.cs31620.assignment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import uk.ac.aber.dcs.cs31620.assignment.R
import uk.ac.aber.dcs.cs31620.assignment.databinding.FragmentHomeBinding
import uk.ac.aber.dcs.cs31620.assignment.model.Common
import uk.ac.aber.dcs.cs31620.assignment.model.LanguageViewModel
import uk.ac.aber.dcs.cs31620.assignment.model.WordViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var languageViewModel: LanguageViewModel
    private lateinit var wordsViewModel: WordViewModel
    private lateinit var yourLanguage: TextView
    private lateinit var wordText: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        languageViewModel = ViewModelProvider(this).get(LanguageViewModel::class.java)
        wordsViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        yourLanguage = binding.homeYourLanguage
        wordText = binding.homeWordsText

        setLanguageText()

        setWordCountText()

        addClickHandler()

        return binding.root
    }

    private fun setWordCountText() {
        wordsViewModel.getWords().observe(viewLifecycleOwner) { wordsList ->
            wordText.setText(this.getString(R.string.home_word_count, wordsList.size))
        }
    }

    private fun setLanguageText() {
        languageViewModel.getLanguage().observe(viewLifecycleOwner) { languageList ->
            languageList.forEach lit@{
                when (it.langauge) {
                    Common.DesiredLanguage.value -> {
                        yourLanguage.setText(it.value)
                        return@lit
                    }
                }
            }
        }
    }

    private fun addClickHandler() {
        val buttonAdd = binding.homeAddWordButton;
        buttonAdd.setOnClickListener(View.OnClickListener {
            goToAdd()
        })
    }

    private fun goToAdd() {
        val navController = findNavController()
        navController.navigate(R.id.action_start_to_add)
    }
}
