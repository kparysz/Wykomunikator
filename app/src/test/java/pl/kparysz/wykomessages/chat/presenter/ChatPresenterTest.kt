package pl.kparysz.wykomessages.chat.presenter

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import pl.kparysz.wykomessages.SubscriptionManagerTest
import pl.kparysz.wykomessages.chat.repository.ChatDetailApi
import pl.kparysz.wykomessages.chat.view.ChatView
import pl.kparysz.wykomessages.models.dataclass.PrivateMessageDetail

class ChatPresenterTest{

    lateinit var systemUnderTest: ChatPresenter
    var subscriptionManagerTest = SubscriptionManagerTest()
    var mockOfView = mock<ChatView>()
    var mockOfChatApi = mock<ChatDetailApi>()

    @Before
    fun setUp() {
        systemUnderTest = ChatPresenter(subscriptionManagerTest, mockOfChatApi)
        systemUnderTest.setView(mockOfView)
    }

    @Test
    fun shouldShowChat() {
        val expectedList = mockOfPrivateMessageDetail()
        whenever(mockOfChatApi.getChatDetail(any())).thenReturn(Observable.just(expectedList))
        systemUnderTest.downloadChat("userName")

        verify(mockOfView).showChat(expectedList)
    }

    private fun mockOfPrivateMessageDetail() = (0..5)
            .map { it -> PrivateMessageDetail("body", "author", "date", it % 2 == 0) }
}