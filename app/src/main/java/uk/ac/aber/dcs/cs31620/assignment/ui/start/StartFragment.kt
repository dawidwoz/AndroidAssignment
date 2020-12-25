package uk.ac.aber.dcs.cs31620.assignment.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import uk.ac.aber.dcs.cs31620.assignment.MainActivity
import uk.ac.aber.dcs.cs31620.assignment.R
import uk.ac.aber.dcs.cs31620.assignment.databinding.ActivityMainBinding
import uk.ac.aber.dcs.cs31620.assignment.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    private lateinit var startViewModel: StartViewModel
    private lateinit var binding: FragmentStartBinding
    private lateinit var bindingGlobal: ActivityMainBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): ConstraintLayout {
        binding = FragmentStartBinding.inflate(layoutInflater)
        startViewModel = ViewModelProvider(this).get(StartViewModel::class.java)

        MainActivity.UiController.hideBottomNav()
        MainActivity.UiController.hideToolbar()

        var buttonNext = binding.button;
        buttonNext.setOnClickListener(View.OnClickListener {
            var navController =  findNavController()
            val navOption = NavOptions.Builder().setPopUpTo(R.id.navigation_start, true).build()
            navController.navigate(R.id.action_start_to_home, null, navOption)
            MainActivity.UiController.showBottomNav()
            MainActivity.UiController.showToolbar()
        })
        return binding.root
    }
}