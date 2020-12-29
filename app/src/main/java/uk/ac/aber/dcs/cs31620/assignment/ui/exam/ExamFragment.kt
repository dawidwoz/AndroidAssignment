package uk.ac.aber.dcs.cs31620.assignment.ui.exam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uk.ac.aber.dcs.cs31620.assignment.MainActivity
import uk.ac.aber.dcs.cs31620.assignment.R
import uk.ac.aber.dcs.cs31620.assignment.databinding.FragmentExamBinding

class ExamFragment : Fragment()  {

    private lateinit var binding: FragmentExamBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExamBinding.inflate(layoutInflater)

        arguments?.getInt("numberQuestion")?.let{
            binding.textView.setText(it.toString())
        } ?: run {
            MainActivity.displayToast(requireContext(), R.string.general_error)
        };

        return binding.root
    }
}
