package uk.ac.aber.dcs.cs31620.assignment.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import uk.ac.aber.dcs.cs31620.assignment.MainActivity
import uk.ac.aber.dcs.cs31620.assignment.R
import uk.ac.aber.dcs.cs31620.assignment.databinding.FragmentStartBinding
import uk.ac.aber.dcs.cs31620.assignment.datasource.LanguageRepository
import uk.ac.aber.dcs.cs31620.assignment.model.Language


class StartFragment : Fragment() {

    private lateinit var startViewModel: StartViewModel
    private lateinit var binding: FragmentStartBinding
    private lateinit var yourLanguage: EditText
    private lateinit var desiredLanguage: EditText
    private lateinit var repository: LanguageRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout {
        binding = FragmentStartBinding.inflate(layoutInflater)
        startViewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        yourLanguage = binding.editOwnLanguage
        desiredLanguage = binding.editForeignLanguage

        repository = LanguageRepository(requireActivity().application)

        MainActivity.UiController.hideBottomNav()
        MainActivity.UiController.hideToolbar()

        val buttonNext = binding.button;
        buttonNext.setOnClickListener(View.OnClickListener {
            if (validateForm()) {
                saveAndGoHome()
            } else {
                displayToastError(R.string.validation_error_start_form)
            }
        })
        checkLanguages()
        return binding.root
    }

    private fun validateForm(): Boolean {
        return yourLanguage.text.isNotEmpty() && desiredLanguage.text.isNotEmpty()
    }

    private fun displayToastError(text: Int) {
        val toast = Toast.makeText(requireContext(), text, Toast.LENGTH_LONG)
        toast.show()
    }

    private fun checkLanguages() {
        val languages: LiveData<List<Language>> = repository.getLanguages()
        languages.observe(viewLifecycleOwner) { languageList ->
            if (languageList.size == 2) {
                goToHome()
            }
        }
    }

    private fun saveAndGoHome() {
        repository.insert(Language("your_language", yourLanguage.text.toString()))
        repository.insert(Language("desired_language", desiredLanguage.text.toString()))
    }

    private fun goToHome() {
        val navController = findNavController()
        val navOption = NavOptions.Builder().setPopUpTo(R.id.navigation_start, true).build()
        if(R.id.action_start_to_home != null) {
                navController.navigate(R.id.action_start_to_home, null, navOption)
        }
        MainActivity.UiController.showBottomNav()
        MainActivity.UiController.showToolbar()
    }
}
