package pl.kparysz.wykomessages.splash.presenter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import pl.kparysz.wykomessages.prefs.WykopPreferencesApi
import pl.kparysz.wykomessages.splash.view.SplashView

class SplashPresenterTest {

    lateinit var systemUnderTest: SplashPresenter
    var mockOfPreferencesApi = mock<WykopPreferencesApi>()
    var mockOfView = mock<SplashView>()

    @Before
    fun setUp() {
        systemUnderTest = SplashPresenter(mockOfPreferencesApi)
    }

    @Test
    fun shouldOpenLoginActivity() {
        whenever(mockOfPreferencesApi.isUserLogged()).thenReturn(false)
        systemUnderTest.setView(mockOfView)

        verify(mockOfView).openLoginActivity()
    }

    @Test
    fun shouldOpenMessageActivity() {
        whenever(mockOfPreferencesApi.isUserLogged()).thenReturn(true)
        systemUnderTest.setView(mockOfView)

        verify(mockOfView).openMessagesActivity()
    }
}