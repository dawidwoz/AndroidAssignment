package uk.ac.aber.dcs.cs31620.assignment.ui.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import uk.ac.aber.dcs.cs31620.assignment.R
import uk.ac.aber.dcs.cs31620.assignment.databinding.FragmentLearnBinding
import uk.ac.aber.dcs.cs31620.assignment.model.Word
import uk.ac.aber.dcs.cs31620.assignment.model.WordViewModel

class LearnFragment : Fragment() {

    private lateinit var binding: FragmentLearnBinding
    private lateinit var wordsViewModel: WordViewModel

    private lateinit var buttonLearn: Button
    private lateinit var textLearn: TextView
    private var shouldGetNewWord: Boolean = false
    private var currentWord : Word? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLearnBinding.inflate(layoutInflater)
        wordsViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        buttonLearn = binding.learnButtonWord
        textLearn = binding.learnQuestion

        getNewWord()

        addClickHandler()

        return binding.root
    }

    private fun getNewWord() {
        wordsViewModel.getRandomWords(1).observe(viewLifecycleOwner) {
            currentWord = it.getOrNull(0)
            if (currentWord == null) {
                noElementView()
            } else {
                fullView()
                textLearn.text = requireContext().getString(R.string.learn_question_translation)
                buttonLearn.text = currentWord?.original
                shouldGetNewWord = true
            }
        }
    }

    private fun addClickHandler() {
        buttonLearn.setOnClickListener {
            if (!shouldGetNewWord) {
                getNewWord()
            } else {
                textLearn.text = requireContext().getString(R.string.learn_question_new_word)
                buttonLearn.text = currentWord?.translation
                shouldGetNewWord = false
            }
        }
    }

    private fun noElementView() {
        binding.learnButtonWord.visibility = View.GONE
        binding.learnQuestion.visibility = View.GONE
        binding.learnTextNoWords.visibility = View.VISIBLE
    }

    private fun fullView() {
        binding.learnButtonWord.visibility = View.VISIBLE
        binding.learnQuestion.visibility = View.VISIBLE
        binding.learnTextNoWords.visibility = View.GONE
    }

}
