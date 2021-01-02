package uk.ac.aber.dcs.cs31620.assignment


import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ExamScreenTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun examTest() {
        val appCompatEditText = onView(
            allOf(
                withId(R.id.start_edit_own_language),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("English"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.start_edit_foreign_language),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("German"), closeSoftKeyboard())

        val materialButton = onView(
            allOf(
                withId(R.id.start_button), withText("Next"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val floatingActionButton = onView(
            allOf(
                withId(R.id.home_add_word_button), withContentDescription("Button to add new word"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.add_edit_your_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText3.perform(scrollTo(), replaceText("one"), closeSoftKeyboard())

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.add_edit_desired_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_foreign_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText4.perform(scrollTo(), replaceText("eins"), closeSoftKeyboard())

        val materialButton2 = onView(
            allOf(
                withId(R.id.add_add_button), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        materialButton2.perform(scrollTo(), click())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.add_edit_your_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText5.perform(scrollTo(), replaceText("two"), closeSoftKeyboard())

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.add_edit_desired_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_foreign_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText6.perform(scrollTo(), replaceText("zwei"), closeSoftKeyboard())

        val materialButton3 = onView(
            allOf(
                withId(R.id.add_add_button), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        materialButton3.perform(scrollTo(), click())

        val appCompatEditText7 = onView(
            allOf(
                withId(R.id.add_edit_your_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText7.perform(scrollTo(), replaceText("three"), closeSoftKeyboard())

        val appCompatEditText8 = onView(
            allOf(
                withId(R.id.add_edit_desired_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_foreign_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText8.perform(scrollTo(), replaceText("drei"), closeSoftKeyboard())

        val materialButton4 = onView(
            allOf(
                withId(R.id.add_add_button), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        materialButton4.perform(scrollTo(), click())

        val appCompatEditText9 = onView(
            allOf(
                withId(R.id.add_edit_your_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText9.perform(scrollTo(), replaceText("four"), closeSoftKeyboard())

        val appCompatEditText10 = onView(
            allOf(
                withId(R.id.add_edit_desired_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_foreign_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText10.perform(scrollTo(), replaceText("vier"), closeSoftKeyboard())

        val materialButton5 = onView(
            allOf(
                withId(R.id.add_add_button), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        materialButton5.perform(scrollTo(), click())

        val appCompatEditText11 = onView(
            allOf(
                withId(R.id.add_edit_your_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText11.perform(scrollTo(), replaceText("five"), closeSoftKeyboard())

        val appCompatEditText12 = onView(
            allOf(
                withId(R.id.add_edit_desired_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_foreign_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText12.perform(scrollTo(), replaceText("fünf"), closeSoftKeyboard())

        val materialButton6 = onView(
            allOf(
                withId(R.id.add_add_button), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        materialButton6.perform(scrollTo(), click())

        val appCompatEditText13 = onView(
            allOf(
                withId(R.id.add_edit_your_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText13.perform(scrollTo(), replaceText("six"), closeSoftKeyboard())

        val appCompatEditText14 = onView(
            allOf(
                withId(R.id.add_edit_desired_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_foreign_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText14.perform(scrollTo(), replaceText("sechs"), closeSoftKeyboard())

        val materialButton7 = onView(
            allOf(
                withId(R.id.add_add_button), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        materialButton7.perform(scrollTo(), click())

        val appCompatEditText15 = onView(
            allOf(
                withId(R.id.add_edit_your_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText15.perform(scrollTo(), replaceText("seven"), closeSoftKeyboard())

        val appCompatEditText16 = onView(
            allOf(
                withId(R.id.add_edit_desired_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_foreign_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText16.perform(scrollTo(), replaceText("sieben"), closeSoftKeyboard())

        val materialButton8 = onView(
            allOf(
                withId(R.id.add_add_button), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        materialButton8.perform(scrollTo(), click())

        val appCompatEditText17 = onView(
            allOf(
                withId(R.id.add_edit_your_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText17.perform(scrollTo(), replaceText("eight"), closeSoftKeyboard())

        val appCompatEditText18 = onView(
            allOf(
                withId(R.id.add_edit_desired_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_foreign_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText18.perform(scrollTo(), replaceText("acht"), closeSoftKeyboard())

        val materialButton9 = onView(
            allOf(
                withId(R.id.add_add_button), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        materialButton9.perform(scrollTo(), click())

        val appCompatEditText19 = onView(
            allOf(
                withId(R.id.add_edit_your_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText19.perform(scrollTo(), replaceText("nine"), closeSoftKeyboard())

        val appCompatEditText20 = onView(
            allOf(
                withId(R.id.add_edit_desired_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_foreign_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText20.perform(scrollTo(), replaceText("neun"), closeSoftKeyboard())

        val materialButton10 = onView(
            allOf(
                withId(R.id.add_add_button), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        materialButton10.perform(scrollTo(), click())

        val appCompatEditText21 = onView(
            allOf(
                withId(R.id.add_edit_your_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText21.perform(scrollTo(), replaceText("ten"), closeSoftKeyboard())

        val appCompatEditText22 = onView(
            allOf(
                withId(R.id.add_edit_desired_word),
                childAtPosition(
                    allOf(
                        withId(R.id.add_foreign_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText22.perform(scrollTo(), replaceText("zehn"), closeSoftKeyboard())

        val materialButton11 = onView(
            allOf(
                withId(R.id.add_add_button), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        materialButton11.perform(scrollTo(), click())

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            withId(R.id.container),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.navigation_test), withContentDescription("Test"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val appCompatEditText23 = onView(
            allOf(
                withId(R.id.test_edit_number_question),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText23.perform(replaceText("0"), closeSoftKeyboard())

        val materialButton12 = onView(
            allOf(
                withId(R.id.test_button_start), withText("Start"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton12.perform(click())

        val button = onView(
            allOf(
                withId(R.id.test_button_start), withText("START"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val appCompatEditText24 = onView(
            allOf(
                withId(R.id.test_edit_number_question), withText("0"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText24.perform(replaceText("11"))

        val appCompatEditText25 = onView(
            allOf(
                withId(R.id.test_edit_number_question), withText("11"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText25.perform(closeSoftKeyboard())

        val materialButton13 = onView(
            allOf(
                withId(R.id.test_button_start), withText("Start"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton13.perform(click())

        val button2 = onView(
            allOf(
                withId(R.id.test_button_start), withText("START"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        button2.check(matches(isDisplayed()))

        val appCompatEditText26 = onView(
            allOf(
                withId(R.id.test_edit_number_question), withText("11"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText26.perform(replaceText("10"))

        val appCompatEditText27 = onView(
            allOf(
                withId(R.id.test_edit_number_question), withText("10"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText27.perform(closeSoftKeyboard())

        val materialButton14 = onView(
            allOf(
                withId(R.id.test_button_start), withText("Start"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton14.perform(click())

        /*
        * We need to scroll down to the button and wait for the next check. 1500 ms is a safe value to wait until it's done.
         */
        onView(withId(R.id.exam_words_list))
            .perform(swipeUp());

        SystemClock.sleep(1500);

        val materialButton15 = onView(
            allOf(
                withId(R.id.exam_button_submit)
            )
        )
        materialButton15.perform(scrollTo(), click())

        val textView = onView(
            allOf(
                withId(android.R.id.message),
                withText("Are you sure you want to submit your answers?"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Are you sure you want to submit your answers?")))

        val button4 = onView(
            allOf(
                withId(android.R.id.button2), withText("NO"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        button4.check(matches(isDisplayed()))

        val button5 = onView(
            allOf(
                withId(android.R.id.button1), withText("YES"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        button5.check(matches(isDisplayed()))

        val materialButton16 = onView(
            allOf(
                withId(android.R.id.button2), withText("No"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        materialButton16.perform(scrollTo(), click())

        val materialButton17 = onView(
            allOf(
                withId(R.id.exam_button_submit), withText("Submit"),
                childAtPosition(
                    allOf(
                        withId(R.id.exam_submit_button),
                        childAtPosition(
                            withId(R.id.exam_words_list),
                            3
                        )
                    ),
                    0
                )
            )
        )
        materialButton17.perform(scrollTo(), click())

        val materialButton18 = onView(
            allOf(
                withId(android.R.id.button1), withText("Yes"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        materialButton18.perform(scrollTo(), click())

        val button6 = onView(
            allOf(
                withId(R.id.exam_button_submit),
                withParent(
                    allOf(
                        withId(R.id.exam_submit_button),
                        withParent(withId(R.id.exam_words_list))
                    )
                ),
                isDisplayed()
            )
        )
        button6.check(matches(isDisplayed()))

        val materialButton19 = onView(
            allOf(
                withId(R.id.exam_button_submit),
                childAtPosition(
                    allOf(
                        withId(R.id.exam_submit_button),
                        childAtPosition(
                            withId(R.id.exam_words_list),
                            3
                        )
                    ),
                    0
                )
            )
        )
        materialButton19.perform(scrollTo(), click())

        val editText = onView(
            allOf(
                withId(R.id.test_edit_number_question), withText("10"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        editText.check(matches(withText("10")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
