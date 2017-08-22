

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.test.InstrumentationTestCase
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

open class AutomatedTestActivity : InstrumentationTestCase() {

    private var server: MockWebServer? = null

    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
        server = MockWebServer()
        server!!.start(12345)
        server!!.setDispatcher(dispatcher)
    }

    @After
    @Throws(Exception::class)
    public override fun tearDown() {
        server!!.shutdown()
    }

    private val dispatcher = object : Dispatcher() {

        @Throws(InterruptedException::class)
        override fun dispatch(request: RecordedRequest): MockResponse {

            if (request.path == "/pm/conversationslist/appkey,FAKE_APP_KEY/userkey,") {
                return MockResponse().setResponseCode(200).setBody(getBody("messages.json"))
            } else if (request.path == "/pm/conversation/name/appkey,FAKE_APP_KEY/userkey,") {
                return MockResponse().setResponseCode(200).setBody(getBody("chat.json"))
            }
            return MockResponse().setResponseCode(404)
        }
    }

    private fun getBody(fileName: String): String {
        var stringFromFile = ""
        try {
            stringFromFile = getStringFromFile(InstrumentationRegistry.getContext(), fileName)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return stringFromFile
    }

    @Throws(Exception::class)
    private fun getStringFromFile(context: Context, filePath: String): String {
        val stream = context.resources.assets.open(filePath)

        val ret = convertStreamToString(stream)
        stream.close()
        return ret
    }

    @Throws(Exception::class)
    private fun convertStreamToString(inputStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val sb = StringBuilder()
        reader.readLines().forEach { sb.append(it).append("\n") }
        reader.close()
        return sb.toString()
    }
}
