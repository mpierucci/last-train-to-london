package properties

import org.gradle.api.Project
import java.io.FileInputStream
import java.util.*

val Project.gitHubProperties: Properties
    get() = Properties().apply {
        load(FileInputStream(this@gitHubProperties.rootProject.file("github.properties")))
    }