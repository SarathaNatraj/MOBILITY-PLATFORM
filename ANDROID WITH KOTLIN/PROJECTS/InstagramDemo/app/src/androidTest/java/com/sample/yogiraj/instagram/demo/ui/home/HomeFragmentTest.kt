/*
 *  
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sample.yogiraj.instagram.demo.ui.home
import yogiraj.instagram.demo.R
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.sample.yogiraj.instagram.demo.DataModelObjectProvider

import com.sample.yogiraj.instagram.demo.data.local.rule.UserSessionRule
import com.sample.yogiraj.instagram.demo.di.rule.TestComponentRule

import com.sample.yogiraj.instagram.demo.utils.test.action.RecyclerViewItemActions
import com.sample.yogiraj.instagram.demo.utils.test.matcher.ImageMatchers
import com.sample.yogiraj.instagram.demo.utils.test.matcher.RecyclerViewMatchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

/**
 * Instrumented Test on [HomeFragment].
 *
 * @ 
 */
class HomeFragmentTest {

    // Get the Application Context
    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    /**
     * [org.junit.rules.TestRule] for Dagger setup.
     */
    private val componentRule = TestComponentRule(appContext)

    /**
     * [org.junit.rules.TestRule] for User session.
     */
    private val userSessionRule =
        com.sample.yogiraj.instagram.demo.data.local.rule.UserSessionRule(componentRule)

    /**
     * Getter for the [org.junit.rules.TestRule], which returns the [RuleChain] of TestRules
     * to be applied in the order of [componentRule], followed by [userSessionRule].
     */
    @get:Rule
    val ruleChain: RuleChain = RuleChain.outerRule(componentRule)
        .around(userSessionRule)

    @Before
    fun setUp() {
        // Launch HomeFragment in Container for testing
        launchFragmentInContainer<HomeFragment>(Bundle(), R.style.AppTheme)

        // Delay each test by a second, in order to allow the layout to load completely
        SystemClock.sleep(1000)
    }

    @After
    fun tearDown() {
        // No-op
    }

    @Test
    fun givenAvailablePosts_onDoubleTapOfPostPhoto_shouldLikeUnlikeThePost() {
        // Check if the RecyclerView is displayed
        Espresso.onView(ViewMatchers.withId(R.id.rv_home_posts))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Double-Tap on the Photo of the Post item at Position 5 which is posted
        // by user "User_20", in order to like the Post
        Espresso.onView(ViewMatchers.withId(R.id.rv_home_posts))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    5,
                    RecyclerViewItemActions.actionOnViewInItem(
                        R.id.image_home_item_post_photo,
                        R.id.rv_home_posts,
                        ViewActions.doubleClick()
                    )
                )
            )

        // Assert that the Post item at Position 5 has been liked by User "User_20", by verifying
        // that the corresponding Heart Image Button is showing the selected state image
        Espresso.onView(ViewMatchers.withId(R.id.rv_home_posts))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
            .check(
                ViewAssertions.matches(
                    RecyclerViewMatchers.hasViewInItemAtPosition(
                        5,
                        ImageMatchers.hasImageDrawable(R.drawable.ic_heart_selected),
                        R.id.imgbtn_home_item_toggle_post_like
                    )
                )
            )

        // Double-Tap again on the Photo of the Post item at Position 5 which is posted
        // by user "User_20", in order to Unlike the Post
        Espresso.onView(ViewMatchers.withId(R.id.rv_home_posts))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    5,
                    RecyclerViewItemActions.actionOnViewInItem(
                        R.id.image_home_item_post_photo,
                        R.id.rv_home_posts,
                        ViewActions.doubleClick()
                    )
                )
            )

        // Assert that the Post item at Position 5 has been unliked by User "User_20", by verifying
        // that the corresponding Heart Image Button is showing the unselected state image
        Espresso.onView(ViewMatchers.withId(R.id.rv_home_posts))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
            .check(
                ViewAssertions.matches(
                    RecyclerViewMatchers.hasViewInItemAtPosition(
                        5,
                        ImageMatchers.hasImageDrawable(R.drawable.ic_heart_unselected),
                        R.id.imgbtn_home_item_toggle_post_like
                    )
                )
            )
    }


}