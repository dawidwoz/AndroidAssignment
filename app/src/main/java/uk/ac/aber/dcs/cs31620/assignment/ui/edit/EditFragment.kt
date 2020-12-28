package uk.ac.aber.dcs.cs31620.assignment.ui.edit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import uk.ac.aber.dcs.cs31620.assignment.MainActivity
import uk.ac.aber.dcs.cs31620.assignment.R
import uk.ac.aber.dcs.cs31620.assignment.databinding.FragmentEditBinding
import uk.ac.aber.dcs.cs31620.assignment.model.Common
import uk.ac.aber.dcs.cs31620.assignment.model.LanguageViewModel

class EditFragment : Fragment() {
    private lateinit var binding : FragmentEditBinding
    private lateinit var languageViewModel: LanguageViewModel

    private lateinit var editYourWord: EditText
    private lateinit var editDesiredWord: EditText

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(layoutInflater)
        languageViewModel = ViewModelProvider(this).get(LanguageViewModel::class.java)
        editYourWord = binding.editEditYourWord
        editDesiredWord = binding.editEditDesiredWord

        languageViewModel.getLanguage().observe(viewLifecycleOwner) { languageList ->
            languageList.forEach {
                when (it.langauge) {
                    Common.YourLanguage.value -> binding.editYourWord.append(" " + it.value + ":")
                    Common.DesiredLanguage.value -> binding.editDesiredWord.append(" " + it.value + ":")
                    else -> MainActivity.UiController.displayToast(requireContext(), R.string.general_error)
                }
            }
        }

        val buttonChange = binding.editChangeButton;
        buttonChange.setOnClickListener(View.OnClickListener {
            if (validateForm()) {
                changeWord()
            } else {
                MainActivity.displayToast(requireContext(),R.string.word_validation_error_start_form)
            }
        })

        val buttonDelete = binding.editDeleteButton;
        buttonDelete.setOnClickListener(View.OnClickListener {
            deleteWord()
        })

        fillEditView();

        MainActivity.UiController.hideBottomNav()

        return binding.root
    }

    private fun changeWord() {
        Log.d("CHANGE", "There is a change function")
    }

    private fun deleteWord() {
        Log.d("DELETE", "There is a delete function")
    }

    private fun validateForm(): Boolean {
        return editYourWord.text.isNotEmpty() && editDesiredWord.text.isNotEmpty()
    }

    private fun fillEditView() {
        arguments?.getString("originalWord")?.let{
            editYourWord.setText(it)
        } ?: run {
            MainActivity.displayToast(requireContext(), R.string.general_error)
        };
        arguments?.getString("translationWord")?.let{
            editDesiredWord.setText(it)
        } ?: run {
            MainActivity.displayToast(requireContext(), R.string.general_error)
        };
    }

}
