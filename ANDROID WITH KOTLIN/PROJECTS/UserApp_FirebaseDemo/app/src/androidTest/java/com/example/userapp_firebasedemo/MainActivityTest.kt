package com.example.userapp_firebasedemo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testRegistrationForm() {
        // Verify the title is displayed
        onView(withId(R.id.text1)).check(matches(isDisplayed()))
        onView(withId(R.id.text1)).check(matches(withText("REGISTRATION")))

        // Fill the full name field
        onView(withId(R.id.ed1))
            .perform(typeText("John Doe"), closeSoftKeyboard())
        onView(withId(R.id.ed1)).check(matches(withText("John Doe")))

        // Fill the username field
        onView(withId(R.id.ed2))
            .perform(typeText("johndoe"), closeSoftKeyboard())
        onView(withId(R.id.ed2)).check(matches(withText("johndoe")))

        // Fill the password field
        onView(withId(R.id.ed3))
            .perform(typeText("password123"), closeSoftKeyboard())//typing
        onView(withId(R.id.ed3)).check(matches(withText("password123")))//checking typed text

        // Click the Register button
        onView(withId(R.id.btn_rigis)).perform(click())

        // Verify the "Already Registered?" text is displayed
        onView(withId(R.id.txt3)).check(matches(isDisplayed()))
        onView(withId(R.id.txt3)).check(matches(withText("Already Registered? ")))

        // Verify the "Click here" link is displayed
        onView(withId(R.id.login_link)).check(matches(isDisplayed()))
        onView(withId(R.id.login_link)).check(matches(withText("Click here")))
    }


}
