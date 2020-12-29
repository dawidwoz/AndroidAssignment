package uk.ac.aber.dcs.cs31620.assignment.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import uk.ac.aber.dcs.cs31620.assignment.MainActivity
import uk.ac.aber.dcs.cs31620.assignment.R
import uk.ac.aber.dcs.cs31620.assignment.databinding.FragmentTestBinding
import uk.ac.aber.dcs.cs31620.assignment.model.WordViewModel

class TestFragment : Fragment() {

    private lateinit var binding : FragmentTestBinding
    private lateinit var wordsViewModel: WordViewModel
    private lateinit var wordCount: EditText
    private var maxQuestionNumber: Int = 0

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestBinding.inflate(layoutInflater)
        wordsViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordCount = binding.testEditNumberQuestion

        wordsViewModel.getWords().observe(viewLifecycleOwner) { wordsList ->
            maxQuestionNumber = wordsList.size
            val newHint = wordCount.hint.toString() + " " + maxQuestionNumber
            wordCount.hint = newHint;
        }

        val buttonStart = binding.testButtonStart;
        buttonStart.setOnClickListener(View.OnClickListener {
            if (validateForm()) {
                
            } else {
                MainActivity.UiController.displayToast(requireContext(), R.string.question_number_validation_error_test_form)
            }
        })

        return binding.root
    }

    private fun checkIfInRange(text: String) : Boolean {
        var questionNumber = try {
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
}
