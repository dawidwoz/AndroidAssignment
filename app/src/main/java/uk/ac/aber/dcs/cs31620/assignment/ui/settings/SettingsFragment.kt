package uk.ac.aber.dcs.cs31620.assignment.ui.settings

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
import uk.ac.aber.dcs.cs31620.assignment.databinding.FragmentSettingsBinding
import uk.ac.aber.dcs.cs31620.assignment.model.LanguageViewModel

class SettingsFragment : Fragment() {

    private lateinit var languageViewModel: LanguageViewModel
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var yourLanguage: EditText
    private lateinit var desiredLanguage: EditText

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        languageViewModel =
                ViewModelProvider(this).get(LanguageViewModel::class.java)
        yourLanguage = binding.settingEditYourLanguage
        desiredLanguage = binding.settingEditDesiredLanguage
        languageViewModel.getLanguage().observe(viewLifecycleOwner) { languageList ->
            languageList.forEach {
                when (it.langauge) {
                    "your_language" -> yourLanguage.setText(it.value)
                    "desired_language" -> desiredLanguage.setText(it.value)
                    else -> MainActivity.UiController.displayToast(requireContext(), R.string.general_error)
                }
            }
        }
        val buttonSave = binding.settingSaveButton;
        buttonSave.setOnClickListener(View.OnClickListener {
            if (validateForm()) {
                saveLanguages()
                MainActivity.UiController.displayToast(requireContext(),R.string.setting_success_update)
            } else {
                MainActivity.UiController.displayToast(requireContext(),R.string.validation_error_start_form)
            }
        })
        return binding.root
    }

    private fun validateForm(): Boolean {
        return yourLanguage.text.isNotEmpty() && desiredLanguage.text.isNotEmpty()
    }

    private fun saveLanguages() {
        activity?.let { it1 -> MainActivity.hideSoftKeyboard(it1) }
        languageViewModel.saveYourLanguage(yourLanguage.text.toString())
        languageViewModel.saveDesiredLanguage(desiredLanguage.text.toString())
    }
}
