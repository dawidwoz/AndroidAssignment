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
class DeleteWordTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun deleteWordTest() {
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
        appCompatEditText2.perform(replaceText("Polish"), closeSoftKeyboard())

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

        val floatingActionButton = onView(
            allOf(
                withId(R.id.list_add_word_button), withContentDescription("Button to add new word"),
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
        appCompatEditText3.perform(scrollTo(), replaceText("tea"), closeSoftKeyboard())

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
        appCompatEditText4.perform(scrollTo(), replaceText("herbata"), closeSoftKeyboard())

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
                withId(R.id.edit_edit_your_word), withText("tea"),
                withParent(
                    allOf(
                        withId(R.id.edit_word),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        editText.check(matches(withText("tea")))

        val editText2 = onView(
            allOf(
                withId(R.id.edit_edit_desired_word), withText("herbata"),
                withParent(
                    allOf(
                        withId(R.id.edit_foreign_word),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        editText2.check(matches(withText("herbata")))

        val editText3 = onView(
            allOf(
                withId(R.id.edit_edit_desired_word), withText("herbata"),
                withParent(
                    allOf(
                        withId(R.id.edit_foreign_word),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        editText3.check(matches(withText("herbata")))

        val materialButton3 = onView(
            allOf(
                withId(R.id.edit_delete_button), withText("Delete"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        materialButton3.perform(scrollTo(), click())

        val textView = onView(
            allOf(
                withId(android.R.id.message),
                withText("Are you sure you want to delete this word?"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Are you sure you want to delete this word?")))

        val button = onView(
            allOf(
                withId(android.R.id.button2), withText("NO"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val button2 = onView(
            allOf(
                withId(android.R.id.button1), withText("YES"),
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

        val materialButton4 = onView(
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
        materialButton4.perform(scrollTo(), click())

        val textView2 = onView(
            allOf(
                withId(R.id.list_text_no_words), withText("Upps! There is nothing here!"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Upps! There is nothing here!")))

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
                withId(R.id.home_words_text),
                withText("You've added 0 words to your vocabulary list."),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("You've added 0 words to your vocabulary list.")))
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
