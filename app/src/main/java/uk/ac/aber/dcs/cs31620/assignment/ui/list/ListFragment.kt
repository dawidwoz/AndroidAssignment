package uk.ac.aber.dcs.cs31620.assignment.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.word_item.view.*
import uk.ac.aber.dcs.cs31620.assignment.MainActivity
import uk.ac.aber.dcs.cs31620.assignment.R
import uk.ac.aber.dcs.cs31620.assignment.databinding.FragmentListBinding
import uk.ac.aber.dcs.cs31620.assignment.model.Common
import uk.ac.aber.dcs.cs31620.assignment.model.Word
import uk.ac.aber.dcs.cs31620.assignment.model.WordViewModel
import uk.ac.aber.dcs.cs31620.assignment.ui.common.WordsListAdapter

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var wordViewModel: WordViewModel
    private lateinit var wordsListAdapter: WordsListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        MainActivity.hideToolbar()

        addClickHandler()

        addWordsRecyclerView()

        checkIfThereWords()

        return binding.root
    }

    override fun onPause() {
        MainActivity.showToolbar()
        super.onPause()
    }

    override fun onDestroy() {
        MainActivity.showToolbar()
        super.onDestroy()
    }

    private fun addClickHandler() {
        val buttonAdd = binding.listAddWordButton;
        buttonAdd.setOnClickListener(View.OnClickListener {
            goToAdd()
        })
    }

    private fun checkIfThereWords() {
        wordViewModel.getWords().observe(viewLifecycleOwner) { wordList ->
            wordsListAdapter.changeDataSet(wordList as MutableList<Word>)
            if (wordList.isEmpty()) {
                binding.listWordTitle.visibility = View.GONE
                binding.listTextNoWords.visibility = View.VISIBLE
            } else {
                binding.listWordTitle.visibility = View.VISIBLE
                binding.listTextNoWords.visibility = View.GONE
            }
        }
    }

    private fun goToAdd() {
        val navController = findNavController()
        navController.navigate(R.id.action_list_to_add)
    }

    private fun goToEdit(originalWord: String, desiredWord: String, idWord: String) {
        val navController = findNavController()
        val bundle = bundleOf(
                Common.OriginalWord.value to originalWord,
                Common.TranslationWord.value to desiredWord,
                Common.IdWord.value to idWord
        )
        navController.navigate(R.id.action_list_to_edit, bundle)
    }

    private fun addWordsRecyclerView() {
        val listWords = binding.listWordsList

        val gridLayoutManager = GridLayoutManager(context, 1)
        listWords.layoutManager = gridLayoutManager

        wordsListAdapter = WordsListAdapter(context)

        wordsListAdapter.clickListener = View.OnClickListener {
            goToEdit(
                    it.word_original.text.toString(),
                    it.word_translation.text.toString(),
                    it.word_id.text.toString()
            )
        };

        listWords.adapter = wordsListAdapter

        listWords.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (dy > 0) { // Scrolling down
                    MainActivity.hideBottomNav()
                } else if (dy < 0) { // Scrolling up
                    MainActivity.showBottomNav()
                }

                if (gridLayoutManager.findLastVisibleItemPosition() == gridLayoutManager.itemCount - 1) {
                    MainActivity.hideBottomNav()
                }

                if (gridLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                    MainActivity.showBottomNav()
                }

                super.onScrolled(recyclerView, dx, dy)
            }
        }
        )
    }
}
