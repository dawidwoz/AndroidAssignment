package uk.ac.aber.dcs.cs31620.assignment.ui.exam.question

import android.text.SpannableStringBuilder

class Question {
    var question: SpannableStringBuilder = SpannableStringBuilder()
    var questionId: String = ""
    var answer1: String = ""
    var answer2: String = ""
    var answer3: String = ""
    var answer4: String = ""
    var selectedAnswer: Int = 0
}
