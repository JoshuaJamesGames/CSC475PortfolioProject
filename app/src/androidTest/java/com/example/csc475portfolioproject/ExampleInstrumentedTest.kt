package com.example.csc475portfolioproject

import android.app.Activity
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {
    private val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java)
        .putExtra("title", "Testing rules!")
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule<MainActivity>(intent)
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.csc475portfolioproject", appContext.packageName)
    }

    fun clickNewCharacter(){
        onView(withId(R.id.fbAddCharacter)).perform(click())
    }

}