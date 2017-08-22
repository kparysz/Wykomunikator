import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pl.kparysz.wykomessages.R
import pl.kparysz.wykomessages.chat.view.ChatActivity

@RunWith(AndroidJUnit4::class)
class ChatActivityTest : AutomatedTestActivity(){

    @get:Rule
    var activity = ActivityTestRule(ChatActivity::class.java, true, false)

    @Before
    @Throws(Exception::class)
    public override fun setUp() {
        super.setUp()
        val intent = Intent().apply { putExtra(ChatActivity.USER_NAME_BUNDLE_KEY, "name") }
        activity.launchActivity(intent)
    }

    @Test
    fun seeExampleUserOnConversationList() {
        Espresso.onView(ViewMatchers.withId(R.id.chat_list))
                .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("Dobra aplikacja!"))))
    }
}
