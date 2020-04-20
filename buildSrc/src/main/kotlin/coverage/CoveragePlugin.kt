package coverage

import com.android.build.gradle.*
import com.android.build.gradle.api.BaseVariant
import org.gradle.api.DomainObjectSet
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get
import org.gradle.testing.jacoco.plugins.JacocoPlugin
import org.gradle.testing.jacoco.tasks.JacocoReport


/**
 * Code coverage plugin, provide tasks to generate code coverage reports
 */

class CoveragePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        //creates extension to configure plugin in each module
        target.extensions.create(COVERAGE, CoverageExtension::class.java)


        applyJacocoPlugin(target)
        enableCoverageOnDebug(target)
    }

    private fun applyJacocoPlugin(target: Project) = target.afterEvaluate {
        val coverageExtension = target.extensions.getByType(CoverageExtension::class.java)
        if (!coverageExtension.isEnabled) return@afterEvaluate

        val additionalSourceDirs = coverageExtension.additionalSourceDirs

        target.plugins.apply(JacocoPlugin::class.java)
        target.plugins.all {
            if (this is LibraryPlugin) configureLibraryPlugin(
                target,
                coverageExtension.excludes,
                additionalSourceDirs
            )
            else if (this is AppPlugin) configureAppPlugin(
                target,
                coverageExtension.excludes,
                additionalSourceDirs
            )
        }
    }

    private fun configureLibraryPlugin(
        project: Project,
        excludes: List<String>,
        additionalSourceDirs: List<String>? = null
    ) {
        val libraryExtension = project.extensions.getByType(LibraryExtension::class.java)
        configureJacoco(project, libraryExtension.libraryVariants, excludes)
    }

    private fun configureAppPlugin(
        project: Project,
        excludes: List<String>,
        additionalSourceDirs: List<String>? = null
    ) {
        val appExtension = project.extensions.getByType(AppExtension::class.java)
        configureJacoco(project, appExtension.applicationVariants, excludes)
    }

    private fun configureJacoco(
        project: Project,
        variants: DomainObjectSet<out BaseVariant>,
        excludes: List<String>
    ) = variants.all {
        if (!buildType.isDebuggable) return@all

        val dir = project.buildDir
        val variantName = name
        val variantNameCapitalize = name.capitalize()


        project.tasks.register("jacoco${variantNameCapitalize}Report", JacocoReport::class.java) {
            dependsOn(project.tasks["test${variantNameCapitalize}UnitTest"])

            group = "coverage"
            description = "Generates coverage report"

            executionData.setFrom(project.fileTree("$dir") {
                setIncludes(listOf("jacoco/test${variantNameCapitalize}UnitTest.exec"))
            })
            setOnlyIf { executionData.files.any { it.exists() } }

            classDirectories.setFrom(
                project.fileTree("${dir}/intermediates/javac/$variantName") { setExcludes(excludes) },
                project.fileTree("${dir}/tmp/kotlin-classes/$variantName") { setExcludes(excludes) }
            )
            sourceDirectories.setFrom(listOf(KOTLIN_SOURCE_DIR, JAVA_SOURCE_DIR))
            additionalSourceDirs.setFrom(KOTLIN_SOURCE_DIR)

            reports.xml.isEnabled = true
        }
    }

    private fun enableCoverageOnDebug(target: Project) {
        val androidExtension = target.extensions.getByName(EXTENSION_ANDROID)
        if (androidExtension !is BaseExtension) return

        androidExtension.buildTypes { getByName(VERSION_DEBUG) { isTestCoverageEnabled = true } }
    }

    open class CoverageExtension {

        open var isEnabled: Boolean = true

        open var additionalSourceDirs: List<String> = listOf(KOTLIN_SOURCE_DIR, JAVA_SOURCE_DIR)

        open var excludes: MutableList<String> = mutableListOf()

        open fun excludes(vararg excludes: String) = this.excludes.addAll(excludes)
    }

    companion object {
        private const val EXTENSION_ANDROID = "android"
        private const val COVERAGE = "coverage"

        private const val VERSION_DEBUG = "debug"

        private const val KOTLIN_SOURCE_DIR = "src/main/kotlin/"
        private const val JAVA_SOURCE_DIR = "src/main/java/"
    }
}

