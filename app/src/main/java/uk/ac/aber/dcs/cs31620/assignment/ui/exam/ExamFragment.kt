package uk.ac.aber.dcs.cs31620.assignment.ui.exam

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import uk.ac.aber.dcs.cs31620.assignment.MainActivity
import uk.ac.aber.dcs.cs31620.assignment.R
import uk.ac.aber.dcs.cs31620.assignment.databinding.FragmentExamBinding
import uk.ac.aber.dcs.cs31620.assignment.model.WordViewModel

class ExamFragment : Fragment()  {

    private lateinit var binding: FragmentExamBinding
    private lateinit var wordsViewModel: WordViewModel
    private var questionNumber: Int = 0

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExamBinding.inflate(layoutInflater)
        wordsViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        arguments?.getInt("numberQuestion")?.let{
            questionNumber = it;
        } ?: run {
            MainActivity.displayToast(requireContext(), R.string.general_error)
        };

        wordsViewModel.getRandomWords(questionNumber).observe(viewLifecycleOwner) {
            Log.d("LIST", it.toString())
            binding.textView.setText(it[0].translation)
        }

        return binding.root
    }
}
