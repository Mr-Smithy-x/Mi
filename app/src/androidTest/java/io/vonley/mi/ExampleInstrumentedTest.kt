package io.vonley.mi

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

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
        assertEquals("io.vonley.mi", appContext.packageName)
        /*val clientSync = ClientSyncService(appContext)
        clientSync.fetchClients().forEach { client ->
            val info = "Info: ${client.getHostName()} : ${client.address.hostAddress}"
            Log.e("client", info)
            println(info)
            assertEquals(client.getHostName(), client.getHostName())
        }*/
    }
}