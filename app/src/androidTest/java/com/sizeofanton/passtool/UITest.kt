package com.sizeofanton.passtool


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.sizeofanton.passtool.matchers.LengthEqualsTo
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class UITest{

    @get:Rule var activityScenarioRule = activityScenarioRule<MainActivity>()


    @Test
    fun clickInfoTextView(){
        onView(withId(R.id.buttonInfo)).perform(click())
        onView(withText(R.string.no_password_snack))
            .check(matches(isDisplayed()));

        Thread.sleep(6000)

        onView(withId(R.id.buttonGenerate)).perform(click())
        onView(withId(R.id.buttonInfo)).perform(click())
        onView(withText(R.string.password_fair))
            .check(matches(isDisplayed()));

    }

    @Test
    fun clickSaveTextView(){
        onView(withId(R.id.buttonSave)).perform(click())
        onView(withText(R.string.no_password_snack))
            .check(matches(isDisplayed()))

        Thread.sleep(6000)

        onView(withId(R.id.buttonGenerate)).perform(click())
        onView(withId(R.id.buttonSave)).perform(click())
        onView(withText(R.string.password_copied))
            .check(matches(isDisplayed()));
    }

    @Test
    fun clickButton(){
        onView(withId(R.id.buttonGenerate)).perform(click())
        onView(withId(R.id.tvPassword)).check(matches(not(withText(""))))
    }

    @Test
    fun clickSwitches(){
        onView(withId(R.id.switchUpperCase)).perform(click()).check(matches(isChecked()))
        onView(withId(R.id.switchLowerCase)).perform(click()).check(matches(not(isChecked())))
        onView(withId(R.id.switchDigits)).perform(click()).check(matches(not(isChecked())))
        onView(withId(R.id.switchSymbols)).perform(click()).check(matches(isChecked()))

    }

    @Test
    fun passwordLength(){
        onView(withId(R.id.buttonGenerate)).perform(click())
        onView(withId(R.id.tvPassword)).check(matches(LengthEqualsTo(6)))

        onView(withId(R.id.numberSeekBar)).perform(swipeRight())
        onView(withId(R.id.buttonGenerate)).perform(click())
        onView(withId(R.id.tvPassword)).check(matches(LengthEqualsTo(30)))
    }


}