package com.mpierucci.lasttraintolondon.lines.presentation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.mpierucci.lasttraintolondon.core.fragment.GenericFragmentFactory
import com.mpierucci.lasttraintolondon.lines.R
import com.mpierucci.lasttraintolondon.lines.presentation.di.DaggerLineTestComponent
import com.mpierucci.lasttraintolondon.lines.ui.LineStatusScreen
import com.mpierucci.lasttraintolondon.ristretto.assertions.RecyclerViewItemCountAssertion
import com.mpierucci.lasttraintolondon.ristretto.mockwebserver.FileResponseDispatcher
import com.mpierucci.lasttraintolondon.ristretto.rules.DisableAnimationsRule
import com.mpierucci.lasttraintolondon.ristretto.rules.MockWebServerRule
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


    private var idlingResource: IdlingResource? = null

    @get:Rule
    val animationRule = DisableAnimationsRule()

    @get:Rule
    val webServerRule = MockWebServerRule()


    @Before
    fun setup() {
        webServerRule.mockWebServer.dispatcher = FileResponseDispatcher(pathToFilesMap)

        DaggerLineTestComponent.factory().create(
            InstrumentationRegistry.getInstrumentation().context
        ).inject(this)
    }

    @Test
    fun displayLineStatuses() {
        val scenario = launchFragmentInContainer<LineStatusScreen>(
            factory = fragmentFactory,
            themeResId = R.style.Theme_MaterialComponents // Theme needed since you are testing fragment in isolation
        )

        scenario.onFragment {
            idlingResource = it.idlingResource
            IdlingRegistry.getInstance().register(idlingResource)
        }

        onView(withId(R.id.linesStatus)).check(RecyclerViewItemCountAssertion(13))
    }

    @After
    fun teardown() {
        idlingResource?.let { IdlingRegistry.getInstance().unregister(it) }

    }
}