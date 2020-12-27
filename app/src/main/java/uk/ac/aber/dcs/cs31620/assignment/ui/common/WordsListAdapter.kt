package uk.ac.aber.dcs.cs31620.assignment.ui.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs31620.assignment.databinding.WordItemBinding
import uk.ac.aber.dcs.cs31620.assignment.model.Word

class WordsListAdapter (private val context: Context?) : RecyclerView.Adapter<WordsListAdapter.ViewHolder>() {
    private var dataSet: MutableList<Word> = mutableListOf() // Empty list
    var clickListener: View.OnClickListener? = null

    inner class ViewHolder(
            itemView: View,
            private val wordOriginal: TextView,
            private val wordTranslation: TextView
    ) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener(clickListener)
        }

        fun bindDataSet(word: Word) {
            wordOriginal.text = word.orginal
            wordTranslation.text = word.translation
        }
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): WordsListAdapter.ViewHolder {
        val catItemBinding = WordItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(
                catItemBinding.wordItem,
                catItemBinding.wordOriginal,
                catItemBinding.wordTranslation
        )
    }

    override fun onBindViewHolder(holder: WordsListAdapter.ViewHolder, position: Int) {
        holder.bindDataSet(dataSet[position])
    }

    fun changeDataSet(dataSet: MutableList<Word>){
        this.dataSet = dataSet
        this.notifyDataSetChanged()
    }

}
