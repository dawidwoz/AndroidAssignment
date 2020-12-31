package uk.ac.aber.dcs.cs31620.assignment.ui.exam

import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.text.bold
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs31620.assignment.R
import uk.ac.aber.dcs.cs31620.assignment.databinding.ExamItemBinding
import uk.ac.aber.dcs.cs31620.assignment.databinding.ExamSubmitButtonBinding
import uk.ac.aber.dcs.cs31620.assignment.model.Common
import uk.ac.aber.dcs.cs31620.assignment.model.Word
import uk.ac.aber.dcs.cs31620.assignment.ui.exam.question.Question
import kotlin.math.roundToInt
import kotlin.random.Random

class ExamListAdapter (private val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataSet: MutableList<Word> = mutableListOf() // Empty list
    private lateinit var questionInformation: MutableList<Question?>
    var clickListenerItem: View.OnClickListener? = null
    var highlightAnswers: Boolean = false
    var resultMessage: String = ""
    lateinit var clickButtonItem: () -> Unit

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.exam_submit_button -> {
                val examItemBinding = ExamSubmitButtonBinding.inflate(LayoutInflater.from(context), parent, false)
                return ViewHolderSubmitButton(
                        examItemBinding.examSubmitButton,
                        examItemBinding.examButtonSubmit
                )
            }
            R.layout.exam_item -> {
                val examItemBinding = ExamItemBinding.inflate(LayoutInflater.from(context), parent, false)
                return ViewHolderExamItem(
                        examItemBinding.examItem,
                        examItemBinding.examOriginal,
                        examItemBinding.examWordId,
                        examItemBinding.examAnswers,
                        listOf(
                                examItemBinding.examAnswer1,
                                examItemBinding.examAnswer2,
                                examItemBinding.examAnswer3,
                                examItemBinding.examAnswer4
                        )
                )
            }
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun getItemCount(): Int = dataSet.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.exam_item ->
            {
                if(!(this::questionInformation.isInitialized)) {
                    questionInformation = MutableList<Question?>(dataSet.size+1) { null }
                }
                (holder as ViewHolderExamItem).bindDataSet(dataSet[position], position+1)
            }
            R.layout.exam_submit_button -> (holder as ViewHolderSubmitButton).bind()
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (position) {
            dataSet.size -> R.layout.exam_submit_button
            else -> R.layout.exam_item
        }
    }

    fun changeDataSet(dataSet: MutableList<Word>){
        this.dataSet = dataSet
        this.notifyDataSetChanged()
    }

    fun checkAnswers() {
        val totalPoints: Float = dataSet.size + 1F
        var gainedPoints : Float = 0F
        dataSet.forEachIndexed { index, word ->
            val currentQuestion = questionInformation.getOrNull(index + 1)
            val givenAnswer = when(currentQuestion?.selectedAnswer) {
                1 -> currentQuestion.answer1
                2 -> currentQuestion.answer2
                3 -> currentQuestion.answer3
                4 -> currentQuestion.answer4
                else -> ""
            }
            if (word.translation == givenAnswer.toString()) {
               gainedPoints++
            }
        }
        highlightAnswers = true;
        var result : Float = gainedPoints/totalPoints
        resultMessage = ((result * 100).roundToInt()).toString() + "% " +  context!!.getString(R.string.exam_result_message)
        changeDataSet(dataSet)
    }

    private fun getRandomInt(min: Int ,max: Int): Int = Random.nextInt(min, max)

    inner class ViewHolderSubmitButton(
            itemView: View,
            private val button: Button
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind() {
            if(highlightAnswers) {
                button.text = resultMessage
            }
            button.setOnClickListener(View.OnClickListener() {
                    clickButtonItem()
            })
        }
    }

    inner class ViewHolderExamItem(
            itemView: View,
            private val examOriginal: TextView,
            private val examWordId: TextView,
            private val answerGroup: RadioGroup,
            private val answerFields: List<RadioButton>
    ) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener(clickListenerItem)
        }

        fun bindDataSet(word: Word, questionNumber: Int) {
            var question : Question? = questionInformation.getOrNull(questionNumber)
            if(question == null) {
                question = createQuestion(word, questionNumber)
            }
            setRadios(question.selectedAnswer)
            examOriginal.setText(question.question)
            examWordId.text = question.questionId
            answerFields[0].text = question.answer1
            answerFields[1].text = question.answer2
            answerFields[2].text = question.answer3
            answerFields[3].text = question.answer4
            setAnswerOnClick(question, answerFields)
            questionInformation[questionNumber] = question
            if(highlightAnswers) {
                highlightCorrectAnswer(word.translation, answerFields)
            }
        }

        private fun defaultAnswerFunction(wordTranslation: String): String {
            var translationWithTypo = StringBuilder(wordTranslation)
            for(i in 0 .. getRandomInt(1,2)) {
                val firstIndex = getRandomInt(0, translationWithTypo.length-1)
                val secondIndex = getRandomInt(0, translationWithTypo.length-1)
                val temp = translationWithTypo[firstIndex]
                translationWithTypo[firstIndex] = translationWithTypo[secondIndex]
                translationWithTypo[secondIndex] = temp
            }
            return translationWithTypo.toString()
        }

        private fun createQuestion(word: Word ,questionNumber: Int) : Question {
            val questionObject : Question = Question()
            questionObject.questionId = word.id.toString()
            val questionText = context?.getString(R.string.exam_question_begin)
            val question = SpannableStringBuilder()
                    .append("$questionNumber. $questionText ")
                    .bold { append(word.original) }
            question.setSpan(RelativeSizeSpan(1.5f), question.length - word.original.length, question.length, 0);
            questionObject.question = question

            val answers = prepareAnswers(word.translation)

            val getRandomAnswer: () -> String = {
                if (answers.isNotEmpty()) {
                    answers.removeAt(getRandomInt(0, answers.size))
                } else {
                    Log.e(Common.TAG_QUESTION.value, context!!.getString(R.string.question_generator_error))
                    defaultAnswerFunction(word.translation)
                }
            }
            questionObject.answer1 = SpannableStringBuilder().append(getRandomAnswer())
            questionObject.answer2 = SpannableStringBuilder().append(getRandomAnswer())
            questionObject.answer3 = SpannableStringBuilder().append(getRandomAnswer())
            questionObject.answer4 = SpannableStringBuilder().append(getRandomAnswer())

            return questionObject
        }

        private fun setAnswerOnClick(question: Question, answerFields: List<RadioButton>) {
            answerFields.forEachIndexed { index, it ->
                if(!highlightAnswers) {
                    it.setOnClickListener {
                        onItemSelected(question, index + 1)
                    }
                } else {
                    it.setOnClickListener {
                        null
                    }
                }
            }
        }

        private fun prepareAnswers(solution: String): MutableList<String> {
            val answers: MutableList<String> = mutableListOf(solution)
            for (i in 1..3) {
                val anotherWordTranslation = dataSet[getRandomInt(0,dataSet.size)].translation
                if(solution != anotherWordTranslation) {
                    answers.add(anotherWordTranslation)
                } else {
                    answers.add(defaultAnswerFunction(solution))
                }
            }
            return answers;
        }

        private fun highlightCorrectAnswer(solution: String, answers: List<RadioButton>) {
            answers.forEach {
                if(solution == it.text.toString()) {
                    val currentValue = it.text.toString()
                    val highlightedText = SpannableStringBuilder().bold { append(currentValue) }
                    highlightedText.setSpan(ForegroundColorSpan(Color.rgb(0, 255, 0)), 0, highlightedText.length, 0)
                    it.text = highlightedText
                } else if(it.isChecked) {
                    val currentValue = it.text.toString()
                    val highlightedText = SpannableStringBuilder().bold { append(currentValue) }
                    highlightedText.setSpan(ForegroundColorSpan(Color.rgb(255, 0, 0)), 0, highlightedText.length, 0)
                    it.text = highlightedText
                }
            }
        }

        private fun onItemSelected(question: Question, position: Int) {
            question.selectedAnswer = position
        }

        private fun setRadios(selectedAnswer: Int) {
            answerGroup.clearCheck()

            if (selectedAnswer == 0) return 

            when (selectedAnswer) {
                1 ->  answerFields[0].isChecked = true
                2 ->  answerFields[1].isChecked = true
                3 ->  answerFields[2].isChecked = true
                4 ->  answerFields[3].isChecked = true
            }
        }
    }

}
