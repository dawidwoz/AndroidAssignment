package uk.ac.aber.dcs.cs31620.assignment


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
class ChangeLanguageTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun changeLanguageTest() {
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
        appCompatEditText.perform(replaceText("French"), closeSoftKeyboard())

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
        appCompatEditText2.perform(replaceText("English"), closeSoftKeyboard())

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
        appCompatEditText3.perform(scrollTo(), replaceText("Bonjour"), closeSoftKeyboard())

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
        appCompatEditText4.perform(scrollTo(), replaceText("Good morning"), closeSoftKeyboard())

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

        val textView = onView(
            allOf(
                withId(R.id.home_words_text),
                withText("You've added 1 words to your vocabulary list."),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("You've added 1 words to your vocabulary list.")))

        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.navigation_settings), withContentDescription("Settings"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val editText = onView(
            allOf(
                withId(R.id.setting_edit_your_language), withText("French"),
                withParent(
                    allOf(
                        withId(R.id.setting_language),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        editText.check(matches(withText("French")))

        val editText2 = onView(
            allOf(
                withId(R.id.setting_edit_desired_language), withText("English"),
                withParent(
                    allOf(
                        withId(R.id.setting_foreign_language),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        editText2.check(matches(withText("English")))

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.setting_edit_your_language), withText("French"),
                childAtPosition(
                    allOf(
                        withId(R.id.setting_language),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText5.perform(replaceText(""))

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.setting_edit_your_language),
                childAtPosition(
                    allOf(
                        withId(R.id.setting_language),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText6.perform(closeSoftKeyboard())

        val appCompatEditText7 = onView(
            allOf(
                withId(R.id.setting_edit_desired_language), withText("English"),
                childAtPosition(
                    allOf(
                        withId(R.id.setting_foreign_language),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText7.perform(replaceText(""))

        val appCompatEditText8 = onView(
            allOf(
                withId(R.id.setting_edit_desired_language),
                childAtPosition(
                    allOf(
                        withId(R.id.setting_foreign_language),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText8.perform(closeSoftKeyboard())

        val materialButton3 = onView(
            allOf(
                withId(R.id.setting_save_button), withText("Save"),
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
        materialButton3.perform(click())

        val button = onView(
            allOf(
                withId(R.id.setting_save_button), withText("SAVE"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val appCompatEditText9 = onView(
            allOf(
                withId(R.id.setting_edit_your_language),
                childAtPosition(
                    allOf(
                        withId(R.id.setting_language),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText9.perform(replaceText("English"), closeSoftKeyboard())

        val appCompatEditText10 = onView(
            allOf(
                withId(R.id.setting_edit_desired_language),
                childAtPosition(
                    allOf(
                        withId(R.id.setting_foreign_language),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText10.perform(replaceText("French"), closeSoftKeyboard())

        val materialButton4 = onView(
            allOf(
                withId(R.id.setting_save_button), withText("Save"),
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
        materialButton4.perform(click())

        val textView2 = onView(
            allOf(
                withId(android.R.id.message),
                withText("Are you sure? Present vocabulary will be deleted!"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Are you sure? Present vocabulary will be deleted!")))

        val button2 = onView(
            allOf(
                withId(android.R.id.button2), withText("NO"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        button2.check(matches(isDisplayed()))

        val button3 = onView(
            allOf(
                withId(android.R.id.button1), withText("YES"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        button3.check(matches(isDisplayed()))

        val materialButton5 = onView(
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
        materialButton5.perform(scrollTo(), click())

        val bottomNavigationItemView2 = onView(
            allOf(
                withId(R.id.navigation_home), withContentDescription("Home"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView2.perform(click())

        val textView3 = onView(
            allOf(
                withId(R.id.home_your_language), withText("English"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("English")))

        val textView4 = onView(
            allOf(
                withId(R.id.home_words_text),
                withText("You've added 1 words to your vocabulary list."),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("You've added 1 words to your vocabulary list.")))

        val bottomNavigationItemView3 = onView(
            allOf(
                withId(R.id.navigation_settings), withContentDescription("Settings"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView3.perform(click())

        val appCompatEditText11 = onView(
            allOf(
                withId(R.id.setting_edit_your_language), withText("French"),
                childAtPosition(
                    allOf(
                        withId(R.id.setting_language),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText11.perform(replaceText("English"))

        val appCompatEditText12 = onView(
            allOf(
                withId(R.id.setting_edit_your_language), withText("English"),
                childAtPosition(
                    allOf(
                        withId(R.id.setting_language),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText12.perform(closeSoftKeyboard())

        val appCompatEditText13 = onView(
            allOf(
                withId(R.id.setting_edit_desired_language), withText("English"),
                childAtPosition(
                    allOf(
                        withId(R.id.setting_foreign_language),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText13.perform(replaceText("French"))

        val appCompatEditText14 = onView(
            allOf(
                withId(R.id.setting_edit_desired_language), withText("French"),
                childAtPosition(
                    allOf(
                        withId(R.id.setting_foreign_language),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText14.perform(closeSoftKeyboard())

        val materialButton6 = onView(
            allOf(
                withId(R.id.setting_save_button), withText("Save"),
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
        materialButton6.perform(click())

        val materialButton7 = onView(
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
        materialButton7.perform(scrollTo(), click())

        val bottomNavigationItemView4 = onView(
            allOf(
                withId(R.id.navigation_home), withContentDescription("Home"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView4.perform(click())

        val textView5 = onView(
            allOf(
                withId(R.id.home_your_language), withText("French"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("French")))

        val textView6 = onView(
            allOf(
                withId(R.id.home_words_text),
                withText("You've added 0 words to your vocabulary list."),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        textView6.check(matches(withText("You've added 0 words to your vocabulary list.")))
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
