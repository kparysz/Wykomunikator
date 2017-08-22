import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pl.kparysz.wykomessages.R
import pl.kparysz.wykomessages.messages.view.MainActivity


@RunWith(AndroidJUnit4::class)
class MessagesActivityTest : AutomatedTestActivity() {

    @get:Rule
    var activity = ActivityTestRule(MainActivity::class.java, true, false)

    @Before
    @Throws(Exception::class)
    public override fun setUp() {
        super.setUp()
        activity.launchActivity(null)
    }

    @Test
    fun seeExampleUserOnConversationList() {
        onView(withId(R.id.messages_list))
                .check(matches(hasDescendant(withText("Example User"))))
    }

}