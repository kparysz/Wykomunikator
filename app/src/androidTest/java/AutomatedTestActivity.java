import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.test.InstrumentationTestCase;

import org.junit.After;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

public class AutomatedTestActivity  extends InstrumentationTestCase {

    private MockWebServer server;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        server = new MockWebServer();
        server.start(12345);
        server.setDispatcher(dispatcher);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    private final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

            if (request.getPath().equals("/pm/conversationslist/appkey,FAKE_APP_KEY/userkey,")){
                return new MockResponse().setResponseCode(200).setBody(getBody("messages.json"));
            }
            return new MockResponse().setResponseCode(404);
        }
    };

    private String getBody(String fileName) {
        String stringFromFile = "";
        try {
            stringFromFile = getStringFromFile(InstrumentationRegistry.getContext(), fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringFromFile;
    }

    private String getStringFromFile(Context context, String filePath) throws Exception {
        final InputStream stream = context.getResources().getAssets().open(filePath);

        String ret = convertStreamToString(stream);
        stream.close();
        return ret;
    }

    private String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }
}
