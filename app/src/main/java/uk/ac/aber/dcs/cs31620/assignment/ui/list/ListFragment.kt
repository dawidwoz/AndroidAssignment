package uk.ac.aber.dcs.cs31620.assignment.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs31620.assignment.MainActivity
import uk.ac.aber.dcs.cs31620.assignment.R
import uk.ac.aber.dcs.cs31620.assignment.databinding.FragmentListBinding
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

        val buttonAdd = binding.addWordButton;
        buttonAdd.setOnClickListener(View.OnClickListener {
            goToAdd()
        })

        addWordsRecyclerView()

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

        return binding.root
    }

    private fun goToAdd() {
        val navController = findNavController()
        navController.navigate(R.id.action_start_to_add)
    }

    private fun addWordsRecyclerView() {
        val listWords = binding.listWordsList

        val gridLayoutManager = GridLayoutManager(context, 1)
        listWords.layoutManager = gridLayoutManager

        wordsListAdapter = WordsListAdapter(context)
        listWords.adapter = wordsListAdapter
        listWords.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (dy > 0) { //Scrolling down
                    MainActivity.hideBottomNav()
                } else if (dy < 0) { //Scrolling up
                    MainActivity.showBottomNav()
                }

                if (gridLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                    MainActivity.showBottomNav()
                }

                if (gridLayoutManager.findLastVisibleItemPosition() == gridLayoutManager.itemCount - 1) {
                    MainActivity.hideBottomNav()
                }

                super.onScrolled(recyclerView, dx, dy)
            }
        }
        )
    }
}
