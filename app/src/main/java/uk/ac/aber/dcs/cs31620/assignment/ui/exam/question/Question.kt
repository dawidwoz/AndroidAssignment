package uk.ac.aber.dcs.cs31620.assignment.ui.exam.question

import android.text.SpannableStringBuilder

class Question {
    var question: SpannableStringBuilder = SpannableStringBuilder()
    var questionId: String = ""
    var answer1: SpannableStringBuilder = SpannableStringBuilder()
    var answer2: SpannableStringBuilder = SpannableStringBuilder()
    var answer3: SpannableStringBuilder = SpannableStringBuilder()
    var answer4: SpannableStringBuilder = SpannableStringBuilder()
    var selectedAnswer: Int = 0
}
