package uk.ac.aber.dcs.cs31620.assignment

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`

@LargeTest
@RunWith(AndroidJUnit4::class)
class LearnScreenTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun learnTest() {
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
        appCompatEditText3.perform(scrollTo(), replaceText("der Rechner"), closeSoftKeyboard())

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
        appCompatEditText4.perform(scrollTo(), replaceText("Komputer"), closeSoftKeyboard())

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
        appCompatEditText5.perform(scrollTo(), replaceText("Deutschland"), closeSoftKeyboard())

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
        appCompatEditText6.perform(scrollTo(), replaceText("Niemcy"), closeSoftKeyboard())

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
        appCompatEditText7.perform(scrollTo(), replaceText("schön"), closeSoftKeyboard())

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
        appCompatEditText8.perform(scrollTo(), replaceText("spoko"), closeSoftKeyboard())

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
        appCompatEditText9.perform(scrollTo(), click())

        val appCompatEditText10 = onView(
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
        appCompatEditText10.perform(scrollTo(), click())

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
        appCompatEditText11.perform(scrollTo(), replaceText("schlau"), closeSoftKeyboard())

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
        appCompatEditText12.perform(scrollTo(), replaceText("madry"), closeSoftKeyboard())

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

        val bottomNavigationItemView2 = onView(
            allOf(
                withId(R.id.navigation_learn), withContentDescription("Learn"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView2.perform(click())

        val button = onView(
            allOf(
                withId(R.id.learn_button_word),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val materialButton6 = onView(
            allOf(
                withId(R.id.learn_button_word),
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
        materialButton6.perform(click())

        val button2 = onView(
            allOf(
                withId(R.id.learn_button_word),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        button2.check(matches(isDisplayed()))

        val materialButton7 = onView(
            allOf(
                withId(R.id.learn_button_word),
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
        materialButton7.perform(click())

        val button3 = onView(
            allOf(
                withId(R.id.learn_button_word),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        button3.check(matches(isDisplayed()))
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
