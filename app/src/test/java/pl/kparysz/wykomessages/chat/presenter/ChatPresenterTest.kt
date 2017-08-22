package pl.kparysz.wykomessages.chat.presenter

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import pl.kparysz.wykomessages.SubscriptionManagerTest
import pl.kparysz.wykomessages.chat.repository.ChatDetailApi
import pl.kparysz.wykomessages.chat.view.ChatView
import pl.kparysz.wykomessages.models.dataclass.PrivateMessageDetail
import pl.kparysz.wykomessages.prefs.WykopPreferencesApi
import java.util.concurrent.TimeUnit

class ChatPresenterTest {

    lateinit var systemUnderTest: ChatPresenter
    lateinit var subscriptionManagerTest: SubscriptionManagerTest
    var mockOfView = mock<ChatView>()
    var mockOfChatApi = mock<ChatDetailApi>()
    var mockOfWykopPreferencesApi = mock<WykopPreferencesApi>()
    lateinit var testScheduler: TestScheduler

    @Before
    fun setUp() {
        testScheduler = TestScheduler()
        subscriptionManagerTest = spy(SubscriptionManagerTest(testScheduler))
        systemUnderTest = ChatPresenter(subscriptionManagerTest, mockOfWykopPreferencesApi, mockOfChatApi)
    }

    @Test
    fun shouldShowChat() {
        systemUnderTest.setView(mockOfView)
        systemUnderTest.userName = "userName"
        val expectedList = mockOfPrivateMessageDetail()
        whenever(mockOfChatApi.getChatDetail(any())).thenReturn(Observable.just(expectedList))
        systemUnderTest.downloadChat()

        verify(mockOfView).showChat(expectedList)
    }

    @Test
    fun shouldSendMessage() {
        whenever(mockOfChatApi.sendMessage("userName", "messageBody"))
                .thenReturn(Observable.just(Any()))
        systemUnderTest.setView(mockOfView)
        systemUnderTest.userName = "userName"
        systemUnderTest.sendMessage("userName", "messageBody")

        verify(mockOfView).messageSent()
    }

    @Test
    fun shouldShowErrorWhenMessageDidNotSend() {
        whenever(mockOfChatApi.sendMessage("userName", "messageBody"))
                .thenReturn(Observable.error(Throwable()))
        systemUnderTest.setView(mockOfView)
        systemUnderTest.userName = "userName"
        systemUnderTest.sendMessage("userName", "messageBody")

        verify(mockOfView).showMessageSendError()
    }

    @Test
    fun shouldRefreshRailsEvery120Seconds() {
        whenever(mockOfChatApi.getChatDetail(any())).thenReturn(Observable.empty())
        whenever(mockOfWykopPreferencesApi.getAutoRefreshState()).thenReturn(true)
        systemUnderTest.setView(mockOfView)
        verify(subscriptionManagerTest).subscribeCyclicWithDelay(any<Observable<*>>(),
                any(),
                any(),
                any(),
                eq(10),
                any(),
                eq(10))
    }

    @Test
    fun shouldFillContentCyclic() {
        val expectedList = mockOfPrivateMessageDetail()
        whenever(mockOfChatApi.getChatDetail(any())).thenReturn(Observable.just(expectedList))
        whenever(mockOfWykopPreferencesApi.getAutoRefreshState()).thenReturn(true)
        systemUnderTest.setView(mockOfView)
        systemUnderTest.userName = "userName"
        systemUnderTest.downloadChat()
        testScheduler.advanceTimeBy(10, TimeUnit.SECONDS)

        verify(subscriptionManagerTest).subscribeCyclicWithDelay(any<Observable<*>>(),
                any(),
                any(),
                any(),
                eq(10),
                any(),
                eq(10))
        verify(mockOfView, times(2)).showChat(any())
    }

    private fun mockOfPrivateMessageDetail() = (0..5)
            .map { it -> PrivateMessageDetail("body", "author", "www.avatarurl.com", "date", it % 2 == 0) }
}