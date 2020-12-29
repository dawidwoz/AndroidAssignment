package uk.ac.aber.dcs.cs31620.assignment.ui.exam

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import uk.ac.aber.dcs.cs31620.assignment.MainActivity
import uk.ac.aber.dcs.cs31620.assignment.R
import uk.ac.aber.dcs.cs31620.assignment.databinding.FragmentExamBinding
import uk.ac.aber.dcs.cs31620.assignment.model.Word
import uk.ac.aber.dcs.cs31620.assignment.model.WordViewModel

class ExamFragment : Fragment()  {

    private lateinit var binding: FragmentExamBinding
    private lateinit var wordsViewModel: WordViewModel
    private var questionNumber: Int = 0
    private lateinit var examListAdapter: ExamListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExamBinding.inflate(layoutInflater)
        wordsViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        arguments?.getInt("numberQuestion")?.let {
            questionNumber = it;
        } ?: run {
            MainActivity.displayToast(requireContext(), R.string.general_error)
        };

        addWordsRecyclerView()

        wordsViewModel.getRandomWords(questionNumber).observe(viewLifecycleOwner) {
            examListAdapter.changeDataSet(it as MutableList<Word>)
        }

        val buttonSubmit = binding.examButtonSubmit;
        buttonSubmit.setOnClickListener(View.OnClickListener {
                confirmSubmitAlert()
        })

        MainActivity.hideToolbar()
        MainActivity.hideBottomNav()
        MainActivity.setWarnWhenBack(true)

        return binding.root
    }

    override fun onDestroy() {
        MainActivity.showToolbar()
        MainActivity.showBottomNav()
        MainActivity.setWarnWhenBack(false)
        super.onDestroy()
    }

    private fun addWordsRecyclerView() {
        val examWords = binding.examWordsList

        val gridLayoutManager = GridLayoutManager(context, 1)
        examWords.layoutManager = gridLayoutManager

        examListAdapter = ExamListAdapter(context)

        examWords.adapter = examListAdapter
    }

    private fun confirmSubmitAlert() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val dialogClickListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    Log.d("TAG", "YS")
                }
            }
        }
        builder.setMessage(R.string.exam_warning_button_submit)
                .setPositiveButton(R.string.yes, dialogClickListener)
                .setNegativeButton(R.string.no, dialogClickListener).show()
    }
}
