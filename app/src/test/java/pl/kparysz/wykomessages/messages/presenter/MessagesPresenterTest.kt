package pl.kparysz.wykomessages.messages.presenter

import android.content.Context
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import pl.kparysz.wykomessages.SubscriptionManagerTest
import pl.kparysz.wykomessages.messages.repository.ConversationListApi
import pl.kparysz.wykomessages.messages.view.MessagesView
import pl.kparysz.wykomessages.models.dataclass.ConversationDetail
import unofficial.coderoid.wykop.newapp.utils.NavigatorApi

class MessagesPresenterTest {

    lateinit var systemUnderTest: MessagesPresenter
    var subscriptionManagerTest = SubscriptionManagerTest()
    var mockOfNavigation = mock<NavigatorApi>()
    var mockOfView = mock<MessagesView>()
    var mockOfContext = mock<Context>()
    var mockOfConversationListApi = mock<ConversationListApi>()

    @Before
    fun setUp() {
        systemUnderTest = MessagesPresenter(subscriptionManagerTest, mockOfNavigation, mockOfConversationListApi)
    }

    @Test
    fun shouldDownloadConversations() {
        val expectedList = mockConversationList()
        whenever(mockOfConversationListApi.getAllMessages()).thenReturn(Observable.just(mockConversationList()))
        systemUnderTest.setView(mockOfView)

        verify(mockOfView).showMessages(expectedList)
    }

    @Test
    fun shouldShowErrorWhenErrorOccurred() {
        whenever(mockOfConversationListApi.getAllMessages()).thenReturn(Observable.error(Throwable()))
        systemUnderTest.setView(mockOfView)

        verify(mockOfView).showError()
    }

    @Test
    fun shouldOpenChatActivityWhenConversationClicked() {
        whenever(mockOfConversationListApi.getAllMessages()).thenReturn(Observable.just(mockConversationList()))
        whenever(mockOfView.getContext()).thenReturn(mockOfContext)
        systemUnderTest.setView(mockOfView)

        argumentCaptor<List<ConversationDetail>>().apply {
            verify(mockOfView).showMessages(capture())
            firstValue[0].onClickAction.invoke()
        }

        verify(mockOfNavigation).openChatActivity(mockOfContext, "authorName")
    }

    private fun mockConversationList() = (0..4).map { it ->
        ConversationDetail(
                "authorName",
                "avatarUrl",
                "lastUpdate",
                "status",
                0,
                "authorSex")
    }
}