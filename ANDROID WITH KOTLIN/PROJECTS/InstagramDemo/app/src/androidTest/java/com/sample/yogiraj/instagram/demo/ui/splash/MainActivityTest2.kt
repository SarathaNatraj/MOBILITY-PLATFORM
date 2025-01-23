package com.sample.yogiraj.instagram.demo.ui.splash


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.sample.yogiraj.instagram.demo.ui.login.LoginActivity
import com.sample.yogiraj.instagram.demo.ui.main.MainActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import yogiraj.instagram.demo.R

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest2 {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun mainActivityTest2() {
        /*val imageView = onView(
            allOf(
                withId(R.id.image_login_logo), withContentDescription("App logo"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))
*/
        val linearLayout = onView(

                withId(R.id.text_input_login_email)


        )
        linearLayout.check(matches(isDisplayed()))


        val linearLayout2 = onView(

                withId(R.id.text_input_login_password)

        )
        linearLayout2.check(matches(isDisplayed()))


        val button = onView(

                 withText("LOGIN"),
                   )
        button.check(matches(isDisplayed()))


    }

    @Test
    fun loginScreenTest(){
        val editText = onView(withId(R.id.text_input_login_email)).perform(ViewActions.typeText("demo@gmail.com"), ViewActions.closeSoftKeyboard())
        editText.check(matches(withText("demo@gmail.com")))

        val editText2 = onView(
            withId(R.id.edit_login_password)).perform(ViewActions.typeText("demo"),ViewActions.closeSoftKeyboard())
        editText2.check(matches(withText("demo")))

    }
}
