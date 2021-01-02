package uk.ac.aber.dcs.cs31620.assignment


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
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
class EditWordTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun editWordTest() {
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
        appCompatEditText.perform(replaceText("German"), closeSoftKeyboard())

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
        appCompatEditText3.perform(scrollTo(), replaceText("Freitag"), closeSoftKeyboard())

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
        appCompatEditText4.perform(scrollTo(), replaceText("Saturday"), closeSoftKeyboard())

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

        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.navigation_list), withContentDescription("Your worlds"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val recyclerView = onView(
            allOf(
                withId(R.id.list_words_list),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val editText = onView(
            allOf(
                withId(R.id.edit_edit_your_word), withText("Freitag"),
                withParent(
                    allOf(
                        withId(R.id.edit_word),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        editText.check(matches(withText("Freitag")))

        val editText2 = onView(
            allOf(
                withId(R.id.edit_edit_desired_word), withText("Saturday"),
                withParent(
                    allOf(
                        withId(R.id.edit_foreign_word),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        editText2.check(matches(withText("Saturday")))

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.edit_edit_desired_word), withText("Saturday"),
                childAtPosition(
                    allOf(
                        withId(R.id.edit_foreign_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText5.perform(scrollTo(), replaceText("Friday"))

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.edit_edit_desired_word), withText("Friday"),
                childAtPosition(
                    allOf(
                        withId(R.id.edit_foreign_word),
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
        appCompatEditText6.perform(closeSoftKeyboard())

        val materialButton3 = onView(
            allOf(
                withId(R.id.edit_change_button), withText("Change"),
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

        val textView = onView(
            allOf(
                withId(R.id.word_original), withText("Freitag"),
                withParent(
                    allOf(
                        withId(R.id.word_item),
                        withParent(withId(R.id.list_words_list))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Freitag")))

        val textView2 = onView(
            allOf(
                withId(R.id.word_translation), withText("Friday"),
                withParent(
                    allOf(
                        withId(R.id.word_item),
                        withParent(withId(R.id.list_words_list))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Friday")))

        val recyclerView2 = onView(
            allOf(
                withId(R.id.list_words_list),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val appCompatEditText7 = onView(
            allOf(
                withId(R.id.edit_edit_your_word), withText("Freitag"),
                childAtPosition(
                    allOf(
                        withId(R.id.edit_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText7.perform(scrollTo(), click())

        val appCompatEditText8 = onView(
            allOf(
                withId(R.id.edit_edit_your_word), withText("Freitag"),
                childAtPosition(
                    allOf(
                        withId(R.id.edit_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText8.perform(scrollTo(), replaceText(""))

        val appCompatEditText9 = onView(
            allOf(
                withId(R.id.edit_edit_your_word),
                childAtPosition(
                    allOf(
                        withId(R.id.edit_word),
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
        appCompatEditText9.perform(closeSoftKeyboard())

        val materialButton4 = onView(
            allOf(
                withId(R.id.edit_change_button), withText("Change"),
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

        val button = onView(
            allOf(
                withId(R.id.edit_change_button), withText("CHANGE"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val appCompatEditText10 = onView(
            allOf(
                withId(R.id.edit_edit_your_word),
                childAtPosition(
                    allOf(
                        withId(R.id.edit_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText10.perform(scrollTo(), replaceText("freitags"), closeSoftKeyboard())

        val appCompatEditText11 = onView(
            allOf(
                withId(R.id.edit_edit_desired_word), withText("Friday"),
                childAtPosition(
                    allOf(
                        withId(R.id.edit_foreign_word),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText11.perform(scrollTo(), replaceText("Fridays"))

        val appCompatEditText12 = onView(
            allOf(
                withId(R.id.edit_edit_desired_word), withText("Fridays"),
                childAtPosition(
                    allOf(
                        withId(R.id.edit_foreign_word),
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
        appCompatEditText12.perform(closeSoftKeyboard())

        val materialButton5 = onView(
            allOf(
                withId(R.id.edit_change_button), withText("Change"),
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

        val textView3 = onView(
            allOf(
                withId(R.id.word_original), withText("freitags"),
                withParent(
                    allOf(
                        withId(R.id.word_item),
                        withParent(withId(R.id.list_words_list))
                    )
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("freitags")))

        val textView4 = onView(
            allOf(
                withId(R.id.word_translation), withText("Fridays"),
                withParent(
                    allOf(
                        withId(R.id.word_item),
                        withParent(withId(R.id.list_words_list))
                    )
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Fridays")))
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
