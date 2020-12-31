package uk.ac.aber.dcs.cs31620.assignment.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import uk.ac.aber.dcs.cs31620.assignment.MainActivity
import uk.ac.aber.dcs.cs31620.assignment.R
import uk.ac.aber.dcs.cs31620.assignment.databinding.FragmentStartBinding
import uk.ac.aber.dcs.cs31620.assignment.model.LanguageViewModel


class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private lateinit var languageViewModel: LanguageViewModel

    private lateinit var yourLanguage: EditText
    private lateinit var desiredLanguage: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout {
        binding = FragmentStartBinding.inflate(layoutInflater)
        yourLanguage = binding.editOwnLanguage
        desiredLanguage = binding.editForeignLanguage

        languageViewModel = ViewModelProvider(this).get(LanguageViewModel::class.java)

        MainActivity.UiController.hideBottomNav()
        MainActivity.UiController.hideToolbar()

        val buttonNext = binding.button;
        buttonNext.setOnClickListener(View.OnClickListener {
            if (validateForm()) {
                saveLanguages()
            } else {
                MainActivity.displayToast(requireContext(),R.string.language_validation_error_start_form)
            }
        })
        checkLanguages()
        return binding.root
    }

    private fun validateForm(): Boolean {
        return yourLanguage.text.isNotEmpty() && desiredLanguage.text.isNotEmpty()
    }

    private fun checkLanguages() {
        languageViewModel.getLanguage().observe(viewLifecycleOwner) { languageList ->
            if (languageList.size == 2) {
                goToHome()
            }
        }
    }

    private fun saveLanguages() {
        activity?.let { it1 -> MainActivity.hideSoftKeyboard(it1) }
        languageViewModel.saveYourLanguage(yourLanguage.text.toString())
        languageViewModel.saveDesiredLanguage(desiredLanguage.text.toString())
    }

    private fun goToHome() {
        val navController = findNavController()
        val navOption = NavOptions.Builder().setPopUpTo(R.id.navigation_start, true).build()
        navController.navigate(R.id.action_start_to_home, null, navOption)
        MainActivity.UiController.showBottomNav()
        MainActivity.UiController.showToolbar()
    }
}
