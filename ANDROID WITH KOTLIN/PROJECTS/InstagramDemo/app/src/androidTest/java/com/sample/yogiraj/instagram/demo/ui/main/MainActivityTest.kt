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

package com.sample.yogiraj.instagram.demo.ui.main

import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.truth.content.IntentSubject
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth
import yogiraj.instagram.demo.R
import com.sample.yogiraj.instagram.demo.data.local.rule.UserSessionRule
import com.sample.yogiraj.instagram.demo.data.remote.api.FakeNetworkService
import com.sample.yogiraj.instagram.demo.di.rule.TestComponentRule
import com.sample.yogiraj.instagram.demo.ui.base.BaseActivityResultContracts
import com.sample.yogiraj.instagram.demo.ui.detail.PostDetailActivity
import com.sample.yogiraj.instagram.demo.ui.like.PostLikeActivity
import com.sample.yogiraj.instagram.demo.ui.profile.edit.EditProfileActivity
import com.sample.yogiraj.instagram.demo.utils.test.action.BottomNavigationViewActions
import com.sample.yogiraj.instagram.demo.utils.test.action.RecyclerViewItemActions
import com.sample.yogiraj.instagram.demo.utils.test.activity.TestActivityResultRegistry
import com.sample.yogiraj.instagram.demo.utils.test.matcher.RecyclerViewMatchers
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

/**
 * Instrumented test on [MainActivity].
 *
 * @ 
 */
class MainActivityTest {

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
     * [ActivityScenarioRule] for [MainActivity] setup, which launches MainActivity
     * for every test method.
     */
    private val mainActivityScenarioRule =
        ActivityScenarioRule<MainActivity>(Intent(appContext, MainActivity::class.java))

    /**
     * Getter for the [org.junit.rules.TestRule], which returns the [RuleChain] of TestRules
     * to be applied in the order of [componentRule], followed by [userSessionRule],
     * followed by [mainActivityScenarioRule]
     */
    @get:Rule
    val ruleChain: RuleChain = RuleChain.outerRule(componentRule)
        .around(userSessionRule)
        .around(mainActivityScenarioRule)

    @Before
    fun setUp() {
        // Start to capture Intents
        Intents.init()


        // Delay each test by a second, in order to allow the layout to load completely
        SystemClock.sleep(1000)
    }

    @After
    fun tearDown() {
        // Stop and release all the Intents captured
        Intents.release()
    }

    @Test
    fun testCheckBottomNavMenuHomeFragmentViewsDisplay() {
        // First assert MainActivity is launched by checking if the Bottom Navigation View is displayed
        Espresso.onView(ViewMatchers.withId(R.id.bottom_nav_main))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Click on the Home button of the Bottom Navigation Menu to display
        // HomeFragment in the fragment container
        Espresso.onView(ViewMatchers.withId(R.id.bottom_nav_main))
            .perform(BottomNavigationViewActions.navigateTo(R.id.action_main_bottom_nav_home))

        // Assert HomeFragment is loaded by checking if the RecyclerView for Posts is displayed
        Espresso.onView(ViewMatchers.withId(R.id.rv_home_posts))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testCheckBottomNavMenuPhotoFragmentViewsDisplay() {
        // First assert MainActivity is launched by checking if the Bottom Navigation View is displayed
        Espresso.onView(ViewMatchers.withId(R.id.bottom_nav_main))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Click on the "Add Photos" button of the Bottom Navigation Menu to display
        // PhotoFragment in the fragment container
        Espresso.onView(ViewMatchers.withId(R.id.bottom_nav_main))
            .perform(BottomNavigationViewActions.navigateTo(R.id.action_main_bottom_nav_photo))

        // Assert PhotoFragment is loaded by checking if the Camera and Gallery Options are displayed
        Espresso.onView(ViewMatchers.withId(R.id.image_photo_option_camera))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.image_photo_option_gallery))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testCheckBottomNavMenuProfileFragmentViewsDisplay() {
        // First assert MainActivity is launched by checking if the Bottom Navigation View is displayed
        Espresso.onView(ViewMatchers.withId(R.id.bottom_nav_main))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Click on the Profile button of the Bottom Navigation Menu to display
        // ProfileFragment in the fragment container
        Espresso.onView(ViewMatchers.withId(R.id.bottom_nav_main))
            .perform(BottomNavigationViewActions.navigateTo(R.id.action_main_bottom_nav_profile))

        // Assert ProfileFragment is loaded by checking if the Edit Profile button is displayed
        Espresso.onView(ViewMatchers.withId(R.id.button_profile_edit))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }




    @Test
    fun givenAvailablePostsInHomeBottomNavMenu_onClickOfPostLikesCount_shouldLaunchPostLikeActivity() {
        // Load HomeFragment and verify
        testCheckBottomNavMenuHomeFragmentViewsDisplay()

        // Block PostLikeActivity start to capture Intents
        InstrumentationRegistry.getInstrumentation().addMonitor(
            Instrumentation.ActivityMonitor(PostLikeActivity::class.java.name, null, true)
        )

        // Click on the Likes Count Text of the Post item at Position 5 which is posted
        // by user "User_20", in order to launch the PostLikeActivity
        Espresso.onView(ViewMatchers.withId(R.id.rv_home_posts))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(6),
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    RecyclerViewItemActions.actionOnViewInItem(
                        R.id.text_home_item_post_like_count,
                        R.id.rv_home_posts,
                        ViewActions.click()
                    )
                )
            )

        // Whenever an activity is launched, current activity goes into the STOPPED state.
        // Hence simulate the same so that we can capture the Intents and verify them. This
        // prevents from resetting the Intents captured when PostLikeActivity is being launched.
        mainActivityScenarioRule.scenario.moveToState(Lifecycle.State.STARTED)

        // Verify that the activity launched via the Intent was PostLikeActivity
        Intents.intended(IntentMatchers.hasComponent(PostLikeActivity::class.java.name))

        // Assert that the number of Intents captured till last action was 2.
        // This includes the Intent used for launching MainActivity at the start of the test.
        Truth.assertThat(Intents.getIntents()).hasSize(2)

        Intents.getIntents().first { intent: Intent ->
            // Filter only the Intent meant for PostLikeActivity
            intent.component?.className?.contains(PostLikeActivity::class.java.simpleName)
                ?: false
        }.run {
            // Assert this Intent is for PostLikeActivity
            IntentSubject.assertThat(this).hasComponentClass(PostLikeActivity::class.java.name)
            // Assert this Intent has extras containing the "PostLikeActivity.EXTRA_POST_ID" Key
            // needed for launching PostLikeActivity for a particular Post's Like count that was clicked.
            IntentSubject.assertThat(this).extras().containsKey(PostLikeActivity.EXTRA_POST_ID)
        }

    }


    @Test
    fun givenMyPostsInProfileBottomNavMenu_onClickOfPost_shouldLaunchPostDetailActivity() {
        // Load ProfileFragment and verify
        testCheckBottomNavMenuProfileFragmentViewsDisplay()

        // Block PostDetailActivity start to capture Intents
        InstrumentationRegistry.getInstrumentation().addMonitor(
            Instrumentation.ActivityMonitor(PostDetailActivity::class.java.name, null, true)
        )

        // Click on the Post item at Position 5 in order to launch PostDetailActivity
        Espresso.onView(ViewMatchers.withId(R.id.rv_profile_my_posts))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5),
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    5,
                    ViewActions.click()
                )
            )

        // Whenever an activity is launched, current activity goes into the STOPPED state.
        // Hence simulate the same so that we can capture the Intents and verify them. This
        // prevents from resetting the Intents captured when PostDetailActivity is being launched.
        mainActivityScenarioRule.scenario.moveToState(Lifecycle.State.STARTED)

        // Verify that the activity launched via the Intent was PostDetailActivity
        Intents.intended(IntentMatchers.hasComponent(PostDetailActivity::class.java.name))

        // Assert that the number of Intents captured till last action was 2.
        // This includes the Intent used for launching MainActivity at the start of the test.
        Truth.assertThat(Intents.getIntents()).hasSize(2)

        Intents.getIntents().first { intent: Intent ->
            // Filter only the Intent meant for PostDetailActivity
            intent.component?.className?.contains(PostDetailActivity::class.java.simpleName)
                ?: false
        }.run {
            // Assert this Intent is for PostDetailActivity
            IntentSubject.assertThat(this).hasComponentClass(PostDetailActivity::class.java.name)
            // Assert this Intent has extras containing the "PostDetailActivity.EXTRA_POST_ID" Key
            // needed for launching PostDetailActivity for a particular Post that was clicked.
            IntentSubject.assertThat(this).extras().containsKey(PostDetailActivity.EXTRA_POST_ID)
        }
    }

    @Test
    fun givenProfileBottomNavMenu_onClickOfEditProfile_shouldLaunchEditProfileActivity() {
        // Load ProfileFragment and verify
        testCheckBottomNavMenuProfileFragmentViewsDisplay()

        // Block EditProfileActivity start to capture Intents
        InstrumentationRegistry.getInstrumentation().addMonitor(
            Instrumentation.ActivityMonitor(EditProfileActivity::class.java.name, null, true)
        )

        // Click on the "Edit Profile" button in order to launch EditProfileActivity
        Espresso.onView(ViewMatchers.withId(R.id.button_profile_edit))
            .perform(ViewActions.click())

        // Whenever an activity is launched, current activity goes into the STOPPED state.
        // Hence simulate the same so that we can capture the Intents and verify them. This
        // prevents from resetting the Intents captured when EditProfileActivity is being launched.
        mainActivityScenarioRule.scenario.moveToState(Lifecycle.State.STARTED)

        // Verify that the activity launched via the Intent was EditProfileActivity
        Intents.intended(IntentMatchers.hasComponent(EditProfileActivity::class.java.name))

        // Assert that the number of Intents captured till last action was 2.
        // This includes the Intent used for launching MainActivity at the start of the test.
        Truth.assertThat(Intents.getIntents()).hasSize(2)

        Intents.getIntents().first { intent: Intent ->
            // Filter only the Intent meant for EditProfileActivity
            intent.component?.className?.contains(EditProfileActivity::class.java.simpleName)
                ?: false
        }.run {
            // Assert this Intent is for EditProfileActivity
            IntentSubject.assertThat(this).hasComponentClass(EditProfileActivity::class.java.name)
            // Assert this Intent has No extras
            IntentSubject.assertThat(this).extras().isNull()
        }
    }

    @Test
    fun givenProfileBottomNavMenu_onClickOfEditProfile_shouldLaunchEditProfileActivityAndReturnResultForEditsApplied() {
        // Reload event triggered to Home and Profile Fragments when some Profile Edits are applied
        val expectedReloadEventTriggered = true
        // Message that will be published on success of some Profile Edits applied
        val expectedProfileEditSuccessMessage = com.sample.yogiraj.instagram.demo.data.remote.api.FakeNetworkService.RESPONSE_INFO_UPDATED

        // TestActivityResultRegistry instance to provide the result of Profile edits
        // from EditProfileActivity
        val testActivityResultRegistry = TestActivityResultRegistry(
            Bundle().apply {
                putInt(
                    BaseActivityResultContracts.BUNDLE_KEY_RESULT_CODE,
                    EditProfileActivity.RESULT_EDIT_PROFILE_SUCCESS
                )
                putString(
                    EditProfileActivity.EXTRA_RESULT_EDIT_SUCCESS,
                    expectedProfileEditSuccessMessage
                )
            }
        )

        mainActivityScenarioRule.scenario.apply {
            // Move to CREATED state in order to re-register all the ActivityResultLaunchers of MainActivity
            moveToState(Lifecycle.State.CREATED)

            // On the MainActivity, forcefully re-register all the ActivityResultLaunchers with the
            // TestActivityResultRegistry instance for ActivityResultRegistry in order to
            // stub the EditProfileActivity result
            onActivity { activity: MainActivity ->
                activity.activityResultObserver.initResultLaunchers(
                    testActivityResultRegistry,
                    activity,
                    activity
                )
            }

            // After stubbing, move to STARTED and RESUMED state
            moveToState(Lifecycle.State.STARTED)
            moveToState(Lifecycle.State.RESUMED)
        }

        // Load ProfileFragment and verify
        testCheckBottomNavMenuProfileFragmentViewsDisplay()

        // Click on the "Edit Profile" button in order to launch EditProfileActivity
        // (Note: Since the result is stubbed, EditProfileActivity will not be launched
        // but will be called upon to return the stubbed result)
        Espresso.onView(ViewMatchers.withId(R.id.button_profile_edit))
            .perform(ViewActions.click())

        // Check if the message "Profile information has been updated" is shown by a Toast
      Espresso.onView(ViewMatchers.withId(android.R.id.message))
            .inRoot( ToastMatcher())
            .check(matches(isDisplayed()))

        // Wait a while to process and receive the result
        SystemClock.sleep(3000)

        // Assert that the EditProfileActivity was called and launched for result
        assertTrue(testActivityResultRegistry.isActivityLaunched)

        mainActivityScenarioRule.scenario.onActivity { activity: MainActivity ->
            // On the MainActivity

            // Assert that the Shared ViewModel received the stubbed result
            // in "userProfileInfoUpdateToHome" and "userProfileInfoUpdateToProfile" LiveData
            assertEquals(
                expectedReloadEventTriggered,
                activity.mainSharedViewModel.userProfileInfoUpdateToHome.value?.peekContent()
            )
            assertEquals(
                expectedReloadEventTriggered,
                activity.mainSharedViewModel.userProfileInfoUpdateToProfile.value?.peekContent()
            )

        }
    }



}