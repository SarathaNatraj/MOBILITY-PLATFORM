package com.sample.yogiraj.instagram.demo.ui.main

import android.os.IBinder
import androidx.test.espresso.Root
import android.view.WindowManager
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher


class ToastMatcher : TypeSafeMatcher<Root?>() {
    override fun describeTo(description: Description) {
        description.appendText("is toast")
    }



    override fun matchesSafely(item: Root?): Boolean {
        val type: Int = item?.getWindowLayoutParams()!!.get().type
        if (type == WindowManager.LayoutParams.TYPE_TOAST) {
            val windowToken: IBinder = item?.getDecorView().getWindowToken()
            val appToken: IBinder = item?.getDecorView().getApplicationWindowToken()
            if (windowToken === appToken) {
                //means this window isn't contained by any other windows.
            }
            return true
        }
        return false
    }
}

