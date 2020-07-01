package com.mpierucci.lasttraintolondon.lines.presentation

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.mpierucci.lasttraintolondon.core.fragment.GenericFragmentFactory
import com.mpierucci.lasttraintolondon.lines.presentation.di.DaggerLineTestComponent
import com.mpierucci.lasttraintolondon.lines.presentation.fluent.arrengements.user
import com.mpierucci.lasttraintolondon.lines.presentation.fluent.aserrtions.userSees
import com.mpierucci.lasttraintolondon.ristretto.fluent.Given
import com.mpierucci.lasttraintolondon.ristretto.fluent.Then
import com.mpierucci.lasttraintolondon.ristretto.mockwebserver.FileResponseDispatcher
import com.mpierucci.lasttraintolondon.ristretto.rules.DisableAnimationsRule
import com.mpierucci.lasttraintolondon.ristretto.rules.MockWebServerRule
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
class LineStatusScreenTest {

    @Inject
    lateinit var fragmentFactory: GenericFragmentFactory

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @get:Rule
    val animationRule = DisableAnimationsRule()

    @get:Rule
    val webServerRule = MockWebServerRule()

    var okHttpResource: IdlingResource? = null

    @Before
    fun setup() {
        webServerRule.mockWebServer.dispatcher = FileResponseDispatcher(pathToFilesMap)

        DaggerLineTestComponent.factory().create(
            InstrumentationRegistry.getInstrumentation().context
        ).inject(this)

        okHttpResource = OkHttp3IdlingResource.create("OkHttp", okHttpClient)

        IdlingRegistry.getInstance().register(okHttpResource)
    }

    @After
    fun tearDown() {
        okHttpResource?.let {
            IdlingRegistry.getInstance().unregister(it)
        }
    }

    @Test
    fun user_opens_line_fragment_screen_and_sees_all_lines() = runBlockingTest {
        Given.user.launchLineStatusFragment(fragmentFactory)

        Then.userSees.lineStatusScreen()
            .withLinesDisplayed(13)
    }
}