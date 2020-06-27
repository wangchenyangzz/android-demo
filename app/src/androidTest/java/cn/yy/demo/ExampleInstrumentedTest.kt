package cn.yy.demo

import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import cn.yy.demo.page.PageActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("cn.yy.demo", appContext.packageName)
    }

    @RunWith(AndroidJUnit4::class)
    class MyTestSuite {
        @Test fun testEvent() {
            val scenario = ActivityScenario.launch(MainActivity::class.java)
            scenario.recreate()
        }
    }
}
