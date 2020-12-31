package uk.ac.aber.dcs.cs31620.assignment.ui.edit

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import uk.ac.aber.dcs.cs31620.assignment.MainActivity
import uk.ac.aber.dcs.cs31620.assignment.R
import uk.ac.aber.dcs.cs31620.assignment.databinding.FragmentEditBinding
import uk.ac.aber.dcs.cs31620.assignment.model.Common
import uk.ac.aber.dcs.cs31620.assignment.model.LanguageViewModel
import uk.ac.aber.dcs.cs31620.assignment.model.WordViewModel

class EditFragment : Fragment() {
    private lateinit var binding : FragmentEditBinding
    private lateinit var languageViewModel: LanguageViewModel
    private lateinit var wordsViewModel: WordViewModel
    private var wordId: Int = 0

    private lateinit var editYourWord: EditText
    private lateinit var editDesiredWord: EditText

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(layoutInflater)
        languageViewModel = ViewModelProvider(this).get(LanguageViewModel::class.java)
        wordsViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
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
            confirmDeleteAlert()
        })

        fillEditView();

        MainActivity.UiController.hideBottomNav()

        return binding.root
    }

    override fun onDestroy() {
        MainActivity.UiController.showBottomNav()
        super.onDestroy()
    }

    private fun changeWord() {
        activity?.let { it1 -> MainActivity.hideSoftKeyboard(it1) }
        wordsViewModel.updateWordById(wordId,editYourWord.text.toString(), editDesiredWord.text.toString())
        MainActivity.displayToast(requireContext(), R.string.edit_change_word_success)
        goToSource()
    }

    private fun confirmDeleteAlert() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val dialogClickListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    deleteWord()
                }
            }
        }
        builder.setMessage(R.string.edit_delete_confirm_message)
            .setPositiveButton(R.string.yes, dialogClickListener)
            .setNegativeButton(R.string.no, dialogClickListener).show()
    }

    private fun deleteWord() {
        activity?.let { it1 -> MainActivity.hideSoftKeyboard(it1) }
        wordsViewModel.deleteWordById(wordId)
        MainActivity.displayToast(requireContext(), R.string.edit_delete_word_success)
        goToSource()
    }

    private fun validateForm(): Boolean {
        return editYourWord.text.isNotEmpty() && editDesiredWord.text.isNotEmpty()
    }

    private fun fillEditView() {
        arguments?.getString("originalWord")?.let{
            editYourWord.setText(it)
        } ?: run {
            handleError()
        };

        arguments?.getString("translationWord")?.let{
            editDesiredWord.setText(it)
        } ?: run {
            handleError()
        };

        arguments?.getString("idWord")?.let{
            wordId = try {
                it.toInt()
            } catch (nfe: NumberFormatException) {
                handleError()
                0
            }
        } ?: run {
            wordId = 0;
            handleError()
        };
    }

    private fun handleError() {
        MainActivity.displayToast(requireContext(), R.string.general_error)
        goToSource()
    }

    private fun goToSource() {
        val navController = findNavController()
        navController.popBackStack()
    }

}
