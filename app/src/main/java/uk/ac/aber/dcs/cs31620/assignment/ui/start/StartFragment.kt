package uk.ac.aber.dcs.cs31620.assignment.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import uk.ac.aber.dcs.cs31620.assignment.R

class StartFragment : Fragment() {

    private lateinit var homeViewModel: StartViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(StartViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_start, container, false)
        val textView: TextView = root.findViewById(R.id.text_start)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}