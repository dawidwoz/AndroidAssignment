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
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class StartScreenTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun startScreenTest() {
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

        val button = onView(
            allOf(
                withId(R.id.start_button), withText("NEXT"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

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
        appCompatEditText.perform(replaceText("Polish"), closeSoftKeyboard())

        val materialButton2 = onView(
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
        materialButton2.perform(click())

        val button2 = onView(
            allOf(
                withId(R.id.start_button), withText("NEXT"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        button2.check(matches(isDisplayed()))

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.start_edit_own_language), withText("Polish"),
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
        appCompatEditText2.perform(replaceText(""))

        val appCompatEditText3 = onView(
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
        appCompatEditText3.perform(closeSoftKeyboard())

        val appCompatEditText4 = onView(
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
        appCompatEditText4.perform(replaceText("German"), closeSoftKeyboard())

        val materialButton3 = onView(
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
        materialButton3.perform(click())

        val button3 = onView(
            allOf(
                withId(R.id.start_button), withText("NEXT"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        button3.check(matches(isDisplayed()))

        val appCompatEditText5 = onView(
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
        appCompatEditText5.perform(replaceText("Polish"), closeSoftKeyboard())

        val materialButton4 = onView(
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
        materialButton4.perform(click())

        val viewGroup = onView(
            allOf(
                withParent(
                    allOf(
                        withId(R.id.nav_host_fragment),
                        withParent(withId(R.id.nav_host_fragment))
                    )
                ),
                isDisplayed()
            )
        )
        viewGroup.check(matches(isDisplayed()))
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
