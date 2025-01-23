package com.example.userapp_firebasedemo

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.userapp_firebasedemo.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowAlertDialog


@Config(manifest= Config.NONE)
@RunWith(AndroidJUnit4::class)
class MainActivityTest {


   // private lateinit var binding : ActivityMainBinding
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Mock
    private lateinit var mockDatabase: FirebaseDatabase

    @Mock
    private lateinit var mockDbReference: DatabaseReference

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        scenario = ActivityScenario.launch(MainActivity::class.java)
        `when`(mockDatabase.getReference("Users")).thenReturn(mockDbReference)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun testRegisterButton_withValidInputs_registersUser() {
        scenario.onActivity { activity ->
            // Simulate user input
            activity.binding.ed1.setText("John Doe")
            activity.binding.ed2.setText("john_doe")
            activity.binding.ed3.setText("password123")

            // Click the register button
            activity.binding.btnRigis.performClick()

            // Verify Firebase interactions
            verify(mockDbReference).child("john_doe")
            verify(mockDbReference.child("john_doe")).setValue(any(Users::class.java))

            // Verify UI behavior
            val dialog = ShadowAlertDialog.getLatestAlertDialog()
            assertNotNull(dialog)
            assertTrue(dialog.isShowing)
        }
    }

    @Test
    fun testRegisterButton_withEmptyFields_showAlertDialog() {
        scenario.onActivity { activity ->
            // Simulate empty input
            activity.binding.ed1.setText("")
            activity.binding.ed2.setText("")
            activity.binding.ed3.setText("")

            // Click the register button
            activity.binding.btnRigis.performClick()

            // Verify UI behavior
            val dialog = ShadowAlertDialog.getLatestAlertDialog()
            assertNotNull(dialog)
            assertTrue(dialog.isShowing)
                 }
    }


}
