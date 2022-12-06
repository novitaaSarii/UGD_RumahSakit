package com.novita.ugd_rumahsakit


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val materialButton = onView(
            allOf(
                withId(R.id.btn_create), withText("Create Account"),
                childAtPosition(
                    allOf(
                        withId(R.id.MainLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText = onView(
            allOf(
                withId(R.id.username),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.etUsername),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("q"), closeSoftKeyboard())

        val materialButton2 = onView(
            allOf(
                withId(R.id.btn_create), withText("Create Account"),
                childAtPosition(
                    allOf(
                        withId(R.id.MainLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.password),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.etPassword),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(replaceText("q"), closeSoftKeyboard())

        val materialButton3 = onView(
            allOf(
                withId(R.id.btn_create), withText("Create Account"),
                childAtPosition(
                    allOf(
                        withId(R.id.MainLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())
        onView(isRoot()).perform(waitFor(3000))
        val textInputEditText3 = onView(
            allOf(
                withId(R.id.email),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.etEmail),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText("q"), closeSoftKeyboard())

        val materialButton4 = onView(
            allOf(
                withId(R.id.btn_create), withText("Create Account"),
                childAtPosition(
                    allOf(
                        withId(R.id.MainLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.email), withText("q"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.etEmail),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(click())

        val textInputEditText5 = onView(
            allOf(
                withId(R.id.email), withText("q"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.etEmail),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText5.perform(click())

        val textInputEditText6 = onView(
            allOf(
                withId(R.id.email), withText("q"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.etEmail),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText6.perform(click())

        val textInputEditText7 = onView(
            allOf(
                withId(R.id.email), withText("q"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.etEmail),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText7.perform(replaceText("q@gmail.com"))

        val textInputEditText8 = onView(
            allOf(
                withId(R.id.email), withText("q@gmail.com"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.etEmail),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText8.perform(closeSoftKeyboard())

        val materialButton5 = onView(
            allOf(
                withId(R.id.btn_create), withText("Create Account"),
                childAtPosition(
                    allOf(
                        withId(R.id.MainLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton5.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText9 = onView(
            allOf(
                withId(R.id.tanggal),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.etTanggalLahir),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText9.perform(replaceText("Jan"), closeSoftKeyboard())

        val textInputEditText10 = onView(
            allOf(
                withId(R.id.tanggal), withText("Jan"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.etTanggalLahir),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText10.perform(replaceText("Januari "))

        val textInputEditText11 = onView(
            allOf(
                withId(R.id.tanggal), withText("Januari "),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.etTanggalLahir),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText11.perform(closeSoftKeyboard())

        val materialButton6 = onView(
            allOf(
                withId(R.id.btn_create), withText("Create Account"),
                childAtPosition(
                    allOf(
                        withId(R.id.MainLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton6.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText12 = onView(
            allOf(
                withId(R.id.Nomor),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.etNomorTelepon),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText12.perform(replaceText("081377729210"), closeSoftKeyboard())

        val materialButton7 = onView(
            allOf(
                withId(R.id.btn_create), withText("Create Account"),
                childAtPosition(
                    allOf(
                        withId(R.id.MainLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton7.perform(click())
        onView(isRoot()).perform(waitFor(3000))
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

    fun waitFor(delay: Long): ViewAction?{
        return object  : ViewAction {
            override fun getConstraints(): Matcher<View>{
                return isRoot()
            }

            override  fun getDescription(): String{
                return "wait for " + delay + "milliseconds"
            }

            override fun perform(uiController: UiController, view: View){
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }
}
