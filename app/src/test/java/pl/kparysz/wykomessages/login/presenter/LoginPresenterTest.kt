package pl.kparysz.wykomessages.login.presenter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import pl.kparysz.wykomessages.SubscriptionManagerTest
import pl.kparysz.wykomessages.login.repository.UserLoginApi
import pl.kparysz.wykomessages.login.view.LoginView
import pl.kparysz.wykomessages.models.dataclass.User
import pl.kparysz.wykomessages.prefs.WykopPreferencesApi

class LoginPresenterTest {

    lateinit var systemUnderTest: LoginPresenter
    var mockOfPreferencesApi = mock<WykopPreferencesApi>()
    var subscriptionManagerTest = SubscriptionManagerTest()
    var mockOfView = mock<LoginView>()
    var mockOfUserApi = mock<UserLoginApi>()

    @Before
    fun setUp() {
        systemUnderTest = LoginPresenter(mockOfPreferencesApi, subscriptionManagerTest, mockOfUserApi)
        systemUnderTest.setView(mockOfView)
    }

    @Test
    fun shouldSaveToken() {
        val url = "http://www.example.com/first/value/to/get/token"
        val expectedToken = "get"
        whenever(mockOfUserApi.loginUser("get")).thenReturn(Observable.empty())
        systemUnderTest.getToken(url)

        verify(mockOfPreferencesApi).saveUserToken(expectedToken)
    }

    @Test
    fun shouldLoginUser() {
        val url = "http://www.example.com/first/value/to/get/token"
        whenever(mockOfUserApi.loginUser("get")).thenReturn(Observable.just(User("userName")))
        systemUnderTest.getToken(url)

        verify(mockOfView).openMessagesActivity()
    }

    @Test
    fun shouldShowErrorWhenUserCanNotBeLogged() {
        val url = "http://www.example.com/first/value/to/get/token"
        whenever(mockOfUserApi.loginUser("get")).thenReturn(Observable.error(Throwable()))
        systemUnderTest.getToken(url)

        verify(mockOfView).showError()
    }

    @Test
    fun shouldShowError() {
        systemUnderTest.getToken("")

        verify(mockOfView).showError()
    }

    @Test
    fun shouldShowErrorWhenUrlNull() {
        systemUnderTest.getToken(null)

        verify(mockOfView).showError()
    }
}