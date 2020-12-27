package uk.ac.aber.dcs.cs31620.assignment.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uk.ac.aber.dcs.cs31620.assignment.R
import uk.ac.aber.dcs.cs31620.assignment.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var listViewModel: ListViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)
        listViewModel =
                ViewModelProvider(this).get(ListViewModel::class.java)
        val buttonAdd = binding.addWordButton;
        buttonAdd.setOnClickListener(View.OnClickListener {
            goToAdd()
        })
        return binding.root
    }

    private fun goToAdd() {
        val navController = findNavController()
        navController.navigate(R.id.action_start_to_add)
    }
}
