import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.kparysz.wykomessages.R;
import pl.kparysz.wykomessages.messages.view.MessagesActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class MessagesActivityTest extends AutomatedTestActivity {

    @Rule
    public ActivityTestRule<MessagesActivity> activity =
            new ActivityTestRule<>(MessagesActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        super.setUp();
        activity.launchActivity(null);
    }

    @Test
    public void seeExampleUserOnConversationList() {
        onView(withId(R.id.messages_list))
                .check(matches(hasDescendant(withText("Example User"))));
    }

}