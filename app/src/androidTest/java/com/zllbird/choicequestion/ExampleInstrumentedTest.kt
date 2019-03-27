package com.zllbird.choicequestion

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.dbflow5.structure.save
import com.zllbird.choicequestion.choice.model.Topic
import io.reactivex.Flowable

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
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.zllbird.choicequestion", appContext.packageName)
    }

    @Test
    fun loadChoiceTopic(){
    }

}
