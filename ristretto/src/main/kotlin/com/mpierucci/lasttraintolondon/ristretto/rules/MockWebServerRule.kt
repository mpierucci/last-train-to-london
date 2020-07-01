package com.mpierucci.lasttraintolondon.ristretto.rules

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement


/**
 * Rule to start mockWebServer on port 8080 and shut down after the test run.
 *
 * It provide access to its mockWebServer instance for response manipulation
 */
class MockWebServerRule : TestRule {
    val mockWebServer = MockWebServer()

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {

                mockWebServer.start(8080)
                base.evaluate()
                mockWebServer.shutdown()
            }
        }
    }
}