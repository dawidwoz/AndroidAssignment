package uk.ac.aber.dcs.cs31620.assignment.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import uk.ac.aber.dcs.cs31620.assignment.MainActivity
import uk.ac.aber.dcs.cs31620.assignment.R
import uk.ac.aber.dcs.cs31620.assignment.databinding.FragmentTestBinding
import uk.ac.aber.dcs.cs31620.assignment.model.Common
import uk.ac.aber.dcs.cs31620.assignment.model.WordViewModel

class TestFragment : Fragment() {

    private lateinit var binding : FragmentTestBinding
    private lateinit var wordsViewModel: WordViewModel

    private lateinit var wordCount: EditText
    private var maxQuestionNumber: Int = 0
    private var questionNumber: Int = 0

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestBinding.inflate(layoutInflater)
        wordsViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordCount = binding.testEditNumberQuestion

        checkHowManyWords()

        addClickHandler()

        return binding.root
    }

    private fun checkHowManyWords() {
        wordsViewModel.getWords().observe(viewLifecycleOwner) { wordsList ->
            maxQuestionNumber = wordsList.size
            if(maxQuestionNumber == 0) {
                noElementView()
            } else {
                fullView()
                val newHint = wordCount.hint.toString() + " " + maxQuestionNumber
                wordCount.hint = newHint;
            }
        }
    }

    private fun addClickHandler() {
        val buttonStart = binding.testButtonStart;
        buttonStart.setOnClickListener(View.OnClickListener {
            if (validateForm()) {
                goToExam()
            } else {
                MainActivity.UiController.displayToast(requireContext(), R.string.question_number_validation_error_test_form)
            }
        })
    }

    private fun goToExam() {
        val navController = findNavController()
        val bundle = bundleOf(
                Common.ArgumentNumberQuestion.value to questionNumber
        )
        navController.navigate(R.id.action_test_to_exam, bundle)
    }

    private fun checkIfInRange(text: String) : Boolean {
        questionNumber = try {
            text.toInt()
        } catch (nfe: NumberFormatException) {
            MainActivity.UiController.displayToast(requireContext(), R.string.question_number_validation_error_test_form)
            0
        }

        if(questionNumber > maxQuestionNumber || questionNumber < 1) {
            return false
        }

        return true
    }

    private fun validateForm(): Boolean {
        return wordCount.text.isNotEmpty() && checkIfInRange(wordCount.text.toString())
    }

    private fun noElementView() {
        binding.testButtonStart.visibility = View.GONE
        binding.testEditNumberQuestion.visibility = View.GONE
        binding.testQuestionBeforeStart.visibility = View.GONE
        binding.testTextNoWords.visibility = View.VISIBLE
    }

    private fun fullView() {
        binding.testButtonStart.visibility = View.VISIBLE
        binding.testEditNumberQuestion.visibility = View.VISIBLE
        binding.testQuestionBeforeStart.visibility = View.VISIBLE
        binding.testTextNoWords.visibility = View.GONE
    }

}
