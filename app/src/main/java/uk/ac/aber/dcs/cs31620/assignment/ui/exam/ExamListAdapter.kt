package uk.ac.aber.dcs.cs31620.assignment.ui.exam

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.text.bold
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs31620.assignment.R
import uk.ac.aber.dcs.cs31620.assignment.databinding.ExamItemBinding
import uk.ac.aber.dcs.cs31620.assignment.databinding.ExamSubmitButtonBinding
import uk.ac.aber.dcs.cs31620.assignment.model.Word

class ExamListAdapter (private val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataSet: MutableList<Word> = mutableListOf() // Empty list
    var clickListenerItem: View.OnClickListener? = null
    var clickButtonItem: View.OnClickListener? = null

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.exam_submit_button -> {
                val examItemBinding = ExamSubmitButtonBinding.inflate(LayoutInflater.from(context), parent, false)
                return ViewHolderSubmitButton(
                        examItemBinding.examSubmitButton
                )
            }
            R.layout.exam_item -> {
                val examItemBinding = ExamItemBinding.inflate(LayoutInflater.from(context), parent, false)
                return ViewHolderExamItem(
                        examItemBinding.examItem,
                        examItemBinding.examOriginal,
                        examItemBinding.examOriginal,
                        examItemBinding.examWordId
                )
            }
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun getItemCount(): Int = dataSet.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.exam_item -> (holder as ViewHolderExamItem).bindDataSet(dataSet[position], position+1)
            R.layout.exam_submit_button -> (holder as ViewHolderSubmitButton).bind()
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (position) {
            dataSet.size -> R.layout.exam_submit_button
            else -> R.layout.exam_item
        }
    }


    inner class ViewHolderExamItem(
            itemView: View,
            private val examOriginal: TextView,
            private val examTranslation: TextView,
            private val examWordId: TextView
    ) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener(clickListenerItem)
        }

        fun bindDataSet(word: Word, questionNumber: Int) {
            val questionText = context?.getString(R.string.exam_question_begin)
            val wordToTranslate = SpannableStringBuilder()
                    .append( "$questionNumber. $questionText ")
                    .bold { append(word.original) }
            wordToTranslate.setSpan(RelativeSizeSpan(1.5f), wordToTranslate.length-word.original.length,wordToTranslate.length, 0);
            examOriginal.setText(wordToTranslate)
            //examTranslation.text = word.translation
            examWordId.text = word.id.toString()
        }
    }

    fun changeDataSet(dataSet: MutableList<Word>){
        this.dataSet = dataSet
        this.notifyDataSetChanged()
    }

    inner class ViewHolderSubmitButton(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val btn = itemView.findViewById<Button>(R.id.exam_button_submit)
        fun bind() {
            btn.setOnClickListener(clickButtonItem);
        }
    }

}
