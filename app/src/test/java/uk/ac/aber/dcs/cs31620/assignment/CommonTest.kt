package uk.ac.aber.dcs.cs31620.assignment

import org.junit.Test

import org.junit.Assert.*
import uk.ac.aber.dcs.cs31620.assignment.model.Common

/**
 * The common test for predefined important values
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CommonTest {

    @Test
    fun isDatabaseNameCorrect() {
        assertEquals("language_database", Common.DatabaseName.value)
    }

    @Test
    fun isYourLanguageCorrect() {
        assertEquals("your_language", Common.YourLanguage.value)
    }

    @Test
    fun isDesiredLanguageCorrect() {
        assertEquals("desired_language", Common.DesiredLanguage.value)
    }

    @Test
    fun isArgumentNumberQuestionCorrect() {
        assertEquals("numberQuestion", Common.ArgumentNumberQuestion.value)
    }

    @Test
    fun isTranslationWordCorrect() {
        assertEquals("translationWord", Common.TranslationWord.value)
    }

    @Test
    fun isOriginalWordCorrect() {
        assertEquals("originalWord", Common.OriginalWord.value)
    }

    @Test
    fun isIdWordCorrect() {
        assertEquals("idWord", Common.IdWord.value)
    }

    @Test
    fun isTagQuestionCorrect() {
        assertEquals("QUESTION", Common.TAG_QUESTION.value)
    }
}
