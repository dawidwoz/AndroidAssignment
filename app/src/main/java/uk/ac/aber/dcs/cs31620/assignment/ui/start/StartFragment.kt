package uk.ac.aber.dcs.cs31620.assignment.ui.start

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout {
        binding = FragmentStartBinding.inflate(layoutInflater)
        startViewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        yourLanguage = binding.editOwnLanguage
        desiredLanguage = binding.editForeignLanguage

        MainActivity.UiController.hideBottomNav()
        MainActivity.UiController.hideToolbar()

        var buttonNext = binding.button;
        buttonNext.setOnClickListener(View.OnClickListener {
            if (validateForm()) {
                saveAndGoHome()
            } else {
                displayToastError()
            }
        })
        return binding.root
    }

    private fun validateForm(): Boolean {
        return yourLanguage.text.isNotEmpty() && desiredLanguage.text.isNotEmpty()
    }

    private fun displayToastError() {
        val text = R.string.validation_error_start_form
        val toast = Toast.makeText(requireContext(), text, Toast.LENGTH_LONG)
        toast.show()
    }

    private fun saveAndGoHome() {
        val repository = LanguageRepository(requireActivity().application)
        repository.insert(Language("your_language", yourLanguage.text.toString()))
        repository.insert(Language("desired_language", desiredLanguage.text.toString()))
        var catList: LiveData<List<Language>> = repository.getLanguages()
        val navController = findNavController()
        val navOption = NavOptions.Builder().setPopUpTo(R.id.navigation_start, true).build()
        navController.navigate(R.id.action_start_to_home, null, navOption)
        MainActivity.UiController.showBottomNav()
        MainActivity.UiController.showToolbar()
    }
}
