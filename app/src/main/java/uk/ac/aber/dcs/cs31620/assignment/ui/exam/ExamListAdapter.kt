package uk.ac.aber.dcs.cs31620.assignment.ui.exam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs31620.assignment.databinding.ExamItemBinding
import uk.ac.aber.dcs.cs31620.assignment.model.Word

class ExamListAdapter (private val context: Context?) : RecyclerView.Adapter<ExamListAdapter.ViewHolder>() {
    private var dataSet: MutableList<Word> = mutableListOf() // Empty list
    var clickListener: View.OnClickListener? = null

    inner class ViewHolder(
            itemView: View,
            private val examOriginal: TextView,
            private val examTranslation: TextView,
            private val examWordId: TextView
    ) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener(clickListener)
        }

        fun bindDataSet(word: Word) {
            examOriginal.text = word.original
            examTranslation.text = word.translation
            examWordId.text = word.id.toString()
        }
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ExamListAdapter.ViewHolder {
        val examItemBinding = ExamItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(
                examItemBinding.examItem,
                examItemBinding.examOriginal,
                examItemBinding.examTranslation,
                examItemBinding.examWordId
        )
    }

    override fun onBindViewHolder(holder: ExamListAdapter.ViewHolder, position: Int) {
        holder.bindDataSet(dataSet[position])
    }

    fun changeDataSet(dataSet: MutableList<Word>){
        this.dataSet = dataSet
        this.notifyDataSetChanged()
    }

}
